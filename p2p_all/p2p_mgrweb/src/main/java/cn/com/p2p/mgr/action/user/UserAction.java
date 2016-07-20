/**  */
package cn.com.p2p.mgr.action.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.customer.dto.InvestorsDetailDto;
import cn.com.p2p.customer.service.CustomerService;
import cn.com.p2p.customer.service.StatisticalService;
import cn.com.p2p.customer.service.dto.StatisticalDto;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.usermangent.service.WebUserService;

/**
 * @author shaolichao
 *
 */

@Namespace("/user")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker"),
        @Result(name = BaseAction.UPDATE, location = "index.htm", type = "freemarker"),
		})
public class UserAction extends BaseAction{

	
	/**  */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	/**
	 * 用户投资状况明细
	 */
	private InvestorsDetailDto investdetail;
	
	/**
	 * 用户融资状况明细
	 */
	private StatisticalDto loandetail;
	
	/**
	 * 前台用户明细
	 */
	private WebUser webUser;
	
    /**
     * 模板引擎
     */
    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	StatisticalService statisticalService;

	@Autowired
	WebUserService webUserService;
	
	private List<WebUser> lstWebUser = new ArrayList<WebUser>();
	
	
	private WebUserCriteria criteria = new WebUserCriteria();

	@Action(value="index")
	@Override
	public String init() throws Exception {
		lstWebUser = webUserService.getWebUserAll(criteria);
		return INIT;
	}
    /**
     * 查看界面
     * @throws Exception
     */
	@Action(value="view")
	public String view() throws Exception {
		investdetail = customerService.getInvestorsDetail(id);
		loandetail = statisticalService.getLoanCount(id);
		return VIEW;
	}
	
	/**
	 * 
	 * <p>邮箱设置</p>.<br>
	 * author：shaolichao'<br>	
	 * @throws Exception
	 */
    @Action(value = "setemail")
    public void setemail() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        investdetail = customerService.getInvestorsDetail(id);
        map.put("webUser", investdetail.getBasicMsg());
        String ftl = "/user/setemail.ftl";
        String result = feroFreemarkerProcessor.process(ftl, map, this);
        map.clear();
        map.put("result", result);
        Struts2Utils.renderJson(map);
    }

    
    /**
	 * 
	 * <p>电话设置</p>.<br>
	 * author：shaolichao'<br>	
	 * @throws Exception
	 */
    @Action(value = "setphone")
    public void setphone() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        investdetail = customerService.getInvestorsDetail(id);
        map.put("webUser", investdetail.getBasicMsg());
        String ftl = "/user/setphone.ftl";
        String result = feroFreemarkerProcessor.process(ftl, map, this);
        map.clear();
        map.put("result", result);
        Struts2Utils.renderJson(map);
    }

    /**
     * 更新状态
     * @throws Exception
     */
    @Action(value = "updateStatus")
    public void updateStatus() throws Exception {
    	Map<String, Object> map = getAjaxMap();
		String id = map.get("receiverId").toString();
        // 查询消息模版信息
    	webUser = webUserService.findOne(id);
        // 根据模版状态更新
        if ("1".equals(webUser.getActive())) {
        	webUser.setActive(1L);
        } else {
        	webUser.setActive(0L);
        }
        webUserService.dynamicUpdate(webUser);
        this.ajaxCheckSuccess();
    }
    
    
    @Validators(str="custPersonCheck",result = BaseAction.INIT, param = "update")
    @Action(value = "update")
    public String update() throws Exception {
        webUserService.dynamicUpdate(webUser);
        return BaseAction.UPDATE;
    }
    

	
	/** 获取id */
    public String getId() {
        return id;
    }
	/** 设置id */
    public void setId(String id) {
        this.id = id;
    }
	
	/** 获取lstWebUser */
	public List<WebUser> getLstWebUser() {
		return lstWebUser;
	}


	/** 设置lstWebUser */
	public void setLstWebUser(List<WebUser> lstWebUser) {
		this.lstWebUser = lstWebUser;
	}
	/** 获取WebUser */
	public WebUser getWebUser() {
		return webUser;
	}


	/** 设置lstWebUser */
	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}

	/** 获取criteria */
	public WebUserCriteria getCriteria() {
		return criteria;
	}


	/** 设置criteria */
	public void setCriteria(WebUserCriteria criteria) {
		this.criteria = criteria;
	}
	/** 获取investdetail */
	public InvestorsDetailDto getInvestdetail() {
		return investdetail;
	}
	/** 设置investdetail */
	public void setInvestdetail(InvestorsDetailDto investdetail) {
		this.investdetail = investdetail;
	}
	/** 获取loandetail */
	public StatisticalDto getLoandetail() {
		return loandetail;
	}
	/** 设置loandetail */
	public void setLoandetail(StatisticalDto loandetail) {
		this.loandetail = loandetail;
	}


}
