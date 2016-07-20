/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageLogCriteria.java
 * Description:       查询条件MessageLogCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-24             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "MESSAGE_LOG")
public class MessageLogCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**发送时间*/
    @Column(name="SEND_TIME")
    private Date sendTime;

    /**消息类型*/
    @Column(name="MSG_TYPE")
    private String msgType;

    /**主题*/
    @Column(name="SUBJECT")
    private String subject;

    /**内容*/
    @Column(name="CONTENT")
    private String content;

    /**TO_USER_ID*/
    @Column(name="TO_USER_ID")
    private String toUserId;

    /**状态*/
    @Column(name="STATUS")
    private String status;

    /**接收人类型*/
    @Column(name="MSG_RECEIVE_TYPE")
    private String msgReceiveType;

    /**商户ID*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**CREATE_USER_ID*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

    /**CREATED_AT*/
    @Column(name="CREATED_AT")
    private Date createdAt;

         
    public MessageLogCriteria() {

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
    /**获取发送时间*/
    public Date getSendTime() {
        return sendTime;
    }

    /**设置发送时间*/
    public void setSendTime(Date sendTime, Operator ... oper) {
        this.sendTime = sendTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("sendTime", param);
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
    /**获取主题*/
    public String getSubject() {
        return subject;
    }

    /**设置主题*/
    public void setSubject(String subject, Operator ... oper) {
        this.subject = subject;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("subject", param);
            }
        }
    }
    /**获取内容*/
    public String getContent() {
        return content;
    }

    /**设置内容*/
    public void setContent(String content, Operator ... oper) {
        this.content = content;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("content", param);
            }
        }
    }
    /**获取TO_USER_ID*/
    public String getToUserId() {
        return toUserId;
    }

    /**设置TO_USER_ID*/
    public void setToUserId(String toUserId, Operator ... oper) {
        this.toUserId = toUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("toUserId", param);
            }
        }
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
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
    /**获取CREATE_USER_ID*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置CREATE_USER_ID*/
    public void setCreateUserId(String createUserId, Operator ... oper) {
        this.createUserId = createUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUserId", param);
            }
        }
    }
    /**获取CREATED_AT*/
    public Date getCreatedAt() {
        return createdAt;
    }

    /**设置CREATED_AT*/
    public void setCreatedAt(Date createdAt, Operator ... oper) {
        this.createdAt = createdAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createdAt", param);
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
        sendTime("SEND_TIME"),
        msgType("MSG_TYPE"),
        subject("SUBJECT"),
        content("CONTENT"),
        toUserId("TO_USER_ID"),
        status("STATUS"),
        msgReceiveType("MSG_RECEIVE_TYPE"),
        tenantCd("TENANT_CD"),
        createUserId("CREATE_USER_ID"),
        createdAt("CREATED_AT");

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
