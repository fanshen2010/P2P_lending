package cn.com.p2p.mgr.action;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.customer.service.PersonalService;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.dto.CustomLoan;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.query.LoanInfoByCriteriaQuery;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.RepaymentStatusEnum;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.security.password.service.CheckUserPasswordService;

@Namespace("/static")
@Results({ @Result(name = "yx", location = "yx.ftl", type = "freemarker"),
	   @Result(name = "yhj", location = "yhj.ftl", type = "freemarker"),
	   @Result(name = "tzfx", location = "tzfx.ftl", type = "freemarker"),
	   @Result(name = "rzfx", location = "rzfx.ftl", type = "freemarker"),
	   @Result(name = "cwfx", location = "cwfx.ftl", type = "freemarker"),
	   @Result(name = "khfx", location = "khfx.ftl", type = "freemarker"),
	   @Result(name = "wzyyfx", location = "wzyyfx.ftl", type = "freemarker")})
public class StaticAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="index")
	public String init() throws Exception {
       return INIT;
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="yx")
	public String yx() throws Exception {
       return "yx";
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="yhj")
	public String yhj() throws Exception {
       return "yhj";
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="tzfx")
	public String tzfx() throws Exception {
       return "tzfx";
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="rzfx")
	public String rzfx() throws Exception {
       return "rzfx";
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="cwfx")
	public String cwfx() throws Exception {
       return "cwfx";
	}
    /**
     * <p>后台首页</p>
     * @author 
     * @date 2015-05-19 16:07
     * @description 无
     */
	@Action(value="khfx")
	public String khfx() throws Exception {
       return "khfx";
	}
     /**
      * <p>后台首页</p>
      * @author 
      * @date 2015-05-19 16:07
      * @description 无
      */
 	@Action(value="wzyyfx")
 	public String wzyyfx() throws Exception {
        return "wzyyfx";
 	}

}
