package cn.com.p2p.loan.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.map.LinkedMap;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.customer.repository.CustEnterpriseInfoRepository;
import cn.com.p2p.domain.customer.repository.CustPersonalInfoRepository;
import cn.com.p2p.domain.invest.criteria.InvestDetailCriteria;
import cn.com.p2p.domain.invest.dto.CustomInvestDetail;
import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.criteria.LoanCriteria;
import cn.com.p2p.domain.loan.criteria.RepayDetailCriteria;
import cn.com.p2p.domain.loan.dto.LoaneeLoanRedordDto;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.domain.loan.entity.LoanEnterpriseInfo;
import cn.com.p2p.domain.loan.entity.LoanInfo;
import cn.com.p2p.domain.loan.entity.LoanPersonalInfo;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.loan.query.LoanInfoByCriteriaQuery;
import cn.com.p2p.domain.loan.query.LoanPersonalCountQuery;
import cn.com.p2p.domain.loan.query.LoanQuery;
import cn.com.p2p.domain.loan.repository.LoanEnterpriseInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanPersonalInfoRepository;
import cn.com.p2p.domain.loan.repository.LoanRepository;
import cn.com.p2p.domain.loan.repository.RepayDetailRepository;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.query.UploadFileQuery;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.domain.user.criteria.PfmTenantDepartmentCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.CustomerEnum;
import cn.com.p2p.framework.enumpack.DepartmentTypeEnum;
import cn.com.p2p.framework.enumpack.InvestDetailStatusEnum;
import cn.com.p2p.framework.enumpack.LoanTypeEnum;
import cn.com.p2p.framework.enumpack.SearchInfoTypeEnum;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.invest.service.InvestSearchService;
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

/**
 * 融资信息查询
 * 
 * @author FERO-015
 *
 */
@Service
public class LoanSearchServiceImpl implements LoanSearchService {
    /* 融资企业信息 */
    @Autowired
    private LoanEnterpriseInfoRepository loanEnterpriseInfoRepository;

    /* 图片信息 */
    @Autowired
    private UploadFileQuery uploadFileQuery;
    @Autowired
    private UploadFileRepository uploadFileRepository;

    /* 融资 */
    @Autowired
    private LoanRepository loanRepository;

    /* 融资个人信息 */
    @Autowired
    private LoanPersonalInfoRepository loanPersonalInfoRepository;

    /* 融资信息 */
    @Autowired
    private LoanInfoRepository loanInfoRepository;

    /* 根据融资状态查询接口 */
    @Autowired
    private LoanInfoByCriteriaQuery loanInfoByCriteriaQuery;

    /* 融资信息 */
    @Autowired
    private LoanQuery loanQuery;

    /* 融资个人统计信息 */
    @Autowired
    LoanPersonalCountQuery loanPersonalCountQuery;

    /* 客户信息 */
    @Autowired
    CustEnterpriseInfoRepository custEnterpriseInfoRepository;
    /* 客户信息 */
    @Autowired
    CustPersonalInfoRepository custPersonalInfoRepository;
    /* 投资表 */
    @Autowired
    private InvestSearchService investSearchService;

    /* 融资还款明细接口 */
    @Autowired
    private RepayDetailRepository repayDetailRepository;

    /* 组织机构 */
    @Autowired
    private PfmTenantDepartmentManageService pfmTenantDepartmentManageService;

    /** 平台用户 */
    @Autowired
    private WebUserRepository webUserRepository;

