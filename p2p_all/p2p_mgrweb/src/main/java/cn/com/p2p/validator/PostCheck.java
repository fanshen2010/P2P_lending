package cn.com.p2p.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.criteria.PfmTenantPostCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.repository.PfmTenantPostRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.validator.DataCheck;
import cn.com.p2p.framework.web.validator.ValidatorTypeEnum;

/**
 * <p>职务管理校验</p>
 * @author zhushanyu
 * @date 2015-04-09 16:48
 * @description 验证要求<br>
 *              1.职务：必填、64个字符以内、唯一值<br>
 *              2.职务描述：1000个字符<br>
 */
@Service("postCheck")
public class PostCheck extends BaseCheck  implements DataCheck{

    
    /** 职位的基本查询的接口 */
    @Autowired
    private PfmTenantPostRepository pfmTenantPostRepository;
    
    public List<String> check(BaseAction action, Map<String, Object> parameters){
        dataParameters=parameters;
        errors= new ArrayList<String>();

        /* 职务名 */
        addError(requiredDataCheck.checkData(this,ValidatorTypeEnum.TextRequired,"postDto.post.postName",getFieldValue("postDto.post.postName"),null,new String[]{"职务"}));
        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"postDto.post.postName",getFieldValue("postDto.post.postName"),getParamsValue(new String[]{"max","64"}),new String[]{"职务"}));
        addError(customCheck.checkData(this,ValidatorTypeEnum.Custom,"postDto.post.postName",getFieldValue("postDto.post.postName"),getParamsValue(new String[]{"checkPostName","p:postDto.post.postName","p:postDto.post.postCd"}),new String[]{"职务"}));
        
        /* 备注 */
        addError(legthCheck.checkData(this,ValidatorTypeEnum.Length,"postDto.post.postMem",getFieldValue("postDto.post.postMem"),getParamsValue(new String[]{"max","1000"}),new String[]{"备注"}));
        
        return errors;
    }
   
    
    /* ======================================================后台服务检验--自定义校验 ====================================================== */
    /**
     * <p>职位名称唯一性校验<p>
     * @author zhushanyu
     * @date 2015-04-09 17:09
     * @param params[0] 方发名称
     * @param params[1] 界面传入的 postName 值
     * @param params[2] 实体对象对应的ID，新添加时为null
     * @return 消息内容
     * @description 由于职位名称可以修改，所以在此需要获取数据库中的实体对象
     *              ，与界面传入的值进行比较，如果相同则直接通过，否则进行条件查询，
     *              如果查询到数据则校验不通过。
     */
    public String checkPostName(Object[] params) {
        String result = "";   // 消息结果
        // 查询数据库，获取数据实体
        PfmTenantPost post = pfmTenantPostRepository.findOne((String)params[2]);
        // 添加时，无法根据ID查询到实体数据，在此直接过滤
        if(post != null){
             // 此时判断 params[1]是否等于"" 防止查询所有数据（对数据库的无条件查询）
            if(!("".equals(params[1]) || params[1].equals(post.getPostName()))){
                PfmTenantPostCriteria criteria = new PfmTenantPostCriteria();
                criteria.setPostName((String)params[1], Operator.equal);
                List<PfmTenantPost> posts = pfmTenantPostRepository.findByCriteria(criteria);
                if(posts != null && !posts.isEmpty()){
                    result = "职务名已被占用！";
                }
            }
        }
        return result;
    }
}
