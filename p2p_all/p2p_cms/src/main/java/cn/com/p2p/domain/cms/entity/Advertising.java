/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        Advertising.java
 * Description:       实体Advertising类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-01             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.entity;

import java.io.Serializable;

import java.util.Date;

public class Advertising implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**广告版位ID*/
    private String id;

    /**版位编码*/
    private String adverCode;

    /**版位名*/
    private String adverName;

    /**备注*/
    private String remark;

    /**状态*/
    private String status;

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

         
    public Advertising() {

    }

    /**获取广告版位ID*/
    public String getId() {
        return id;
    }

    /**设置广告版位ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取版位编码*/
    public String getAdverCode() {
        return adverCode;
    }

    /**设置版位编码*/
    public void setAdverCode(String adverCode) {
        this.adverCode = adverCode;
    }
    /**获取版位名*/
    public String getAdverName() {
        return adverName;
    }

    /**设置版位名*/
    public void setAdverName(String adverName) {
        this.adverName = adverName;
    }
    /**获取备注*/
    public String getRemark() {
        return remark;
    }

    /**设置备注*/
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status) {
        this.status = status;
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
        return "Advertising ["
        + ", id=" + id
        + ", adverCode=" + adverCode
        + ", adverName=" + adverName
        + ", remark=" + remark
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
