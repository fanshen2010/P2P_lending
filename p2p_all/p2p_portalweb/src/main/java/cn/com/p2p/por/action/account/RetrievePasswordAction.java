package cn.com.p2p.por.action.account;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.CryptUtils;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.exception.CaptchaException;
import cn.com.p2p.framework.web.interceptor.Constant;
import cn.com.p2p.security.control.front.MyVerifyCodeFront;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;

/**
 * <p>
 * 找回密码
 * </p>
 * @author 
 * @date 2015-10-23 14:03
 */
@Namespace("/retrievepass")
@Results({
        @Result(name = BaseAction.INIT, location = "retrieveindex.ftl", type = "freemarker"),
        @Result(name = "chooseways", location = "retrieveways.ftl", type = "freemarker"),
        @Result(name = "mailway", location = "mailretrieve.ftl", type = "freemarker"),
        @Result(name = "sendMail", location = "mailsendsuccess.ftl", type = "freemarker"),
        @Result(name = "cellphoneway", location = "celphoneretrieve.ftl", type = "freemarker"),
        @Result(name = BaseAction.UPDATE, location = "changesuccess.ftl", type = "freemarker"),
        @Result(name = BaseAction.UPDATE, location = "changesuccess.ftl", type = "freemarker"),
})
public class RetrievePasswordAction extends BaseAction {

    private static final long serialVersionUID = -3129068909196596557L;

    /** 用户注册Service */
    @Autowired
    private WebUserService webUserService;

    /** 系统配置查询Service */
    @Autowired
    private SettingService settingService;

    /** 前台用户实体 */
    private WebUser webUser;

    /**
     * <p>
     * 找回密码首页
     * </p>
     * @author 
     * @date 2015-10-23 14:12
     * @description 无
     */
    @Action(value = "index")
    public String init() {
        return INIT;
    }

    /**
     * <p>
     * 选择找回方式
     * </p>
     * @author 
     * @date 2015-10-23 14:12
     * @description 分为邮箱找回，手机找回
     */
    @Action(value = "chooseways")
    public String chooseways() {
        // 验证码，后台校验
        try {
            MyVerifyCodeFront.checkVerifyCode(Struts2Utils.getRequest());
            WebUserCriteria criteria = new WebUserCriteria();
            criteria.setLogin(webUser.getLogin(), Operator.equal);
            List<WebUser> webUsers = webUserService.findByCriteria(criteria);
            if (webUsers != null && !webUsers.isEmpty()) {
                webUser = webUsers.get(0);
            }
            return "chooseways";
        } catch (Exception e) {
            if (e instanceof CaptchaException) {

            }
            e.getMessage();
        }
        return BaseAction.INIT;
    }

    /**
     * <p>
     * 邮箱找回密码
     * </p>
     * @author 
     * @date 2015-10-23 14:15
     * @description 用户点击找回密码链接后，跳转的Action
     */
    @Action(value = "mailway")
    public String mailway() {
        webUser = webUserService.findOne(webUser.getId());
        return "mailway";
    }

    /**
     * <p>
     * 手机找回密码
     * </p>
     * @author 
     * @date 2015-10-23 14:17
     * @description 无
     */
    @Action(value = "cellphoneway")
    public String cellphoneway() {
        webUser = webUserService.findOne(webUser.getId());
        return "cellphoneway";
    }

    /**
     * <p>
     * 邮箱更新密码
     * </p>
     * @author 
     * @date 2015-10-23 14:17
     * @description 用户点击找回密码链接后，含有参数：webUser.id
     */
    @Action(value = "mailupdate")
    public String mailupdate() {
        // TODO 30分钟有效，未限制
        if(webUser != null && StringUtils.isNotEmpty(webUser.getId())){
        	String newpassword = webUser.getPassword();
            webUser = webUserService.findOne(webUser.getId());
        	try{
        		// 校验验证码
        		MyVerifyCodeFront.checkVerifyCode(Struts2Utils.getRequest());
        		// 加密密码
        		webUser.setPasswordSalt(StringUtils.getRandomSalt());
    			String newpasswordByte = DESPlus.byteArr2HexStr((newpassword + webUser.getPasswordSalt()).getBytes());
    			webUser.setPassword(newpasswordByte);
        	} catch (Exception e) {
                if(e instanceof CaptchaException) {
                	Struts2Utils.getRequest().setAttribute(Constant.COMM_ERROR_MESSAGE_KEY, "Captcha Error");
                	return "mailway";
                }else{
                	e.printStackTrace();
                }
            }
            // 动态的更新用户
            webUserService.dynamicUpdate(webUser);
            //发短信，站内信
    		webUserService.sendInternalFrontMessage(webUser.getId(), "2", webUser.getEmail());
            return UPDATE;
        }
        return INIT;
    }

