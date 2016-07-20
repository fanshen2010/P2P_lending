/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRoleCriteria.java
 * Description:       查询条件PfmRoleCriteria类定义
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

@Table(name = "PFM_ROLE")
public class PfmRoleCriteria extends BaseCriteria {

    /**角色ID*/
    @Column(name="ROLE_ID")
    private String roleId;

    /**角色说明*/
    @Column(name="ROLE_MEM")
    private String roleMem;

    /**角色名称*/
    @Column(name="ROLE_NAME")
    private String roleName;

    /**角色状态*/
    @Column(name="ROLE_STATE")
    private Integer roleState;

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

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

         
    public PfmRoleCriteria() {

    }

    /**获取角色ID*/
    public String getRoleId() {
        return roleId;
    }

    /**设置角色ID*/
    public void setRoleId(String roleId, Operator ... oper) {
        this.roleId = roleId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleId", param);
            }
        }
    }
    /**获取角色说明*/
    public String getRoleMem() {
        return roleMem;
    }

    /**设置角色说明*/
    public void setRoleMem(String roleMem, Operator ... oper) {
        this.roleMem = roleMem;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleMem", param);
            }
        }
    }
    /**获取角色名称*/
    public String getRoleName() {
        return roleName;
    }

    /**设置角色名称*/
    public void setRoleName(String roleName, Operator ... oper) {
        this.roleName = roleName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleName", param);
            }
        }
    }
    /**获取角色状态*/
    public Integer getRoleState() {
        return roleState;
    }

    /**设置角色状态*/
    public void setRoleState(Integer roleState, Operator ... oper) {
        this.roleState = roleState;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("roleState", param);
            }
        }
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

        roleId("ROLE_ID"),
        roleMem("ROLE_MEM"),
        roleName("ROLE_NAME"),
        roleState("ROLE_STATE"),
        tenantCd("TENANT_CD"),
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
        return "PfmRole ["
        + ", roleId=" + roleId
        + ", roleMem=" + roleMem
        + ", roleName=" + roleName
        + ", roleState=" + roleState
        + ", tenantCd=" + tenantCd
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
