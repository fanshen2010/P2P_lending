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

public class MessageManagementCriteria extends BaseCriteria {

    /**
	 * 
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
    
    
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMsgType() {
		return msgType;
	}


	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}


	public String getMsgBizType() {
		return msgBizType;
	}


	public void setMsgBizType(String msgBizType) {
		this.msgBizType = msgBizType;
	}


	public String getMsgTitle() {
		return msgTitle;
	}


	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}


	public String getMsgTeml() {
		return msgTeml;
	}


	public void setMsgTeml(String msgTeml) {
		this.msgTeml = msgTeml;
	}


	public String getMsgReceiveType() {
		return msgReceiveType;
	}


	public void setMsgReceiveType(String msgReceiveType) {
		this.msgReceiveType = msgReceiveType;
	}


	public String getValidFlag() {
		return validFlag;
	}


	public void setValidFlag(String validFlag) {
		this.validFlag = validFlag;
	}


	public String getTenantCd() {
		return tenantCd;
	}


	public void setTenantCd(String tenantCd) {
		this.tenantCd = tenantCd;
	}
}
