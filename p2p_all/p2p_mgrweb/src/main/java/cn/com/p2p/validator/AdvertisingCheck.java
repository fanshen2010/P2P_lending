package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.contentmanagent.service.AdvertisingService;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * <p>广告位管理校验</p>
 * @author zhushanyu
 * @date 2015-04-23 11:17
 * @description 验证要求<br>
 *              1.广告位名称：必填、20个字符以内<br>
 *              2.广告位编码： 必填、唯一值、6-18位<br>
 *              3.状态：必填<br>
 *              4.备注：1000个字符以内<br>
 */
@Service("advertisingCheck")
public class AdvertisingCheck extends BaseCheck  implements DataCheck{
    
    /** 广告位管理服务接口 */
    @Autowired
    private AdvertisingService advertisingService;
    
    public List<String> check(BaseAction action, Map<String, Object> parameters){
        dataParameters=parameters;
        errors= new ArrayList<String>();
        /* 广告位名称 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"advertising.adverName",getFieldValue("advertising.adverName"),null,new String[]{"广告位名称"}));
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"advertising.adverName",getFieldValue("advertising.adverName"),getParamsValue(new String[]{"max","20"}),new String[]{"广告位名称"}));
        
       /* 广告位编码 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"advertising.adverCode",getFieldValue("advertising.adverCode"),null,new String[]{"广告位编码"}));
       addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"advertising.adverCode",null,getParamsValue(new String[]{"checkAdvertisingCode","p:advertising.adverCode","p:advertising.id"}),new String[]{"广告位编码"}));
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"advertising.adverCode",getFieldValue("advertising.adverCode"),getParamsValue(new String[]{"max","18"}),new String[]{"广告位编码"}));
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"advertising.adverCode",getFieldValue("advertising.adverCode"),getParamsValue(new String[]{"min","6"}),new String[]{"广告位编码"}));
       
        /* 状态 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"advertising.status",getFieldValue("advertising.status"),null,new String[]{"状态"}));
        
        /* 备注 */
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"advertising.remark",getFieldValue("advertising.remark"),getParamsValue(new String[]{"max","1000"}),new String[]{"备注"}));
       return errors;
    }
    
    /* ======================================================后台服务检验--自定义校验 ====================================================== */
    /**
     * <p>广告位编码唯一性校验<p>
     * @author zhushanyu
     * @date 2015-04-23 11：21
     * @param params[0] 自定义方法名
     * @param params[1] 界面传入的 adverCode值
     * @param params[2] 界面传入的id值
     * @return 消息内容
     * @description 校验广告位编码的唯一性时，需要获取原有的code进行比较，
     *              可以使用的情况有两种:1、与原有code相同即未修改，2、与其他记录的code都不同，
     */
    public String checkAdvertisingCode(Object[] params) {
        String result = "";   // 消息结果
        // 调用编码唯一性检查方法
        boolean isUsable = advertisingService.checkAdvertisingCode((String)params[1], (String)params[2]);
        if(!isUsable){
            result = "编码已经被使用";
        }
        return result;
    }
}
