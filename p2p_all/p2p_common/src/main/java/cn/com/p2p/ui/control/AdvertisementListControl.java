package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.cms.ADDto;
import cn.com.p2p.contentmanagent.service.ADService;
import cn.com.p2p.domain.cms.criteria.AdvertisementCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.util.DirectiveUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取前台广告列表UI组件
 * @author 
 *
 */
public class AdvertisementListControl implements TemplateDirectiveModel {

    /** 广告管理Service */
    @Autowired
    private  ADService adService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        
        //广告版位
        String adverCode=DirectiveUtils.getString("adverCode", params);
        //每页条数
        int pageSize=4;
        try {
            pageSize = DirectiveUtils.getInt("pageSize", params);
        } catch (Exception e) {
            pageSize=4;
        }
        
        
      //首页幻灯片
        AdvertisementCriteria adCriteria = new AdvertisementCriteria();
        adCriteria.setAdverCode(adverCode,Operator.equal);
        adCriteria.setStatus("1", Operator.equal);
        adCriteria.setSortFields(OrderField.orderNum, SortType.ASC);
        adCriteria.getPage().setDefalutPageRows(pageSize);
        List<ADDto> AdDtos = adService.findAdByAdvertising(adCriteria);
        env.setVariable("AdDtos", DEFAULT_WRAPPER.wrap(AdDtos));

        body.render(env.getOut());
        
    }

}
