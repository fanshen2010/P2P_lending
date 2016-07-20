package cn.com.p2p.customer.service.impl;

import cn.com.p2p.customer.service.EnterpriseService;
import cn.com.p2p.customer.service.StatisticalService;
import cn.com.p2p.customer.service.dto.CustomerDto;
import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.repository.CustEnterpriseInfoRepository;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 企业融资客户信息 接口实现
 *
 * @author 
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {
    @Autowired
    private CustEnterpriseInfoRepository custEnterpriseInfoRepository;
    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private WebUserRepository webUserRepository;
    @Autowired
    private StatisticalService statisticalService;

    @Override
    public List<CustomerDto> getList(CustEnterpriseInfoCriteria criteria) {
        List<CustomerDto> result = new ArrayList<CustomerDto>();
        List<CustEnterpriseInfo> list = custEnterpriseInfoRepository.findByCriteria(criteria);
        // 临时保存客户编号
        String code;
        for (CustEnterpriseInfo custEnterpriseInfo : list) {
            CustomerDto customerDto = new CustomerDto();
            code = custEnterpriseInfo.getId();
            // 将保存的关联前台用户Id转换为用户login
            WebUser webUser = webUserRepository.findOne(custEnterpriseInfo.getUserId());
            if (!StringUtils.objectNull(webUser)) {
                custEnterpriseInfo.setUserId(webUser.getLogin());
            } else {
                custEnterpriseInfo.setUserId(null);
            }
            // 企业客户信息
            customerDto.setEnterpriseDto(custEnterpriseInfo);
            // 企业客户融资统计信息
            customerDto.setStatisticalDto(statisticalService.getLoanCount(code));
            result.add(customerDto);
        }
        return result;
    }

    @Override
    public CustomerDto findOne(String id) {
        CustomerDto customerDto = new CustomerDto();
        CustEnterpriseInfo custEnterpriseInfo = custEnterpriseInfoRepository.findOne(id);
        if (custEnterpriseInfo == null) {
            return new CustomerDto();
        }
        String code = custEnterpriseInfo.getId();
        /* 转换UserId为Login 2015-10-15 14:17  BEGIN */
        WebUser webUser = null;
        if (custEnterpriseInfo != null && StringUtils.isNotEmpty(custEnterpriseInfo.getUserId())) {
            webUser = webUserRepository.findOne(custEnterpriseInfo.getUserId());
        }
        if (webUser != null && StringUtils.isNotEmpty(webUser.getLogin())) {
            custEnterpriseInfo.setUserId(webUser.getLogin());
        }
        /* 转换UserId为Login 2015-10-15 14:17  END */
        // 企业客户信息
        customerDto.setEnterpriseDto(custEnterpriseInfo);
        // 企业客户融资统计信息
        customerDto.setStatisticalDto(statisticalService.getLoanCount(code));
        return customerDto;
    }

    @Override
    public boolean update(CustEnterpriseInfo custEnterpriseInfo) {
        // 保存的关联的用户名须转成userId保存
        String login = custEnterpriseInfo.getUserId();
        WebUser webUser = webUserQuery.findByLogin(login);
        if (StringUtils.isNotEmpty(login) && !StringUtils.objectNull(webUser)) {
            custEnterpriseInfo.setUserId(webUser.getId());
        } else {
            custEnterpriseInfo.setUserId(null);
        }
        int i = custEnterpriseInfoRepository.update(custEnterpriseInfo);
        return i > 0;
    }

    @Override
    public boolean save(CustEnterpriseInfo custEnterpriseInfo) {
        // 保存的关联的用户名须转成userId保存
        String login = custEnterpriseInfo.getUserId();
        WebUser webUser = webUserQuery.findByLogin(login);
        if (StringUtils.isNotEmpty(login) && !StringUtils.objectNull(webUser)) {
            custEnterpriseInfo.setUserId(webUser.getId());
        } else {
            custEnterpriseInfo.setUserId(null);
        }
        // 设置id
        custEnterpriseInfo.setId(StringUtils.getUUID());
        // 设置code
        custEnterpriseInfo.setCustomerCode(StringUtils.getUUID());
        int i = custEnterpriseInfoRepository.insert(custEnterpriseInfo);
        return i > 0;
    }

    /**
     * 企业全称重复校验
     * @author 
     * 
     * @param customerName 企业全称
     * @param Id 当前ID
     * @return 校验结果 存在返回true
     */
    @Override
    public boolean customerNameCheck(String customerName, String Id) {
        boolean checkFlag = false;

        CustEnterpriseInfoCriteria criteria = new CustEnterpriseInfoCriteria();
        criteria.setCustomerName(customerName, Operator.equal);

        List<CustEnterpriseInfo> custEnterpriseInfoList = custEnterpriseInfoRepository.findByCriteria(criteria);

        if (custEnterpriseInfoList != null && !custEnterpriseInfoList.isEmpty()) {
            for (CustEnterpriseInfo custEnterpriseInfo : custEnterpriseInfoList) {
                // 由于存在修改情况，所以过滤掉当前ID
                if (!StringUtils.equals(custEnterpriseInfo.getId(), Id)) {
                    checkFlag = true;
                    break;
                }
            }
        }

        return checkFlag;
    }
}
