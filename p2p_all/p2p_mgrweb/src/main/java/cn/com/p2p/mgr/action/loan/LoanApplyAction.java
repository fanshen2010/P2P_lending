package cn.com.p2p.mgr.action.loan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.enumpack.BuyTheWayEnum;
import cn.com.p2p.framework.enumpack.CustomerEnum;
import cn.com.p2p.framework.enumpack.HouseTypeEnum;
import cn.com.p2p.framework.enumpack.LoanInterestMannerEnum;
import cn.com.p2p.framework.enumpack.LoanTypeEnum;
import cn.com.p2p.framework.enumpack.PaymentWayEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.enumpack.SexEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.token.Token;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.framework.web.validator.ValidatorErrorParam;
import cn.com.p2p.loan.service.LoanApplyService;
import cn.com.p2p.loan.service.LoanSearchService;
import cn.com.p2p.loan.service.dto.CustomerDto;
import cn.com.p2p.loan.service.dto.LoanCustomerInfoDto;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.loan.service.dto.LoanEnterpriseInfoDto;
import cn.com.p2p.loan.service.dto.LoanPersonalBasicDto;
import cn.com.p2p.loan.service.dto.LoanPersonalJobDto;
import cn.com.p2p.loan.service.dto.LoanProjectInfoDto;
import cn.com.p2p.loan.service.dto.LoanProjectMsgDto;
import cn.com.p2p.usermangent.service.PfmTenantDepartmentManageService;
import cn.com.p2p.utils.Constants;
import freemarker.template.TemplateException;

@Namespace("/loan/apply")
@Results({
        @Result(name = "index", location = "index.ftl", type = "freemarker"),
        @Result(name = "enterprise", location = "enterprise/enterprise.ftl", type = "freemarker"),
        @Result(name = "new_enterprise", location = "enterprise/new_enterprise.ftl", type = "freemarker"),
        @Result(name = "personal", location = "personal/personal.ftl", type = "freemarker"),
        @Result(name = "new_personal", location = "personal/new_personal.ftl", type = "freemarker"),
        @Result(name = "apply_success", location = "apply_success.ftl", type = "freemarker"),
        @Result(name = "draft", location = "/loan/draft/index.htm", type = "redirect"),
        @Result(name = com.opensymphony.xwork2.Action.INPUT, location = "login/login.ftl", type = "freemarker") })
