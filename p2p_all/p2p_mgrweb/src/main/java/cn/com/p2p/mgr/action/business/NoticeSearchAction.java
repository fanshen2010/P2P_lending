package cn.com.p2p.mgr.action.business;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.message.service.MessageSupportService;

/**
 * <p>
 * 消息管理
 * </p>
 * @author zhushanyu
 * @date 2015-04-24 11:27
 */
@Namespace("/business/notice")
@Results({
        @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
})
public class NoticeSearchAction extends BaseAction {

    private static final long serialVersionUID = -2967006942333845049L;

    /** 消息支持Sevice */
    @Autowired
    private MessageSupportService messageSupportService;

    /** 消息日志，用户集合 */
    private List<MessageLog> userLogs;

    /** 消息日志，业务人员集合 */
    private List<MessageLog> businessLogs;

    /** 界面分页显示必须的查询条件实体 */
    private MessageLogCriteria criteria = new MessageLogCriteria();

    /** 用户消息的查询条件实体 */
    private MessageLogCriteria userCriteria = new MessageLogCriteria();

    /** 业务人员消息的查询条件实体 */
    private MessageLogCriteria businessCriteria = new MessageLogCriteria();

    /** 模板引擎 */
    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;

    /**
     * <p>
     * 列表页
     * </p>
     * @author zhushanyu
     * @date 2015-04-24 11:28
     * @update 增加排序规则，发送时间，降序
     * @description 首页展示用户消息列表页
     */
    @Action(value = "index")
    public String init() {
        // 用户消息查询
        userCriteria.setMsgType("1", Operator.equal);
        userCriteria.setMsgReceiveType("0", Operator.equal);
        userCriteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        userLogs = messageSupportService.findPageMessageLog(userCriteria);
        return INIT;
    }

    /**
     * AJAX查询用户消息列表
     * @throws Exception
     */
    @Action(value = "userSearch")
    public void userSearch() throws Exception {
        // 处理页面数据
        @SuppressWarnings("unchecked")
        Map<String, Object> map = getAjaxMap();

        if (map.get("userCriteria.subject") != null && !"".equals(map.get("userCriteria.subject"))) {
            userCriteria.setSubject(map.get("userCriteria.subject").toString(), Operator.like);
        }
        // 用户消息查询
        userCriteria.setMsgType("1", Operator.equal);
        userCriteria.setMsgReceiveType("0", Operator.equal);
        userCriteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        userLogs = messageSupportService.findPageMessageLog(userCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process("/business/notice/userSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    /**
     * AJAX查询业务人员消息列表
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "businessSearch")
    public void businessSearch() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();

        if (map.get("businessCriteria.subject") != null && !"".equals(map.get("businessCriteria.subject"))) {
            businessCriteria.setSubject(map.get("businessCriteria.subject").toString(), Operator.like);
        }
        // 用户消息查询
        businessCriteria.setMsgType("1", Operator.equal);
        businessCriteria.setMsgReceiveType("1", Operator.equal);
        businessCriteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        businessLogs = messageSupportService.findPageMessageLog(businessCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process("/business/notice/businessSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    /**
     * <p>
     * 用户消息列表页
     * </p>
     * @author zhushanyu
     * @date 2015-04-24 11:28
     * @update 增加排序规则，发送时间，降序
     * @description 无
     */
    @SuppressWarnings("unchecked")
    @Action(value = "userPage")
    public String userPage() {
        // 处理页面数据
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            map = getAjaxMap();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        Integer pageNum = Integer.parseInt(map.get("criteria.page.currentPage").toString());
        userCriteria.getPage().setCurrentPage(pageNum);

        if (map.get("userCriteria.subject") != null && !"".equals(map.get("userCriteria.subject"))) {
            userCriteria.setSubject(map.get("userCriteria.subject").toString(), Operator.like);
        }
        // 用户消息查询
        userCriteria.setMsgType("1", Operator.equal);
        userCriteria.setMsgReceiveType("0", Operator.equal);
        userCriteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        userLogs = messageSupportService.findPageMessageLog(userCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result;
        try {
            result = feroFreemarkerProcessor.process("/business/notice/userSearch.ftl", map, this);
            map.put("html", result);
            // ajax返回
            Struts2Utils.renderJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <p>
     * 业务人员消息列表页
     * </p>
     * @author zhushanyu
     * @date 2015-04-24 11:28
     * @update 增加排序规则，发送时间，降序
     * @description 无
     */
    @SuppressWarnings("unchecked")
    @Action(value = "businessPage")
    public String businessPage() {
        // 处理页面数据
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        try {
            map = getAjaxMap();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        Integer pageNum = Integer.parseInt(map.get("criteria.page.currentPage").toString());
        businessCriteria.getPage().setCurrentPage(pageNum);

        if (map.get("businessCriteria.subject") != null && !"".equals(map.get("businessCriteria.subject"))) {
            businessCriteria.setSubject(map.get("businessCriteria.subject").toString(), Operator.like);
        }
        // 业务人员消息查询
        businessCriteria.setMsgType("1", Operator.equal);
        businessCriteria.setMsgReceiveType("1", Operator.equal);
        businessCriteria.setSortFields(MessageLogCriteria.OrderField.sendTime, SortType.DESC);
        businessLogs = messageSupportService.findPageMessageLog(businessCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result;
        try {
            result = feroFreemarkerProcessor.process("/business/notice/businessSearch.ftl", map, this);
            map.put("html", result);
            // ajax返回
            Struts2Utils.renderJson(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* ================================getter setter
     * 方法================================================ */

    public List<MessageLog> getUserLogs() {
        return userLogs;
    }

    public void setUserLogs(List<MessageLog> userLogs) {
        this.userLogs = userLogs;
    }

    public List<MessageLog> getBusinessLogs() {
        return businessLogs;
    }

    public void setBusinessLogs(List<MessageLog> businessLogs) {
        this.businessLogs = businessLogs;
    }

    public MessageLogCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(MessageLogCriteria criteria) {
        this.criteria = criteria;
    }

    public MessageLogCriteria getUserCriteria() {
        return userCriteria;
    }

    public void setUserCriteria(MessageLogCriteria userCriteria) {
        this.userCriteria = userCriteria;
    }

    public MessageLogCriteria getBusinessCriteria() {
        return businessCriteria;
    }

    public void setBusinessCriteria(MessageLogCriteria businessCriteria) {
        this.businessCriteria = businessCriteria;
    }

}
