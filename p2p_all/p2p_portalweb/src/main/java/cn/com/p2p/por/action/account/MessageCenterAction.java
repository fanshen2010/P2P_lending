package cn.com.p2p.por.action.account;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.criteria.MessageLogCriteria.OrderField;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.message.service.MessageSupportService;
@Namespace("/account/message")
@Results({ @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
	})
public class MessageCenterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private MessageSupportService messageSupportService;
	
	private List<MessageLog> lstMessageLog = new LinkedList<MessageLog>();
	
	private MessageLogCriteria criteria = new MessageLogCriteria();

	@Action(value="index")
	@Override
	public String init() throws Exception {
		String userId = getLoginuser().getId();
		criteria.setToUserId(userId,Operator.equal);
		criteria.setSortFields(OrderField.sendTime, SortType.DESC);
		lstMessageLog = messageSupportService.findPageMessageLog(criteria);
		messageSupportService.changeStatusToReadByCriteria(criteria);
		return INIT;
	}

	public List<MessageLog> getLstMessageLog() {
		return lstMessageLog;
	}

	public void setLstMessageLog(List<MessageLog> lstMessageLog) {
		this.lstMessageLog = lstMessageLog;
	}

	public MessageLogCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(MessageLogCriteria criteria) {
		this.criteria = criteria;
	}

}
