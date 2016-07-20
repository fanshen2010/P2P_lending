package cn.com.p2p.ui.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.message.criteria.MessageLogCriteria;
import cn.com.p2p.domain.message.entity.MessageLog;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.message.service.MessageSupportService;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 根据条件获取人员的短信数和消息数
 * @author 
 *
 */
public class GetMessageControl implements TemplateMethodModel {

    @Autowired
    private MessageSupportService messageSupportService;

    @Override
    public Object exec(List arguments) throws TemplateModelException {
String result = null;
        try {
            String userId = arguments.get(0).toString();
            String msgType = arguments.get(1).toString();
            String receiveType = arguments.get(1).toString();

            if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(msgType) || StringUtils.isEmpty(receiveType)) {
                result= null;
            }else{
                MessageLogCriteria criteria=new MessageLogCriteria();
                criteria.setToUserId(userId, Operator.equal);
                criteria.setMsgType(msgType, Operator.equal);
                criteria.setMsgReceiveType(receiveType, Operator.equal);
                criteria.setStatus("0", Operator.equal);
                List<MessageLog> list=messageSupportService.findPageMessageLog(criteria);
                int totalRecord=criteria.getPage().getTotalRecord();
                if(totalRecord >0){
                    return totalRecord;
                }else{
                    
                    return null;
                }
                
            }
        } catch (Exception e1) {
            result= null;
        }
        return result;
    }

}
