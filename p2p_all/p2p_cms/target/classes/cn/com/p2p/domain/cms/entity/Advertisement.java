/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        Advertisement.java
 * Description:       实体Advertisement类定义
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

public class Advertisement implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**广告ID*/
    private String id;

    /**版位*/
    private String adverCode;

    /**标题*/
    private String title;

    /**链接*/
    private String connectUrl;

    /**描述*/
    private String description;

    /**开始时间*/
    private Date startAt;

    /**结束时间*/
    private Date endAt;

    /**图片*/
    private String adPic;

    /**打开方式*/
    private String opens;

    /**广告排序*/
    private Integer orderNum;

    /**状态*/
    private String status;

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

         
    public Advertisement() {

    }

    /**获取广告ID*/
    public String getId() {
        return id;
    }

    /**设置广告ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取版位*/
    public String getAdverCode() {
        return adverCode;
    }

    /**设置版位*/
    public void setAdverCode(String adverCode) {
        this.adverCode = adverCode;
    }
    /**获取标题*/
    public String getTitle() {
        return title;
    }

    /**设置标题*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**获取链接*/
    public String getConnectUrl() {
        return connectUrl;
    }

    /**设置链接*/
    public void setConnectUrl(String connectUrl) {
        this.connectUrl = connectUrl;
    }
    /**获取描述*/
    public String getDescription() {
        return description;
    }

    /**设置描述*/
    public void setDescription(String description) {
        this.description = description;
    }
    /**获取开始时间*/
    public Date getStartAt() {
        return startAt;
    }

    /**设置开始时间*/
    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }
    /**获取结束时间*/
    public Date getEndAt() {
        return endAt;
    }

    /**设置结束时间*/
    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
    /**获取图片*/
    public String getAdPic() {
        return adPic;
    }

    /**设置图片*/
    public void setAdPic(String adPic) {
        this.adPic = adPic;
    }
    /**获取打开方式*/
    public String getOpens() {
        return opens;
    }

    /**设置打开方式*/
    public void setOpens(String opens) {
        this.opens = opens;
    }
    /**获取广告排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置广告排序*/
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status) {
        this.status = status;
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
        return "Advertisement ["
        + ", id=" + id
        + ", adverCode=" + adverCode
        + ", title=" + title
        + ", connectUrl=" + connectUrl
        + ", description=" + description
        + ", startAt=" + startAt
        + ", endAt=" + endAt
        + ", adPic=" + adPic
        + ", opens=" + opens
        + ", orderNum=" + orderNum
        + ", status=" + status
        + ", remark=" + remark
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