public class LoanApplyAction extends BaseAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;

    @Autowired
    private LoanSearchService loanSearchService;

    @Autowired
    private LoanApplyService loanApplyService;

    @Autowired
    private PfmTenantDepartmentManageService pfmTenantDepartmentManageService;

    /** 是否导入上次融资资料 */
    private String export;

    /** 企业编号 */
    private String loanCode;

    /** 企业全称 */
    private String loanName;

    /** 个人身份证号 */
    private String identity;

    /** 客户编号 */
    private String customerCode;

    /** 融资草稿总Dto */
    private LoanDto loanDto;

    /** 融资项目基本信息Dto */
    private LoanProjectInfoDto projectInfoDto;

    /** 融资项目其它信息Dto */
    private LoanProjectMsgDto loanProjectMsgDto;

    /** 企业基本信息Dto */
    private LoanEnterpriseInfoDto enterpriseInfoDto;

    /** 个人基本信息Dto */
    private LoanPersonalBasicDto loanPersonalBasicDto;

    /** 个人工作信息Dto */
    private LoanPersonalJobDto loanPersonalJobDto;

    /** 产品列表 */
    private LinkedMap productList;

    /**
     * 融资类型
     */
    private String loanType;

    /**
     * 融资修改区分0:添加 1:修改
     */
    private String updateDistinguish;

    private CustomerDto customer;

    /**
     * 新增融资首页
     */
    @Action(value = "index")
    @Override
    public String init() throws Exception {
        return "index";
    }

    /**
     * 企业融资
     * 
     * @return
     */
    @Action(value = "enterprise")
    public String enterprise() {
        return "enterprise";
    }

    /**
     * 个人融资
     * 
     * @return
     */
    @Action(value = "personal")
    public String personal() {
        return "personal";
    }

    /**
     * ajax查询获取客户信息
     * 
     * @throws JSONException
     * @throws IOException
     * @throws TemplateException
     */
    @Action(value = "customerMsg")
    public void customerMsg() throws JSONException, IOException,
            TemplateException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (customer != null) {
            if (StringUtils.isEmpty(customer.getCreditCode()) && StringUtils.isEmpty(customer.getCustomerName())) {
                map.put("custEnterpriseInfo", null);
                map.put("custPersonalInfo", null);
                map.put("user", null);
                map.put("loanCustomerInfoDto", null);
                // 得到渲染好的模板内容
                String result = "";
                if (StringUtils.compare(customer.getType(),
                        CustomerEnum.Personal.getCode())) {
                    result = feroFreemarkerProcessor.process(
                            Constants.PERSONALMSG_TEMPLATE, map, this);
                } else if (StringUtils.compare(customer.getType(),
                        CustomerEnum.Enterprise.getCode())) {
                    result = feroFreemarkerProcessor.process(
                            Constants.ENTERPRISEMSG_TEMPLATE, map, this);
                }
                map.clear();
                map.put("html", result);
            } else {
                LoanCustomerInfoDto loanCustomerInfoDto = loanSearchService
                        .getLoanCustomerInfo(customer);
                map.put("custEnterpriseInfo", loanCustomerInfoDto.getCustEnterpriseInfo());
                map.put("custPersonalInfo", loanCustomerInfoDto.getCustPersonalInfo());
                map.put("user", loanCustomerInfoDto.getUserInfoDto());
                map.put("loanCustomerInfoDto", loanCustomerInfoDto);
                // 得到渲染好的模板内容
                String result = "";
                if (StringUtils.compare(customer.getType(),
                        CustomerEnum.Personal.getCode())) {
                    result = feroFreemarkerProcessor.process(
                            Constants.PERSONALMSG_TEMPLATE, map, this);
                } else if (StringUtils.compare(customer.getType(),
                        CustomerEnum.Enterprise.getCode())) {
                    result = feroFreemarkerProcessor.process(
                            Constants.ENTERPRISEMSG_TEMPLATE, map, this);
                }
                map.clear();
                map.put("html", result);
            }

            // ajax返回

        } else {
            map.put("custEnterpriseInfo", null);
            map.put("custPersonalInfo", null);
            map.put("user", null);
            map.put("loanCustomerInfoDto", null);
            // 得到渲染好的模板内容
            String result = "";
            map.put("html", result);
        }
        Struts2Utils.renderJson(map);
    }

    /**
     * 融资信息页面
     * 
     * @return
     * @throws Exception
     */
    @Action(value = "new_loan")
    @Token(generator = true)
    public String supplement() throws Exception {
        String result = "";
        // 企业融资产品列表
        if (StringUtils.compare(Constants.STR_1, loanType)) {
            if ("1".equals(export)) {// 导入上次融资数据
                // 企业基本信息Dto
                if (StringUtils.compare(updateDistinguish, "1")) {
                    loanDto = loanSearchService.getLoanInfo(loanCode,
                            SearchInfoTypeEnum.LOAN,
                            SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL, SearchInfoTypeEnum.LOAN_INFO);
                    loanProjectMsgDto = loanDto.getLoanProjectMsgDto();
                } else {
                    loanDto = loanSearchService.getLoanInfo(loanCode,
                            SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL, SearchInfoTypeEnum.LOAN_INFO);
                }
                enterpriseInfoDto = loanDto.getEnterpriseInfoDto();

                // 融资项目基本信息Dto
                projectInfoDto = loanDto.getProjectInfoDto();

            } else {// 不导入上次融资数据
                // 企业基本信息Dto
                enterpriseInfoDto = new LoanEnterpriseInfoDto();
                if (StringUtils.isNotEmpty(customerCode)) {
                    enterpriseInfoDto = loanSearchService.getEnterpriseInfo(customerCode, null);
                }
                enterpriseInfoDto.setEnterpriseName(loanName);
            }

            result = "new_enterprise";
        } else if (StringUtils.compare(Constants.STR_0, loanType)) {
            if ("1".equals(export)) {
                // 导入上次融资数据
                if (StringUtils.compare(updateDistinguish, "1")) {
                    loanDto = loanSearchService.getLoanInfo(loanCode,
                            SearchInfoTypeEnum.LOAN,
                            SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL, SearchInfoTypeEnum.LOAN_INFO);
                    loanProjectMsgDto = loanDto.getLoanProjectMsgDto();
                } else {
                    loanDto = loanSearchService.getLoanInfo(loanCode,
                            SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL, SearchInfoTypeEnum.LOAN_INFO);
                }
                // 个人基本信息Dto
                loanPersonalBasicDto = loanDto.getPersonalBasicDto();

                String identity = loanPersonalBasicDto.getIdentity();
                if (StringUtils.isNotEmpty(identity)) {
                    loanPersonalBasicDto.setBirthdayDate(DateUtils.parseDate(identity.substring(6, 10) + "-"
                            + identity.substring(10, 12) + "-" + identity.substring(12, 14)));
                    // 获取性别
                    if (Integer.parseInt(identity.substring(16, 17)) % 2 == 1) {
                        loanPersonalBasicDto.setGender(SexEnum.SEX_01.getCode());
                    } else {
                        loanPersonalBasicDto.setGender(SexEnum.SEX_02.getCode());
                    }
                }
                // 个人工作信息Dto
                loanPersonalJobDto = loanDto.getPersonalJobDto();

                // 融资基本信息Dto
                projectInfoDto = loanDto.getProjectInfoDto();

            } else {// 不导入上次融资数据
                // 个人基本信息Dto
                loanPersonalBasicDto = new LoanPersonalBasicDto();

                if (StringUtils.isNotEmpty(customerCode)) {
                    loanPersonalBasicDto = loanSearchService.getPersonalInfo(customerCode, null);
                }

                // 个人工作信息Dto
                loanPersonalJobDto = new LoanPersonalJobDto();
            }
            result = "new_personal";
        }
        return result;
    }


    /**
     * 企业融资申请提交
     * 
     * @return
     * @throws Exception
     */
    @Validators(str = "loanEnterpriseCheck", result = "new_enterprise", param = "enterpriseApply")
    @Action(value = "enterpriseApply")
    @Token(check = true, result = "apply_success")
    public String enterpriseApply() throws Exception {

        loanDto = new LoanDto();
        // 企业基本信息Dto
        loanDto.setEnterpriseInfoDto(enterpriseInfoDto);
        // 融资项目基本信息Dto
        loanDto.setProjectInfoDto(projectInfoDto);
        // 融资项目其它信息Dto
        loanDto.setLoanProjectMsgDto(loanProjectMsgDto);
        // 设置融资类型，用来区分个人和企业的存储方式（0：个人，1：企业）
        loanDto.setLoanType(LoanTypeEnum.Enterprise.getCode());
        // 企业融资数据保存
        loanApplyService.apply(loanDto, getLoginuser());
        return "apply_success";
    }

    /**
     * 个人融资申请提交
     * 
     * @return
     * @throws Exception
     */
    @Validators(str = "loanPersonalCheck", result = "new_personal", param = "personalApply")
    @Action(value = "personalApply")
    @Token(check = true, result = "apply_success")
    public String personalApply() throws Exception {

    	loanDto = new LoanDto();
        // 个人基本信息Dto
        loanDto.setPersonalBasicDto(loanPersonalBasicDto);
        // 个人工作信息Dto
        loanDto.setPersonalJobDto(loanPersonalJobDto);
        // 融资项目基本信息Dto
        loanDto.setProjectInfoDto(projectInfoDto);
        // 融资项目其它信息Dto
        loanDto.setLoanProjectMsgDto(loanProjectMsgDto);
        // 设置融资类型，用来区分个人和企业的存储方式（0：个人，1：企业）
        loanDto.setLoanType(LoanTypeEnum.Personal.getCode());
        // 个人融资数据保存
        loanApplyService.apply(loanDto, getLoginuser());

        return "apply_success";
    }

    /**
     * 画面补充信息删除
     * @throws Exception
     */
    @Action(value = "delete")
    public void delete() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        String result = feroFreemarkerProcessor.process(Constants.DELETE_TEMPLATE, map, this);
        map.put("html", result);
        Struts2Utils.renderJson(map);
    }

    /**
     * 个人融资-办公场地性质-租金是否显示
     */
    @Action(value = "rent")
    public void rent() throws Exception {
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();
        String result = feroFreemarkerProcessor.process(Constants.RENT_TEMPLATE, map, this);
        map.put("html", result);
        Struts2Utils.renderJson(map);
    }

    /**
     * 数据验证出错后回调处理，
     * 
     * @param param 注解中的参数
     * @param requestMap
     */

    public void doDataValidatorFailure(String param, ValidatorErrorParam requestMap) {
    	
    }

    // ===========================================页面数据===========================

    /** 获取loanName */
    public String getLoanName() {
        return loanName;
    }

    /** 设置loanName */
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    /**
     * @return the export
     */
    public String getExport() {
        return export;
    }

    /**
     * @param export
     *            the export to set
     */
    public void setExport(String export) {
        this.export = export;
    }

    /**
     * @return the enterpriseInfoDto
     */
    public LoanEnterpriseInfoDto getEnterpriseInfoDto() {
        return enterpriseInfoDto;
    }

    /**
     * @param enterpriseInfoDto
     *            the enterpriseInfoDto to set
     */
    public void setEnterpriseInfoDto(LoanEnterpriseInfoDto enterpriseInfoDto) {
        this.enterpriseInfoDto = enterpriseInfoDto;
    }

    /**
     * @return the projectInfoDto
     */
    public LoanProjectInfoDto getProjectInfoDto() {
        return projectInfoDto;
    }

    /**
     * @param projectInfoDto
     *            the projectInfoDto to set
     */
    public void setProjectInfoDto(LoanProjectInfoDto projectInfoDto) {
        this.projectInfoDto = projectInfoDto;
    }

    /**
     * @return the loanProjectMsgDto
     */
    public LoanProjectMsgDto getLoanProjectMsgDto() {
        return loanProjectMsgDto;
    }

    /**
     * @param loanProjectMsgDto
     *            the loanProjectMsgDto to set
     */
    public void setLoanProjectMsgDto(LoanProjectMsgDto loanProjectMsgDto) {
        this.loanProjectMsgDto = loanProjectMsgDto;
    }


    /** 获取个人基本信息Dto */
    public LoanPersonalBasicDto getLoanPersonalBasicDto() {
        return loanPersonalBasicDto;
    }

    /** 设置个人基本信息Dto */
    public void setLoanPersonalBasicDto(
            LoanPersonalBasicDto loanPersonalBasicDto) {
        this.loanPersonalBasicDto = loanPersonalBasicDto;
    }

    /** 获取个人工作信息Dto */
    public LoanPersonalJobDto getLoanPersonalJobDto() {
        return loanPersonalJobDto;
    }

    /** 设置个人工作信息Dto */
    public void setLoanPersonalJobDto(LoanPersonalJobDto loanPersonalJobDto) {
        this.loanPersonalJobDto = loanPersonalJobDto;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    /**
     * @return the loanCode
     */
    public String getLoanCode() {
        return loanCode;
    }

    /**
     * @param loanCode
     *            the loanCode to set
     */
    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode;
    }

    /** 获取产品列表 */
    public LinkedMap getProductList() {
        return productList;
    }

    /** 设置产品列表 */
    public void setProductList(LinkedMap productList) {
        this.productList = productList;
    }

    /** 获取融资草稿总Dto */
    public LoanDto getLoanDto() {
        return loanDto;
    }

    /** 设置融资草稿总Dto */
    public void setLoanDto(LoanDto loanDto) {
        this.loanDto = loanDto;
    }

    /**
     * @return the loanType
     */
    public String getLoanType() {
        return loanType;
    }

    /**
     * @param loanType
     *            the loanType to set
     */
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    /**
     * @return the updateDistinguish
     */
    public String getUpdateDistinguish() {
        return updateDistinguish;
    }

    /**
     * @param updateDistinguish the updateDistinguish to set
     */
    public void setUpdateDistinguish(String updateDistinguish) {
        this.updateDistinguish = updateDistinguish;
    }

    /**
     * @return the customer
     */
    public CustomerDto getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    /** 获取客户编号 */
    public String getCustomerCode() {
        return customerCode;
    }

    /** 设置客户编号 */
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

}
