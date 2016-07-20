package cn.com.p2p.security.login.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.user.criteria.PfmTenantPostCriteria;
import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.framework.dao.BaseCriteria;


public class PostDto extends BaseCriteria implements Serializable{

    private static final long serialVersionUID = 1267539748292780984L;

    /** 界面对应的职务列表 */
    private List<PfmTenantPost> posts;
    
    /** 查看、编辑、删除、新增时 */
    private PfmTenantPost post;
    
    /** 职位对应的用户列表*/
    private List<PfmUser> users;
    
    /** 成员设置界面，搜索没有职位的用户时，作为参数实体 */
    private PfmUser user;
    
    /** 成员设置界面，搜索没有职位的用户时，对应的用户集合   */
    private List<PfmUser> pfmUserOfNoPosts;
    
    /** 成员设置界面，批量增加用户时，对应的用户ID集合  */
    private List<String> userIds;
    
    /**
     * 职位列表页 对应的角色集合列表，
     * 先查询相应的职位信息，并获取所有职位对应的角色列表，
     * 最后按照顺序加入到集合中，输出时顺序输出
     */
    private List<List<PfmRole>> roleList;

    /**
     * 职位增加，修改界面，将前台用户选中的角色列表数据传入后台
     */
    private List<String> roleIds;
    
    /**
     * 职位添加、修改界面，选中相应的角色，将显示选中角色对应的菜单组合列表（去除重复）
     */
    private Map<String, Object> menuMap;
    
    /**
     * 职位列表界面查询条件存储及分页实体
     */    
    private PfmTenantPostCriteria criteria;
    
    /* ****************************** getter setter 方法****************************************** */
    public List<PfmTenantPost> getPosts() {
        return posts;
    }

    public void setPosts(List<PfmTenantPost> posts) {
        this.posts = posts;
    }

    public PfmTenantPost getPost() {
        return post;
    }

    public void setPost(PfmTenantPost post) {
        this.post = post;
    }

    public List<PfmUser> getUsers() {
        return users;
    }

    public void setUsers(List<PfmUser> users) {
        this.users = users;
    }

    public List<List<PfmRole>> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<List<PfmRole>> roleList) {
        this.roleList = roleList;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public PfmUser getUser() {
        return user;
    }

    public void setUser(PfmUser user) {
        this.user = user;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<PfmUser> getPfmUserOfNoPosts() {
        return pfmUserOfNoPosts;
    }

    public void setPfmUserOfNoPosts(List<PfmUser> pfmUserOfNoPosts) {
        this.pfmUserOfNoPosts = pfmUserOfNoPosts;
    }

    public Map<String, Object> getMenuMap() {
        return menuMap;
    }

    public void setMenuMap(Map<String, Object> menuMap) {
        this.menuMap = menuMap;
    }

    public PfmTenantPostCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(PfmTenantPostCriteria criteria) {
        this.criteria = criteria;
    }

}
