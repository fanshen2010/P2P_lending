/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageTemplete.java
 * Description:       实体MessageTemplete类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-13             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.entity;

import java.io.Serializable;


public class MessageTemplete implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**消息类型*/
    private String msgType;

    /**消息业务类型*/
    private String msgBizType;

    /**短信标题*/
    private String msgTitle;

    /**短信模板*/
    private String msgTeml;

    /**接收人类型*/
    private String msgReceiveType;

    /**状态*/
    private String validFlag;

    /**商户ID*/
    private String tenantCd;

         
    public MessageTemplete() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取消息类型*/
    public String getMsgType() {
        return msgType;
    }

    /**设置消息类型*/
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    /**获取消息业务类型*/
    public String getMsgBizType() {
        return msgBizType;
    }

    /**设置消息业务类型*/
    public void setMsgBizType(String msgBizType) {
        this.msgBizType = msgBizType;
    }
    /**获取短信标题*/
    public String getMsgTitle() {
        return msgTitle;
    }

    /**设置短信标题*/
    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle;
    }
    /**获取短信模板*/
    public String getMsgTeml() {
        return msgTeml;
    }

    /**设置短信模板*/
    public void setMsgTeml(String msgTeml) {
        this.msgTeml = msgTeml;
    }
    /**获取接收人类型*/
    public String getMsgReceiveType() {
        return msgReceiveType;
    }

    /**设置接收人类型*/
    public void setMsgReceiveType(String msgReceiveType) {
        this.msgReceiveType = msgReceiveType;
    }
    /**获取状态*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置状态*/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
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
        return "MessageTemplete ["
        + ", id=" + id
        + ", msgType=" + msgType
        + ", msgBizType=" + msgBizType
        + ", msgTitle=" + msgTitle
        + ", msgTeml=" + msgTeml
        + ", msgReceiveType=" + msgReceiveType
        + ", validFlag=" + validFlag
        + ", tenantCd=" + tenantCd
        + "]";
    }

}
