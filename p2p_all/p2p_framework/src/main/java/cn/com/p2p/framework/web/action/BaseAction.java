
package cn.com.p2p.framework.web.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ResultPath;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ServletContextAware;

import cn.com.p2p.framework.constant.Constants;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.framework.web.validator.ValidatorErrorParam;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
//import com.opensymphony.xwork2.interceptor.ValidationWorkflowAware;


/**
 * Struts2 Action 核心基类
 * 
 * @author zhirong
 */
@SuppressWarnings("serial")
@ResultPath(value = "/${commonResultPath}")
public abstract class BaseAction extends ActionSupport implements Preparable,/*ValidationWorkflowAware,*/ ServletContextAware {

	public static final String RELOAD = "reload";

	public static final String INIT = "init";

	public static final String TASKINIT = "taskinit";

	public static final String ADD = "add";

	public static final String TASKADD = "taskadd";

	public static final String SAVE = "save";

	public static final String TASKSAVE = "tasksave";

	public static final String SITE = "site";

	public static final String MAIL = "mail";

	public static final String INSTRUCTIONS = "instructions";

	public static final String EDIT = "edit";

	public static final String TASKEDIT = "taskedit";

	public static final String UPDATE = "update";

	public static final String TASKUPDATE = "taskupdate";

	public static final String DELETE = "delete";

	public static final String TASKDELETE = "taskdelete";

	public static final String VIEW = "view";

	public static final String TASKVIEW = "taskview";

	public static final String JSON = "json";

	public static final String P2PINVESTDETAIL = "p2pinvestdetail";

	public static final String P2PPRODUCTDETAIL = "p2pproductdetail";

	public static final String INVEST = "invest";

	public static final String PAY = "pay";

	public static final String INVESTCONFIRM = "investconfirm";

	public static final String PAYMSG = "paymsg";

	public static final String PAYSUCCESS = "paysuccess";

	public static final String INVESTRECORD = "investrecord";
    //投资完成视图名
	public static final String INVESTCOMPLETED = "investCompleted";

	public static final String INVESTRECORDDETAIL = "investrecorddetail";

	public static final String CANCEL = "cancel";

	public static final String APPLYCANCEL = "applycancel";

	public static final String APPLYLIST = "applylist";

	public static final String APPLYDETAIL = "applydetail";

	public static final String BACKLIST = "backlist";

	public static final String BACKDETAIL = "backdetail";

	public static final String COLLECT = "COLLECT";

	public static final String APPLYFINANCE = "applyfinance";

	public static final String APPLYPERSONAL = "applypersonal";

	public static final String APPLYENTERPRISE = "applyenterprise";

	public static final String APPLYENTERPRISEINFO = "enterpriseinfo";

	public static final String APPLYENTERPRISECREDIT = "enterprisecredit";

	public static final String APPLYENTERPRISECERTIFIC = "enterprisecertific";

	public static final String APPLYINIT = "applyinit";

	public static final String APPLYLAST = "applylast";

	public static final String APPLYSUCCESS = "applysuccess";

	public static final String PIC = "pic";

	public static final String LENDING = "lending";

	public static final String BACK = "back";

	public static final String BACKTOORGANIZATION = "backtoorganization";

	public static final String NOPAY = "nopay";

	public static final String JURISDICTION = "jurisdiction";

	public static final String EXAMINE = "examine";

	public static final String NEWS = "news";

	@Autowired
	public Properties settings;

//	public GenericDao<T,Serializable> dao;

	public static final String ERROR_PARAM = "errorParam";

	protected Logger logger;

	private String chainMsg;

	private String chainForward;

	private String commonResultPath;
	
	// 文件上传默认属性
	protected File file;
	
	protected String fileId;
	
	protected String fileFileName;
	
	protected String fileContentType;

	
	protected String passWord;

	/**
	 * 操作成功
	 */
	protected boolean operateSuccess=false;
	public String getCommonResultPath(){

		if(commonResultPath == null){

			commonResultPath = (String) Struts2Utils.getSession().getAttribute("COMMONRESULTPATH");

			if(StringUtils.isEmpty(commonResultPath)){
				commonResultPath = settings.getProperty("commonResultPath","WEB-INF/view");
			}

		}
		return commonResultPath;
	}


	/**
	 * 前台安全中心，导航确认
	 */
	public String position = "";

	public String nav = "";

	private UserContext loginuser;

	public Date sysDate;

	/**
	 * ajax提交数据
	 */
	public String ajaxVale;

