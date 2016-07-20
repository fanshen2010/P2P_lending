package cn.com.p2p.mgr.action.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.message.criteria.MessageManagementCriteria;
import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.util.Struts2Utils;
import cn.com.p2p.message.service.MessageTempleteManageService;
import cn.com.p2p.system.service.MessageReceiverService;
import cn.com.p2p.system.service.dto.MessageReceiverDto;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

@Namespace("/system/messageManagement")
@Results({
        @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
        @Result(name = "businessView", location = "businessView.ftl", type = "freemarker"),
        @Result(name = "businessList", location = "businessList.ftl", type = "freemarker"),
        @Result(name = BaseAction.SAVE, location = "businessList.htm", type = "redirect"),
        @Result(name = "updateUser", location = "index.htm", type = "redirect"),
        @Result(name = "updateBusiness", location = "businessList.htm", type = "redirect"),
})
public class MessageManagementAction extends BaseAction {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /** 短信通道接口 */
    @Autowired
    private MessageTempleteManageService messageTempleteManageService;

    /** 模板引擎 */
    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;

    @Autowired
    protected MessageReceiverService messageReceiverService;

    /** 用户短信模板列表 */
    private List<MessageTemplete> lstUserSms = new ArrayList<MessageTemplete>();

    /** 业务人员短信模板列表 */
    private List<MessageTemplete> lstBusinessSms = new ArrayList<MessageTemplete>();

    /** 短信模板查询条件 */
    private MessageManagementCriteria criteria = new MessageManagementCriteria();
    /** 用户短信模板查询条件 */
    private MessageManagementCriteria userCriteria = new MessageManagementCriteria();
    /** 业务人员短信模板查询条件 */
    private MessageManagementCriteria businessCriteria = new MessageManagementCriteria();

    private MessageTemplete messageTemplete = new MessageTemplete();

    private MessageReceiverDto messageReceiverDto = new MessageReceiverDto();

    /** 已经设定的用户 */
    private List<PfmUserDto> pfmUsers = new ArrayList<PfmUserDto>();

    /** 已经设定的用户 */
    private List<PfmUserDto> messageUsers = new ArrayList<PfmUserDto>();

    @Autowired
    private BusinessStaffService businessStaffService;

    private String receiverId;

    private String realName;

    private String id;

    private List<String> receiveuserList;

    /**
     * 获取短信管理初始页数据
     * @throws Exception
     */
    @Action(value = "index")
    @Override
    public String init() throws Exception {
        // 用户短信管理
        userCriteria.setMsgType("1");
        userCriteria.setMsgReceiveType("0");
        lstUserSms = messageTempleteManageService.getMessageTempLists(userCriteria);
        return INIT;
    }

    @Action(value = "businessList")
    public String businessList() throws Exception {
        // 用户短信管理
        businessCriteria.setMsgType("1");
        businessCriteria.setMsgReceiveType("1");
        lstBusinessSms = messageTempleteManageService.getMessageTempLists(businessCriteria);
        return "businessList";
    }

