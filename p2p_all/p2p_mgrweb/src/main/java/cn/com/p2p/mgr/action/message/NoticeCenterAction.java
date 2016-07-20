package cn.com.p2p.mgr.action.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.message.service.MessageSupportService;

/**
 * <p>通知中心</p>
 * @author zhushanyu
 * @date 2015-04-24 09:53
 */
@Namespace("/noticecenter")
@Results({ 
        @Result(name = BaseAction.INIT, location = "noticecenter.ftl", type = "freemarker"),
        @Result(name = BaseAction.DELETE, location = "index.htm", type = "redirect"),
    })
public class NoticeCenterAction extends BaseAction{

    private static final long serialVersionUID = -843223819939469997L;

    /** 消息支持服务接口 */
    @Autowired
    private MessageSupportService messageSupportService;
    
    /** 消息日志实体集合 */
    private List<MessageLog> messageLogs;
    
    /** 消息日志实体 */
    private MessageLog messageLog;
    
    /** 消息日志查询条件实体 */
    private MessageLogCriteria criteria = new MessageLogCriteria();
    
    /**
     * <p>消息中心列表页</p>
     * @author zhushanyu
     * @date 2015-04-25 09:47
     */
    @Action(value="index")
    public String init() throws Exception {
        criteria.setTenantCd(this.getLoginuser().getCompanyId(), Operator.equal);
        criteria.setToUserId(this.getLoginuser().getId(), Operator.equal);
        criteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        messageLogs = messageSupportService.findPageMessageLog(criteria);
        return INIT;
    }

    /**
     * <p>更改消息的状态为已读</p>
     * @author zhushanyu
     * @date 2015-04-25 09:53
     */
    @Action(value="status")
    public String status() throws Exception {
        messageSupportService.changeStatusToRead(messageLog.getId());
        
        MessageLogCriteria criteria=new MessageLogCriteria();
        criteria.setToUserId(this.getLoginuser().getId(), Operator.equal);
        criteria.setMsgType("1", Operator.equal);
        criteria.setMsgReceiveType("1", Operator.equal);
        criteria.setStatus("0", Operator.equal);
        messageSupportService.findPageMessageLog(criteria);
        int totalRecord=criteria.getPage().getTotalRecord();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("record", totalRecord);
        Struts2Utils.renderJson(map);
        return null;
    }

    
    /* =========================================  getter、setter 方法    ========================================== */
    
    public List<MessageLog> getMessageLogs() {
        return messageLogs;
    }

    public void setMessageLogs(List<MessageLog> messageLogs) {
        this.messageLogs = messageLogs;
    }

    public MessageLog getMessageLog() {
        return messageLog;
    }

    public void setMessageLog(MessageLog messageLog) {
        this.messageLog = messageLog;
    }

    public MessageLogCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(MessageLogCriteria criteria) {
        this.criteria = criteria;
    }

}
