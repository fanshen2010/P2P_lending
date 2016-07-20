/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantCriteria.java
 * Description:       查询条件PfmTenantCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_TENANT")
public class PfmTenantCriteria extends BaseCriteria {

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**商户名称*/
    @Column(name="TENANT_NAME")
    private String tenantName;

    /**商户平台有效期*/
    @Column(name="VALID_FLAG")
    private String validFlag;

    /**开始日*/
    @Column(name="START_DATE")
    private Date startDate;

    /**结束日*/
    @Column(name="END_DATE")
    private Date endDate;

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

         
    public PfmTenantCriteria() {

    }

    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd, Operator ... oper) {
        this.tenantCd = tenantCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantCd", param);
            }
        }
    }
    /**获取商户名称*/
    public String getTenantName() {
        return tenantName;
    }

    /**设置商户名称*/
    public void setTenantName(String tenantName, Operator ... oper) {
        this.tenantName = tenantName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantName", param);
            }
        }
    }
    /**获取商户平台有效期*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置商户平台有效期*/
    public void setValidFlag(String validFlag, Operator ... oper) {
        this.validFlag = validFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("validFlag", param);
            }
        }
    }
    /**获取开始日*/
    public Date getStartDate() {
        return startDate;
    }

    /**设置开始日*/
    public void setStartDate(Date startDate, Operator ... oper) {
        this.startDate = startDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("startDate", param);
            }
        }
    }
    /**获取结束日*/
    public Date getEndDate() {
        return endDate;
    }

    /**设置结束日*/
    public void setEndDate(Date endDate, Operator ... oper) {
        this.endDate = endDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("endDate", param);
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

        tenantCd("TENANT_CD"),
        tenantName("TENANT_NAME"),
        validFlag("VALID_FLAG"),
        startDate("START_DATE"),
        endDate("END_DATE"),
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
        return "PfmTenant ["
        + ", tenantCd=" + tenantCd
        + ", tenantName=" + tenantName
        + ", validFlag=" + validFlag
        + ", startDate=" + startDate
        + ", endDate=" + endDate
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
