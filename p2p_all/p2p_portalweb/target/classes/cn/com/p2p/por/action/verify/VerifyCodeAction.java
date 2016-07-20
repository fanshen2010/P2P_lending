package cn.com.p2p.por.action.verify;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.util.VerifyCodeGenerator;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.exception.CaptchaException;
import cn.com.p2p.security.control.back.MyVerifyCodeBack;
import cn.com.p2p.security.control.front.MyVerifyCodeFront;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;
import cn.com.p2p.utils.Constants;

@Namespace("/")
public class VerifyCodeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private WebUserService webUserService;
	
	/** 系统配置查询Service */
	@Autowired
	private SettingService settingService;
	
	@Action(value="verifyIndex")
    public String init() {
        return INIT;
    }

	@Action(value = "verifyCode")
	public void verifyCode(){
		String verifyCode = MyVerifyCodeBack.getVerifyCode(4);
		Struts2Utils.getSession().setAttribute("verifyCode", verifyCode);
		MyVerifyCodeBack.flushVerifyCode(Struts2Utils.getResponse(), verifyCode);
	}
	
	/**
	 * <p>手机验证码，图片验证码校验</p>.<br>
	 * author：<br>
	 *===================================
	 */
	@Action(value="checkVerifyCodeAjax")
	public void checkVerifyCodeAjax(){
		try{
            MyVerifyCodeFront.checkVerifyCode(Struts2Utils.getRequest());
            this.ajaxCheckSuccess();
        } catch (Exception e){
            if(e instanceof CaptchaException) {
            	this.ajaxCheckFailure();
            }
        }
	}
	
	@Action(value = "sendPhoneVerifyCode")
	public void sendPhoneVerifyCode() throws Exception{
		Map<String,Object> map = getAjaxMap();
		String phoneNumber = map.get("phoneNumber").toString().trim();
		String errMsg ="error";
		Boolean flag = false;
		
		// 手机校验码
		String sendType = map.get("sendType").toString().trim();
		String phoneVerifyCode = VerifyCodeGenerator.getVerifyCode(6);
		String serviceName = settingService.findSysSettingBySettingCode("site_name").getSettingValue();
		String serviceTell = settingService.findSysSettingBySettingCode("service_tell").getSettingValue();
		/*
		if("1".equals(sendType)){//注册验证码
			flag = webUserService.sendSMSFrontMessage(phoneNumber, "1", serviceName,phoneVerifyCode,serviceName);
		}else if("2".equals(sendType)){//忘记密码-手机找回验证码
			flag = webUserService.sendSMSFrontMessage(phoneNumber, "2", serviceName,phoneVerifyCode,serviceTell);
		}else if("4".equals(sendType)){//修改手机号原手机验证码
			flag = webUserService.sendSMSFrontMessage(phoneNumber, "4", serviceName,phoneVerifyCode,serviceTell);
		}else if("5".equals(sendType)){//修改手机号新手机验证码
			flag = webUserService.sendSMSFrontMessage(phoneNumber, "5", serviceName,phoneVerifyCode,serviceTell);
		}else if("17".equals(sendType)){//融资申请手机验证码
			flag = webUserService.sendSMSFrontMessage(phoneNumber, "17", serviceName,phoneVerifyCode,serviceTell);
		}
			
		if(flag==true){
			errMsg = "success";
		}
		*/
		errMsg = "success";
		
		//页面alert测试用
		map.put("phoneVerifyCode",phoneVerifyCode);
		Struts2Utils.getSession().setAttribute("phoneVerifyCode",phoneVerifyCode);
		logger.debug("验证码" + phoneVerifyCode);

		// ajax返回
		map.put("msg",errMsg);
		Struts2Utils.renderJson(map);
	}
}
