/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanEnterpriseInfoCriteria.java
 * Description:       查询条件LoanEnterpriseInfoCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "LOAN_ENTERPRISE_INFO")
public class LoanEnterpriseInfoCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**融资编号*/
    @Column(name="LOAN_CODE")
    private String loanCode;

    /**企业基本json*/
    @Column(name="BASIC_JSON")
    private String basicJson;

    /**法人信息json*/
    @Column(name="LEGAL_JSON")
    private String legalJson;

    /**股东合伙人json*/
    @Column(name="EQUITY_JSON")
    private String equityJson;

    /**联系人json*/
    @Column(name="CONTACT_JSON")
    private String contactJson;

    /**企业证照json*/
    @Column(name="CREDIT_JSON")
    private String creditJson;

    /**电子合同json*/
    @Column(name="ELECT_JSON")
    private String electJson;

    /**商户ID*/
    @Column(name="TENANT_ID")
    private String tenantId;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    /**创建者*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

    /**更新者*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;

    /**版本*/
    @Column(name="VERSION")
    private Integer version;

         
    public LoanEnterpriseInfoCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode, Operator ... oper) {
        this.loanCode = loanCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanCode", param);
            }
        }
    }
    /**获取企业基本json*/
    public String getBasicJson() {
        return basicJson;
    }

    /**设置企业基本json*/
    public void setBasicJson(String basicJson, Operator ... oper) {
        this.basicJson = basicJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("basicJson", param);
            }
        }
    }
    /**获取法人信息json*/
    public String getLegalJson() {
        return legalJson;
    }

    /**设置法人信息json*/
    public void setLegalJson(String legalJson, Operator ... oper) {
        this.legalJson = legalJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("legalJson", param);
            }
        }
    }
    /**获取股东合伙人json*/
    public String getEquityJson() {
        return equityJson;
    }

    /**设置股东合伙人json*/
    public void setEquityJson(String equityJson, Operator ... oper) {
        this.equityJson = equityJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("equityJson", param);
            }
        }
    }
    /**获取联系人json*/
    public String getContactJson() {
        return contactJson;
    }

    /**设置联系人json*/
    public void setContactJson(String contactJson, Operator ... oper) {
        this.contactJson = contactJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("contactJson", param);
            }
        }
    }
    /**获取企业证照json*/
    public String getCreditJson() {
        return creditJson;
    }

    /**设置企业证照json*/
    public void setCreditJson(String creditJson, Operator ... oper) {
        this.creditJson = creditJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("creditJson", param);
            }
        }
    }
    /**获取电子合同json*/
    public String getElectJson() {
        return electJson;
    }

    /**设置电子合同json*/
    public void setElectJson(String electJson, Operator ... oper) {
        this.electJson = electJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("electJson", param);
            }
        }
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }

    /**设置商户ID*/
    public void setTenantId(String tenantId, Operator ... oper) {
        this.tenantId = tenantId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantId", param);
            }
        }
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime, Operator ... oper) {
        this.createTime = createTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createTime", param);
            }
        }
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime, Operator ... oper) {
        this.updateTime = updateTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateTime", param);
            }
        }
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId, Operator ... oper) {
        this.createUserId = createUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUserId", param);
            }
        }
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId, Operator ... oper) {
        this.updateUserId = updateUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateUserId", param);
            }
        }
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version, Operator ... oper) {
        this.version = version;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("version", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        loanCode("LOAN_CODE"),
        basicJson("BASIC_JSON"),
        legalJson("LEGAL_JSON"),
        equityJson("EQUITY_JSON"),
        contactJson("CONTACT_JSON"),
        creditJson("CREDIT_JSON"),
        electJson("ELECT_JSON"),
        tenantId("TENANT_ID"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME"),
        createUserId("CREATE_USER_ID"),
        updateUserId("UPDATE_USER_ID"),
        version("VERSION");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
    }
    public String toString() {
        return "LoanEnterpriseInfo ["
        + ", id=" + id
        + ", loanCode=" + loanCode
        + ", basicJson=" + basicJson
        + ", legalJson=" + legalJson
        + ", equityJson=" + equityJson
        + ", contactJson=" + contactJson
        + ", creditJson=" + creditJson
        + ", electJson=" + electJson
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
