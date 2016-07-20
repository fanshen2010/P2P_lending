package cn.com.p2p.invest.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.invest.criteria.InvestCriteria;
import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestDetail;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.entity.InvestInformation;
import cn.com.p2p.domain.invest.query.InvestDetailQuery;
import cn.com.p2p.domain.invest.query.InvestInformationQuery;
import cn.com.p2p.domain.invest.repository.InvestDetailRepository;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.NumericUtils;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.invest.service.dto.InvestIncomeDto;
import cn.com.p2p.invest.service.dto.InvestInformationDto;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.utils.Constants;

/**
 * 融资信息查询
 * 
 * @author 
 *
 */
@Service
public class InvestSearchServiceImpl implements InvestSearchService {
	
	/* 投资统计信息接口 */
    @Autowired
    private InvestInformationQuery investInformationQuery;
    
    /* 融资信息接口*/
    @Autowired
    private LoanRepository loanRepository;
    
    @Autowired
    /* 投资信息接口 */
    private InvestRepository investRepository;
    
    /* 投资详情查询接口 */
    @Autowired
    private InvestDetailRepository investDetailRepository;
    /* 投资详情业务查询接口 */
    @Autowired
    private InvestDetailQuery  investDetailQuery;
    
  //系统设定 settingService 接口
  	@Autowired
  	private SettingService settingService;
  	
	//投资份额(份)
	int investShare;


	/**
     * <p>融资业务中获取投资信息</p>.<br>
     * 
     * author：<br> 
     * ===================================
     * 
     * @param loanCode 融资编号
     * @return 投资信息
     */
	@Override
	public List<InvestInformationDto> getInvestInformation(String loanCode) {
	
		// 融资业务中的投资信息
		List<InvestInformationDto> investInformationDtoList = new ArrayList<InvestInformationDto>();
		//根据融资编号获取投资信息
		List<InvestInformation> investInformationList = investInformationQuery.getInvestInformation(loanCode);
		//编辑investInformationList
		for(int i = 0;i < investInformationList.size(); i ++){
			InvestInformationDto  investInformationDto = new InvestInformationDto();
			//investInformationDto赋值
			investInformationDto.setLogin(investInformationList.get(i).getInvestUserName());
			investInformationDto.setInvestmentTime(investInformationList.get(i).getInvestmentTime());
			investInformationDto.setInvestmentAmount(investInformationList.get(i).getInvestmentAmount());
			investInformationDto.setReceivableAmount(investInformationList.get(i).getReceivablePrincipal().add(investInformationList.get(i).getReceivableInterest()));
			investInformationDto.setReceivablePrincipal(investInformationList.get(i).getReceivablePrincipal());
			investInformationDto.setReceivableInterest(investInformationList.get(i).getReceivableInterest());
			//investInformationDtoList赋值
			investInformationDtoList.add(investInformationDto);
			}
		
		return investInformationDtoList;
	}


	
	@Override
	public List<Invest> getInvestAll(InvestCriteria criteria) {
		return investRepository.findPageByCriteria(criteria);
	}



	
	@Override
	public Invest getInvestById(String InvestId) {
		return investRepository.findOne(InvestId);
	}
	
	/**
	 * 
	 * 根据多条件查询投资详情
	 * 
	 * @author 
	 * 
	 * @param InvestDetailCriteria criteria 投资详情查询条件类
	 * 
	 * @return List<InvestDetail> 投资详情List
	 */
	@Override
	public List<CustomInvestDetail> getInvestDetailByCriteria(InvestDetailCriteria criteria) {
		return investDetailQuery.findInvestDetailListByCriteria(criteria);
		
	}
	
	
	/**
     * 
     * 根据多条件查询投资的分页详情
     * 
     * @author 
     * 
     * @param InvestDetailCriteria criteria 投资详情查询条件类
     * 
     * @return List<InvestDetail> 投资详情List
     */
    @Override
    public List<InvestDetail> getPageInvestDetailByCriteria(InvestDetailCriteria criteria) {
        return investDetailRepository.findPageByCriteria(criteria);
    }