    /**
     * <p>
     * 手机更新密码
     * </p>
     * @author 
     * @date 2015-10-23 14:17
     * @description 无
     */
    @Action(value = "phoneupdate")
    public String phoneupdate() {
    	if(webUser != null && StringUtils.isNotEmpty(webUser.getId())){
    		String newpassword = webUser.getPassword();
    		webUser = webUserService.findOne(webUser.getId());
    		try{
        		// 校验验证码
        		MyVerifyCodeFront.checkPhoneVerifyCode(Struts2Utils.getRequest());
        		Struts2Utils.getSession().removeAttribute("phoneVerifyCode");
        		// 加密密码
        		webUser.setPasswordSalt(StringUtils.getRandomSalt());
    			String newpasswordByte = DESPlus.byteArr2HexStr((newpassword + webUser.getPasswordSalt()).getBytes());
    			webUser.setPassword(newpasswordByte);
        	} catch (Exception e) {
                if(e instanceof CaptchaException) {
                	Struts2Utils.getRequest().setAttribute(Constant.COMM_ERROR_MESSAGE_KEY, "Captcha Error");
                	return "cellphoneway";
                }else{
                	e.printStackTrace();
                }
            }
            // 动态的更新用户
            webUserService.dynamicUpdate(webUser);
            //发短信，站内信
            webUserService.sendInternalFrontMessage(webUser.getId(), "2", webUser.getCelphone());
            return UPDATE;
    	}
        return INIT;
    }

    /**
     * <p>
     * 手机找回密码
     * </p>
     * @author 
     * @date 2015-10-23 14:17
     * @description 无
     */
    @Action(value = "checkUserLogin")
    public String checkUserLogin() {
        try {
            String data = this.getAjaxMap().get("webUser.login").toString();
            boolean isExsit = webUserService.checkUserLogin(data);
            if (isExsit) {
                this.ajaxCheckSuccess();
            } else {
                this.ajaxCheckFailure();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * 发送邮件
     * </p>
     * @author 
     * @date 2015-10-29 16:00
     * @description 发送一封邮件,包含参数webUser.id
     */
    @Action(value = "sendMail")
    public String sendMail() {

        webUser = webUserService.findOne(webUser.getId());
        String currentTimeStr = DateUtils.formatDate(DateUtils.getCurrentDateTime(), "yyyy-MM-dd hh:mm:ss");
        String parameter = "webUser.id=" + webUser.getId() + "&time=" + currentTimeStr;
        String newParameter = "";
        try {
            newParameter = CryptUtils.byte2hex(CryptUtils.encryptData(parameter.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String successUrl = settings.get("domainSsl") + "/retrievepass/mailupdate.htm?" + newParameter;

        SysSetting serviceTell = settingService.findSysSettingBySettingCode("service_tell");
        /*
        webUserService.sendMailMessage(webUser.getEmail(), "1", webUser.getLogin(), currentTimeStr, successUrl,
                serviceTell.getSettingValue());
        */
        return "sendMail";
    }

    /**
     * <p>
     * 校验验证码
     * </p>
     * @author 
     * @date 2015-10-29 16:00
     * @description 获取用户输入的验证码，与平台自动生成的验证码相比较，
     *              如果相等则通过
     */
    @Action(value = "checkVCode")
    public String checkVCode() {
        try {
            // 获得用户输入的验证码
            String data = this.getAjaxMap().get("code").toString();
            // TODO 获得平台自动生成的验证码
            String VCode = (String) Struts2Utils.getSession().getAttribute("verifyCode");

            // 如果用户输入正确
            if (VCode.equals(data)) {
                this.ajaxCheckSuccess();
            } else {
                this.ajaxCheckFailure();
            }

            MyVerifyCodeFront.checkVerifyCode(Struts2Utils.getRequest());
        } catch (Exception e) {
            if (e instanceof CaptchaException) {
                // throw new CaptchaException("手机验证码错误");
            }
        }
        return null;
    }

    /* ========================================================== getter and
     * setter mehods
     * ================================================================= */
    public WebUser getWebUser() {
        return webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }

}
