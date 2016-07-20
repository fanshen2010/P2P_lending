package cn.com.p2p.customer.service.dto;

import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.loan.service.dto.LoanEnterpriseInfoDto;
import cn.com.p2p.loan.service.dto.LoanPersonalBasicDto;

/**
 * 融资客户个人/企业信息数据传输对象
 * 主要用于后台个人/企业客户管理页面
 * @author 
 *
 */
public class CustomerDto {

   /**个人融资客户信息*/
    private CustPersonalInfo personalDto;
   /**企业融资客户信息*/
    private CustEnterpriseInfo enterpriseDto;
   /**个人/企业融资统计信息*/
    private StatisticalDto statisticalDto;


    public CustPersonalInfo getPersonalDto() {
        return personalDto;
    }

    public void setPersonalDto(CustPersonalInfo personalDto) {
        this.personalDto = personalDto;
    }

    public CustEnterpriseInfo getEnterpriseDto() {
        return enterpriseDto;
    }

    public void setEnterpriseDto(CustEnterpriseInfo enterpriseDto) {
        this.enterpriseDto = enterpriseDto;
    }

    public StatisticalDto getStatisticalDto() {
        return statisticalDto;
    }

    public void setStatisticalDto(StatisticalDto statisticalDto) {
        this.statisticalDto = statisticalDto;
    }
}
