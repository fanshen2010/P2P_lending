/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageLog.java
 * Description:       实体MessageLog类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-24             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.entity;

import java.io.Serializable;

import java.util.Date;

public class MessageLog implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**发送时间*/
    private Date sendTime;

    /**消息类型*/
    private String msgType;

    /**主题*/
    private String subject;

    /**内容*/
    private String content;

    /**TO_USER_ID*/
    private String toUserId;

    /**状态*/
    private String status;

    /**接收人类型*/
    private String msgReceiveType;

    /**商户ID*/
    private String tenantCd;

    /**CREATE_USER_ID*/
    private String createUserId;

    /**CREATED_AT*/
    private Date createdAt;

         
    public MessageLog() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取发送时间*/
    public Date getSendTime() {
        return sendTime;
    }

    /**设置发送时间*/
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    /**获取消息类型*/
    public String getMsgType() {
        return msgType;
    }

    /**设置消息类型*/
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    /**获取主题*/
    public String getSubject() {
        return subject;
    }

    /**设置主题*/
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**获取内容*/
    public String getContent() {
        return content;
    }

    /**设置内容*/
    public void setContent(String content) {
        this.content = content;
    }
    /**获取TO_USER_ID*/
    public String getToUserId() {
        return toUserId;
    }

    /**设置TO_USER_ID*/
    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status) {
        this.status = status;
    }
    /**获取接收人类型*/
    public String getMsgReceiveType() {
        return msgReceiveType;
    }

    /**设置接收人类型*/
    public void setMsgReceiveType(String msgReceiveType) {
        this.msgReceiveType = msgReceiveType;
    }
    /**获取商户ID*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户ID*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }
    /**获取CREATE_USER_ID*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置CREATE_USER_ID*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取CREATED_AT*/
    public Date getCreatedAt() {
        return createdAt;
    }

    /**设置CREATED_AT*/
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return "MessageLog ["
        + ", id=" + id
        + ", sendTime=" + sendTime
        + ", msgType=" + msgType
        + ", subject=" + subject
        + ", content=" + content
        + ", toUserId=" + toUserId
        + ", status=" + status
        + ", msgReceiveType=" + msgReceiveType
        + ", tenantCd=" + tenantCd
        + ", createUserId=" + createUserId
        + ", createdAt=" + createdAt
        + "]";
    }

}
