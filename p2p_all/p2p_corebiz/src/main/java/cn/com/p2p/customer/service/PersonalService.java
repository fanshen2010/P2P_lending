package cn.com.p2p.customer.service;

import cn.com.p2p.customer.service.dto.CustomerDto;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.loan.criteria.LoanPersonalInfoCriteria;

import java.util.List;

/**
 * 个人融资客户信息 接口
 *
 * @author 
 */
public interface PersonalService {

    /**
     * <p>查询个人融资客户信息列表* </p>
     *
     * @param criteria 个人融资客户信息查询条件
     * @return 个人融资客户信息列表
     * @author: 
     */
    public List<CustomerDto> getList(CustPersonalInfoCriteria criteria);

    /**
     * <p>查询单个个人融资客户信息* </p>
     *
     * @param id 个人融资客户id
     * @return 个人融资客户信息
     * @author: 
     */
    public CustomerDto findOne(String id);


    /**
     * <p>个人融资客户信息设置* </p>
     *
     * @param custPersonalInfo 个人融资客户信息
     * @return 是否成功
     * @author: 
     */
    public boolean update(CustPersonalInfo custPersonalInfo);

    /**
     * <p>个人融资客户信息新增* </p>
     *
     * @param custPersonalInfo 个人融资客户信息
     * @return 是否成功
     * @author: 
     */
    public boolean save(CustPersonalInfo custPersonalInfo);
    
    /**
     * <p>个人客户管理：身份证号唯一性校验</p>
     * @author 
     * @date 2015年4月30日 17:01:59
     * @description 无
     */
    public boolean checkIdentity(String identity);


}
