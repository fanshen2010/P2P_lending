package cn.com.p2p.customer.service.impl;

import cn.com.p2p.customer.service.PersonalService;
import cn.com.p2p.customer.service.StatisticalService;
import cn.com.p2p.customer.service.dto.CustomerDto;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.customer.repository.CustPersonalInfoRepository;
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
 * 个人融资客户信息 接口实现
 *
 * @author 
 */
@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private CustPersonalInfoRepository custPersonalInfoRepository;
    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private WebUserRepository webUserRepository;

    @Autowired
    private StatisticalService statisticalService;

    @Override
    public List<CustomerDto> getList(CustPersonalInfoCriteria criteria) {
        List<CustomerDto> result = new ArrayList<CustomerDto>();
        List<CustPersonalInfo> list = custPersonalInfoRepository.findByCriteria(criteria);
        // 临时保存客户编号
        String code;
        for (CustPersonalInfo custPersonalInfo : list) {
            CustomerDto customerDto = new CustomerDto();
            code = custPersonalInfo.getId();
            // 将保存的关联前台用户Id转换为用户login
            WebUser webUser = webUserRepository.findOne(custPersonalInfo.getUserId());
            if (!StringUtils.objectNull(webUser)) {
                custPersonalInfo.setUserId(webUser.getLogin());
            } else {
                custPersonalInfo.setUserId(null);
            }
            // 个人客户信息
            customerDto.setPersonalDto(custPersonalInfo);
            // 个人客户融资统计信息
            customerDto.setStatisticalDto(statisticalService.getLoanCount(code));
            result.add(customerDto);
        }
        return result;
    }

    @Override
    public CustomerDto findOne(String id) {
        CustomerDto customerDto = new CustomerDto();
        CustPersonalInfo custPersonalInfo = custPersonalInfoRepository.findOne(id);
        if (custPersonalInfo == null) {
            return new CustomerDto();
        }
        // 客户id
        String code = custPersonalInfo.getId();
        /* 转换UserId为Login 2015-10-15 14:17  BEGIN */
        WebUser webUser = null;
        if (custPersonalInfo != null && StringUtils.isNotEmpty(custPersonalInfo.getUserId())) {
            webUser = webUserRepository.findOne(custPersonalInfo.getUserId());
        }
        if (webUser != null && StringUtils.isNotEmpty(webUser.getLogin())) {
            custPersonalInfo.setUserId(webUser.getLogin());
        }
        /* 转换UserId为Login 2015-10-15 14:17  END */
        // 个人客户信息
        customerDto.setPersonalDto(custPersonalInfo);
        // 个人客户融资统计信息
        customerDto.setStatisticalDto(statisticalService.getLoanCount(code));
        return customerDto;
    }

    @Override
    public boolean update(CustPersonalInfo custPersonalInfo) {
        // 保存的关联的用户名须转成userId保存
        String login = custPersonalInfo.getUserId();
        WebUser webUser = webUserQuery.findByLogin(login);
        if (StringUtils.isNotEmpty(login) && !StringUtils.objectNull(webUser)) {
            custPersonalInfo.setUserId(webUser.getId());
        } else {
            custPersonalInfo.setUserId(null);
        }
        int i = custPersonalInfoRepository.update(custPersonalInfo);
        return i > 0;
    }

    @Override
    public boolean save(CustPersonalInfo custPersonalInfo) {
        // 保存的关联的用户名须转成userId保存
        String login = custPersonalInfo.getUserId();
        WebUser webUser = webUserQuery.findByLogin(login);
        if (StringUtils.isNotEmpty(login) && !StringUtils.objectNull(webUser)) {
            custPersonalInfo.setUserId(webUser.getId());
        } else {
            custPersonalInfo.setUserId(null);
        }
        // 设置id
        custPersonalInfo.setId(StringUtils.getUUID());
        // 设置code
        custPersonalInfo.setCustomerCode(StringUtils.getUUID());

        // 身份证重复校验
        if (checkIdentity(custPersonalInfo.getIdentity())) {
            return false;
        }

        int i = custPersonalInfoRepository.insert(custPersonalInfo);
        return i > 0;
    }

    @Override
    public boolean checkIdentity(String identity) {
        // 默认不存在
        boolean isExsit = false;
        // 设置查询条件
        CustPersonalInfoCriteria criteria = new CustPersonalInfoCriteria();
        criteria.setIdentity(identity, Operator.equal);
        List<CustPersonalInfo> custPersonalInfo = custPersonalInfoRepository.findByCriteria(criteria);
        // 如果查询到的实体集合包含元素，则通过校验
        if (custPersonalInfo != null && !custPersonalInfo.isEmpty()) {
            isExsit = true;
        }
        return isExsit;
    }
}
