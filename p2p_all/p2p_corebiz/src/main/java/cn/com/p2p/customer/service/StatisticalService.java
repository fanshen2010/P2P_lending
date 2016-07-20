package cn.com.p2p.customer.service;

import cn.com.p2p.customer.service.dto.StatisticalDto;

/**
 * 融资客户业务统计信息 接口
 * 主要用于后台个人/企业客户管理页面的统计数据查询
 * @author 
 *
 */
public interface StatisticalService {

    /**
     * <p>查询个人/企业融资客户的融资统计信息* </p>
     *
     * @param code 个人/企业融资客户id
     * @return 个人/企业融资客户的融资统计信息
     * @author: 
     */
    public StatisticalDto getLoanCount(String code);


}
