package cn.com.p2p.por.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import cn.com.p2p.domain.payment.entity.CiccAccount;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.SpringContextUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.util.VerifyCodeGenerator;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.exception.CaptchaException;
import cn.com.p2p.framework.web.interceptor.Constant;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.security.control.front.MyVerifyCodeFront;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;

/**
 * <p>注册</p>
 * @author 
 * @date 2015-10-15 09:40
 */
@Namespace("/")
@Results({ 
          @Result(name = BaseAction.INIT, location = "register.ftl", type = "freemarker"),
          @Result(name = BaseAction.SAVE, location = "registersuccess.ftl", type = "freemarker"),
        })

public class RegisterAction extends BaseAction{

    private static final long serialVersionUID = 1L;

    /** 用户注册Service */
    @Autowired
    private WebUserService webUserService;
    
    /** 系统配置查询Service */
	@Autowired
	private SettingService settingService;
	
	@Autowired
	private PaymentService paymentService;
    
    /** 前台用户实体 */
    private WebUser webUser;
    
    /**
     * <p>注册首页</p>
     * @author 
     * @date 2015-10-15 09:40
     * @description 无
     */
    @Action(value="register")
    public String init() {
        return INIT;
    }
    
    /**
     * <p>保存注册信息</p>
     * @author 
     * @date 2015-10-15 10:26
     * @description 无
     */
    @Action(value="saveinfo")
    public String saveinfo() {
		try {
			// 校验验证码
    		MyVerifyCodeFront.checkPhoneVerifyCode(Struts2Utils.getRequest());
    		Struts2Utils.getSession().removeAttribute("phoneVerifyCode");
			
	        // TODO 没有后台校验 
			String password = webUser.getPassword();
			webUserService.save(webUser);
	        paymentService.ciccDoRegister(webUser.getId(),webUser.getLogin());
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					webUser.getLogin(), password);

			Struts2Utils.getSession();

			token.setDetails(new WebAuthenticationDetails(Struts2Utils
					.getRequest()));

			Authentication authenticatedUser = ((AuthenticationManager) SpringContextUtils
					.getBean("authenticationManager")).authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);

			super.setServletContext(null);
			
//			//发短信，站内信
			String serviceName = settingService.findSysSettingBySettingCode("site_name").getSettingValue();
			webUserService.sendInternalFrontMessage(webUser.getId(), "1", serviceName);
		} catch (Exception e) {
			if(e instanceof CaptchaException) {
            	Struts2Utils.getRequest().setAttribute(Constant.COMM_ERROR_MESSAGE_KEY, "Captcha Error");
            	return INIT;
            }else{
            	e.printStackTrace();
            	// 不用处理
            }
		}
        return SAVE;
    }
    
   
    /**
     * <p>校验验证码</p>
     * @author 
     * @date 2015-10-29 15:48
     * @description 获取用户输入的验证码，与平台自动生成的验证码相比较，
     *              如果相等则通过
     */
    @Action(value="checkVCode")
    public String checkVCode() {
        try {
            // 获得用户输入的验证码
            String data = this.getAjaxMap().get("code").toString();
            // TODO 获得平台自动生成的验证码
            String VCode = (String) Struts2Utils.getSession().getAttribute("verifyCode");
            
            // 如果用户输入正确
            if(VCode.equals(data)){
                this.ajaxCheckSuccess();
            }else{
                this.ajaxCheckFailure();
            }
            
            MyVerifyCodeFront.checkVerifyCode(Struts2Utils.getRequest());
        } catch (Exception e) {
            if(e instanceof CaptchaException) {
                // throw new CaptchaException("手机验证码错误");
            }
        }
        return null;
    }
    
    /**
     * <p>用户名唯一性校验</p>
     * @author 
     * @date 2015-10-27 11:00
     * @description 无
     */
    @Action(value="checkUserLogin")
    public String checkUserLogin() {
        try {
            String data = this.getAjaxMap().get("webUser.login").toString();
            boolean isExsit = webUserService.checkUserLogin(data);
            if(isExsit){
                this.ajaxCheckFailure();
            }else{
                this.ajaxCheckSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * <p>手机号唯一性校验</p>
     * @author 
     * @date 2015-10-27 11:00
     * @description 无
     */
    @Action(value="checkUserPhone")
    public String checkUserPhone() {
        try {
            String data = this.getAjaxMap().get("webUser.celphone").toString();
            WebUserCriteria webUserCriteria = new WebUserCriteria();
            webUserCriteria.setCelphone(data, Operator.equal);
            List<WebUser> webUsers = webUserService.findByCriteria(webUserCriteria);
            if(webUsers != null && !webUsers.isEmpty()){
                this.ajaxCheckFailure();
            }else{
                this.ajaxCheckSuccess();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
 /* ========================================================== getter and setter mehods ================================================================= */
    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

}