	public Map ajaxMap;

	public String successUrl;

	/**
	 * 中金操作返回错误信息
	 */
	public String payMessage;

	/**
	 * 操作流水
	 */
	public String txSN;

	/**
	 * 是否统一账户平台（1:统一平台，其它：肯定不是统一平台）
	 */
	public String isUnified;

	/**
	 * 是否为上品艺馆（1：是，其他：否）
	 */
	public String isSpyg;

	/**
	 * 是否显示后台注册按钮（1：是，其他：否）
	 */
	public String isRegistered;

	/**
	 * 电子合同功能（0：无电子合同，1：电子合同无签章，2：电子合同有签章）
	 */
	public String isContract;

	/**
	 * fileServer 参数
	 */
	public String fileServer;

	/**
	 * 是否有委托借款协议原图（1：是，其他：否）
	 */
	public String isContractOriginal;

	/**
	 * 是否有委托借款协议遮盖图（1：是，其他：否）
	 */
	public String isContractCovering;

    /**
     * 项目代号 瑞赢在线:winonline 易优资:yuz 百姓财富:bxcf 尚品易馆:rz
     */
    public String proCode ;


	public BaseAction(){

		logger = LoggerFactory.getLogger(getClass());
	}


	/**
	 * 默认执行函数
	 */
	@Override
	public String execute() throws Exception{
		getCommonResultPath();
		return init();
	}


	/**
	 * 画面初始化
	 * 
	 * @return
	 * @throws Exception
	 */
	public abstract String init() throws Exception;


	/**
	 * 当出现action error时，struts2通过回调这个方法获得调整地址
	 */
//	@Override
//	public String getInputResultName(){
//
//		return ERROR_PARAM;
//	}


	@Override
	public void prepare() throws Exception{

	}


	public String getChainMsg(){

		return chainMsg;
	}


	public void setChainMsg(String chainMsg){

		this.chainMsg = chainMsg;
	}


	public String getChainForward(){

		return chainForward;
	}


	public void setChainForward(String chainForward){

		this.chainForward = chainForward;
	}


//	/**
//	 * 返回查询条件
//	 * 
//	 * @return
//	 */
//	public Object getSearchCondationMap(){
//
//		return ServletActionContext.getRequest().getAttribute(HibernateWebUtils.SEARCH_KEY);
//	}


	/***
	 * 将搜索条件数据添加到Request请求中
	 */
	public void fillSearchParams(String prefix){

		Struts2Utils.fillSearchParams(prefix);
	}


	public String getAjaxVale(){

		return ajaxVale;
	}


	public void setAjaxVale(String ajaxVale){

		this.ajaxVale = ajaxVale;
	}


	public Map getAjaxMap() throws JSONException{

		Map<String,Object> m = new HashMap<String,Object>();
		if(ajaxVale == null){
			Enumeration<String> enumparam = ServletActionContext.getRequest().getParameterNames();
			while(enumparam.hasMoreElements()){
				String paramName = enumparam.nextElement();
				m.put(paramName,ServletActionContext.getRequest().getParameter(paramName));
			}

		} else{
			JSONObject jsonObj = new JSONObject(ajaxVale);
			String[] strArr = jsonObj.getNames(jsonObj);
			for(int i = 0;i < strArr.length;i++){
				m.put(strArr[i],jsonObj.get(strArr[i]));
			}
		}
		return m;
	}


	public void setAjaxMap(Map ajaxMap){

		this.ajaxMap = ajaxMap;
	}


	public List<String> getErrorMsgList(){

		return (List<String>) Struts2Utils.getRequest().getAttribute(Constants.COMM_ERROR_MESSAGE_KEY);
	}


	public String getErrorMsgLogin(){

		return (String) Struts2Utils.getRequest().getAttribute(Constants.COMM_ERROR_MESSAGE_LOGIN_KEY);
	}


	public void addMsgToErrorMsgList(String msg){

		List<String> tmpList = (List<String>) Struts2Utils.getRequest().getAttribute(Constants.COMM_ERROR_MESSAGE_KEY);
		if(tmpList == null){
			tmpList = new ArrayList<String>();
			tmpList.add(msg);

			ServletActionContext.getRequest().setAttribute(Constants.COMM_ERROR_MESSAGE_KEY,tmpList);
		} else{
			tmpList.add(msg);
		}
	}


