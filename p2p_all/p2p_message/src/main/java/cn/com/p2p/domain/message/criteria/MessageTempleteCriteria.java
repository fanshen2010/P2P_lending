/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        MessageTempleteCriteria.java
 * Description:       查询条件MessageTempleteCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-13             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.message.criteria;


import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "MESSAGE_TEMPLETE")
public class MessageTempleteCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**消息类型*/
    @Column(name="MSG_TYPE")
    private String msgType;

    /**消息业务类型*/
    @Column(name="MSG_BIZ_TYPE")
    private String msgBizType;

    /**短信标题*/
    @Column(name="MSG_TITLE")
    private String msgTitle;

    /**短信模板*/
    @Column(name="MSG_TEML")
    private String msgTeml;

    /**接收人类型*/
    @Column(name="MSG_RECEIVE_TYPE")
    private String msgReceiveType;

    /**状态*/
    @Column(name="VALID_FLAG")
    private String validFlag;

    /**商户ID*/
    @Column(name="TENANT_CD")
    private String tenantCd;

         
    public MessageTempleteCriteria() {

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
    /**获取消息业务类型*/
    public String getMsgBizType() {
        return msgBizType;
    }

    /**设置消息业务类型*/
    public void setMsgBizType(String msgBizType, Operator ... oper) {
        this.msgBizType = msgBizType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgBizType", param);
            }
        }
    }
    /**获取短信标题*/
    public String getMsgTitle() {
        return msgTitle;
    }

    /**设置短信标题*/
    public void setMsgTitle(String msgTitle, Operator ... oper) {
        this.msgTitle = msgTitle;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgTitle", param);
            }
        }
    }
    /**获取短信模板*/
    public String getMsgTeml() {
        return msgTeml;
    }

    /**设置短信模板*/
    public void setMsgTeml(String msgTeml, Operator ... oper) {
        this.msgTeml = msgTeml;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("msgTeml", param);
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
    /**获取状态*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置状态*/
    public void setValidFlag(String validFlag, Operator ... oper) {
        this.validFlag = validFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("validFlag", param);
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
        msgType("MSG_TYPE"),
        msgBizType("MSG_BIZ_TYPE"),
        msgTitle("MSG_TITLE"),
        msgTeml("MSG_TEML"),
        msgReceiveType("MSG_RECEIVE_TYPE"),
        validFlag("VALID_FLAG"),
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
