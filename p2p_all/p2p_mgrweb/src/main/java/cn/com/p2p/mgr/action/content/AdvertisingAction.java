package cn.com.p2p.mgr.action.content;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.contentmanagent.service.AdvertisingService;
import cn.com.p2p.domain.cms.entity.Advertising;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.web.action.BaseAction;

/**
 * <p>广告位管理</p>
 * @author zhushanyu
 * @date 2015-04-23 09:35
 */
@Namespace("/content/advertising")
@Results({ 
        @Result(name = BaseAction.INIT, location = "advertisingindex.ftl", type = "freemarker"),
        @Result(name = BaseAction.EDIT, location = "advertisingedit.ftl", type = "freemarker"),
        @Result(name = BaseAction.SAVE, location = "index.htm", type = "redirect"),
        @Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect"),
        @Result(name = BaseAction.DELETE, location = "index.htm", type = "redirect"),
    })
public class AdvertisingAction extends BaseAction{

    private static final long serialVersionUID = 1L;

    /** 广告位管理服务接口 */
    @Autowired
    private AdvertisingService advertisingService;
    
    /** 广告栏目实体 */
    private Advertising advertising;
    
    /** 广告栏目实体集合 */
    private List<Advertising> advertisings;
    
    /**
     * <p>列表界面</p>
     * @author zhushanyu
     * @date 2015-04-23 09:37
     * @description 无
     */
    @Action(value="index")
    public String init() {
        advertisings = advertisingService.findAll();
        return INIT;
    }
    
    /**
     * <p>修改界面</p>
     * @author zhushanyu
     * @date 2015-04-23 10:16
     * @description 无
     */
    @Action(value="edit")
    public String edit() {
        advertising = advertisingService.findOne(advertising.getId());
        return EDIT;
    }
    
    /**
     * <p>删除操作</p>
     * @author zhushanyu
     * @date 2015-04-23 10:23
     * @description 无
     */
    @Action(value="delete")
    public String delete() {
        int count = advertisingService.delete(advertising.getId());
        if(count == 1){
            // 删除成功后的操作
            this.delSuccess();
        }else{
            this.delFailure();
        }
        return null;
    }
    
    /**
     * <p>更新操作</p>
     * @author zhushanyu
     * @date 2015-04-23 10:36
     * @description 动态的更新实体，待更新的实体属性为空时，此字段不更新
     */
    @Validators(str="advertisingCheck",result = BaseAction.INIT, param = "update")
    @Action(value="update")
    public String update() {
        advertisingService.dynamicUpdate(advertising);
        return UPDATE;
    }
    
    /**
     * <p>保存操作</p>
     * @author zhushanyu
     * @date 2015-04-23 10:58
     * @description 无
     */
    @Validators(str="advertisingCheck",result = BaseAction.INIT, param = "save")
    @Action(value="save")
    public String save() {
        advertisingService.save(advertising);
        return SAVE;
    }
    
    /**
     * <p>校验编码的唯一性</p>
     * @author zhushanyu
     * @date 2015-04-23 10:58
     * @description 用户未修改原有编码时，校验通过
     */
    @Action(value="checkAdvertisingCode")
    public String checkAdvertisingCode() {
        try {
            String data = this.getAjaxMap().get("advertising.adverCode").toString();
            boolean isUsable = advertisingService.checkAdvertisingCode(data, advertising.getId());
            if(isUsable){
                this.ajaxCheckSuccess();
            }else{
                this.ajaxCheckFailure();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return null;
    }

    /* =========================================  getter、setter 方法    ========================================== */
    
    public Advertising getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Advertising advertising) {
        this.advertising = advertising;
    }

    public List<Advertising> getAdvertisings() {
        return advertisings;
    }

    public void setAdvertisings(List<Advertising> advertisings) {
        this.advertisings = advertisings;
    }

}
