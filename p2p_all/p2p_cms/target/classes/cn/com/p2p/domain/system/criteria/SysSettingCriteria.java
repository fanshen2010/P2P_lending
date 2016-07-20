/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        SysSettingCriteria.java
 * Description:       查询条件SysSettingCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-14             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.system.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "SYS_SETTING")
public class SysSettingCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**组编码*/
    @Column(name="GROUP_CODE")
    private String groupCode;

    /**组名称*/
    @Column(name="GROUP_TITLE")
    private String groupTitle;

    /**编码*/
    @Column(name="SETTING_CODE")
    private String settingCode;

    /**名称*/
    @Column(name="SETTING_TITLE")
    private String settingTitle;

    /**值*/
    @Column(name="SETTING_VALUE")
    private String settingValue;
    
    /**类型*/
    @Column(name="SETTING_TYPE")
    private String settingType;

    /**备注*/
    @Column(name="REMARK")
    private String remark;

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

         
    public SysSettingCriteria() {

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
    /**获取组编码*/
    public String getGroupCode() {
        return groupCode;
    }

    /**设置组编码*/
    public void setGroupCode(String groupCode, Operator ... oper) {
        this.groupCode = groupCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("groupCode", param);
            }
        }
    }
    /**获取组名称*/
    public String getGroupTitle() {
        return groupTitle;
    }

    /**设置组名称*/
    public void setGroupTitle(String groupTitle, Operator ... oper) {
        this.groupTitle = groupTitle;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("groupTitle", param);
            }
        }
    }
    /**获取编码*/
    public String getSettingCode() {
        return settingCode;
    }

    /**设置编码*/
    public void setSettingCode(String settingCode, Operator ... oper) {
        this.settingCode = settingCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("settingCode", param);
            }
        }
    }
    /**获取名称*/
    public String getSettingTitle() {
        return settingTitle;
    }

    /**设置名称*/
    public void setSettingTitle(String settingTitle, Operator ... oper) {
        this.settingTitle = settingTitle;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("settingTitle", param);
            }
        }
    }
    /**获取值*/
    public String getSettingValue() {
        return settingValue;
    }

    /**设置值*/
    public void setSettingValue(String settingValue, Operator ... oper) {
        this.settingValue = settingValue;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("settingValue", param);
            }
        }
    }
    
    /**获取类型*/
    public String getSettingType() {
        return settingType;
    }
    
    /**设置类型*/
    public void setSettingType(String settingType, Operator ... oper) {
        this.settingType = settingType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("settingType", param);
            }
        }
    }
    
    /**获取备注*/
    public String getRemark() {
        return remark;
    }

    /**设置备注*/
    public void setRemark(String remark, Operator ... oper) {
        this.remark = remark;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("remark", param);
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
        groupCode("GROUP_CODE"),
        groupTitle("GROUP_TITLE"),
        settingCode("SETTING_CODE"),
        settingTitle("SETTING_TITLE"),
        settingValue("SETTING_VALUE"),
        settingType("SETTING_TYPE"),
        remark("REMARK"),
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
        return "SysSetting ["
        + ", id=" + id
        + ", groupCode=" + groupCode
        + ", groupTitle=" + groupTitle
        + ", settingCode=" + settingCode
        + ", settingTitle=" + settingTitle
        + ", settingValue=" + settingValue
        + ", settingType=" + settingType
        + ", remark=" + remark
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
