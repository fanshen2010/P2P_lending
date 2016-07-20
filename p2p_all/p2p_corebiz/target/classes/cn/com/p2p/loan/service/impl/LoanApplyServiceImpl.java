package cn.com.p2p.loan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.customer.repository.CustEnterpriseInfoRepository;
import cn.com.p2p.domain.customer.repository.CustPersonalInfoRepository;
import cn.com.p2p.domain.invest.entity.Invest;
import cn.com.p2p.domain.invest.query.InvestQuery;
import cn.com.p2p.domain.invest.repository.InvestRepository;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.LoanEnterpriseInfo;
import cn.com.p2p.domain.loan.entity.LoanInfo;
import cn.com.p2p.domain.loan.entity.LoanPersonalInfo;
import cn.com.p2p.domain.loan.query.LoanEnterpriseInfoQuery;
import cn.com.p2p.domain.loan.query.LoanInfoQuery;
import cn.com.p2p.domain.loan.query.LoanPersonalCountQuery;
import cn.com.p2p.domain.loan.query.LoanPersonalInfoQuery;
import cn.com.p2p.domain.loan.query.LoanQuery;
import cn.com.p2p.domain.loan.repository.LoanEnterpriseInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanPersonalInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.query.UploadFileQuery;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.InvestStatusEnmu;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.enumpack.LoanTypeEnum;
import cn.com.p2p.framework.enumpack.OperateStatusEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.loan.service.LoanApplyService;
import cn.com.p2p.loan.service.PaymentService;
import cn.com.p2p.loan.service.dto.LoanDto;
import cn.com.p2p.loan.service.dto.LoanEnterpriseInfoDto;
import cn.com.p2p.loan.service.dto.LoanPersonalBasicDto;
import cn.com.p2p.loan.service.dto.LoanProjectInfoDto;
import cn.com.p2p.payment.enumtype.AccountTypeEnum;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.usermangent.service.WebUserService;
import cn.com.p2p.utils.Constants;
//import cn.com.p2p.workflow.service.ApplyService;

@Service
public class LoanApplyServiceImpl implements LoanApplyService {

    /* 融资企业信息 */
    @Autowired
    private LoanEnterpriseInfoQuery loanEnterpriseInfoQuery;

    /* 融资企业信息 */
    @Autowired
    private LoanPersonalInfoQuery loanPersonalInfoQuery;

    /* 融资个人基本Service */
    @Autowired
    private LoanPersonalInfoRepository loanPersonalInfoRepository;

    /* 融资企业基本Service */
    @Autowired
    private LoanEnterpriseInfoRepository loanEnterpriseInfoRepository;

    /* 图片信息 */
    @Autowired
    private UploadFileQuery uploadFileQuery;

    /* 图片基本service */
    @Autowired
    private UploadFileRepository uploadFileRepository;

    /* 融资信息基本service */
    @Autowired
    private LoanInfoRepository loanInfoRepository;

    /* 融资service */
    @Autowired
    private LoanRepository loanRepository;

    /* 融资信息 */
    @Autowired
    private LoanQuery loanQuery;
    
    /* 投资自定义查询 */
    @Autowired
    private InvestQuery investQuery;
    
    /* 投资service */
    @Autowired
    private InvestRepository investRepository;
    
    @Autowired
    private LoanPersonalCountQuery loanPersonalCountQuery;

    /* 客户信息 */
    @Autowired
    private CustEnterpriseInfoRepository custEnterpriseInfoRepository;
    /* 客户信息 */
    @Autowired
    private CustPersonalInfoRepository custPersonalInfoRepository;
    /* 融资信息 */
    @Autowired
    private LoanInfoQuery loanInfoQuery;
    /*发短信，站内信*/
    @Autowired
    private WebUserService webUserService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private PaymentService paymentService;
    

