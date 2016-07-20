package cn.com.p2p.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.businessmanagentsys.service.dto.MenuListDto;
import cn.com.p2p.domain.message.entity.MessageReceiver;
import cn.com.p2p.domain.message.entity.MessageTemplete;
import cn.com.p2p.domain.message.query.MessageTempleteQuery;
import cn.com.p2p.domain.message.repository.MessageReceiverRepository;
import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.query.PfmUserManageQuery;
import cn.com.p2p.domain.user.repository.PfmUserRepository;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.system.service.MessageReceiverService;
import cn.com.p2p.system.service.dto.MessageReceiverDto;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

@Service
public class MessageReceiverServiceImpl implements MessageReceiverService {

    @Autowired
    private MessageTempleteQuery messageTempleteQuery;

    @Autowired
    private PfmUserManageQuery pfmUserManageQuery;

    @Autowired
    private MessageReceiverRepository messageReceiverRepository;

    /** 商户的管理用户表 数据访问接口 */
    @Autowired
    private PfmUserRepository pfmUserRepository;

    @Autowired
    private BusinessStaffService businessStaffService;

    @Override
    public MessageReceiverDto findMessageReceiverByMsgId(String id, String realName) {
        MessageReceiverDto messageReceiverDto = new MessageReceiverDto();
        List<MessageReceiver> messageReceivers = new ArrayList<MessageReceiver>();
        List<PfmUserDto> messageUsers = new ArrayList<PfmUserDto>();
        List<PfmUserDto> pfmUsers = new ArrayList<PfmUserDto>();
        pfmUsers = pfmUserManageQuery.findAll(realName);

        messageReceivers = messageTempleteQuery.findMessageReceiverByMsgId(id);

        for (int a = 0; a < messageReceivers.size(); a++) {
            PfmUserDto messageUser = new PfmUserDto();
            messageUser = pfmUserManageQuery.findPfmUserById(messageReceivers.get(a).getReceiverId());
            messageUsers.add(messageUser);
            for (int i = 0; i < pfmUsers.size(); i++) {
                if (messageUser.getId().equals(pfmUsers.get(i).getId())) {
                    pfmUsers.remove(pfmUsers.get(i));
                }
            }
        }
        messageReceiverDto.setMessageUsers(messageUsers);
        messageReceiverDto.setPfmUsers(pfmUsers);
        return messageReceiverDto;
    }

    @Override
    public void saveMessageReceiver(MessageReceiverDto messageReceiverDto, MessageTemplete messageTemplete) {
        List<MessageReceiver> messageReceivers = new ArrayList<MessageReceiver>();
        messageReceivers = messageTempleteQuery.findMessageReceiverByMsgId(messageTemplete.getId());
        for (int i = 0; i < messageReceivers.size(); i++) {
            messageReceiverRepository.delete(messageReceivers.get(i).getId());
        }

        for (int a = 0; a < messageReceiverDto.getMessageUsers().size(); a++) {
            PfmUserDto messageUser = new PfmUserDto();
            MessageReceiver messageReceiver = new MessageReceiver();

            messageUser = businessStaffService.findPfmUserInfo(messageReceiverDto.getMessageUsers().get(a).getId());

            messageReceiver.setId(StringUtils.getUUID());
            messageReceiver.setMsgId(messageTemplete.getId());
            messageReceiver.setReceiverId(messageUser.getId());
            messageReceiver.setMsgReceiveType("1");
            messageReceiver.setTenantCd("001");

            if (StringUtils.isNotEmpty(messageTemplete.getMsgType())) {
                messageReceiver.setMsgType(messageTemplete.getMsgType());
            }
            if (StringUtils.isNotEmpty(messageUser.getRealName())) {
                messageReceiver.setReceiverName(messageUser.getRealName());
            }
            if (StringUtils.isNotEmpty(messageUser.getContactPhone())) {
                messageReceiver.setReceiverPhone(messageUser.getContactPhone());
            }

            messageReceiverRepository.insert(messageReceiver);
        }
    }

}
