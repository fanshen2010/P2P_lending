package cn.com.p2p.por.action.account;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestCriteria;
import cn.com.p2p.domain.invest.dto.MyInvestDto;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.entity.InvestDetail;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.NumericUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.invest.service.InvestSearchService;
import cn.com.p2p.invest.service.InvestService;
import cn.com.p2p.loan.service.LoanApplyService;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.utils.Constants;

/**
 * 前台个人中心我的投资
 * @author 
 *
 */
@Namespace("/account/myinvest")
@Results({ @Result(name = BaseAction.INIT, location = "investList.ftl", type = "freemarker")
,@Result(name ="details", location = "myInvest.ftl", type = "freemarker")
,@Result(name ="pending", location = "pending.ftl", type = "freemarker")
,@Result(name ="complete", location = "complete.ftl", type = "freemarker")
,@Result(name ="receivables", location = "receivables.ftl", type = "freemarker")

    })
public class MyInvestAction extends BaseAction{

	/**  */
	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private InvestService investService;
	@Autowired
    private InvestSearchService investSearchService;
	
	@Autowired
	protected FeroFreemarkerProcessor feroFreemarkerProcessor;
	
	//系统设定 settingService 接口
	@Autowired
	private SettingService settingService;	
	
	//投资自定义查询接口
	@Autowired
	private InvestQuery investQuery;
	
	//融资操作service
	@Autowired
	private LoanApplyService loanApplyService;
	
	private List<MyInvestDto> invests;
	
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	/** 我的投资查询条件 */
    private CustomInvestCriteria criteria = new CustomInvestCriteria();
    
	/** 投资明细查询条件 */
    private InvestDetailCriteria  investDetailcriteria = new InvestDetailCriteria();
    
    private String result;
    /** 投资ID*/
    private String investId;
	/** 投资详细信息 */
    private List<InvestDetail> lstInvestDetail;
    /** 投资信息 */
    private Invest invest;
    /** 投资Code*/
    private String investCode;
	
    private String flag="pending";
    
    /** 项目价值最低折价率  */
    private double convertMin;
    
	/** 项目价值最高折价率 */
	private double convertMax;
	
	/** 服务费率 */
	private double service;
	
	/** 转账有效时间 */
	private String effectiveDate;
	

	@Action(value="investList")
	@Override
	public String init() throws Exception {
		// 我的投资Tab页显示列表总数量
		map = investService.findInvestCount(super.getLoginuser().getId());
		// 调用第一页内容
		if(cn.com.p2p.framework.constant.Constants.STR_PENDING.equals(flag)){
		    
		    this.getMyInvestPending();
		}else if(cn.com.p2p.framework.constant.Constants.STR_RECEIVABLES.equals(flag)){
            
            this.getMyInvestReceivables();
		}else if(cn.com.p2p.framework.constant.Constants.STR_COMPLETE.equals(flag)){
		    
		    this.getMyInvestComplete();
        }
		return flag;
	}
	
