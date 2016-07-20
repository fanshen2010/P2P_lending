package cn.com.p2p.mgr.action.customer;

import cn.com.p2p.customer.service.PersonalService;
import cn.com.p2p.customer.service.dto.CustomerDto;
import cn.com.p2p.domain.customer.criteria.CustPersonalInfoCriteria;
import cn.com.p2p.domain.customer.entity.CustPersonalInfo;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.query.WebUserQuery;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.freemarker.FeroFreemarkerProcessor;
import cn.com.p2p.framework.web.validator.ValidatorErrorParam;
import cn.com.p2p.loan.service.PaymentService;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人融资客户信息
 *
 * @author 
 */
@Namespace("/customer/personal")
@Results({
        @Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
        @Result(name = BaseAction.VIEW, location = "view.ftl", type = "freemarker"),
        @Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect"),
})
public class PersonalAction extends CustomerAction {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Autowired
    private PersonalService personalService;
    /**
     * 模板引擎
     */
    @Autowired
    protected FeroFreemarkerProcessor feroFreemarkerProcessor;
    @Autowired
    private WebUserQuery webUserQuery;
    @Autowired
    private PaymentService paymentService;
    
    //个人/企业融资客户列表 含客户信息及其融资统计信息
    private List<CustomerDto> customerDtoList;
    //个人/企业融资客户 含客户信息及其融资统计信息
    private CustomerDto customerDto;
    //个人客户信息
    private CustPersonalInfo custPersonalInfo;
    //客户id
    private String id;
    //个人融资客户查询条件
    private String name; //姓名
    private String login; //用户名
    private String phone; //联系电话
    //个人融资客户条件查询辅助类
    private CustPersonalInfoCriteria criteria = new CustPersonalInfoCriteria();
    
    private BigDecimal balance;

    @Action(value = "index")
    public String index() throws Exception {
        //页面传来的登录名须转成userId字段 查询
        if (StringUtils.isNotEmpty(name)) {
            criteria.setCustomerName(name, BaseCriteria.Operator.like);
        }
        if (StringUtils.isNotEmpty(phone)) {
            criteria.setCellphone(phone, BaseCriteria.Operator.like);
        }
        customerDtoList = personalService.getList(criteria);
        return BaseAction.INIT;
    }

    @Action(value = "add")
    public void add() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        String ftl = "/customer/personal/add.ftl";
        String result = feroFreemarkerProcessor.process(ftl, map, this);
        map.put("result", result);
        Struts2Utils.renderJson(map);
    }

    @Validators(str="custPersonCheck",result = BaseAction.INIT, param = "save")
    @Action(value = "save")
    public String save() throws Exception {
        personalService.save(custPersonalInfo);
        paymentService.ciccDoCustRegister(custPersonalInfo.getId(), custPersonalInfo.getCustomerName());
        return BaseAction.UPDATE;
    }

    /**
     * 身份证号重复校验
     */
    @Action(value="identityCheck")
    public void identityCheck(){
    	String checkFlag = "true";
    	
    	//当前身份证号存在则校验不通过
    	if(personalService.checkIdentity(custPersonalInfo.getIdentity())){
    		checkFlag = "false";
    	}
    	Struts2Utils.renderText(checkFlag);
    }
    
    @Action(value = "view")
    public String view() throws Exception {
        customerDto = personalService.findOne(id);
        balance = paymentService.ciccDoSearchBalanceByCustCode(id);
        return BaseAction.VIEW;
    }

    @Action(value = "edit")
    public void edit() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        customerDto = personalService.findOne(id);
        map.put("custPersonalInfo", customerDto.getPersonalDto());
        String ftl = "/customer/personal/edit.ftl";
        String result = feroFreemarkerProcessor.process(ftl, map, this);
        map.clear();
        map.put("result", result);
        Struts2Utils.renderJson(map);
    }

    @Validators(str="custPersonCheck",result = BaseAction.INIT, param = "update")
    @Action(value = "update")
    public String update() throws Exception {
        personalService.update(custPersonalInfo);
        return BaseAction.UPDATE;
    }

    /**
     * 数据验证出错后回调处理，
     *
     * @param param 注解中的参数
     * @param requestMap
     */

    public void doDataValidatorFailure(String param,ValidatorErrorParam requestMap){
        customerDtoList = personalService.getList(criteria);
    }


    public List<CustomerDto> getCustomerDtoList() {
        return customerDtoList;
    }

    public void setCustomerDtoList(List<CustomerDto> customerDtoList) {
        this.customerDtoList = customerDtoList;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }

    public CustPersonalInfo getCustPersonalInfo() {
        return custPersonalInfo;
    }

    public void setCustPersonalInfo(CustPersonalInfo custPersonalInfo) {
        this.custPersonalInfo = custPersonalInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CustPersonalInfoCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(CustPersonalInfoCriteria criteria) {
        this.criteria = criteria;
    }

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
