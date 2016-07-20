package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * <p>广告管理校验</p>
 * @author zhushanyu
 * @date 2015-04-09 17:13
 * @description 验证要求<br>
 *              1.标题：必填、32个字符以内<br>
 *              2.图片： 必填、bmp, png, jpeg, jpg, gif、不大于2M<br>
 *              3.链接：url校验<br>
 *              4.打开方式： 默认新窗口，等价于必填<br>
 *              5.开始时间：日期时间类型,24小时制，默认当前时间<br>
 *              6.结束时间：日期时间类型,24小时制，不填则一直有效<br>
 *              7.排序： 必填、小于1000的正整数、默认值：1<br>
 *              8.状态： 默认启用，等价于必填<br>
 *              9.备注：1000个字符以内<br>
 */
@Service("advertisementCheck")
public class AdvertisementCheck extends BaseCheck  implements DataCheck{
    
    public List<String> check(BaseAction action, Map<String, Object> parameters){
        dataParameters=parameters;
        errors= new ArrayList<String>();
        /* 标题 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"dto.advertisement.title",getFieldValue("dto.advertisement.title"),null,new String[]{"标题"}));
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"dto.advertisement.title",getFieldValue("dto.advertisement.title"),getParamsValue(new String[]{"max","32"}),new String[]{"标题"}));
        
       /* 图片 */
       addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"file",null,getParamsValue(new String[]{"checkPicture","p:fileFileName","p:fileId"}),new String[]{"图片"}));
       
        /* 链接 */
       addError(stringCheck.checkData(this,ValidatorTypeEnum.String,"dto.advertisement.connectUrl",getFieldValue("dto.advertisement.connectUrl"),getParamsValue(new String[]{"url"}),new String[]{"链接"}));
        
        /* 打开方式 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"dto.advertisement.opens",getFieldValue("dto.advertisement.opens"),null,new String[]{"打开方式"}));
        
        /* 开始时间 */
       addError(dateCheck.checkData(this,ValidatorTypeEnum.Date,"dto.advertisement.startAt",getFieldValue("dto.advertisement.startAt"),getParamsValue(new String[]{"dateTimeFormat24"}),new String[]{"开始时间"}));
        
        /* 结束时间 */
       addError(dateCheck.checkData(this,ValidatorTypeEnum.Date,"dto.advertisement.startAt",getFieldValue("dto.advertisement.startAt"),getParamsValue(new String[]{"dateTimeFormat24"}),new String[]{"结束时间"}));
        
        /* 排序 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"dto.advertisement.orderNum",getFieldValue("dto.advertisement.orderNum"),null,new String[]{"排序"}));
       addError(rangeCheck.checkData(this,ValidatorTypeEnum.Range,"dto.advertisement.orderNum",getFieldValue("dto.advertisement.orderNum"),getParamsValue(new String[]{"range","1","1000"}),new String[]{"排序"}));
        
        /* 状态 */
       addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"dto.advertisement.status",getFieldValue("dto.advertisement.status"),null,new String[]{"状态"}));
        
        /* 备注 */
       addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"dto.advertisement.remark",getFieldValue("dto.advertisement.remark"),getParamsValue(new String[]{"max","1000"}),new String[]{"备注"}));
       return errors;
    }
    
    /* ======================================================后台服务检验--自定义校验 ====================================================== */
    /**
     * <p>图片必填校验<p>
     * @author zhushanyu
     * @date 2015-04-20 10:44
     * @param params[0] 自定义方法名
     * @param params[1] 界面传入的 fileFileName值,此值存在于baseAction中
     * @param params[2] 界面传入的 fileId值,此值存在于baseAction中
     * @return 消息内容
     * @description 1、用户初始上传图片  或者  修改原有图片重新上传时，fileId为空，fileFileName不能为空；
     *              2、修改界面，用户未修改图片，此时fileId不为空，fileFileName为空
     */
    public String checkPicture(Object[] params) {
        String result = "";   // 消息结果
        // 如果  fileId==null 证明用户有新上传的图片，无论是添加界面还是修改界面，此时fileFileName不为空
        // 如果  fileId!=null 证明用户在修改界面，没有修改原有图片，此时fileFileName为空
        if(params[2] == null ){
            if(params[1] == null){
                result="please choose picture";
            }
        }
        return result;
    }
}