	/**
     * <p>根据用户ID获取投资信息（对应前台个人中心收益详情）</p>.<br>
     * 
     * author：<br> 
     * ===================================
     * 
     * @param userId 用户ID
     * @return 投资信息
     */
	@Override
	public InvestIncomeDto getInvestByUser(String userId) {
		InvestInformation investInfo = new InvestInformation();
		InvestIncomeDto investIncomeDto = new InvestIncomeDto();
		//根据userId查询需要的投资信息
		investInfo = investInformationQuery.getInvestByUser(userId);
		
		if(investInfo!=null){
			BigDecimal investmentAmount = investInfo.getTotalInvestmentAmount()!=null?investInfo.getTotalInvestmentAmount():BigDecimal.ZERO;
			BigDecimal toInvestAmount = investInfo.getTotalToInvestAmount()!=null?investInfo.getTotalToInvestAmount():BigDecimal.ZERO;
			BigDecimal investingAmount = investInfo.getTotalInvestingAmount()!=null?investInfo.getTotalInvestingAmount():BigDecimal.ZERO;
			BigDecimal investedAmount = investInfo.getTotalInvestedAmount()!=null?investInfo.getTotalInvestedAmount():BigDecimal.ZERO;
			BigDecimal receivableInterest = investInfo.getTotalReceivableInterest()!=null?investInfo.getTotalReceivableInterest():BigDecimal.ZERO;
			BigDecimal receivedInterest = investInfo.getTotalReceivedInterest()!=null?investInfo.getTotalReceivedInterest():BigDecimal.ZERO;
			BigDecimal receivablePunitive = investInfo.getTotalReceivablePunitive()!=null?investInfo.getTotalReceivablePunitive():BigDecimal.ZERO;
			BigDecimal receivedPunitive = investInfo.getTotalReceivedPunitive()!=null?investInfo.getTotalReceivedPunitive():BigDecimal.ZERO;
			
			//计算已收收益（包括利润和罚息）		
			BigDecimal receivedEarnings = receivedInterest.add(receivedPunitive);
			//计算待收收益（包括利润和罚息）
			BigDecimal collectPunitive = receivablePunitive.subtract(receivedPunitive);
			BigDecimal collectInterest = receivableInterest.subtract(receivedInterest);
			BigDecimal collectEarnings = collectPunitive.add(collectInterest);
			//计算总收益（包括利润和罚息）
			//--invest表总收益字段是null,所以用已收收益+待收收益算总收益--
			//BigDecimal TotalInvestEarnings = investInfo.getTotalInvestEarnings()!=null?investInfo.getTotalInvestEarnings():BigDecimal.ZERO;;
			BigDecimal TotalInvestEarnings = receivedEarnings.add(collectEarnings);
			
			
			//将投资金额和笔数放入dto中
			investIncomeDto.setTotalInvestmentAmount(investmentAmount);
			investIncomeDto.setToInvestAmount(toInvestAmount);
			investIncomeDto.setToInvestCount(investInfo.getTotalToInvestCount());
			investIncomeDto.setInvestingAmount(investingAmount);
			investIncomeDto.setInvestingCount(investInfo.getTotalInvestingCount());
			investIncomeDto.setInvestedAmount(investedAmount);
			//将收益金额放入dto中
			investIncomeDto.setTotalInvestEarnings(TotalInvestEarnings);
			investIncomeDto.setCollectEarnings(collectEarnings);
			investIncomeDto.setReceivedEarnings(receivedEarnings);
			//将下次收益信息放入dto中
			investIncomeDto.setNextPaymentDate(investInfo.getNextPaymentDate());
			investIncomeDto.setNextPaymentAmount(investInfo.getNextPaymentAmount());
		}
		return investIncomeDto;
	}
	
