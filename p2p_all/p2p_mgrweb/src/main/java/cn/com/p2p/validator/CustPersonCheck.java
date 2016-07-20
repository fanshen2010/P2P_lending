package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.customer.criteria.CustEnterpriseInfoCriteria;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustEnterpriseInfo;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.customer.repository.CustEnterpriseInfoRepository;
import cn.com.p2p.domain.customer.repository.CustPersonalInfoRepository;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

@Service("custPersonCheck")
public class CustPersonCheck extends BaseCheck implements DataCheck {

    @Autowired
    private WebUserQuery webUserQuery;
    
    @Autowired
    private CustPersonalInfoRepository custPersonalInfoRepository;

    @Autowired
    CustEnterpriseInfoRepository enterpriseInfoRepository;
    
    @Override
    public List<String> check(BaseAction action, Map<String, Object> parameters) {
        dataParameters = parameters;
        errors = new ArrayList<String>();
        //客户姓名
        addError(requiredDataCheck.checkData(this, ValidatorTypeEnum.TextRequired, "custPersonalInfo.customerName", getFieldValue("custPersonalInfo.customerName"), null, new String[]{"客户姓名"}));
        addError(legthCheck.checkData(this, ValidatorTypeEnum.Length, "custPersonalInfo.customerName", getFieldValue("custPersonalInfo.customerName"), getParamsValue(new String[]{"max", "32"}), new String[]{"客户姓名"}));
        //身份证号
        addError(legthCheck.checkData(this, ValidatorTypeEnum.Length, "custPersonalInfo.identity", getFieldValue("custPersonalInfo.identity"), getParamsValue(new String[]{"max", "18"}), new String[]{"身份证号"}));
        addError(requiredDataCheck.checkData(this, ValidatorTypeEnum.TextRequired, "custPersonalInfo.identity", getFieldValue("custPersonalInfo.identity"), null, new String[]{"身份证号"}));
        addError(stringCheck.checkData(this, ValidatorTypeEnum.String, "custPersonalInfo.identity", getFieldValue("custPersonalInfo.identity"), getParamsValue(new String[]{"identityCard"}), new String[]{"身份证号"}));
        //手机号码
        addError(requiredDataCheck.checkData(this, ValidatorTypeEnum.TextRequired, "custPersonalInfo.cellphone", getFieldValue("custPersonalInfo.cellphone"), null, new String[]{"手机号码"}));
        addError(stringCheck.checkData(this, ValidatorTypeEnum.String, "custPersonalInfo.cellphone", getFieldValue("custPersonalInfo.cellphone"), getParamsValue(new String[]{"phone"}), new String[]{"手机号码"}));
        addError(legthCheck.checkData(this, ValidatorTypeEnum.Length, "custPersonalInfo.cellphone", getFieldValue("custPersonalInfo.cellphone"), getParamsValue(new String[]{"max", "11"}), new String[]{"手机号码"}));
        //电子邮箱
        addError(legthCheck.checkData(this, ValidatorTypeEnum.Length, "custPersonalInfo.email", getFieldValue("custPersonalInfo.email"), getParamsValue(new String[]{"max", "64"}), new String[]{"邮箱"}));
        addError(stringCheck.checkData(this, ValidatorTypeEnum.String, "custPersonalInfo.email", getFieldValue("custPersonalInfo.email"), getParamsValue(new String[]{"email"}), new String[]{"邮箱"}));
        return errors;
    }

}
