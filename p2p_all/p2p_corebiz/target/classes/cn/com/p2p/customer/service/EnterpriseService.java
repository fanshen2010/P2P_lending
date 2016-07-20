package cn.com.p2p.customer.service;

import cn.com.p2p.customer.service.dto.CustomerDto;
import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.loan.criteria.LoanEnterpriseInfoCriteria;
import cn.com.p2p.domain.loan.criteria.LoanPersonalInfoCriteria;

import java.util.List;

/**
 * 企业融资客户信息 接口
 * @author 
 *
 */
public interface EnterpriseService {
    /**
     * <p>查询企业融资客户信息列表* </p>
     *
     * @param criteria 企业融资客户信息查询条件
     * @return 企业融资客户信息列表
     * @author: 
     */
    public List<CustomerDto> getList(CustEnterpriseInfoCriteria criteria);

    /**
     * <p>查询单个企业融资客户信息* </p>
     *
     * @param id 企业融资客户id
     * @return 企业融资客户信息
     * @author: 
     */
    public CustomerDto findOne(String id);

    /**
     * <p>企业融资客户信息设置* </p>
     *
     * @param custEnterpriseInfo 企业融资客户信息
     * @return 是否成功
     * @author: 
     */
    public boolean update(CustEnterpriseInfo custEnterpriseInfo);

    /**
     * <p>企业融资客户信息新增* </p>
     *
     * @param custEnterpriseInfo 企业融资客户信息
     * @return 是否成功
     * @author: 
     */
    public boolean save(CustEnterpriseInfo custEnterpriseInfo);

    /**
     * 企业全称重复校验
     * @author 
     * 
     * @param customerName 企业全称
     * @param Id 当前ID
     * @return 校验结果 存在返回true
     */
    public boolean customerNameCheck(String customerName,String Id);

}
