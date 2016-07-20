/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        SysSetting.java
 * Description:       实体SysSetting类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-14             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.system.entity;

import java.io.Serializable;

import java.util.Date;

public class SysSetting implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**组编码*/
    private String groupCode;

    /**组名称*/
    private String groupTitle;

    /**编码*/
    private String settingCode;

    /**名称*/
    private String settingTitle;

    /**值*/
    private String settingValue;
    
    /**类型*/
    private String settingType;

    /**备注*/
    private String remark;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者*/
    private String createUserId;

    /**更新者*/
    private String updateUserId;

    /**版本*/
    private Integer version;

         
    public SysSetting() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取组编码*/
    public String getGroupCode() {
        return groupCode;
    }

    /**设置组编码*/
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
    /**获取组名称*/
    public String getGroupTitle() {
        return groupTitle;
    }

    /**设置组名称*/
    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }
    /**获取编码*/
    public String getSettingCode() {
        return settingCode;
    }

    /**设置编码*/
    public void setSettingCode(String settingCode) {
        this.settingCode = settingCode;
    }
    /**获取名称*/
    public String getSettingTitle() {
        return settingTitle;
    }

    /**设置名称*/
    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }
    /**获取值*/
    public String getSettingValue() {
        return settingValue;
    }

    /**设置值*/
    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }
    /**获取备注*/
    public String getRemark() {
        return remark;
    }

    /**设置备注*/
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String toString() {
        return "SysSetting ["
        + ", id=" + id
        + ", groupCode=" + groupCode
        + ", groupTitle=" + groupTitle
        + ", settingCode=" + settingCode
        + ", settingTitle=" + settingTitle
        + ", settingValue=" + settingValue
        + ", remark=" + remark
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

	public String getSettingType() {
		return settingType;
	}

	public void setSettingType(String settingType) {
		this.settingType = settingType;
	}

}
