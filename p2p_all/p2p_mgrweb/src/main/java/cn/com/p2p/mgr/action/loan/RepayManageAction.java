package cn.com.p2p.mgr.action.loan;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestDetail;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.token.Token;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.loan.service.LoanEffectMangementService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.utils.Constants;

/**
 * 还款管理
 * 
 * @author 
 */
@Namespace("/loan/afterLoan/repayManage")
@Results({ @Result(name = "repayManage", location = "repayManage.ftl", type = "freemarker"),
	       @Result(name = "repayDetail", location = "repayDetail.ftl", type = "freemarker") })
public class RepayManageAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 融资查询接口 */
	@Autowired
	private LoanSearchService loanSearchService;
	
	/** 投资查询接口 */
	@Autowired
	private InvestSearchService investSearchService;

	/** 融资编号 */
	private String loanCode;	
	/** 融资项目 */
	private String loanProject;
	/** 客户名称 */
	private String customName;
	/** 最小还款日期 */
	private Date repayDateMin;

	/** 最大还款日期 */
	private Date repayDateMax;
	
	private String receivableRepayStatus;
	
	/** 融资扣款集合 */
	private List<Loan> loanBuckleRepayList;
	
	/** 操作类型 */
	private String operatType;
	/** 当前选中融资编号 */
	private String curLoanCode;
	/** 一级标题 */
	private String titleName;
	
	/** 查询参数类 */
	private LoanCommSelCriteria criteria = new LoanCommSelCriteria();

	/** 融资还款详情 */
	private RepayDetail retRepayDetail;
	
	/** 投资详情List */
	private List<InvestDetail> investDetailList	;
	
	/** 最大期号 */
	private int MaxNum;
	/** 还款类型 */
	private String repayType;
	
	
	private String payTlementType;
	
	/** Tab页  选中当前期数 */
	private int curNum;
	
	/** 操作类型常量-还款  */
	public static final String POERATTYPE_ONE = "one";
	
	/** 操作类型常量-查看 */
	public static final String POERATTYPE_TWO = "two";
	
	/** include Tab页Dto */
	private LoanDto loanDto;

	@Autowired
	private LoanEffectMangementService loanEffectMangementService;

	@Autowired
	private FeroFreemarkerProcessor feroFreemarkerProcessor;
	
	@Override
	@Action(value="repayManage")
	public String init() throws Exception {
		
		//查询参数赋值
		criteria.setLoanCode(loanCode);
		criteria.setLoanName(loanProject);
		criteria.setCustomName(customName);
		criteria.setRepayTimeMin(repayDateMin);
		criteria.setRepayTimeMax(repayDateMax);
		if (StringUtils.isNotEmpty(receivableRepayStatus)) {
		    criteria.setReceivableRepayStatus(receivableRepayStatus);
		}
		List<String> status=new ArrayList<String>();
		status.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
		criteria.setStatus(status);
		//调用融资扣款管理查询接口
		loanBuckleRepayList = loanSearchService.getPageLoanInfoByCriteria(criteria);
		
		return "repayManage";
	}

	/**
	 * 还款明细页
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws JSONException 
	 * 
	 */
	@Action(value="repayDetail")
	@Token(generator=true)
	public String overdueDetail() throws IllegalAccessException, InvocationTargetException, JSONException {
		
		//判断当前切入口 设置标题
		if(StringUtils.equals(POERATTYPE_ONE,operatType)) {	//还款
			titleName = "Repay";
		} else if(StringUtils.equals(POERATTYPE_TWO,operatType)) {	//查看
			titleName = "View";
		}
		
		//还款详情
		LoanDto repayLoanDto = new LoanDto();	
 		repayLoanDto = loanSearchService.getLoanInfo(curLoanCode,SearchInfoTypeEnum.REPAYMENT_INFO,SearchInfoTypeEnum.REPAYMENT_RECORD,SearchInfoTypeEnum.LOAN);
 		if(repayLoanDto != null)
 		{
 			//获取还款详细列表 
 			retRepayDetail = repayLoanDto.getPresentRepayDetail();
 		}
 		
 		//调用接口获取 include Tab页信息
 		loanDto = loanSearchService.getLoanInfo(curLoanCode,SearchInfoTypeEnum.COMMON_TAB_ALL);
		
		return "repayDetail";
	}
	
	//Tab页 还款详情操作
	@Action(value = "repayDetailView")
	public void repayDetailView() throws Exception {
		
		//添加查询参数
		InvestDetailCriteria detailCriteria = new InvestDetailCriteria();
		detailCriteria.setLoanCode(curLoanCode, Operator.equal);
		detailCriteria.setNum(curNum, Operator.equal);
		
		List<CustomInvestDetail> investDetailList = investSearchService.getInvestDetailByCriteria(detailCriteria);
		Map <String,Object> investDetailMap = new HashMap<String, Object>();
		
		investDetailMap.put("investDetails", investDetailList);
		String  result=feroFreemarkerProcessor.process(Constants.INVEST_DETAIL_TEMPLATE, investDetailMap, this);
		
		investDetailMap.put("html", result);
		
		Struts2Utils.renderJson(investDetailMap);
	}
	
	//还款操作
	@Action(value = "repayOperating")
	@Token(check=true)
	public void repayOperating() throws Exception {
		//融资还款
		Map<String, Object> resultMap = new HashMap<String, Object>();
		OperateInfo operateInfo = loanEffectMangementService.doLoanDetailRepayment(curLoanCode, passWord, getLoginuser(), payTlementType);
		if(operateInfo.getRetCode()==0){
			resultMap.put("result", true);
            resultMap.put("html", "repay success");
		}else{
			resultMap.put("result", false);
	        resultMap.put("html", operateInfo.getErrMessage());
		}
		Struts2Utils.renderJson(resultMap);
	}
	
	/****************************** Get Set 方法 *************************************/
	
	/** 获取融资编号 */
	public String getLoanCode() {
		return loanCode;
	}

	/** 设置融资编号 */
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	/** 过去融资项目 */
	public String getLoanProject() {
		return loanProject;
	}

	/** 设置融资项目 */
	public void setLoanProject(String loanProject) {
		this.loanProject = loanProject;
	}

	/** 获取客户名称 */
	public String getCustomName() {
		return customName;
	}

	/** 设置客户名称 */
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	
	/** 获取最小还款日期 */
	public Date getRepayDateMin() {
		return repayDateMin;
	}

	/** 设置最小还款日期 */
	public void setRepayDateMin(Date repayDateMin) {
		this.repayDateMin = repayDateMin;
	}

	/** 获取最大还款日期 */
	public Date getRepayDateMax() {
		return repayDateMax;
	}

	/** 设置最大还款日期 */
	public void setRepayDateMax(Date repayDateMax) {
		this.repayDateMax = repayDateMax;
	}
	
	/** 获取融资扣款集合 */
	public List<Loan> getLoanBuckleRepayList() {
		return loanBuckleRepayList;
	}

	/** 设置融资扣款集合 */
	public void setLoanBuckleRepayList(List<Loan> loanBuckleRepayLis) {
		this.loanBuckleRepayList = loanBuckleRepayLis;
	}
	
	/** 获取操作类型 */
	public String getOperatType() {
		return operatType;
	}

	/** 设置操作类型 */
	public void setOperatType(String operatType) {
		this.operatType = operatType;
	}

	/** 获取当前融资Code */
	public String getCurLoanCode() {
		return curLoanCode;
	}

	/** 设置当前融资Code */
	public void setCurLoanCode(String curLoanCode) {
		this.curLoanCode = curLoanCode;
	}

	/** 获取一级标题 */
	public String getTitleName() {
		return titleName;
	}

	/** 设置一级标题 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	/** 获取还款最大期号 */
	public int getMaxNum() {
		return MaxNum;
	}

	/** 设置还款最大期号 */
	public void setMaxNum(int maxNum) {
		MaxNum = maxNum;
	}
	
	/** 获取还款类型 */
	public String getRepayType() {
		return repayType;
	}
	/** 设置还款类型 */
	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}
	
	/** 获取include Tab页Dto */
	public LoanDto getLoanDto() {
		return loanDto;
	}

	/** 设置include Tab页Dto */
	public void setLoanDto(LoanDto loanDto) {
		this.loanDto = loanDto;
	}
	
	
	/** 获取融资还款详情 */
	public RepayDetail getRetRepayDetail() {
		return retRepayDetail;
	}

	/** 设置融资还款详情 */
	public void setRetRepayDetail(RepayDetail retRepayDetail) {
		this.retRepayDetail = retRepayDetail;
	}
	
	/** 获取Tab页  选中当前期数 */
	public int getCurNum() {
		return curNum;
	}

	/** 设置Tab页  选中当前期数 */
	public void setCurNum(int curNum) {
		this.curNum = curNum;
	}
	/** 获取投资详情List */
	public List<InvestDetail> getInvestDetailList() {
		return investDetailList;
	}

	/** 设置投资详情List */
	public void setInvestDetailList(List<InvestDetail> investDetailList) {
		this.investDetailList = investDetailList;
	}
	
	/** 获取查询参数类 */
	public LoanCommSelCriteria getCriteria() {
		return criteria;
	}

	/** 设置查询参数类 */
	public void setCriteria(LoanCommSelCriteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the payTlementType
	 */
	public String getPayTlementType() {
		return payTlementType;
	}

	/**
	 * @param payTlementType the payTlementType to set
	 */
	public void setPayTlementType(String payTlementType) {
		this.payTlementType = payTlementType;
	}

    /** 获取receivableRepayStatus */
    public String getReceivableRepayStatus() {
        return receivableRepayStatus;
    }

    /** 设置receivableRepayStatus*/
    public void setReceivableRepayStatus(String receivableRepayStatus) {
        this.receivableRepayStatus = receivableRepayStatus;
    }
	
	
	
}
