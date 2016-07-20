/** -------------------------------------------------------------------------------------------------- 
 * Title:			
 * 				CalculatorUtils.java
 * Description:	
 * 				CalculatorUtils类定义
 *      		利息计算算法工具类
 * Dependencies:  
 * 				cn.com.p2p.common.dto.CalculatorResultDto
 *				cn.com.p2p.common.dto.CalculatorResultInstallmentsDto
 *				cn.com.p2p.framework.enumpack.LoanInterestMannerEnum
 *				cn.com.p2p.framework.util.DateUtils
 * History:
 *     			Date                 Modifier              Log
 *     			2015-09-02                     Created
 * 
 * --------------------------------------------------------------------------------------------------- */
package cn.com.p2p.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.com.p2p.common.dto.CalculatorResultDto;
import cn.com.p2p.common.dto.CalculatorResultInstallmentsDto;
import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.util.DateUtils;

/**
 * 利息计算工具类
 * 
 * @author 
 * @version 1.0
 */
public class CalculatorUtils {
	/**
	 * <p>利息计算</p>
	 * <pre>根据融资总额、利率、期限、是否按天计算利息</pre>
	 * <pre>
	 * 注意事项：
	 * 1、【isInvest】参数用于确定小数位数的取舍，融资计算时四舍五入，投资计算时舍去，这样做的目的是为了防止投资人待收的利息大于融资人待还的利息，导致还款失败
	 * 2、【carryInterestFrom】参数可为空，为空时不计算分期详细，只计算总利息
	 * </pre>
	 * @param interestManner
	 * 		还款方式
	 * @param isInvest
	 * 		是否是投资用利息计算
	 * @param amount
	 * 		融资总额
	 * @param rate
	 * 		利率
	 * @param timeLimit
	 * 		期限
	 * @param dayFlag
	 * 		是否按天计息
	 * @param carryInterestFrom
	 * 		起息日期
	 * @return
	 * 		<table border>
	 * 			
	 * 			<tr>
	 * 				<td>interest</td>
	 * 				<td colspan=2>总利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td rowspan=6>installments</td>
	 * 				<td colspan=2>分期详细信息List</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>num</td>
	 * 				<td>期数</td>
	 * 			</tr>
	 * 	 		<tr>
	 * 				<td>interest</td>
	 * 				<td>当期利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>amount</td>
	 * 				<td>当期本金</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>carryInterestTo</td>
	 * 				<td>当期还款日期</td>
	 * 			</tr>
	 * 		</table>
	 * @author 
	 */
	public static CalculatorResultDto interestCalculator(LoanInterestMannerEnum interestManner, Boolean isInvest, BigDecimal amount,
			BigDecimal rate, Integer timeLimit, LoanTimeLimitUnitEnum dayFlag, Date carryInterestFrom) {
		RoundingMode rm = null;
		CalculatorResultDto calculatorResultDto = new CalculatorResultDto();
		/**
		 * 如果是投资计算，向下舍弃
		 * 如果是融资计算，向上舍入
		 * 目的是为了防止待收总额超过待还总额，导致还款失败
		 * */
		if (isInvest) {
			rm = RoundingMode.DOWN;
		} else {
			rm = RoundingMode.UP;
		}
		switch (interestManner) {
			case DISPOSABLE_PRINCIPAL_INTEREST:
				// 项目到期日还本付息
				calculatorResultDto = disposablePrincipalInterest(amount, rate, timeLimit, dayFlag, carryInterestFrom, rm);
				break;
			case AVERAGE_CAPITAL:
				// 等额本金
				calculatorResultDto = averageCapital(amount, rate, timeLimit, carryInterestFrom, rm);
				break;
			case AVERAGE_CAPITAL_PLUS_INTEREST:
				// 等额本息
				calculatorResultDto = averageCapitalPlusInterest(amount, rate, timeLimit, carryInterestFrom, rm);
				break;
			case MONTHLY_INTEREST:
				// 按月付息，到期还本
				calculatorResultDto = monthlyInterest(amount, rate, timeLimit, carryInterestFrom, rm);
				break;
			default:
				break;
		}
		return calculatorResultDto;
	}

