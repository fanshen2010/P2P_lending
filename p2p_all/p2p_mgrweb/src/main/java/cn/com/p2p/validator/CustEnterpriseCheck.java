package cn.com.p2p.validator;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("custEnterpriseCheck")
public class CustEnterpriseCheck extends BaseCheck implements DataCheck {

    @Autowired
    private WebUserQuery webUserQuery;
    
    @Autowired
    CustEnterpriseInfoRepository enterpriseInfoRepository;
    
    @Autowired
    private CustPersonalInfoRepository custPersonalInfoRepository;
    
    @Override
    public List<String> check(BaseAction action, Map<String, Object> parameters) {
        dataParameters = parameters;
        errors = new ArrayList<String>();
        //企业全称
        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"custEnterpriseInfo.customerName",getFieldValue("custEnterpriseInfo.customerName"),null,new String[]{"企业全称"}));
        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"custEnterpriseInfo.customerName",getFieldValue("custEnterpriseInfo.customerName"),getParamsValue(new String[]{"max","64"}),new String[]{"企业全称"}));
        //营业执照号
        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"custEnterpriseInfo.licenseNumbers",getFieldValue("custEnterpriseInfo.licenseNumbers"),null,new String[]{"营业执照号"}));
        //联系人姓名
        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"custEnterpriseInfo.contactName",getFieldValue("custEnterpriseInfo.contactName"),null,new String[]{"联系人姓名"}));
        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"custEnterpriseInfo.contactName",getFieldValue("custEnterpriseInfo.contactName"),getParamsValue(new String[]{"max","32"}),new String[]{"联系人姓名"}));
        //联系电话-办公电话
        addError(requiredDataCheck.checkData(this, ValidatorTypeEnum.TextRequired, "custEnterpriseInfo.cellphone", getFieldValue("custEnterpriseInfo.cellphone"), null, new String[]{"联系电话"}));
        addError(stringCheck.checkData(this, ValidatorTypeEnum.String, "custEnterpriseInfo.cellphone", getFieldValue("custEnterpriseInfo.cellphone"), getParamsValue(new String[]{"phone"}), new String[]{"联系电话"}));
        //邮箱
        addError(legthCheck.checkData(this, ValidatorTypeEnum.Length, "custEnterpriseInfo.email", getFieldValue("custEnterpriseInfo.email"), getParamsValue(new String[]{"max", "64"}), new String[]{"邮箱"}));
        addError(stringCheck.checkData(this, ValidatorTypeEnum.String, "custEnterpriseInfo.email", getFieldValue("custEnterpriseInfo.email"), getParamsValue(new String[]{"email"}), new String[]{"邮箱"}));
        return errors;
    }

}