    /**
     * <p>
     * 指定融资的所有信息查询
     * </p>
     * .<br>
     * 
     * author：<br>
     * ===================================
     * 
     * @param pstrLoanCode融资编号
     * @param pSearchInfoType
     *            查询信息区分
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws JSONException
     */
    public LoanDto getLoanInfo(String pstrLoanCode,
            SearchInfoTypeEnum... pSearchInfoType)
            throws IllegalAccessException, InvocationTargetException,
            JSONException {
        LoanDto outputDto = new LoanDto();
        List<SearchInfoTypeEnum> lstSearchInfoType = new ArrayList<SearchInfoTypeEnum>();
        if (pSearchInfoType != null) {
            for (SearchInfoTypeEnum param : pSearchInfoType) {
                lstSearchInfoType.add(param);
            }
        }

        Loan loan = null;

        // 企业个人信息查询的时候
        if (lstSearchInfoType
                .contains(SearchInfoTypeEnum.ENTERPRISE_OR_PERSONAL)) {
            // 指定的融资项目信息查询
            loan = loanRepository.findLoanByLoanCode(pstrLoanCode);
            // 指定的融资不存在的时候
            if (loan == null) {
                return null;
            }
            String strLoanType = loan.getLoanType();
            // 企业
            if (LoanTypeEnum.Enterprise.getCode().equals(strLoanType)) {
                outputDto = this.getLoanEnterpriseInfo(pstrLoanCode, loan.getCustomCode());
            } else if (LoanTypeEnum.Personal.getCode().equals(strLoanType)) {
                // 个人
                outputDto = this.getLoanPersonalInfo(pstrLoanCode, loan.getCustomCode());
            }
            outputDto.setCustomCode(loan.getCustomCode());
            outputDto.setCustomName(loan.getCustomName());
        }
        // 融资项目基本信息
        if (lstSearchInfoType.contains(SearchInfoTypeEnum.LOAN)
                || lstSearchInfoType
                        .contains(SearchInfoTypeEnum.COMMON_TAB_ALL)
                || lstSearchInfoType
                        .contains(SearchInfoTypeEnum.REPAYMENT_INFO)) {
            LoanProjectInfoDto projectInfoDto = new LoanProjectInfoDto();
            if (loan == null) {
                loan = loanRepository.findLoanByLoanCode(pstrLoanCode);
            }
            BeanUtils.copyProperties(projectInfoDto, loan);
            outputDto.setId(loan.getId());
            outputDto.setProjectInfoDto(projectInfoDto);
        }

        if (lstSearchInfoType.contains(SearchInfoTypeEnum.LOAN_INFO)
                || lstSearchInfoType
                        .contains(SearchInfoTypeEnum.COMMON_TAB_ALL)) {
            // 项目信息
            LoanInfo loanInfo = loanInfoRepository
                    .findLoanInfoByLoanCode(pstrLoanCode);
            if (loanInfo != null) {
                LoanProjectMsgDto loanProjectMsgDto = JsonPluginsUtil
                        .jsonToBean(loanInfo.getLoanMsg(),
                                LoanProjectMsgDto.class);
                outputDto.setLoanProjectMsgDto(loanProjectMsgDto);

            }
        }
        // 投资信息
        if (lstSearchInfoType.contains(SearchInfoTypeEnum.INVEST)
                || lstSearchInfoType
                        .contains(SearchInfoTypeEnum.COMMON_TAB_ALL)) {
            outputDto.setInvestInformations(investSearchService
                    .getInvestInformation(pstrLoanCode));
        }
        // 还款记录
        if (lstSearchInfoType.contains(SearchInfoTypeEnum.REPAYMENT_RECORD)
                || lstSearchInfoType
                        .contains(SearchInfoTypeEnum.COMMON_TAB_ALL)) {
            RepayDetailCriteria repay = new RepayDetailCriteria();
            repay.setLoanCode(pstrLoanCode, Operator.equal);
            repay.setSortFields(cn.com.p2p.domain.loan.criteria.RepayDetailCriteria.OrderField.num, SortType.ASC);
            List<RepayDetail> repayDetailList = repayDetailRepository
                    .findByCriteria(repay);
            // 还款记录
            outputDto.setRepayDetailList(repayDetailList);
            if (repayDetailList != null && loan != null) {
                for (RepayDetail repayDetail : repayDetailList) {
                    if (repayDetail.getNum().equals(
                            loan.getReceivableRepayNumber())) {
                        // 当期还款信息
                        outputDto.setPresentRepayDetail(repayDetail);
                        break;
                    }
                }
            }
        }
        return outputDto;
    }

