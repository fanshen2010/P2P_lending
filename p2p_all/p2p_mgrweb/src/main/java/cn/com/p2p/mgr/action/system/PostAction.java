package cn.com.p2p.mgr.action.system;

import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.user.criteria.PfmTenantPostCriteria;
import cn.com.p2p.framework.context.annotation.Validators;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.security.login.service.dto.PostDto;
import cn.com.p2p.usermangent.service.PostService;

/**
 * <p>职务管理</p>
 * @author zhushanyu
 * @date 2015-03-26 09:40
 */
@Namespace("/system/post")
@Results({
    @Result(name = BaseAction.INIT, location = "postindex.ftl", type = "freemarker"),
    @Result(name = BaseAction.ADD, location = "postadd.ftl", type = "freemarker"),
    @Result(name = BaseAction.EDIT, location = "postedit.ftl", type = "freemarker"),
    @Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect"),
    @Result(name = BaseAction.SAVE, location = "index.htm", type = "redirect"),
    @Result(name = BaseAction.DELETE, location = "index.htm", type = "redirect"),
    @Result(name = "member", location = "member.ftl", type = "freemarker",params={"postDto.user.realName","${postDto.user.realName}"}),
    @Result(name = "getmember", location = "separate.ftl", type = "freemarker"),
    @Result(name = "addmember", location = "member.htm", type = "redirect", 
            params={"postDto.post.postCd","${postDto.post.postCd}","postDto.user.realName","${postDto.user.realName}","requestMethod","get"}),
    @Result(name = "assembleMenus", location = "separate.ftl", type = "freemarker"),
    })
public class PostAction extends BaseAction{

    private static final long serialVersionUID = -7090511709418832411L;

    /** 职务管理界面对应的数据传输对象 */
    private PostDto postDto;
    
    
    private String requestMethod="post";
    
    @Autowired
    private PostService postService;
    
    /**
     * 职位列表界面查询条件存储及分页实体，注：初始化Action时实例化，避免报错
     */ 
    private PfmTenantPostCriteria criteria = new PfmTenantPostCriteria();
    
    /**
     * <p>初始化列表页</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 首页
     */
    @Action("index")
    @Override
    public String init() throws Exception {
        
        if(postDto == null){  // 初始化界面时，postDto为空，此时手动实例化，避免报错
            postDto = new PostDto();
        }
        if("get".equals(requestMethod)){
        	postDto.getUser().setRealName(new String(postDto.getUser().getRealName().getBytes("ISO-8859-1"), "UTF-8"));
        }
        // 为数据传输对象设置查询查询条件
        postDto.setCriteria(criteria);
        // 查询职位及职位对应的角色列表
        postDto = postService.findPostRole(postDto);
        return INIT;
    }
    
    /**
     * <p>初始化新增页</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 新增界面
     */
    @Action("add")
    public String add() throws Exception {
        if(postDto == null){  // 初始化界面时，postDto为空，此时手动实例化，避免报错
            postDto = new PostDto();
        }
        postDto.setMenuMap(postService.findPostRoleMenu(null));
        return ADD;
    }
    
    /**
     * <p>保存</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @update 2015-04-21 16:08 从当前登录账户中获取商户ID
     * @description 保存用户新添加的数据
     */
    @Validators(str="postCheck",result = BaseAction.ADD, param = "save")
    @Action("save")
    public String save() throws Exception {
      //TODO 未完成
        /** 这里手动为商户ID,创建者赋值，请注意修改 */
        
        postDto.getPost().setTenantCd(getLoginuser().getCompanyId());
        postDto.getPost().setPostCd(StringUtils.getUUID());
        postDto.getPost().setCreateTime(StringUtils.getCurrentTimestamp());
        postDto.getPost().setCreateUserId(StringUtils.getUUID());
        postDto.getPost().setUpdateTime(StringUtils.getCurrentTimestamp());
        postDto.getPost().setUpdateUserId(StringUtils.getUUID());
        // 注：版本号未进行处理
        postDto.getPost().getVersion();
        
        // 保存成功返回整型1
        int flag = postService.insert(postDto);
        if(flag >= 1){
            /**
             * 这里书写保存成功的操作
             */
            System.out.println(flag);
        }
        return SAVE;
    }
    
