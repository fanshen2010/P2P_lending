package cn.com.p2p.por.action.account;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.CryptUtils;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.util.VerifyCodeGenerator;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.exception.CaptchaException;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.payment.bean.OperateInfo;
import cn.com.p2p.security.control.front.MyVerifyCodeFront;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;
import cn.com.p2p.utils.Constants;

/**
 * 安全中心
 * 
 * @
 */
@Namespace("/account/security")
@Results({@Result(name = BaseAction.INIT,location = "index.ftl",type = "freemarker"),
		@Result(name = "mailSuccess",location = "mailSuccess.ftl",type = "freemarker")})
public class SecurityAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;

	/** 模板渲染 */
	@Autowired
	private FeroFreemarkerProcessor feroFreemarkerProcessor;

	/** 用户投资信息查询Service */
	@Autowired
	private WebUserService webUserService;
	
	/** 系统配置查询Service */
	@Autowired
	private SettingService settingService;
	
	/** 中金账户查询Service */
	@Autowired
	private PaymentService paymentService;

	private WebUser webUser;
	
	private String oldpassword;// 原密码
	
	private String newpassword;// 新密码

	private String repassword;// 重复密码

	private String updatePhone;// 修改后的电话
	
	private String originalMail;//原邮箱
	
	private String updateMail;// 新邮箱（短信验证邮箱）
	
	private String updateMailLock;
	
	private String userId;
	
	private boolean ciccAccountFlag;

	/**
	 * <p>安全中心初始化</p>.<br>
	 * author：<br>
	 *===================================
	 * @return
	 * @throws Exception
	 */
	@Override
	@Action(value = "index")
	public String init() throws Exception{
		String userId = getLoginuser().getId();
		// 根基登录用户ID获取登录用户个人信息
		webUser = webUserService.findOne(userId);
		return INIT;
	}
	
	
	/**
	 * 修改登录密码页面
	 * 
	 * @return changePassword
	 * @throws Exception
	 */
	@Action(value = "changePasswordDialog")
	public void changePasswordDialog() throws Exception{
		
		Map<String, Object> map = getAjaxMap();
		// 得到渲染好的模板内容
		String result = "";
		result = feroFreemarkerProcessor.process(Constants.SECURITY_PASSWOED_CHANGE, map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	

//	@Validators(str="passwordCheck",result = "updateError",param = "updatePassword")
	@Action(value = "updatePassword")
	public void updatePassword() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String errMsg = "error";
		String oldpassword = map.get("oldpassword").toString();
		String newpassword = map.get("newpassword").toString();
		String repassword = map.get("repassword").toString();
		String userId = getLoginuser().getId();
		boolean equalToOldPassword = webUserService.checkOldPassword(oldpassword, userId);
		
		//原密码
		if(oldpassword==null || StringUtils.isBlank(oldpassword)){
			errMsg = "Original Password：should not be null";
		}else if(oldpassword.length() < 6 || oldpassword.length() > 18){
			errMsg = "Original Password：length should be 6~18 character";
		}else if(!equalToOldPassword){
			errMsg = "Original Password Error：fill in again！";
		}
		//新密码
		else if(newpassword==null || StringUtils.isBlank(newpassword)){
			errMsg = "New Password：should not be null";
		}else if(newpassword.length() < 6 || newpassword.length() > 18){
			errMsg = "New Password：length should be 6~18 character";
		}
		//确认密码
		else if(repassword==null || StringUtils.isBlank(repassword)){
			errMsg = "Confirm Password：should not be null";
		}else if(!repassword.equals(newpassword)){
			errMsg = "Confirm Password：is different from New Password";
		}else{
			//更新数据
			WebUser webUser = webUserService.findOne(userId);
			webUser.setPasswordSalt(StringUtils.getRandomSalt());
			String newpasswordByte = DESPlus.byteArr2HexStr((newpassword + webUser.getPasswordSalt()).getBytes());
			webUser.setPassword(newpasswordByte);
			webUserService.dynamicUpdate(webUser);
			//发短信，站内信
			Date currentTime = DateUtils.getCurrentDateTime();
			String currentTimeStr = DateUtils.formatDate(currentTime, "yyyy-MM-dd HH:mm:ss");
			String serviceTell = settingService.findSysSettingBySettingCode("service_tell").getSettingValue();
			String siteName = settingService.findSysSettingBySettingCode("site_name").getSettingValue();
			webUserService.sendInternalFrontMessage(webUser.getId(), "3", currentTimeStr);
			errMsg = "success";
		}
		map.put("msg", errMsg);
		Struts2Utils.renderJson(map);
	}
	
	
	/**
	 * 验证原手机页面
	 * 
	 * @return verifyOriginalPhone
	 * @throws Exception
	 */
	@Action(value = "changeCellphoneDialog")
	public void changeCellphoneDialog() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String userId = getLoginuser().getId();
		WebUser webUser = webUserService.findOne(userId);		
		map.put("updateCellphoneStep", "1");
		
		//TODO 提共通
		String cellphone = webUser.getCelphone();
		String cellphoneLock = cellphone;
		if(cellphone!=null && cellphone.length()>10){
			cellphoneLock = cellphone.substring(0,3)+"****"+cellphone.substring(7);			
		}
		map.put("originalPhoneLock", cellphoneLock);
		map.put("originalPhone", cellphone);
		// 得到渲染好的模板内容
		String result = "";
		result = feroFreemarkerProcessor.process(Constants.SECURITY_CELLPHONE_CHANGE, map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	

	@Action(value = "checkOriginalPhone")
	public void checkOriginalPhone() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String errMsg = "error";
		try{
            MyVerifyCodeFront.checkPhoneVerifyCode(Struts2Utils.getRequest());
            Struts2Utils.getSession().removeAttribute("phoneVerifyCode");
            map.put("updateCellphoneStep", "2");
            // 得到渲染好的模板内容
    		String result = "";
    		result = feroFreemarkerProcessor.process(Constants.SECURITY_CELLPHONE_CHANGE, map, this);
    		map.put("html", result);
    		errMsg = "success";
    		
        } catch (Exception e){
            if(e instanceof CaptchaException) {
            	errMsg = "Captcha Error";
            }
        }
		// ajax返回
		map.put("msg",errMsg);
		Struts2Utils.renderJson(map);
	}
	

	@Action(value = "updatePhone")
	public void updatePhone() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String match = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9]|17[0-9])\\d{8}$";
		String errMsg = "error";
		String userId = getLoginuser().getId();
		boolean isUniquePhone = webUserService.checkUniqueCellphone(updatePhone, userId);
		if(updatePhone==null || StringUtils.isBlank(updatePhone)){
			errMsg = "New Phone：should not be null";
		}else if(!updatePhone.matches(match)){
			errMsg = "New Phone：is not valid";
		}else if(!isUniquePhone){
			errMsg = "New Phone：has been used!";
		}else{
			try{
	            MyVerifyCodeFront.checkPhoneVerifyCode(Struts2Utils.getRequest());
	            Struts2Utils.getSession().removeAttribute("phoneVerifyCode");
	            WebUser webUser = webUserService.findOne(userId);
				webUser.setCelphone(updatePhone);
				webUserService.dynamicUpdate(webUser);
				//TODO 提共通
				String cellphone = webUser.getCelphone();
				String cellphoneLock = cellphone;
				if(cellphone!=null && cellphone.length()>10){
					cellphoneLock = cellphone.substring(0,3)+"****"+cellphone.substring(7);			
				}
				//发短信，站内信
				Date currentTime = DateUtils.getCurrentDateTime();
				String currentTimeStr = DateUtils.formatDate(currentTime, "yyyy-MM-dd HH:mm:ss");
				webUserService.sendInternalFrontMessage(webUser.getId(), "4", currentTimeStr,cellphoneLock);
				map.put("updatePhoneLock", cellphoneLock);
				errMsg = "success";
	        } catch (Exception e){
	            if(e instanceof CaptchaException) {
	            	errMsg = "Captcha Error";
	            }
	        }
		}
		// ajax返回
		map.put("msg",errMsg);
		Struts2Utils.renderJson(map);
	}
	
	
	/**
	 * 修改邮箱页面
	 * 
	 * @return verifyOriginalPhone
	 * @throws Exception
	 */
	@Action(value = "changeMailDialog")
	public void changeMailDialog() throws Exception{
		String userId = getLoginuser().getId();
		WebUser webUser = webUserService.findOne(userId);		
		Map<String, Object> map = getAjaxMap();
		if(webUser.getEmail()==null || webUser.getEmail().isEmpty()){
			map.put("updateMailStep", "new");
		}else{
			map.put("updateMailStep", "1");
		}
		map.put("webUser", webUser);
		// 得到渲染好的模板内容
		String result = "";
		result = feroFreemarkerProcessor.process(Constants.SECURITY_MAIL_CHANGE, map, this);
		map.put("html", result);
		// ajax返回
		Struts2Utils.renderJson(map);
	}
	
	/**
	 * 验证新邮箱发短信
	 * 
	 * @return verifyOriginalMail
	 * @throws Exception
	 */
	@Action(value = "sendActivationMail")
	public void updateMail() throws Exception{
		Map<String, Object> map = getAjaxMap();
		String errMsg = "error";
		String userId = getLoginuser().getId();
		String updateMailStep = map.get("updateMailStep").toString();
		String updateMail = map.get("updateMail").toString();
		String match = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$";;
		
		if("1".equals(updateMailStep)){
			//原邮箱
			String originalMail = map.get("originalMail").toString();
			boolean equalToOldMail = webUserService.checkOldMail(originalMail, userId);
			if(originalMail==null || StringUtils.isBlank(originalMail)){
				errMsg = "Original Email: not be null";
			}else if(!originalMail.matches(match)){
				errMsg = "Original Email: is not valid";
			}else if(!equalToOldMail){
				errMsg = "Original Email: Error!";
			}
		}
		if("error".equals(errMsg)){
			//新邮箱
			boolean isUniqueMail = webUserService.checkUniqueMail(updateMail, userId);
			if(updateMail==null || StringUtils.isBlank(updateMail)){
				errMsg = "New Email: not be null";
			}else if(!updateMail.matches(match)){
				errMsg = "New Email: is not valid";
			}else if(!isUniqueMail){
				errMsg = "New Email: has been used!";
			}else{
				//获取当前时间
				Date currentTime = DateUtils.getCurrentDateTime();
				String currentTimeStr = DateUtils.formatDate(currentTime, "yyyy-MM-dd HH:mm:ss");
				String parameter = "userId=" + userId + "&updateMail=" + updateMail + "&time=" + currentTimeStr;
				String newParameter = CryptUtils.byte2hex(CryptUtils.encryptData(parameter.getBytes()));
				String successUrl = settings.get("domainSsl") + "/account/security/mailSuccess.htm?"+ newParameter; 
				WebUser webUser = webUserService.findOne(userId);
				SysSetting serviceTell = settingService.findSysSettingBySettingCode("service_tell");
				/*
				Boolean flag = webUserService.sendMailMessage(updateMail, "2",webUser.getLogin(),successUrl,currentTimeStr,serviceTell.getSettingValue());
				if(flag==false){
					errMsg = "Send Error";
				}else{
					//TODO 提共通
					String mail = updateMail;
					String mailLock = mail;
					if(mail!=null && mail.length()>2){
						mailLock = mail.substring(0,2)+"*******"+mail.substring(mail.indexOf("@"));			
					}
					map.put("updateMailLock", mailLock);
					errMsg = "success";
				}
				*/
				String mail = updateMail;
				String mailLock = mail;
				if(mail!=null && mail.length()>2){
					mailLock = mail.substring(0,2)+"*******"+mail.substring(mail.indexOf("@"));			
				}
				map.put("updateMailLock", mailLock);
				errMsg = "success";
			}
		}
		map.put("msg", errMsg);
		// ajax返回
		Struts2Utils.renderJson(map);		
	}
	
	
	
	/**
	 * 修改邮箱成功页面
	 * 
	 * @return verifyOriginalPhone
	 * @throws Exception
	 */
	@Action(value = "mailSuccess")
	public String mailSuccess() throws Exception{
	    
	    HttpServletRequest request = Struts2Utils.getRequest();
        String parameter = request.getQueryString();
        String newParameter = new String(CryptUtils.decryptData(CryptUtils.hex2byte(parameter)));
        Map<String,String> mapRequest = urlRequest(newParameter);
        userId = mapRequest.get("userId");
        updateMail = mapRequest.get("updateMail");
        String time = mapRequest.get("time");
        
        if(getLoginuser().getId().equals(userId)){
    		WebUser webUser = webUserService.findOne(userId);
    		webUser.setEmail(updateMail);
    		webUserService.dynamicUpdate(webUser);
    		if(updateMail!=null && updateMail.length()>2){
    			updateMailLock = updateMail.substring(0,2)+"*******"+updateMail.substring(updateMail.indexOf("@"));			
    		}
    		//发短信，站内信
    		Date currentTime = DateUtils.getCurrentDateTime();
    		String currentTimeStr = DateUtils.formatDate(currentTime, "yyyy-MM-dd HH:mm:ss");
    		webUserService.sendInternalFrontMessage(webUser.getId(), "5", currentTimeStr,updateMailLock);
        }
		return "mailSuccess";
	}
	
	/**
     * 解析出url参数中的键值对 如 "Action=del&id=123"，解析出Action:del,id:123存入map中
     * 
     * @param URL url地址
     * @return url请求参数部分
     */
    public static Map<String,String> urlRequest(String strUrlParam){

        Map<String,String> mapRequest = new HashMap<String,String>();

        String[] arrSplit = null;

        // 每个键值为一组
        arrSplit = strUrlParam.split("[&]");
        for(String strSplit : arrSplit){
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");

            // 解析出键值
            if(arrSplitEqual.length > 1){
                // 正确解析
                mapRequest.put(arrSplitEqual[0],arrSplitEqual[1]);

            } else{
                if(arrSplitEqual[0] != ""){
                    // 只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0],"");
                }
            }
        }
        return mapRequest;
    }
	
	//==================================校验=====================================================
		
	
	@Action(value="checkCellphoneAjax")
	public void checkCellphoneAjax(){
        try {
        	String updatePhone = this.getAjaxMap().get("updatePhone").toString();
        	String userId = getLoginuser().getId();
    		boolean isUniquePhone = webUserService.checkUniqueCellphone(updatePhone, userId);
            if(isUniquePhone){
            	this.ajaxCheckSuccess();
            }else{
            	this.ajaxCheckFailure();
//            	Struts2Utils.renderText("该电话号码已存在");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
	
	@Action(value="checkOldMailAjax")
	public void checkOldMailAjax(){
        try {
        	String originalMail = this.getAjaxMap().get("originalMail").toString();
        	String userId = getLoginuser().getId();
    		boolean equalToOldMail = webUserService.checkOldMail(originalMail, userId);
            if(equalToOldMail){
                this.ajaxCheckSuccess();
            }else{
            	this.ajaxCheckFailure();
//            	Struts2Utils.renderText("原邮箱输入错误");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
	
	@Action(value="checkNewMailAjax")
	public void checkNewMailAjax(){
        try {
        	String updateMail = this.getAjaxMap().get("updateMail").toString();
        	String userId = getLoginuser().getId();
    		boolean isUniqueMail = webUserService.checkUniqueMail(updateMail, userId);
            if(isUniqueMail){
                this.ajaxCheckSuccess();
            }else{
            	this.ajaxCheckFailure();
//            	Struts2Utils.renderText("该邮箱已存在");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
	}
	
	public WebUser getWebUser() {
		return webUser;
	}


	public void setWebUser(WebUser webUser) {
		this.webUser = webUser;
	}


	public String getOldpassword() {
		return oldpassword;
	}


	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public String getUpdateMail() {
		return updateMail;
	}


	public void setUpdateMail(String updateMail) {
		this.updateMail = updateMail;
	}
	
	public String getUpdateMailLock() {
		return updateMailLock;
	}


	public void setUpdateMailLock(String updateMailLock) {
		this.updateMailLock = updateMailLock;
	}


	public String getUpdatePhone() {
		return updatePhone;
	}


	public void setUpdatePhone(String updatePhone) {
		this.updatePhone = updatePhone;
	}


	public String getNewpassword() {
		return newpassword;
	}


	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}


	public String getOriginalMail() {
		return originalMail;
	}


	public void setOriginalMail(String originalMail) {
		this.originalMail = originalMail;
	}
	
	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public boolean isCiccAccountFlag() {
		return ciccAccountFlag;
	}


	public void setCiccAccountFlag(boolean ciccAccountFlag) {
		this.ciccAccountFlag = ciccAccountFlag;
	}

}