	/**
	 * <p>一次性还本付息算法：</p>
	 * <pre>项目到期日一次性将本金及利息还清</pre>
	 * <pre>
	 * 注意事项：
	 * 1、不论是否按天都可用
	 * 2、【carryInterestFrom】参数可为空，为空时不计算分期详细
	 * </pre>
	 * 
	 * @param amount
	 * 		融资总额
	 * @param rate
	 * 		利率
	 * @param timeLimit
	 * 		融资期限
	 * @param dayFlag
	 * 		是否按天计息
	 * @param carryInterestFrom
	 * 		起息日期
	 * @param rm
	 * 		小数位数取舍方式，如果是投资收益计算则全部舍去，如果是融资利息计算则四舍五入
	 * @return
	 * 		<table border>
	 * 			
	 * 			<tr>
	 * 				<td>interest</td>
	 * 				<td colspan=2>总利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td rowspan=6>installments</td>
	 * 				<td colspan=2>分期详细信息List</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>num</td>
	 * 				<td>期数</td>
	 * 			</tr>
	 * 	 		<tr>
	 * 				<td>interest</td>
	 * 				<td>当期利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>amount</td>
	 * 				<td>当期本金</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>carryInterestTo</td>
	 * 				<td>当期还款日期</td>
	 * 			</tr>
	 * 		</table>
	 * @author 
	 */
	private static CalculatorResultDto disposablePrincipalInterest(BigDecimal amount, BigDecimal rate, Integer timeLimit, LoanTimeLimitUnitEnum dayFlag,
			Date carryInterestFrom, RoundingMode rm) {
		Boolean isDay = dayFlag.equals(LoanTimeLimitUnitEnum.Day);
		// 利息 = 金额×利率×期限÷（12或360）,按天计算除以360，按月计息除以12
		BigDecimal interest = amount.multiply(rate).multiply(new BigDecimal(timeLimit)).divide(new BigDecimal((isDay ? 360 : 12)), 2, rm);
		// 分期详细信息
		List<CalculatorResultInstallmentsDto> installments = new ArrayList<CalculatorResultInstallmentsDto>();
		// 如果没有起息日期，则只计算利息，不做分期计算
		if (carryInterestFrom != null) {
			// 计算到期日期
			// 根据赵世义描述 “还款日是计息日的一个自然周期后的当日，那么融资的到期日期也不会是融资的第一个计息日加上借款的自然周期向前退一天”
			Date carryInterestTo = DateUtils.dateAdd(carryInterestFrom, (isDay ? Calendar.DAY_OF_MONTH : Calendar.MONTH), timeLimit);
			CalculatorResultInstallmentsDto installment = new CalculatorResultInstallmentsDto(1, amount, interest, carryInterestTo);
			installments.add(installment);
		}else{
		    CalculatorResultInstallmentsDto installment = new CalculatorResultInstallmentsDto(1, amount, interest, null);
            installments.add(installment);
		}
		// 计算结果
		CalculatorResultDto calculatorResultDto = new CalculatorResultDto(interest.setScale(2, rm), installments);
		return calculatorResultDto;
	}