	/***
	 * 获取服务器端校验错误信息
	 * 
	 * @return
	 */
	public List<String> getErrorMsg(){

		List<String> list = new ArrayList<String>();
		List<String> msgList = this.getErrorMsgList();
		StringBuilder sbfMsg = new StringBuilder();
		if(msgList != null && msgList.size() != 0){
			for(String msg : msgList){
				if(StringUtils.isEmpty(msg)){
					continue;
				}
				String msgInfo = msg.replaceAll("^(?:[^:]+:)(.+)$","$1");
				list.add(msgInfo);
			}
		}
		return list;
	}


	public UserContext getLoginuser(){

		return loginuser;
	}


	public void setLoginuser(UserContext loginuser){

		this.loginuser = loginuser;
	}


	public String getIsUnified(){

		return settings.getProperty("unified_platform_setting","0");
	}


	public String getIsSpyg(){

		return settings.getProperty("platform_suggestions_setting","0");
	}


	public String getIsRegistered(){

		return settings.getProperty("registered_setting","0");
	}


	public String getIsContract(){

		return settings.getProperty("contract_setting","0");
	}


	public String getFileServer(){

		return settings.getProperty("fileServer","/uploadfile") + "/";
	}


	public String getIsContractOriginal(){

		return settings.getProperty("contract_original_setting","0");
	}


	public String getIsContractCovering(){

		return settings.getProperty("contract_covering_setting","0");
	}

    public String getProCode() {
        return settings.getProperty("pro_code","");
    }

//    /**
//	 * 版本验证出错后回调处理，
//	 * 
//	 * @param param 注解中的参数
//	 */
//	@Override
//	public void doOptimisticLockingFailure(String param){
//
//	}
//
//
//	/**
//	 * 违反唯一性约束后回调处理，
//	 * 
//	 * @param param 注解中的参数
//	 */
//	@Override
//	public void doUniqueError(String param){
//
//	}


	/**
	 * 数据验证出错后回调处理，
	 * 
	 * @param param 注解中的参数
	 * @param requestMap
	 */

	public void doDataValidatorFailure(String param,ValidatorErrorParam requestMap){

	}


	@Override
	public void setServletContext(ServletContext servletContext){

		loginuser = null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication != null){

			if(authentication.getPrincipal() != null && authentication.getPrincipal() instanceof UserContext){
				loginuser = (UserContext) authentication.getPrincipal();
			}
		}

	}
	
	
	public boolean isAppRequest(){
		HttpServletRequest request=cn.com.p2p.framework.web.util.Struts2Utils.getRequest();
		
      String userAgent = request.getHeader("user-agent");
      if((userAgent.contains("Android")||userAgent.contains("iPhone")) &&userAgent.contains("Mobile")){
    		  return true;
      }
      return false;
	}

	protected void ajaxCheckSuccess(){
		renderTrueFalse(true);
	}
	
	protected void ajaxCheckFailure(){
		renderTrueFalse(false);
	}
	
	protected void delSuccess(){
		renderTrueFalse(true);
	}
	
	protected void delFailure(){
		renderTrueFalse(false);
	}
	
	private void renderTrueFalse(Boolean flag){
		Struts2Utils.renderText(flag.toString());
	}

	public String getIpAddr(){

		HttpServletRequest request = ServletActionContext.getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
		return ip;
	}


	public String getPosition(){

		return position;
	}


	public void setPosition(String position){

		this.position = position;
	}


	public String getNav(){

		return nav;
	}


	public void setNav(String nav){

		this.nav = nav;
	}


	public Date getSysDate(){

		return DateUtils.getCurrentDateTime();
	}


	public void setSysDate(Date sysDate){

		this.sysDate = sysDate;
	}


	public String getSuccessUrl(){

		return successUrl;
	}


	public void setSuccessUrl(String successUrl){

		this.successUrl = successUrl;
	}


	public String getPayMessage(){

		return payMessage;
	}


	public void setPayMessage(String payMessage){

		this.payMessage = payMessage;
	}


	/**
	 * @return the operateSuccess
	 */
	public boolean isOperateSuccess() {
		return operateSuccess;
	}


	/**
	 * @param operateSuccess the operateSuccess to set
	 */
	public void setOperateSuccess(boolean operateSuccess) {
		this.operateSuccess = operateSuccess;
	}


	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public String getFileId() {
		return fileId;
	}


	public void setFileId(String fileId) {
		this.fileId = fileId;
	}


	public String getFileFileName() {
		return fileFileName;
	}


	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}


	public String getFileContentType() {
		return fileContentType;
	}


	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}


	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
