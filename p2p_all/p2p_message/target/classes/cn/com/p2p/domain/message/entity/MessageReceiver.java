/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageReceiver.java
 * Description:       实体MessageReceiver类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-10             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.entity;

import java.io.Serializable;


public class MessageReceiver implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**接受人ID*/
    private String receiverId;

    /**接收人姓名*/
    private String receiverName;

    /**接收人电话*/
    private String receiverPhone;

    /**消息类型*/
    private String msgType;

    /**接收人类型*/
    private String msgReceiveType;

    /**消息ID*/
    private String msgId;

    /**商户ID*/
    private String tenantCd;

         
    public MessageReceiver() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取接受人ID*/
    public String getReceiverId() {
        return receiverId;
    }

    /**设置接受人ID*/
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    /**获取接收人姓名*/
    public String getReceiverName() {
        return receiverName;
    }

    /**设置接收人姓名*/
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    /**获取接收人电话*/
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**设置接收人电话*/
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    /**获取消息类型*/
    public String getMsgType() {
        return msgType;
    }

    /**设置消息类型*/
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    /**获取接收人类型*/
    public String getMsgReceiveType() {
        return msgReceiveType;
    }

    /**设置接收人类型*/
    public void setMsgReceiveType(String msgReceiveType) {
        this.msgReceiveType = msgReceiveType;
    }
    /**获取消息ID*/
    public String getMsgId() {
        return msgId;
    }

    /**设置消息ID*/
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    /**获取商户ID*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户ID*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }

    public String toString() {
        return "MessageReceiver ["
        + ", id=" + id
        + ", receiverId=" + receiverId
        + ", receiverName=" + receiverName
        + ", receiverPhone=" + receiverPhone
        + ", msgType=" + msgType
        + ", msgReceiveType=" + msgReceiveType
        + ", msgId=" + msgId
        + ", tenantCd=" + tenantCd
        + "]";
    }

}