	/**
     * <p>根据用户ID获取投资信息（对应前台个人中心收益图表）</p>.<br>
     * 
     * author：<br> 
     * ===================================
     * 
     * @param userId 用户ID
     * @return 投资信息
     */
	@Override
	public Map<String,String> getInvestStatisticByUser(String userId){

		Map<String,String> output = new HashMap<String,String>();
		
		//根据userId查询需要的投资图表信息
		Date currentTime = new Date();
		//6个月后，当月最后一天
		Calendar futureTimeCalendar =Calendar.getInstance();//获取当前日期 
		futureTimeCalendar.add(Calendar.MONTH, 7);
		futureTimeCalendar.set(Calendar.DAY_OF_MONTH,0);//设置为最后一天 
		Date futureTime = futureTimeCalendar.getTime();
		//6个月前，当月第一天
		Calendar pastTimeCalendar =Calendar.getInstance();//获取当前日期 
		pastTimeCalendar.add(Calendar.MONTH, -5);
		pastTimeCalendar.set(Calendar.DAY_OF_MONTH,1);//设置为本月第一天 
		Date pastTime = pastTimeCalendar.getTime();
		
		//用户每月的计划收益
		List<InvestInformation> investPlan = investInformationQuery.getPlanInvestByUser(userId,pastTime,futureTime);
		//用户每月的实际收益
		List<InvestInformation> investActual = investInformationQuery.getActualInvestByUser(userId,pastTime,currentTime);;
		
		Map<String,Object> xData = this.convertXData(pastTime, futureTime, Calendar.MONTH);
		String investPlanStr = this.convertYData(investPlan, xData.get("monthsArr"),"false");
		String investActualStr = this.convertYData2(investActual, xData.get("monthsArr"), "true");
		
		output.put("months", xData.get("monthsArrStr").toString());
		output.put("investPlan", investPlanStr);
		output.put("investActual", investActualStr);
		
		
		
		
//		//获取横坐标月份
//		Calendar c = Calendar.getInstance();
//		String[] monthsX = new String[12];
//		String[] monthsPlanStr = new String[12];
//		String[] monthsActualStr = new String[12];
//		Date xTime = pastTime;
//		for(int i=0;i<12;i++){
//			xTime = DateUtils.dateAdd(xTime, Calendar.MONTH, 1);
//			String xTimeStr = DateUtils.formatDate(xTime, "yyyyMM");
//			c.setTime(xTime);
//			monthsPlanStr[i]= xTimeStr.substring(4);
//			if(i<6){
//				monthsActualStr[i]= xTimeStr.substring(4);
//			}else{
//				monthsActualStr[i]= null;
//			}
//			int monthNum = c.get(Calendar.MONTH)+1;
//			monthsX[i]= "'"+monthNum+"月"+"'";
//		}
//		output.put("months", Arrays.toString(monthsX));
//		
//		//获取纵坐标(计划收益)
//		List<BigDecimal> investPlanlst = new ArrayList<BigDecimal>();
//		Object[] investPlanArr = new Object[12];
//		if(investPlan!=null && !investPlan.isEmpty()){
//			for(String monthStr:monthsPlanStr){
//				if(monthStr!=null){
//					int count=0;
//					for(InvestInformation investInformation:investPlan){
//						if(monthStr.equals(investInformation.getStatisticMonth())){
//							investPlanlst.add(investInformation.getPlanEarnings());
//							break;
//						}
//						count++;
//					}
//					if(count==investPlan.size()){
//						investPlanlst.add(BigDecimal.ZERO);
//					}
//				}else{
//					investPlanlst.add(null);
//				}
//				
//			}
//			investPlanArr = investPlanlst.toArray();
//		}
//		output.put("investPlan", Arrays.toString(investPlanArr));
//		
//		//获取纵坐标(实际收益)
//		List<BigDecimal> investActuallst = new ArrayList<BigDecimal>();
//		Object[] investActualArr = new Object[12];
//		if(investActual!=null && !investActual.isEmpty()){
//			for(String monthStr:monthsActualStr){
//				if(monthStr!=null){
//					//前6个月为实线，值不能为空，空值用0代替
//					int count=0;
//					for(InvestInformation investInformation:investActual){
//						if(monthStr!=null && monthStr.equals(investInformation.getStatisticMonth())){
//							investActuallst.add(investInformation.getActualEarnings());
//							break;
//						}
//						count++;
//					}
//					if(count==investActual.size()){
//						investActuallst.add(BigDecimal.ZERO);
//					}
//				}else{
//					//后6个月没有线，值为空
//					investActuallst.add(null);
//				}
//			}
//			investActualArr = investActuallst.toArray();
//		}
//		output.put("investActual", Arrays.toString(investActualArr));
		
		
		// 获取图片状态（是否为空图表，normal不为空）
		if((investPlan!=null && !investPlan.isEmpty()) || (investActual!=null && !investActual.isEmpty())){
			output.put("lineState", "normal");
		}
		return output;
	}
	
	public Map<String,Object> convertXData(Date startTime, Date endTime, int type){
		Map<String,Object> outputMap = new HashMap<String,Object>();
		long diff = endTime.getTime() - startTime.getTime();
		int timeInt = 0;
		if(Calendar.MONTH==type){
			long months = diff /1000/60/60/24/30;
			timeInt = (int)months;
		}else if(Calendar.DATE==type){
			long months = diff /1000/60/60/24;
			timeInt = (int)months;
		}else if(Calendar.HOUR==type){
			long months = diff /1000/60/60;
			timeInt = (int)months;
		}
		Calendar c = Calendar.getInstance();
		String[] timeX = new String[timeInt];
		String[] timeArr = new String[timeInt];
		Date xTime = startTime;
		for(int i=0;i<timeInt;i++){
			String xTimeStr = DateUtils.formatDate(xTime, "yyyyMM");
			c.setTime(xTime);
			int monthNum = c.get(Calendar.MONTH)+1;
			//timeX[i]= "'"+monthNum+"月"+"'";
			timeX[i]= "'"+monthNum+"'";
			timeArr[i] = xTimeStr.substring(4);
			xTime = DateUtils.dateAdd(xTime, Calendar.MONTH, 1);
		}
		outputMap.put("monthsArr", timeArr);
		outputMap.put("monthsArrStr", Arrays.toString(timeX));
		return outputMap;
	}
	