    /**
     * <p>
     * 融资申请操作 *
     * </p>
     * .<br>
     * 
     * author：<br>
     * ===================================
     * 
     * @param loanDto 融资基本信息
     * @param uploadFileDtos 证照信息
     * @param loginUser 当前登录用户信息
     * @throws Exception
     */
    @Override
    public void apply(LoanDto loanDto,UserContext userInfo) throws Exception {

        // 融资表id
        String loanId = StringUtils.getUUID();
        // 个人融资表,企业融资表id
        String loanCustomInfoId = StringUtils.getUUID();
        // 融资其他信息表id
        String loanInfoId = StringUtils.getUUID();

        // 融资基本信息保存
        LoanProjectInfoDto projectInfoDto = loanDto.getProjectInfoDto();
        CustEnterpriseInfo customerEnterpriseInfo = new CustEnterpriseInfo();
        CustPersonalInfo customerPersonalInfo = new CustPersonalInfo();
        Loan loan = null;
        if (projectInfoDto != null ) {
            if (StringUtils.isEmpty(projectInfoDto.getId())) {
                loan = new Loan();
                // 融资编号
                String loanCode = getLoanCode();
                loan.setLoanCode(loanCode);
                loan.setId(loanId);
                loan.setApplyUserId(userInfo.getId());
            } else {
                loan = loanRepository.findOne(projectInfoDto.getId());
            }
        }
        
        // 融资类型，区分个人和企业的存储方式（0：个人，1：企业）
        String loanType = loanDto.getLoanType();

        // 融资客户信息表保存
        if (StringUtils.isNotEmpty(loanType)
                && LoanTypeEnum.Personal.getCode().equals(loanType)) {// 个人
            LoanPersonalBasicDto personalBasicDto = loanDto
                    .getPersonalBasicDto();
            // 基本信息差分
            // 个人基本json
            String personalMsg = JsonPluginsUtil.beanToJson(personalBasicDto)
                    .toString();
            JSONObject jsonObject = StringUtils.strToJson(personalMsg);
            // 融资个人信息
            LoanPersonalInfo loanPersonalInfo = loanPersonalInfoRepository
                    .findLoanPersonalInfoByLoanCode(loan.getLoanCode());
            if (loanPersonalInfo == null) {
                loanPersonalInfo = new LoanPersonalInfo();
            }
            if (jsonObject != null) {
                // 客户名称
                customerPersonalInfo.setCustomerName(jsonObject
                        .getString("name"));
                // 身份证号
                customerPersonalInfo.setIdentity(jsonObject
                        .getString("identity"));

                // 联系人手机号
                customerPersonalInfo.setCellphone(jsonObject
                        .getString("cellphone"));
                // 电子邮件
                customerPersonalInfo.setEmail(jsonObject
                        .getString("email"));
                customerPersonalInfo
                        .setCustomerCode(getCustomerCode(customerPersonalInfo
                                .getCellphone()));
                // 设置客户名称
                loan.setCustomName(customerPersonalInfo.getCustomerName());

            }
            // 融资编号
            loanPersonalInfo.setLoanCode(loan.getLoanCode());
            // 个人基本json
            loanPersonalInfo.setBasicJson(personalMsg);
            // 工作信息json
            loanPersonalInfo.setJobJson(JsonPluginsUtil.beanToJson(loanDto
                    .getPersonalJobDto()));
            // 商户ID
            loanPersonalInfo.setTenantId(userInfo.getCompanyId());
            if (StringUtils.isEmpty(loanPersonalInfo.getId())) {
                // ID
                loanPersonalInfo.setId(loanCustomInfoId);
                loanPersonalInfoRepository.insert(loanPersonalInfo);
            } else {
                loanPersonalInfoRepository.update(loanPersonalInfo);
            }

        } else if (StringUtils.isNotEmpty(loanType)
                && LoanTypeEnum.Enterprise.getCode().equals(loanType)) {// 企业

            LoanEnterpriseInfoDto enterpriseInfoDto = loanDto
                    .getEnterpriseInfoDto();
            // 企业基本json
            String enterpriseMsg = JsonPluginsUtil
                    .beanToJson(enterpriseInfoDto).toString();
            JSONObject jsonObject = StringUtils.strToJson(enterpriseMsg);
            // 融资企业信息
            LoanEnterpriseInfo loanEnterpriseInfo = loanEnterpriseInfoRepository
                    .findLoanEnterpriseInfoByLoanCode(loan.getLoanCode());
            if (loanEnterpriseInfo == null) {
                loanEnterpriseInfo = new LoanEnterpriseInfo();
            }
            // 融资编号 LOAN_CODE
            loanEnterpriseInfo.setLoanCode(loan.getLoanCode());
            if (jsonObject != null) {
                // 客户名称
                customerEnterpriseInfo.setCustomerName(jsonObject
                        .getString("enterpriseName"));
                // licenseNumbers
                customerEnterpriseInfo.setLicenseNumbers(jsonObject
                        .getString("businessIicense"));
                // 联系人姓名
                customerEnterpriseInfo.setContactName(jsonObject
                        .getString("legalRepresentative"));
                // 联系电话
                customerEnterpriseInfo.setCellphone(jsonObject
                        .getString("contactPhone"));
                // 联系邮箱
                customerEnterpriseInfo.setEmail(jsonObject.optString("contactMail"));
                customerEnterpriseInfo
                        .setCustomerCode(getCustomerCode(customerEnterpriseInfo
                                .getCellphone()));

                // 设置客户编号
                loan.setCustomCode(customerEnterpriseInfo.getId());
                // 设置客户名称
                loan.setCustomName(customerEnterpriseInfo.getCustomerName());

            }
            // 企业基本json
            loanEnterpriseInfo.setBasicJson(enterpriseMsg);
            // 商户ID
            loanEnterpriseInfo.setTenantId(userInfo.getCompanyId());
            if (StringUtils.isEmpty(loanEnterpriseInfo.getId())) {
                // ID
                loanEnterpriseInfo.setId(loanCustomInfoId);
                loanEnterpriseInfoRepository.insert(loanEnterpriseInfo);
            } else {
                loanEnterpriseInfoRepository.update(loanEnterpriseInfo);
            }

        }

        // 是否插入客户数据
        // 查询是否有客户信息
        if (StringUtils.isNotEmpty(loanType)
                && LoanTypeEnum.Personal.getCode().equals(loanType)) {// 个人
            CustPersonalInfoCriteria criteria = new CustPersonalInfoCriteria();
            criteria.setCustomerName(customerPersonalInfo.getCustomerName(),
                    Operator.equal);
            criteria.setIdentity(customerPersonalInfo.getIdentity(),
                    Operator.equal);
            List<CustPersonalInfo> lstCustomer = custPersonalInfoRepository
                    .findByCriteria(criteria);
            if (lstCustomer != null && lstCustomer.size() > 0) {// 有
                // 设置客户编号
                loan.setCustomCode(lstCustomer.get(0).getId());
            } else {// 无
                customerPersonalInfo.setId(StringUtils.getUUID());
                // 设置客户编号
                loan.setCustomCode(customerPersonalInfo.getId());
                custPersonalInfoRepository.insert(customerPersonalInfo);
                paymentService.ciccDoCustRegister(customerPersonalInfo.getId(),customerPersonalInfo.getCustomerName());
            }
        } else if (StringUtils.isNotEmpty(loanType)
                && LoanTypeEnum.Enterprise.getCode().equals(loanType)) {// 企业
            CustEnterpriseInfoCriteria criteria = new CustEnterpriseInfoCriteria();
            criteria.setCustomerName(customerEnterpriseInfo.getCustomerName(),
                    Operator.equal);
            criteria.setLicenseNumbers(
                    customerEnterpriseInfo.getLicenseNumbers(), Operator.equal);
            List<CustEnterpriseInfo> lstCustomer = custEnterpriseInfoRepository
                    .findByCriteria(criteria);
            if (lstCustomer != null && lstCustomer.size() > 0) {// 有
                // 设置客户编号
                loan.setCustomCode(lstCustomer.get(0).getId());
            } else {// 无
                customerEnterpriseInfo.setId(StringUtils.getUUID());
                // 设置客户编号
                loan.setCustomCode(customerEnterpriseInfo.getId());
                custEnterpriseInfoRepository.insert(customerEnterpriseInfo);
                paymentService.ciccDoCustRegister(customerEnterpriseInfo.getId(),customerEnterpriseInfo.getCustomerName());
            }
        }

        // 融资其他信息保存
        LoanInfo loanInfo = loanInfoRepository.findLoanInfoByLoanCode(loan
                .getLoanCode());
        if (loanInfo == null) {
            loanInfo = new LoanInfo();
        }
        // 融资编号
        loanInfo.setLoanCode(loan.getLoanCode());
        // 融资基本信息//TODO
        // loanInfo.setLoanInfo(loanDto.getLoanInfo());
        // 融资信息Json
        loanInfo.setLoanMsg(JsonPluginsUtil.beanToJson(loanDto
                .getLoanProjectMsgDto()));
        // 商户ID
        loanInfo.setTenantId(userInfo.getCompanyId());
        if (StringUtils.isEmpty(loanInfo.getId())) {
            // ID
            loanInfo.setId(loanInfoId);
            loanInfoRepository.insert(loanInfo);
        } else {
            loanInfoRepository.update(loanInfo);
        }

        // 获取项目名称
        if (projectInfoDto != null) {
            // 设置项目名称
            loan.setLoanName(projectInfoDto.getLoanName());
            // 设置融资金额
            loan.setLoanAmount(projectInfoDto.getLoanAmount());
            // 设置剩余金额
            loan.setCurrentSurplusShare(projectInfoDto.getLoanAmount());
            // 总份数
            loan.setLoanTotalShare(projectInfoDto.getLoanAmount());
            // 设置计息方式
            loan.setLoanInterestManner(projectInfoDto.getLoanInterestManner());
            // 设置利率
            loan.setLoanInterestRates(projectInfoDto.getLoanInterestRates());
            // 设置融资期限
            loan.setLoanTimeLimit(projectInfoDto.getLoanTimeLimit());
            // 设置融资期限单位
            loan.setLoanTimeLimitUnit(projectInfoDto.getLoanTimeLimitUnit());
            // 融资期限（天）
            if (LoanTimeLimitUnitEnum.Month.getCode().equals(
                    projectInfoDto.getLoanTimeLimitUnit())) {
                loan.setLoanTimeLimitDays(projectInfoDto.getLoanTimeLimit() * 30);
            } else {
                loan.setLoanTimeLimitDays(projectInfoDto.getLoanTimeLimit());
            }
            // 设置起投份数
            loan.setLoanStartShare(projectInfoDto.getLoanStartShare());
            // 设置每份单价
            loan.setLoanUnitPrice(projectInfoDto.getLoanUnitPrice());
            // 设置投资截止时间
            loan.setLoanEndTime(projectInfoDto.getLoanEndTime());
            // 设置单用户最大投资限额
            loan.setLoanMaxInvest(projectInfoDto.getLoanMaxInvest());
            // 设置平台管理费率
            loan.setPlatformRate(projectInfoDto.getPlatformRate());
            // 设置担保费率
            loan.setGuaranteeRate(projectInfoDto.getGuaranteeRate());
        }
        // 设置融资状态 //TODO
        loan.setLoanStatus(LoanStatusEnum.LOAN_STATUS_04.getCode());
        // 设置融资类型
        loan.setLoanType(loanDto.getLoanType());
        // 商户Id
        loan.setTenantId(userInfo.getCompanyId());
        // 实际融资额
        loan.setActualAmount(0);
        // 融资审核发布时间
        loan.setLoanPostTime(new Date());
        // ID
        if (StringUtils.isEmpty(projectInfoDto.getId())) {
            loan.setId(loanId);
            loanRepository.insert(loan);
            // TODO 业务类型CD暂时写死
            //applyService.apply(userInfo, projectInfoDto.getProductCode(), loanId, loan);
        } else {
            loanRepository.update(loan);
            //applyService.reapply(userInfo, loan.getId(), loan);
        }

        // 发短信，站内信
        String serviceName = settingService.findSysSettingBySettingCode("site_name").getSettingValue();
        webUserService.sendInternalBackMessage("1", loan.getLoanName(), loan.getLoanCode());
    }