    /**
     * <p>删除操作</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 删除操作
     */
    @Action("delete")
    public String delete() {
        int flag = postService.delete(postDto);
        if(flag >= 1){  // 如果删除成功，返回成功操作提示，否则返回操作失败提示
            this.delSuccess();
        }else{
            this.delFailure();
        }
        return null;
    }
    
    /**
     * <p>初始化修改界面</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 修改界面
     */
    @Action("edit")
    public String edit() {
         postDto = postService.findPost(postDto);
        return EDIT;
    }
    
    /**
     * <p>更新</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 更新用户修改后的数据
     */
    @Validators(str="postCheck",result = BaseAction.EDIT, param = "update")
    @Action("update")
    public String update() {
        // 更新成功返回整型1
        int flag = postService.dynamicUpdate(postDto);
        if(flag == 1){
            /**
             * 这里书写更新成功的操作
             */
            System.out.println(flag);
        }
        return UPDATE;
    }
    
    /**
     * <p>初始化成员设置界面</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 成员设置界面
     */
    @Action("member")
    public String member() {
        
        if("get".equals(requestMethod)){
            try {
                postDto.getUser().setRealName(new String(postDto.getUser().getRealName().getBytes("ISO-8859-1"), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        
        postDto = postService.findPostUser(postDto);
        return "member";
    }
    
    /**
     * <p>AJAX获取没有职位的用户</p>
     * @author zhushanyu
     * @date 2015-04-13 17:40
     * @description 通过AJAX请求获取符合条件的并且没有职位的所有用户
     */
    @Action("getmember")
    public String getmember() {
        postDto.setPfmUserOfNoPosts(postService.findPfmUserOfNoPost(postDto));
        return "getmember";
    }
    
    /**
     * <p>为职位增加用户</p>
     * @author zhushanyu
     * @date 2015-03-26 10:08
     * @description 成员设置界面
     */
    @Action("addmember")
    public String addmember() {
        int flag = postService.addUserForPost(postDto);
        if(flag >= 1){
            /**
             * 这里书写更新成功的操作
             */
            System.out.println(flag);
        }
        return "addmember";
    }
    
    /**
     * <p>职位名称唯一性校验<p>
     * @author zhushanyu
     * @date 2015-04-09 17:09
     * @param params 需要校验的值
     * @return 消息内容
     */
    @Action("checkPostName")
    public String checkPostName() {
        try {
            // 获取参数值
            String data = this.getAjaxMap().get("postDto.post.postName").toString();
            boolean flag = postService.checkPostName(data, postDto);
            if(flag){
                this.ajaxCheckSuccess();
            }else{
                this.ajaxCheckFailure();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * <p>删除职位下的用户</p>
     * @author zhushanyu
     * @date 2015-04-08 18:45
     * @description 删除时仅仅设置用户的职位ID为null
     */
    @Action("delmember")
    public String delmember() {
        int flag = postService.delUserForPost(postDto);
        if(flag == 1){  // 如果删除成功，返回成功操作提示，否则返回操作失败提示
            this.delSuccess();
        }else{
            this.delFailure();
        }
        return null;
    }
    
    
    /**
     * <p>组合角色对应的菜单</p>
     * @author zhushanyu
     * @date 2015-04-08 11:52
     * @description 修改个增加界面，选中相应的角色后，发送AJAX请求，
     *              返回组合之后的菜单列表
     */
    @Action("assembleMenus")
    public String assembleMenus() {
        postDto = postService.assembleMenus(postDto);
        return "assembleMenus";
    }
    
    /*  ================================getter setter 方法================================================  */

    public PostDto getPostDto() {
        return postDto;
    }

    public void setPostDto(PostDto postDto) {
        this.postDto = postDto;
    }

	/**
	 * @return the requestMethod
	 */
	public String getRequestMethod() {
		return requestMethod;
	}

	/**
	 * @param requestMethod the requestMethod to set
	 */
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

    public PfmTenantPostCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(PfmTenantPostCriteria criteria) {
        this.criteria = criteria;
    }

}