    /**
     * 查询用户短信管理列表页数据
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "userSearch")
    public void userSearch() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();
        if (map.get("userCriteria.msgTitle") != null && !"".equals(map.get("userCriteria.msgTitle"))) {
            userCriteria.setMsgTitle(map.get("userCriteria.msgTitle").toString());
        }
        if (map.get("userCriteria.validFlag") != null && !"".equals(map.get("userCriteria.validFlag"))) {
            userCriteria.setValidFlag(map.get("userCriteria.validFlag").toString());
        }
        // 用户短信管理
        userCriteria.setMsgType("1");
        userCriteria.setMsgReceiveType("0");
        lstUserSms = messageTempleteManageService.getMessageTempLists(userCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process(
                "/system/messageManagement/userSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    /**
     * 查询业务人员短信管理列表页数据
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "businessSearch")
    public void businessSearch() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();
        if (map.get("businessCriteria.msgTitle") != null && !"".equals(map.get("businessCriteria.msgTitle"))) {
            businessCriteria.setMsgTitle(map.get("businessCriteria.msgTitle").toString());
        }
        if (map.get("businessCriteria.validFlag") != null && !"".equals(map.get("businessCriteria.validFlag"))) {
            businessCriteria.setValidFlag(map.get("businessCriteria.validFlag").toString());
        }
        // 业务人员短信管理
        businessCriteria.setMsgType("1");
        businessCriteria.setMsgReceiveType("1");
        lstBusinessSms = messageTempleteManageService.getMessageTempLists(businessCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process(
                "/system/messageManagement/businessSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    /**
     * 获取用户短信管理ajax分页数据
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "userPage")
    public void userPage() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();
        Integer pageNum = Integer.parseInt(map.get("criteria.page.currentPage").toString());
        userCriteria.getPage().setCurrentPage(pageNum);

        if (map.get("userCriteria.msgTitle") != null && !"".equals(map.get("userCriteria.msgTitle"))) {
            userCriteria.setMsgTitle(map.get("userCriteria.msgTitle").toString());
        }
        if (map.get("userCriteria.validFlag") != null && !"".equals(map.get("userCriteria.validFlag"))) {
            userCriteria.setValidFlag(map.get("userCriteria.validFlag").toString());
        }
        // 用户短信管理
        userCriteria.setMsgType("1");
        userCriteria.setMsgReceiveType("0");
        lstUserSms = messageTempleteManageService.getMessageTempLists(userCriteria);
        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process("/system/messageManagement/userSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    /**
     * 获取业务人员短信管理ajax分页数据
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Action(value = "businessPage")
    public void businessPage() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();
        Integer pageNum = Integer.parseInt(map.get("criteria.page.currentPage").toString());
        businessCriteria.getPage().setCurrentPage(pageNum);
        if (map.get("businessCriteria.msgTitle") != null && !"".equals(map.get("businessCriteria.msgTitle"))) {
            businessCriteria.setMsgTitle(map.get("businessCriteria.msgTitle").toString());
        }
        if (map.get("businessCriteria.validFlag") != null && !"".equals(map.get("businessCriteria.validFlag"))) {
            businessCriteria.setValidFlag(map.get("businessCriteria.validFlag").toString());
        }
        // 业务人员短信管理
        businessCriteria.setMsgType("1");
        businessCriteria.setMsgReceiveType("1");
        lstBusinessSms = messageTempleteManageService.getMessageTempLists(businessCriteria);
        map.clear();
        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process("/system/messageManagement/businessSearch.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);
    }

    @Action(value = "updateStatus")
    public void updateStatus() throws Exception {
        messageTemplete = messageTempleteManageService.getMessageTempById(receiverId);

        if (messageTemplete.getValidFlag().equals("1")) {
            messageTempleteManageService.stopMessageTemp(receiverId);
        } else {
            messageTempleteManageService.startMessageTemp(receiverId);
        }
        this.ajaxCheckSuccess();
    }

    @SuppressWarnings("unchecked")
    @Action(value = "businessView")
    public void businessView() throws Exception {
        // 处理页面数据
        Map<String, Object> map = getAjaxMap();
        realName = "";
        String receiverId = map.get("receiverId").toString();
        if (map.get("realName") != null) {
            realName = map.get("realName").toString();
        }
        messageTemplete = messageTempleteManageService.getMessageTempById(receiverId);
        messageReceiverDto = messageReceiverService.findMessageReceiverByMsgId(receiverId, realName);
        MessageReceiverDto messageReceiverAll = messageReceiverService.findMessageReceiverByMsgId(receiverId, null);
        if (receiveuserList != null) {
            for (PfmUserDto leftUser : messageReceiverDto.getPfmUsers()) {
                if (this.receiveuserList.contains(leftUser.getId())) {
                    messageReceiverDto.getPfmUsers().remove(leftUser);
                }
                if (messageReceiverDto.getPfmUsers().size() == 0) {
                    break;
                }
            }
            for (PfmUserDto leftUser : messageReceiverAll.getPfmUsers()) {
                if (this.receiveuserList.contains(leftUser.getId())) {
                    messageReceiverDto.getMessageUsers().add(leftUser);
                }
            }
        }

        map.clear();

        // 得到渲染好的模板内容
        String result = feroFreemarkerProcessor.process(
                "/system/messageManagement/businessView.ftl", map, this);
        map.put("html", result);
        // ajax返回
        Struts2Utils.renderJson(map);

    }

    @Action(value = "businessSave")
    public String businessSave() throws Exception {
        List<PfmUserDto> lstMessageUser = new ArrayList<PfmUserDto>();
        messageTemplete = messageTempleteManageService.getMessageTempById(receiverId);
        if (this.receiveuserList != null) {
            for (int a = 0; a < this.receiveuserList.size(); a++) {
                if (this.receiveuserList.get(a) != null) {
                    PfmUserDto messageUser = businessStaffService.findPfmUserInfo(this.receiveuserList.get(a));
                    lstMessageUser.add(messageUser);
                }
            }
        }
        messageReceiverDto.setMessageUsers(lstMessageUser);
        messageReceiverService.saveMessageReceiver(messageReceiverDto, messageTemplete);
        return SAVE;
    }

    // -----------------------------------------------------------------------------------
    // --------------------------------画面使用数据定义---------------------------------------
    // -----------------------------------------------------------------------------------
    public List<MessageTemplete> getLstUserSms() {
        return lstUserSms;
    }

    public void setLstUserSms(List<MessageTemplete> lstUserSms) {
        this.lstUserSms = lstUserSms;
    }

    public List<MessageTemplete> getLstBusinessSms() {
        return lstBusinessSms;
    }

    public void setLstBusinessSms(List<MessageTemplete> lstBusinessSms) {
        this.lstBusinessSms = lstBusinessSms;
    }

    public MessageManagementCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(MessageManagementCriteria criteria) {
        this.criteria = criteria;
    }

    public MessageManagementCriteria getUserCriteria() {
        return userCriteria;
    }

    public void setUserCriteria(MessageManagementCriteria userCriteria) {
        this.userCriteria = userCriteria;
    }

    public MessageManagementCriteria getBusinessCriteria() {
        return businessCriteria;
    }

    public void setBusinessCriteria(MessageManagementCriteria businessCriteria) {
        this.businessCriteria = businessCriteria;
    }

    public MessageTemplete getMessageTemplete() {
        return messageTemplete;
    }

    public void setMessageTemplete(MessageTemplete messageTemplete) {
        this.messageTemplete = messageTemplete;
    }

    public MessageReceiverDto getMessageReceiverDto() {
        return messageReceiverDto;
    }

    public void setMessageReceiverDto(MessageReceiverDto messageReceiverDto) {
        this.messageReceiverDto = messageReceiverDto;
    }

    public List<PfmUserDto> getPfmUsers() {
        return pfmUsers;
    }

    public void setPfmUsers(List<PfmUserDto> pfmUsers) {
        this.pfmUsers = pfmUsers;
    }

    public List<PfmUserDto> getMessageUsers() {
        return messageUsers;
    }

    public void setMessageUsers(List<PfmUserDto> messageUsers) {
        this.messageUsers = messageUsers;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getReceiveuserList() {
        return receiveuserList;
    }

    public void setReceiveuserList(List<String> receiveuserList) {
        this.receiveuserList = receiveuserList;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