    /**
     * 根据融资编号生成规则，生成融资表融资编号 yyyyMMdd+"0000" 升序
     * 
     * @return loanCode 融资编号
     */
    private String getLoanCode() {
        StringBuffer out = new StringBuffer();
        String loanCodePre = DateUtils.fullDate();
        List<String> loanCodes = loanQuery.findLoanCodeByPre(loanCodePre);
        if (loanCodes != null && loanCodes.size() > 0) {
            String suffix = loanCodes.get(0).substring(8);
            out.append(loanCodePre).append(StringUtils.getCode(suffix));
        } else {
            out.append(loanCodePre).append("0000");
        }
        return out.toString();
    }

    /**
     * 根据客户编号生成规则，生成客户编号
     * 
     * @param customer
     * @return
     */
    private String getCustomerCode(String cellphone) {
        // TODO 客户编号暂时规则 自定义为 "C"+TYPE+联系人手机号后4位
        String customerCode = Constants.STR_C
                + cellphone.substring(cellphone.length() - 4);
        return customerCode;
    }

    /**
     * <p>
     * 根据ID删除融资
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * 
     * @param pstrLoanCode 融资编号
     */
    public void doDeletetLoanById(String pstrLoanCode) {
        // 删除融资表
        loanQuery.deleteByLoanCode(pstrLoanCode);
        // 删除企业融资表
        loanEnterpriseInfoQuery.deleteByLoanCode(pstrLoanCode);
        // 删除个人融资表
        loanPersonalInfoQuery.deleteByLoanCode(pstrLoanCode);
        // 删除融资信息
        loanInfoQuery.deleteByLoanCode(pstrLoanCode);
    }
    
}