	/**
	 * <p>等额本息算法</p>
	 * <pre>每期还款的总金额是一样的，每期还款的利息可以通过公式计算出来，每期还款的本金通过每期还款总金额和每期还款利息计算得到</pre>
	 * <pre>
	 * 注意事项：
	 * 1、按天计息的项目应该是不可用此算法
	 * 2、【carryInterestFrom】参数可为空，为空时不计算分期详细
	 * </pre>
	 * 
	 * @param amount
	 * 		融资总额
	 * @param rate
	 * 		利率
	 * @param timeLimit
	 * 		融资期限
	 * @param carryInterestFrom
	 * 		是否按天计息
	 * @param rm
	 * 		小数位数取舍方式，如果是投资收益计算则全部舍去，如果是融资利息计算则四舍五入
	 * @return
	 * 		<table border>
	 * 			<tr>
	 * 				<td>interest</td>
	 * 				<td colspan=2>总利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td rowspan=6>installments</td>
	 * 				<td colspan=2>分期详细信息List</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>num</td>
	 * 				<td>期数</td>
	 * 			</tr>
	 * 	 		<tr>
	 * 				<td>interest</td>
	 * 				<td>当期利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>amount</td>
	 * 				<td>当期本金</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>carryInterestTo</td>
	 * 				<td>当期还款日期</td>
	 * 			</tr>
	 * 		</table>
	 * @author 
	 */
	private static CalculatorResultDto averageCapitalPlusInterest(BigDecimal amount, BigDecimal rate, Integer timeLimit, Date carryInterestFrom,
			RoundingMode rm) {
		/*
		 * 贷款额为a，月利率为i，年利率为I，还款月数为n，每月还款额为b，还款利息总和为Y
		 * I ＝ 12 × i
		 * Y ＝ n × b － a
		 * 第一月还款利息为：a×i
		 * 第二月还款利息为：〔a－（b－a×i）〕×i＝（a×i－b）×（1＋i）^1＋b
		 * 第三月还款利息为：｛a－（b－a×i）－〔b－（a×i－b）×（1＋i）^1－b〕｝×i＝（a×i－b）×（1＋i）^2＋b
		 * .
		 * .
		 * .
		 * 第n月还款利息为：＝（a×i－b）×（1＋i）^（n－1）＋b
		 * 月均还款:b ＝ a×i×（1＋i）^n÷〔（1＋i）^n－1〕
		 * */
		// 月利率i
		BigDecimal monthlyRate = rate.divide(new BigDecimal(12), 10, rm);
		// 月利率+1 i+1
		BigDecimal rateBase = monthlyRate.add(new BigDecimal(1));
		// (i+1)^n
		//目前保留四位小数 by 
		//修改成不保留四位小数 by 
		BigDecimal ratePower = new BigDecimal(Math.pow(rateBase.doubleValue(), timeLimit.doubleValue()));
		// 总利息
		BigDecimal interest = new BigDecimal(0);
		// 月均还款额 = 贷款额*月利率*（1+月利率）^期数 ÷ [（1+月利率）^期数 - 1]
		BigDecimal P = amount.multiply(monthlyRate).multiply(ratePower).divide(ratePower.subtract(new BigDecimal(1)), 2, rm);
		// 剩余本金
		BigDecimal surplusAmount = amount;
		// 分期详细信息
		List<CalculatorResultInstallmentsDto> installments = new ArrayList<CalculatorResultInstallmentsDto>();
		for (int i = 1; i <= timeLimit; i++) {
			
			//当期利息 = 剩余本金 * 月利率
			BigDecimal currentInterest = surplusAmount.multiply(monthlyRate).setScale(2, rm);
			//当期本金 = 月均还款额 = 当期利息
			BigDecimal currentAmount = P.subtract(currentInterest);
			//剩余本金
			surplusAmount = surplusAmount.subtract(currentAmount);
			//总利息
			interest = interest.add(currentInterest);
			//还款日期
			Date carryInterestTo = dateCalculator(carryInterestFrom, i);

			CalculatorResultInstallmentsDto installment = new CalculatorResultInstallmentsDto(i, currentAmount, currentInterest, carryInterestTo);
			installments.add(installment);
		}
		
		// 计算结果
		CalculatorResultDto calculatorResultDto = new CalculatorResultDto(interest.setScale(2, rm), installments);
		return calculatorResultDto;
	}

	/**
	 * <p>等额本金算法</p>
	 * <pre>每期还款的本金是一样的，每期还款的利息根据当期剩余本金计算得到</pre>
	 * <pre>
	 * 注意事项：
	 * 1、按天计息的项目应该是不可用此算法
	 * 2、【carryInterestFrom】参数可为空，为空时不计算分期详细
	 * </pre>
	 * @param amount
	 * 		融资总额
	 * @param rate
	 * 		利率
	 * @param timeLimit
	 * 		融资期限
	 * @param carryInterestFrom
	 * 		是否按天计息
	 * @param rm
	 * 		小数位数取舍方式，如果是投资收益计算则全部舍去，如果是融资利息计算则四舍五入
	 * @return
	 * 		<table border>
	 * 			<tr>
	 * 				<td>interest</td>
	 * 				<td colspan=2>总利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td rowspan=6>installments</td>
	 * 				<td colspan=2>分期详细信息List</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>num</td>
	 * 				<td>期数</td>
	 * 			</tr>
	 * 	 		<tr>
	 * 				<td>interest</td>
	 * 				<td>当期利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>amount</td>
	 * 				<td>当期本金</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>carryInterestTo</td>
	 * 				<td>当期还款日期</td>
	 * 			</tr>
	 * 		</table>
	 * @author 
	 */
	private static CalculatorResultDto averageCapital(BigDecimal amount, BigDecimal rate, Integer timeLimit, Date carryInterestFrom,
			RoundingMode rm) {
		
		// 每月应还本金 = 融资总额÷期限，四舍五入
		BigDecimal monthlyAmount = amount.divide(new BigDecimal(timeLimit), 2, rm);
		BigDecimal interest = new BigDecimal(0);
		
		// 分期详细信息
		List<CalculatorResultInstallmentsDto> installments = new ArrayList<CalculatorResultInstallmentsDto>();
		for (int i = 1; i <= timeLimit; i++) {
			
			// 当月应还本金，最后一期=总额-除最后一期外所有期的总和，其他期=每月应还本金
			BigDecimal currentAmount = (i == timeLimit ? amount.subtract(monthlyAmount.multiply(new BigDecimal(timeLimit - 1)))
					: monthlyAmount);
			/*
			 * 当月利息=剩余本金×月利率 剩余本金=本金 - 每期本金×（当前期数 - 1） 月利率=利率÷12 
			 * 当月利息=(本金 - 每期本金×(当前期数 - 1))×利率÷12 四舍五入保留两位小数
			 */
			BigDecimal currentInterest = amount.subtract(monthlyAmount.multiply(new BigDecimal(i - 1))).multiply(rate)
					.divide(new BigDecimal(12), 2, rm);
			interest = interest.add(currentInterest);
			//还款日期
			Date carryInterestTo = dateCalculator(carryInterestFrom, i);
			
			CalculatorResultInstallmentsDto installment = new CalculatorResultInstallmentsDto(i, currentAmount, currentInterest, carryInterestTo);
			installments.add(installment);
		}
		// 计算结果
		CalculatorResultDto calculatorResultDto = new CalculatorResultDto(interest.setScale(2, rm), installments);
		return calculatorResultDto;
	}

