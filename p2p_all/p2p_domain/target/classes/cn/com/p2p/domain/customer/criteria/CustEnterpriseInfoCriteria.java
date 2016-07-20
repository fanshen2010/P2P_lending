/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CustEnterpriseInfoCriteria.java
 * Description:       查询条件CustEnterpriseInfoCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-20             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.customer.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "CUST_ENTERPRISE_INFO")
public class CustEnterpriseInfoCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**客户编号*/
    @Column(name="CUSTOMER_CODE")
    private String customerCode;

    /**企业全称*/
    @Column(name="CUSTOMER_NAME")
    private String customerName;

    /**组织结构代码证*/
    @Column(name="ORGANIZATION_CODE")
    private String organizationCode;

    /**营业执照号*/
    @Column(name="LICENSE_NUMBERS")
    private String licenseNumbers;

    /**联系人姓名*/
    @Column(name="CONTACT_NAME")
    private String contactName;

    /**联系电话*/
    @Column(name="CELLPHONE")
    private String cellphone;

    /**邮箱*/
    @Column(name="EMAIL")
    private String email;

    /**用户ID*/
    @Column(name="USER_ID")
    private String userId;

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

         
    public CustEnterpriseInfoCriteria() {

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
    /**获取客户编号*/
    public String getCustomerCode() {
        return customerCode;
    }

    /**设置客户编号*/
    public void setCustomerCode(String customerCode, Operator ... oper) {
        this.customerCode = customerCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("customerCode", param);
            }
        }
    }
    /**获取企业全称*/
    public String getCustomerName() {
        return customerName;
    }

    /**设置企业全称*/
    public void setCustomerName(String customerName, Operator ... oper) {
        this.customerName = customerName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("customerName", param);
            }
        }
    }
    /**获取组织结构代码证*/
    public String getOrganizationCode() {
        return organizationCode;
    }

    /**设置组织结构代码证*/
    public void setOrganizationCode(String organizationCode, Operator ... oper) {
        this.organizationCode = organizationCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("organizationCode", param);
            }
        }
    }
    /**获取营业执照号*/
    public String getLicenseNumbers() {
        return licenseNumbers;
    }

    /**设置营业执照号*/
    public void setLicenseNumbers(String licenseNumbers, Operator ... oper) {
        this.licenseNumbers = licenseNumbers;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("licenseNumbers", param);
            }
        }
    }
    /**获取联系人姓名*/
    public String getContactName() {
        return contactName;
    }

    /**设置联系人姓名*/
    public void setContactName(String contactName, Operator ... oper) {
        this.contactName = contactName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("contactName", param);
            }
        }
    }
    /**获取联系电话*/
    public String getCellphone() {
        return cellphone;
    }

    /**设置联系电话*/
    public void setCellphone(String cellphone, Operator ... oper) {
        this.cellphone = cellphone;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("cellphone", param);
            }
        }
    }
    /**获取邮箱*/
    public String getEmail() {
        return email;
    }

    /**设置邮箱*/
    public void setEmail(String email, Operator ... oper) {
        this.email = email;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("email", param);
            }
        }
    }
    /**获取用户ID*/
    public String getUserId() {
        return userId;
    }

    /**设置用户ID*/
    public void setUserId(String userId, Operator ... oper) {
        this.userId = userId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userId", param);
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
        customerCode("CUSTOMER_CODE"),
        customerName("CUSTOMER_NAME"),
        organizationCode("ORGANIZATION_CODE"),
        licenseNumbers("LICENSE_NUMBERS"),
        contactName("CONTACT_NAME"),
        cellphone("CELLPHONE"),
        email("EMAIL"),
        userId("USER_ID"),
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
        return "CustEnterpriseInfo ["
        + ", id=" + id
        + ", customerCode=" + customerCode
        + ", customerName=" + customerName
        + ", organizationCode=" + organizationCode
        + ", licenseNumbers=" + licenseNumbers
        + ", contactName=" + contactName
        + ", cellphone=" + cellphone
        + ", email=" + email
        + ", userId=" + userId
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