	/**
     * 获取我的投资记录：待生效
     * @throws Exception
     */
	private void getMyInvestPending() throws Exception{
        this.getMyInvestPendingData();
    }
    
    
    /**
     * 获取我的投资记录：回款中
     * @throws Exception
     */
    private void getMyInvestReceivables() throws Exception{
        criteria.setUserId(getLoginuser().getId());
        List<String> status=new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_07.getCode());
        criteria.setStatus(status);
        findPageByMyInvestCriteria();
    }
    
    /**
     * 获取我的投资记录：已完成
     * @throws Exception
     */
    private void getMyInvestComplete() throws Exception{
        criteria.setUserId(getLoginuser().getId());
        List<String> status=new ArrayList<String>();
        status.add(InvestStatusEnmu.INVEST_STATUS_09.getCode());
        criteria.setStatus(status);
        findPageByMyInvestCriteria();
    }
	
    /**获取待生效ajax分页数据，提供首次进入我的投资页面和分页画面数据内容*/
    private void getMyInvestPendingData() throws Exception{
    	// 获取投资显示列表根据查询条件为当前登录用户
    	criteria.setUserId(getLoginuser().getId());
    	// 获取投资显示列表根据查询条件为待生效状态
    	 List<String> status=new ArrayList<String>();
    	status.add(InvestStatusEnmu.INVEST_STATUS_02.getCode());
    	status.add(InvestStatusEnmu.INVEST_STATUS_14.getCode());
    	criteria.setStatus(status);
    	// 查询我的投资列表集合
    	findPageByMyInvestCriteria();
    }
    
    /**
     * 获取投资详细信息
     * @throws Exception
     */
    @Action(value="details")
    public String details()  {
        invest = investSearchService.getInvestById(investId);
        if (invest == null) {
            // TODO message
        }
        return "details";
    }
    
    /**
     * 获取当前投资明细
     * @throws Exception
     */
    @Action(value="myInvestDetailAjax")
    public void myInvestDetailAjax() throws Exception{
        Map<String,Object> mappage=new HashMap<String, Object>();
        investDetailcriteria.setInvestCode(investCode, Operator.equal);
        investDetailcriteria.setSortFields(cn.com.p2p.domain.invest.criteria.InvestDetailCriteria.OrderField.num,SortType.ASC);
        lstInvestDetail = investSearchService.getPageInvestDetailByCriteria(investDetailcriteria);
        mappage.put("lstInvestDetail", lstInvestDetail);
        mappage.put("investCode", investCode);
        mappage.put("investDetailcriteria", investDetailcriteria);
        String result=feroFreemarkerProcessor.process(Constants.MY_INVEST_DETAIL_TEMPLATE, mappage, this);
        mappage.put("html", result);
        Struts2Utils.renderJson(mappage);
    }
    
    /**获取我的投资列表集合*/
    private void findPageByMyInvestCriteria(){
    	invests = investService.findPageMyInvest(criteria);
    }
    
    /**
     * 回款中详情查看按钮
     * @throws Exception
     */
    @Action(value="myInvestDetail")
    public String myInvestDetail(){
    	
    	return "investDetail";
    }

    
    
	/** 获取invests */
	public List<MyInvestDto> getInvests() {
		return invests;
	}

	/** 设置invests */
	public void setInvests(List<MyInvestDto> invests) {
		this.invests = invests;
	}

	/** 获取map */
	public Map<String, Integer> getMap() {
		return map;
	}

	/** 设置map */
	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	/** 获取criteria */
	public CustomInvestCriteria getCriteria() {
		return criteria;
	}

	/** 设置criteria */
	public void setCriteria(CustomInvestCriteria criteria) {
		this.criteria = criteria;
	}

	/** 获取result */
	public String getResult() {
		return result;
	}

	/** 设置result */
	public void setResult(String result) {
		this.result = result;
	}

    /** 获取investId */
    public String getInvestId() {
        return investId;
    }

    /** 设置investId*/
    public void setInvestId(String investId) {
        this.investId = investId;
    }

    /** 获取lstInvestDetail */
    public List<InvestDetail> getLstInvestDetail() {
        return lstInvestDetail;
    }

    /** 设置lstInvestDetail*/
    public void setLstInvestDetail(List<InvestDetail> lstInvestDetail) {
        this.lstInvestDetail = lstInvestDetail;
    }

    /** 获取invest */
    public Invest getInvest() {
        return invest;
    }

    /** 设置invest*/
    public void setInvest(Invest invest) {
        this.invest = invest;
    }

    /** 获取investCode */
    public String getInvestCode() {
        return investCode;
    }

    /** 设置investCode*/
    public void setInvestCode(String investCode) {
        this.investCode = investCode;
    }

    /**
     * @return the investDetailcriteria
     */
    public InvestDetailCriteria getInvestDetailcriteria() {
        return investDetailcriteria;
    }

    /**
     * @param investDetailcriteria the investDetailcriteria to set
     */
    public void setInvestDetailcriteria(InvestDetailCriteria investDetailcriteria) {
        this.investDetailcriteria = investDetailcriteria;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /** 获取项目价值最低折价率 */
	public double getConvertMin() {
		return convertMin;
	}
	/** 设置项目价值最低折价率 */
	public void setConvertMin(double convertMin) {
		this.convertMin = convertMin;
	}
	/** 获取项目价值最高折价率 */
	public double getConvertMax() {
		return convertMax;
	}
	/** 设置项目价值最高折价率 */
	public void setConvertMax(double convertMax) {
		this.convertMax = convertMax;
	}
	/** 获取服务费率 */
	public double getService() {
		return service;
	}
	/** 设置服务费率 */
	public void setService(double service) {
		this.service = service;
	}

	/** 获取转账有效时间 */
	public String getEffectiveDate() {
		return effectiveDate;
	}
	/** 设置转账有效时间 */
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}