	/**
	 * <p>按月付息到期还本算法</p>
	 * <pre>每期还款只还利息，最后一期还最后一期的利息和所有本金</pre>
	 * <pre>
	 * 注意事项：
	 * 1、按天计息的项目应该是不可用此算法
	 * 2、【carryInterestFrom】参数可为空，为空时不计算分期详细
	 * </pre>
	 * @param amount
	 * 		融资总额
	 * @param rate
	 * 		利率
	 * @param timeLimit
	 * 		融资期限
	 * @param carryInterestFrom
	 * 		是否按天计息
	 * @param rm
	 * 		小数位数取舍方式，如果是投资收益计算则全部舍去，如果是融资利息计算则四舍五入
	 * @return
	 * 		<table border>
	 * 			<tr>
	 * 				<td>interest</td>
	 * 				<td colspan=2>总利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td rowspan=6>installments</td>
	 * 				<td colspan=2>分期详细信息List</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>num</td>
	 * 				<td>期数</td>
	 * 			</tr>
	 * 	 		<tr>
	 * 				<td>interest</td>
	 * 				<td>当期利息</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>amount</td>
	 * 				<td>当期本金</td>
	 * 			</tr>
	 * 			<tr>
	 * 				<td>carryInterestTo</td>
	 * 				<td>当期还款日期</td>
	 * 			</tr>
	 * 		</table>
	 * @author 
	 */
	private static CalculatorResultDto monthlyInterest(BigDecimal amount, BigDecimal rate, Integer timeLimit, Date carryInterestFrom,
			RoundingMode rm) {
		
		// 月还款利息=金额×利率÷12
		BigDecimal interest = amount.multiply(rate).divide(new BigDecimal(12), 2, rm);
		
		// 分期详细信息
		List<CalculatorResultInstallmentsDto> installments = new ArrayList<CalculatorResultInstallmentsDto>();
		for (int i = 1; i <= timeLimit; i++) {
			// 当期应还本金
			BigDecimal currentAmount = (BigDecimal)(i == timeLimit ? amount : BigDecimal.ZERO);
			// 当期应还利息
			BigDecimal currentInterest = interest;
			//还款日期
			Date carryInterestTo = dateCalculator(carryInterestFrom, i);
						
			CalculatorResultInstallmentsDto installment = new CalculatorResultInstallmentsDto(i, currentAmount, currentInterest, carryInterestTo);
			installments.add(installment);
		}
		
		// 计算结果
		CalculatorResultDto calculatorResultDto = new CalculatorResultDto(interest.multiply(new BigDecimal(timeLimit)).setScale(2, rm), installments);
		return calculatorResultDto;
	}

	/**
	 * <p>每期还款日期计算</p>
	 * <pre>
	 * 注意事项：
	 * 只有按月计息的可以使用，因为按天计息不能分期，所以没有期数
	 * </pre>
	 * @param carryInterestFrom
	 * 		起息日期
	 * @param num
	 * 		期数
	 * @return 当期还款日期
	 * @author 
	 */
	private static Date dateCalculator(Date carryInterestFrom, Integer num) {
		Date carryInterestTo = null;
		if (carryInterestFrom != null) {
			// 根据赵世义描述 “还款日是计息日的一个自然周期后的当日，那么融资的到期日期也不会是融资的第一个计息日加上借款的自然周期向前退一天”
			carryInterestTo = DateUtils.dateAdd(carryInterestFrom, Calendar.MONTH, num);
		}
		return carryInterestTo;
	}
	/**
	 * <p>费用计算：金额×费率</p>
	 * 
	 * @author 
	 * 
	 * @param amount 金额
	 * @param rate 费率
	 * @return
	 */
	public static BigDecimal feeCalculator(Integer amount,BigDecimal rate){

		return new BigDecimal(amount).multiply(rate).setScale(2,RoundingMode.HALF_UP);
	}

}