	public String convertYData(List<InvestInformation> DBData ,Object pmonthsArr,String nullType){
		List<BigDecimal> outputlst = new ArrayList<BigDecimal>();
		String[] monthsArr   = (String[]) pmonthsArr;
		Object[] outputArr = new Object[monthsArr.length];
		
		if(DBData!=null && !DBData.isEmpty()){
			//找到最后一个值的时间,如果没有设为""
			String monthLast = "";
			if("true".equals(nullType)){
				monthLast = DBData.get(DBData.size()-1).getStatisticMonth();
			}
			
			for(String monthStr:monthsArr){
				//最后一个值之前不能为空，空值用0代替，最后一个值以后不赋值
				//没有最后一个值，空值都用0代替
				if(!monthLast.equals(monthStr)){//未到最后一个值
					int count=0;
					for(InvestInformation investInformation:DBData){
						if(monthStr!=null && monthStr.equals(investInformation.getStatisticMonth())){
							outputlst.add(investInformation.getPlanEarnings());
							break;
						}
						count++;
					}
					if(count==DBData.size()){
						outputlst.add(BigDecimal.ZERO);
					}
				}else{//找到最后一个值
					for(InvestInformation investInformation:DBData){
						if(monthStr!=null && monthStr.equals(investInformation.getStatisticMonth())){
							outputlst.add(investInformation.getPlanEarnings());
							break;
						}
					}
					break;
				}
			}
			outputArr = outputlst.toArray();
		}
		return Arrays.toString(outputArr);
	}
	
	public String convertYData2(List<InvestInformation> DBData ,Object pmonthsArr,String nullType){
		List<BigDecimal> outputlst = new ArrayList<BigDecimal>();
		String[] monthsArr   = (String[]) pmonthsArr;
		Object[] outputArr = new Object[monthsArr.length];
		
		if(DBData!=null && !DBData.isEmpty()){
			//找到最后一个值的时间,如果没有设为""
			String monthLast = "";
			if("true".equals(nullType)){
				monthLast = DBData.get(DBData.size()-1).getStatisticMonth();
			}
			
			for(String monthStr:monthsArr){
				//最后一个值之前不能为空，空值用0代替，最后一个值以后不赋值
				//没有最后一个值，空值都用0代替
				if(!monthLast.equals(monthStr)){//未到最后一个值
					int count=0;
					for(InvestInformation investInformation:DBData){
						if(monthStr!=null && monthStr.equals(investInformation.getStatisticMonth())){
							outputlst.add(investInformation.getActualEarnings());
							break;
						}
						count++;
					}
					if(count==DBData.size()){
						outputlst.add(BigDecimal.ZERO);
					}
				}else{//找到最后一个值
					for(InvestInformation investInformation:DBData){
						if(monthStr!=null && monthStr.equals(investInformation.getStatisticMonth())){
							outputlst.add(investInformation.getActualEarnings());
							break;
						}
					}
					break;
				}
			}
			outputArr = outputlst.toArray();
		}
		return Arrays.toString(outputArr);
	}

	
	
	/**
	 * <p>用户每月的计划收益</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param pastTime
	 * @param futureTime
	 * @return
	 */
	@Override
	public List<InvestInformation> getPlanInvestByUser(String userId,Date pastTime,Date futureTime){
		return investInformationQuery.getPlanInvestByUser(userId,pastTime,futureTime);
	}
	
	/**
	 * <p>用户每月的实际收益</p>.<br>
	 * author：<br>
	 *===================================
	 * @param userId
	 * @param pastTime
	 * @param currentTime
	 * @return
	 */
	@Override
	public List<InvestInformation> getActualInvestByUser(String userId,Date pastTime,Date currentTime){
	    return investInformationQuery.getActualInvestByUser(userId,pastTime,currentTime);
	}



	@Override
	public List<Invest> getInvestList(InvestCriteria criteria) {
		return investRepository.findByCriteria(criteria);
	}
	
}