    /**
     * 融资方信息查询
     * author：<br>
     * ===================================
     * 
     * @param pCustomerCriteria
     *            客户信息查询条件
     * @return 融资方信息
     */
    public LoanCustomerInfoDto getLoanCustomerInfo(CustomerDto customerDto) {
        LoanCustomerInfoDto outputDto = new LoanCustomerInfoDto();
        // 平台账户信息 TODO

        // 客户信息查询
        if (StringUtils.compare(customerDto.getType(),
                CustomerEnum.Personal.getCode())) {
            CustPersonalInfoCriteria criteria = new CustPersonalInfoCriteria();
            criteria.setIdentity(customerDto.getCreditCode(), Operator.equal);
            List<CustPersonalInfo> lstCustomer = custPersonalInfoRepository
                    .findByCriteria(criteria);
            if (lstCustomer != null && lstCustomer.size() > 0) {// 有
                CustPersonalInfo customer = lstCustomer.get(0);
                outputDto.setCustPersonalInfo(customer);
                outputDto.setCustomerCode(customer.getId());
                if (StringUtils.isNotEmpty(customer.getUserId())) {
                    WebUser webUser = webUserRepository.findOne(customer.getUserId());
                    outputDto.setUserInfoDto(webUser);
                }
            }
        } else if (StringUtils.compare(customerDto.getType(),
                CustomerEnum.Enterprise.getCode())) {
            CustEnterpriseInfoCriteria criteria = new CustEnterpriseInfoCriteria();
            criteria.setCustomerName(customerDto.getCustomerName(),
                    Operator.equal);
            List<CustEnterpriseInfo> lstCustomer = custEnterpriseInfoRepository
                    .findByCriteria(criteria);
            if (lstCustomer != null && lstCustomer.size() > 0) {// 有
                CustEnterpriseInfo customer = lstCustomer.get(0);
                outputDto.setCustEnterpriseInfo(customer);
                outputDto.setCustomerCode(customer.getId());
                if (StringUtils.isNotEmpty(customer.getUserId())) {
                    WebUser webUser = webUserRepository.findOne(customer.getUserId());
                    outputDto.setUserInfoDto(webUser);
                }
            }
        }
        if (StringUtils.isNotEmpty(outputDto.getCustomerCode())) {
            // 客户的融资信息查询
            List<String> listLoanCode = loanQuery
                    .findLoanCodeByCustomerCode(outputDto.getCustomerCode());
            if (listLoanCode != null && !listLoanCode.isEmpty()) {
                // 融资编号
                outputDto.setLoanCode(listLoanCode.get(0));
                // 融资次数
                outputDto.setLoanCount(listLoanCode.size());
            }
        }
        return outputDto;
    }

    /**
     * 根据多参数分页查询融资信息（ 融资状态可以是多个）
     * 
     * author：
     * 
     * @param loanParamete 查询条件参数
     * @return List<Loan>融资实体
     */
    public List<Loan> getPageLoanInfoByCriteria(LoanCommSelCriteria loanParameter) {
        // 调用数据访问层接口
        return loanInfoByCriteriaQuery.findPageLoanList(loanParameter);
    }

    /**
     * 根据多参数查询融资信息（ 融资状态可以是多个）
     * 
     * author：
     * 
     * @param loanParamete 查询条件参数
     * @return List<Loan>融资实体
     */
    @Override
    public List<Loan> getLoanInfoByCriteria(LoanCommSelCriteria loanParameter) {
        // 调用数据访问层接口
        return loanInfoByCriteriaQuery.findLoanList(loanParameter);
    }

    /**
     * <p>
     * 财务处理列表信息查询
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * @param loanParameter 查询条件参数
     * @return 财务处理列表
     */
    public List<Loan> getFinancialApprovalInfo(LoanCommSelCriteria loanParameter) {
        return loanInfoByCriteriaQuery.findPageFinancialApproval(loanParameter);
    }

    /**
     * 根据多参数查询融资件数（ 融资状态可以是多个）
     * 
     * author：
     * 
     * @param LoanCommSelCriteria
     *            loanParamete 查询条件参数
     * @return 融资件数
     */
    public int getLoanCountByCriteria(LoanCommSelCriteria loanParameter) {
        // 调用数据访问层接口
        return loanQuery.findLoanCountByCriteria(loanParameter);
    }



