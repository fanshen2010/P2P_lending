package cn.com.p2p.mgr.action.content;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.cms.ADDto;
import cn.com.p2p.contentmanagent.service.ADService;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.utils.UploadUtils;


@Namespace("/content/advertisement")
@Results({ 
        @Result(name = BaseAction.INIT, location = "adindex.ftl", type = "freemarker"),
        @Result(name = BaseAction.EDIT, location = "adedit.ftl", type = "freemarker"),
        @Result(name = BaseAction.ADD, location = "add.ftl", type = "freemarker"),
        @Result(name = BaseAction.SAVE, location = "index.htm", type = "redirect"),
        @Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect"),
        @Result(name = BaseAction.DELETE, location = "index.htm", type = "redirect"),
        @Result(name = "updateOrder", location = "index.htm", type = "redirect"),
    })
public class ADAction extends BaseAction {

    private static final long serialVersionUID = -5828292410563416914L;

    /** 广告管理接口 */
    @Autowired
    private  ADService aDService;
    
    /** 广告管理数据传输对象 */
    private ADDto dto;   // 不能名明为aDDto，无法自动进行赋值，对象为null
    
    /**
     * <p>列表界面</p>
     * @author zhushanyu
     * @date 2015-04-13 16:07
     * @description 无
     */
    @Action(value="index")
    public String init() throws Exception {
        dto = aDService.findAllAD();
        return INIT;
    }
    
    /**
     * <p>保存操作</p>
     * @author zhushanyu
     * @date 2015-04-13 16:07
     * @description 保存用户新添加的数据
     */
  //TODO 注意修改：返回界面时，无法获取数据
    @Validators(str="advertisementCheck",result = BaseAction.INIT, param = "save")
    @Action(value="save")
    public String save() throws Exception {
        
       UploadFile uploadFile = UploadUtils.upload(file, fileFileName, fileContentType);
       aDService.save(dto, uploadFile);
        return SAVE;
    }
    
    /**
     * <p>编辑操作</p>
     * @author zhushanyu
     * @date 2015-04-13 16:07
     * @description 无
     */
    @Action(value="edit")
    public String edit() throws Exception {
        dto = aDService.findAD(dto);
        return EDIT;
    }
    /**
     * <p>添加操作</p>
     * @author 
     * @date 2015-04-13 16:07
     * @description 无
     */
    @Action(value="add")
    public String add() throws Exception {
    	return ADD;
    }
    
    /**
     * <p>更新操作</p>
     * @author zhushanyu
     * @date 2015-04-13 16:07
     * @description 更新用户修改后的数据
     */
    //TODO 注意修改：返回界面时，无法获取数据
    @Validators(str="advertisementCheck",result = BaseAction.INIT, param = "update")
    @Action(value="update")
    public String update() throws Exception {
        UploadFile uploadFile = null;
        if(fileId == null){
            if(file != null){
                uploadFile = UploadUtils.upload(file, fileFileName, fileContentType);
            }
        }
       aDService.update(dto, uploadFile, fileId);
       return UPDATE;
    }
    
    /**
     * <p>批量更新广告的排序</p>
     * @author zhushanyu
     * @date 2015-04-13 16:07
     * @description 如果用户删除了某条排序编号后，则不更新此条记录的排序编码
     */
    @Action(value="updateOrder")
    public String saveorder() throws Exception {
        aDService.updateOrder(dto);
        return UPDATE;
    }

    /**
     * <p>删除操作</p>
     * @author zhushanyu
     * @date 2015-04-13 16:03
     * @description 正常执行删除操作，根据操作的结果（标志），
     *              来决定调用delSuccess()还是delFailure();
     */
    @Action(value="delete")
    public String delete() throws Exception {
        int count = aDService.delete(dto);
        if(count == 1){
            this.delSuccess();
        }else{
            this.delFailure();
        }
        return null;
    }
    
    /* =================================================================== getter setter 方法  ================================================================== */
    public ADDto getDto() {
        return dto;
    }

    public void setDto(ADDto dto) {
        this.dto = dto;
    }

}
