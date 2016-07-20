/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartmentCriteria.java
 * Description:       查询条件PfmTenantDepartmentCriteria类定义
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

@Table(name = "PFM_TENANT_DEPARTMENT")
public class PfmTenantDepartmentCriteria extends BaseCriteria {

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**部门CD*/
    @Column(name="DEPARTMENT_CD")
    private String departmentCd;

    /**部门名称*/
    @Column(name="DEPARTMENT_NAME")
    private String departmentName;

    /**部门类型*/
    @Column(name="DEPARTMENT_TYPE")
    private String departmentType;

    /**有效flag*/
    @Column(name="VILID_FLAG")
    private String vilidFlag;

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

         
    public PfmTenantDepartmentCriteria() {

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
    /**获取部门CD*/
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**设置部门CD*/
    public void setDepartmentCd(String departmentCd, Operator ... oper) {
        this.departmentCd = departmentCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("departmentCd", param);
            }
        }
    }
    /**获取部门名称*/
    public String getDepartmentName() {
        return departmentName;
    }

    /**设置部门名称*/
    public void setDepartmentName(String departmentName, Operator ... oper) {
        this.departmentName = departmentName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("departmentName", param);
            }
        }
    }
    /**获取部门类型*/
    public String getDepartmentType() {
        return departmentType;
    }

    /**设置部门类型*/
    public void setDepartmentType(String departmentType, Operator ... oper) {
        this.departmentType = departmentType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("departmentType", param);
            }
        }
    }
    /**获取有效flag*/
    public String getVilidFlag() {
        return vilidFlag;
    }

    /**设置有效flag*/
    public void setVilidFlag(String vilidFlag, Operator ... oper) {
        this.vilidFlag = vilidFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("vilidFlag", param);
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
        departmentCd("DEPARTMENT_CD"),
        departmentName("DEPARTMENT_NAME"),
        departmentType("DEPARTMENT_TYPE"),
        vilidFlag("VILID_FLAG"),
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
        return "PfmTenantDepartment ["
        + ", tenantCd=" + tenantCd
        + ", departmentCd=" + departmentCd
        + ", departmentName=" + departmentName
        + ", departmentType=" + departmentType
        + ", vilidFlag=" + vilidFlag
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