    /**
     * <p>
     * 企业融资信息查询
     * </p>
     * .<br>
     * 
     * author：<br>
     * ===================================
     * 
     * @param pstrLoanCode
     *            融资编号
     * @param pstrCustomCode 客户编号
     * 
     * @return 企业融资信息
     * @throws JSONException
     *             错误
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private LoanDto getLoanEnterpriseInfo(String pstrLoanCode, String pstrCustomCode)
            throws JSONException, IllegalAccessException,
            InvocationTargetException {
        // 融资信息
        LoanDto outputInfo = new LoanDto();
        // 企业融资信息查询
        LoanEnterpriseInfo loanEnterpriseInfo = loanEnterpriseInfoRepository
                .findLoanEnterpriseInfoByLoanCode(pstrLoanCode);
        // 有企业融资信息
        if (loanEnterpriseInfo != null) {
            // 企业基本json
            LoanEnterpriseInfoDto loanEnterpriseInfoDto = JsonPluginsUtil
                    .jsonToBean(loanEnterpriseInfo.getBasicJson(),
                            LoanEnterpriseInfoDto.class);
            outputInfo.setEnterpriseInfoDto(loanEnterpriseInfoDto);
        }
        // 获取企业客户信息
        outputInfo.setEnterpriseInfoDto(this.getEnterpriseInfo(pstrCustomCode, outputInfo.getEnterpriseInfoDto()));
        return outputInfo;
    }
    
    /**
     * <p>获取企业客户信息</p>.<br>
     * author：<br>
     *===================================
     * @param pstrCustomCode 客户编号
     * @param pLoanEnterpriseInfoDto 企业信息
     * @return 企业信息
     */
    public LoanEnterpriseInfoDto getEnterpriseInfo(String pstrCustomCode , LoanEnterpriseInfoDto pLoanEnterpriseInfoDto){
        LoanEnterpriseInfoDto loanEnterpriseInfoDto = pLoanEnterpriseInfoDto;
        CustEnterpriseInfo custEnterpriseInfo = custEnterpriseInfoRepository.findOne(pstrCustomCode);
        if (custEnterpriseInfo != null) {
            if (loanEnterpriseInfoDto == null) {
                loanEnterpriseInfoDto = new LoanEnterpriseInfoDto();
            }
            // 企业全称
            loanEnterpriseInfoDto.setEnterpriseName(custEnterpriseInfo.getCustomerName());
            // 营业执照号
            loanEnterpriseInfoDto.setBusinessIicense(custEnterpriseInfo.getLicenseNumbers());
            // 联系人姓名
            loanEnterpriseInfoDto.setLegalRepresentative(custEnterpriseInfo.getContactName());
            // 联系电话
            loanEnterpriseInfoDto.setContactPhone(custEnterpriseInfo.getCellphone());
            // 联系邮箱
            loanEnterpriseInfoDto.setContactMail(custEnterpriseInfo.getEmail());
        }
        return loanEnterpriseInfoDto;
    }

    /**
     * <p>
     * 个人融资信息查询
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * 
     * @param loanCode
     *            融资编号
     * @return 个人融资信息
     * @throws JSONException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private LoanDto getLoanPersonalInfo(String loanCode, String pstrCustomCode) throws JSONException,
            IllegalAccessException, InvocationTargetException {
        // 根据融资Code查出个人融资信息
        LoanPersonalInfo loanPersonalInfo = loanPersonalInfoRepository
                .findLoanPersonalInfoByLoanCode(loanCode);
        LoanDto loanDto = new LoanDto();

        if (loanPersonalInfo != null) {
            // 个人基本信息设定
            LoanPersonalBasicDto personalBasicDto = JsonPluginsUtil
                    .jsonToBean(loanPersonalInfo.getBasicJson(),
                            LoanPersonalBasicDto.class);
            loanDto.setPersonalBasicDto(personalBasicDto);

            // 个人工作信息设定
            LoanPersonalJobDto personalJobDto = JsonPluginsUtil.jsonToBean(
                    loanPersonalInfo.getJobJson(), LoanPersonalJobDto.class);
            loanDto.setPersonalJobDto(personalJobDto);
        }
        loanDto.setPersonalBasicDto(this.getPersonalInfo(pstrCustomCode, loanDto.getPersonalBasicDto()));
        return loanDto;
    }
    
    
    /**
     * <p>获取个人客户信息</p>.<br>
     * author：<br>
     *===================================
     * @param pstrCustomCode 客户编号
     * @param LoanPersonalBasicDto 个人信息
     * @return 个人信息
     */
    public LoanPersonalBasicDto getPersonalInfo(String pstrCustomCode , LoanPersonalBasicDto pPersonalBasicDto){
        LoanPersonalBasicDto personalBasicDto = pPersonalBasicDto;
        CustPersonalInfo custPersonalInfo = custPersonalInfoRepository.findOne(pstrCustomCode);
        if (custPersonalInfo != null) {
            if (personalBasicDto == null) {
                personalBasicDto = new LoanPersonalBasicDto();
            }
            // 客户名称
            personalBasicDto.setName(custPersonalInfo.getCustomerName());
            // 身份证号
            personalBasicDto.setIdentity(custPersonalInfo.getIdentity());
            // 联系电话
            personalBasicDto.setCellphone(custPersonalInfo.getCellphone());
            // 电子邮件
            personalBasicDto.setEmail(custPersonalInfo.getEmail());
        }
        
        return personalBasicDto;
    }



