/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageReceiverCriteria.java
 * Description:       查询条件MessageReceiverCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-10             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.criteria;


import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "MESSAGE_RECEIVER")
public class MessageReceiverCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**接受人ID*/
    @Column(name="RECEIVER_ID")
    private String receiverId;

    /**接收人姓名*/
    @Column(name="RECEIVER_NAME")
    private String receiverName;

    /**接收人电话*/
    @Column(name="RECEIVER_PHONE")
    private String receiverPhone;

    /**消息类型*/
    @Column(name="MSG_TYPE")
    private String msgType;

    /**接收人类型*/
    @Column(name="MSG_RECEIVE_TYPE")
    private String msgReceiveType;

    /**消息ID*/
    @Column(name="MSG_ID")
    private String msgId;

    /**商户ID*/
    @Column(name="TENANT_CD")
    private String tenantCd;

         
    public MessageReceiverCriteria() {

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
    /**获取接受人ID*/
    public String getReceiverId() {
        return receiverId;
    }

    /**设置接受人ID*/
    public void setReceiverId(String receiverId, Operator ... oper) {
        this.receiverId = receiverId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receiverId", param);
            }
        }
    }
    /**获取接收人姓名*/
    public String getReceiverName() {
        return receiverName;
    }

    /**设置接收人姓名*/
    public void setReceiverName(String receiverName, Operator ... oper) {
        this.receiverName = receiverName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receiverName", param);
            }
        }
    }
    /**获取接收人电话*/
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**设置接收人电话*/
    public void setReceiverPhone(String receiverPhone, Operator ... oper) {
        this.receiverPhone = receiverPhone;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("receiverPhone", param);
            }
        }
    }
    /**获取消息类型*/
    public String getMsgType() {
        return msgType;
    }

    /**设置消息类型*/
    public void setMsgType(String msgType, Operator ... oper) {
        this.msgType = msgType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgType", param);
            }
        }
    }
    /**获取接收人类型*/
    public String getMsgReceiveType() {
        return msgReceiveType;
    }

    /**设置接收人类型*/
    public void setMsgReceiveType(String msgReceiveType, Operator ... oper) {
        this.msgReceiveType = msgReceiveType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgReceiveType", param);
            }
        }
    }
    /**获取消息ID*/
    public String getMsgId() {
        return msgId;
    }

    /**设置消息ID*/
    public void setMsgId(String msgId, Operator ... oper) {
        this.msgId = msgId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgId", param);
            }
        }
    }
    /**获取商户ID*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户ID*/
    public void setTenantCd(String tenantCd, Operator ... oper) {
        this.tenantCd = tenantCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantCd", param);
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
        receiverId("RECEIVER_ID"),
        receiverName("RECEIVER_NAME"),
        receiverPhone("RECEIVER_PHONE"),
        msgType("MSG_TYPE"),
        msgReceiveType("MSG_RECEIVE_TYPE"),
        msgId("MSG_ID"),
        tenantCd("TENANT_CD");

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