    /**
     * 根据多参数查询融资信息（ 融资状态可以是多个）
     * 
     * @param LoanCommSelCriteria
     *            loanParamete 查询条件参数
     * @return List<Loan>融资实体
     */
    @Override
    public List<Loan> getPageLoanInfoByCriteria(LoanCriteria criteria) {

        return loanRepository.findPageByCriteria(criteria);
    }

    /**
     * 前台--项目预告列表
     * 
     * @param loanParameter
     *            查询条件参数
     * @return 项目预告列表
     */
    @Override
    public List<Loan> getLoanNoticeList(LoanCommSelCriteria loanParameter) {
        return loanInfoByCriteriaQuery.findInvestNoticeList(loanParameter);

    }

    /**
     * 前台--项目列表(分页)
     * 
     * @param loanParameter
     *            查询条件参数
     * @return 项目列表
     */
    @Override
    public List<Loan> getPageLoanObjectList(LoanCommSelCriteria loanParameter) {
        return loanInfoByCriteriaQuery.findPageInvestObjectList(loanParameter);
    }

    /**
     * 前台--项目列表
     * 
     * @param loanParameter
     *            查询条件参数
     * @return 项目列表
     */
    @Override
    public List<Loan> getLoanObjectList(LoanCommSelCriteria loanParameter) {
        return loanInfoByCriteriaQuery.findInvestObjectList(loanParameter);
    }

    /**
     * 根据融资客户编号查询当前融资人的融资记录信息
     * 
     * @param customCode
     *            客户编号
     * @return
     */
    @Override
    public LoaneeLoanRedordDto getLoaneeLoanRedordDtoByCustomCode(
            String customCode) {
        LoaneeLoanRedordDto loaneeLoanRedordDto = loanQuery.findLoaneeLoanRedordDtoByCustomCode(customCode);

        // 获取当前融资人融资记录
        List<Loan> loans = loanQuery.findLoansByCustomerCode(customCode);
        loaneeLoanRedordDto.setLoans(loans);
        return loaneeLoanRedordDto;
    }

    /**
     * <p>
     * 担保公司类型转换
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * @param pfmTenantDepartment 担保公司信息
     * @return 转换后的担保公司信息
     */
    public LinkedMap getGuaranteeCorporationInfo() {
        PfmTenantDepartmentCriteria criteria = new PfmTenantDepartmentCriteria();
        criteria.setVilidFlag(Constants.VALID_FLAG_VALID, Operator.equal);
        criteria.setDepartmentType(DepartmentTypeEnum.DEPARTMENT_TYPE_3.getCode(), Operator.equal);
        // 获取担保公司列表
        List<PfmTenantDepartment> pfmTenantDepartment = pfmTenantDepartmentManageService.findDepartmentAll(criteria);
        // 将担保公司列表转换为 LinkedMap 类型
        if (pfmTenantDepartment != null && pfmTenantDepartment.size() > 0) {
            LinkedMap map = new LinkedMap();
            for (PfmTenantDepartment p : pfmTenantDepartment) {
                map.put(p.getDepartmentCd(), p.getDepartmentName());
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * 根据融资code查询
     * @author 
     * 
     * @param loanCode 融资code
     * @return Loan 融资实体
     */
    @Override
    public Loan getLoanByCode(String loanCode) {

        return loanRepository.findLoanByLoanCode(loanCode);
    }

}
