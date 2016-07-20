package cn.com.p2p.usermangent.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.criteria.PfmPostRoleCriteria;
import cn.com.p2p.domain.user.criteria.PfmTenantPostCriteria;
import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.entity.PfmPostRole;
import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.query.PfmRoleManageQuery;
import cn.com.p2p.domain.user.query.PfmUserManageQuery;
import cn.com.p2p.domain.user.repository.PfmMenuRepository;
import cn.com.p2p.domain.user.repository.PfmPostRoleRepository;
import cn.com.p2p.domain.user.repository.PfmRoleMenuRepository;
import cn.com.p2p.domain.user.repository.PfmRoleRepository;
import cn.com.p2p.domain.user.repository.PfmTenantDepartmentRepository;
import cn.com.p2p.domain.user.repository.PfmTenantPostRepository;
import cn.com.p2p.domain.user.repository.PfmUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.security.login.service.dto.PostDto;
import cn.com.p2p.usermangent.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    /** 职位的基本查询的接口 */
    @Autowired
    private PfmTenantPostRepository pfmTenantPostRepository;
    
    /** 角色管理接口 */
    @Autowired
    private PfmRoleRepository pfmRoleRepository;
    
    /** 菜单角色关联数据组件 */
    @Autowired
    private PfmRoleMenuRepository pfmRoleMenuRepository;
    
    /** 菜单管理组件 */
    @Autowired
    private PfmMenuRepository pfmMenuRepository;
    
    /** 职位角色管理组件 */
    @Autowired
    private PfmPostRoleRepository pfmPostRoleRepository;
    
    /** 数据访问层商户部门表接口 */
    @Autowired
    private PfmTenantDepartmentRepository pfmTenantDepartmentRepository;
    
    /** 
     * 角色查询的借口
     * 包含 角色职位查询 
     */
    @Autowired
    private PfmRoleManageQuery roleQuery;
    
    /** 
     * 用户管理组件 
     */
    @Autowired
    private PfmUserRepository userRepository;
    
    /** 
     * 用户自定义管理组件 
     */
    @Autowired
    private PfmUserManageQuery userManageQuery;
    
    /**
     * <p>查询所有的职务记录和职位对应的角色列表，分页显示</p>
     * @author 
     * @date 2015-09-26 13:27
     * @param postDto 包含检索条件实体PfmTenantPostCriteria
     * @return PostDto 包含 List<PfmTenantPost> 职务集合
     * @description 查询职务记录，并按照分页限制的条数显示
     */
    @Override
    public PostDto findPostRole(PostDto postDto) {
        if(postDto.getPost() != null){
            postDto.getCriteria().setPostName(postDto.getPost().getPostName(), Operator.like);
        }
        // 存储所有角色集合
        List<List<PfmRole>> roleList = new ArrayList<List<PfmRole>>();
        
        // 设置排序规则以更新时间排序，相同则以创建时间排序
        postDto.getCriteria().setSortFields(PfmTenantPostCriteria.OrderField.updateTime, SortType.DESC);
        postDto.getCriteria().setSortFields(PfmTenantPostCriteria.OrderField.createTime, SortType.DESC);
        
        // 查询所有的职位集合，无条件查询
        List<PfmTenantPost> posts = pfmTenantPostRepository.findPageByCriteria(postDto.getCriteria());
        
        for(PfmTenantPost post : posts){
            roleList.add(roleQuery.findPostRole(post.getPostCd()));
        }
        postDto.setRoleList(roleList);
        postDto.setPosts(posts);
        return postDto;
    }

    /**
     * <p>根据职务记录ID，查询唯一一条记录</p>
     * @author 
     * @date 2015-09-26 13:27
     * @param String postCd 职务记录ID
     * @return PfmTenantPost 职务对象
     * @description 根据职务记录ID，查询唯一一条记录
     */
    @Override
    public PfmTenantPost findOne(String postCd) {
        return pfmTenantPostRepository.findOne(postCd);
    }

    /**
     * <p>动态更新，更新职位信息及相应的角色职位关联表</p>
     * @author 
     * @date 2015-09-26 14:32
     * @update 2015-10-02 17:52 更新职位信息是更新相应的职位角色信息
     * @param PfmTenantPost post 职务对象
     * @return int 返回是否更新成功的标志，更新成功一条数据返回整型1，两条返回2
     * @description 根据职务记录ID，动态的更新数据，如果相应的属性值为空，则不更新此属性对应的字段
     */
    @Override
    public int dynamicUpdate(PostDto postDto) {

        // TODO 手动设置更新时间
        postDto.getPost().setUpdateTime(new java.util.Date());
        // 动态更新职位
        int i = pfmTenantPostRepository.dynamicUpdate(postDto.getPost());
        int j = 0; // 仅仅作为统计条数，没有意义
        PfmPostRoleCriteria postRoleCriteria = new PfmPostRoleCriteria();   // 实例化查询实体
        String postId = postDto.getPost().getPostCd();   // 获得职位Id
        List<PfmRole> roles = roleQuery.findPostRole(postId);
        // 逐一删除旧的关联数据
        for(PfmRole role : roles){
            postRoleCriteria.setPostId(postId, Operator.equal);
            postRoleCriteria.setRoleId(role.getRoleId(), Operator.equal);
            List<PfmPostRole> postRoles = pfmPostRoleRepository.findByCriteria(postRoleCriteria);
            if(postRoles != null && !postRoles.isEmpty()){
                j = j +  pfmPostRoleRepository.delete(postRoles.get(0).getId());
            }
        }
        // 插入新的关联数据
        if(postDto.getRoleIds() != null){
            for(String roleId : postDto.getRoleIds()){
                PfmPostRole postRole = new PfmPostRole();
                // 手动设置职位角色ID，注意修改
                postRole.setId(StringUtils.getUUID());
                postRole.setPostId(postId);
                postRole.setRoleId(roleId);
                j = j + pfmPostRoleRepository.insert(postRole);
            }
        }
        return i + j;
    }
    
    /**
     * <p>保存新数据</p>
     * @author 
     * @date 2015-09-26 15:16
     * @update 2015-10-02 16:15, 增加职位角色数据的插入
     * @param PfmTenantPost post 职务对象
     * @return int 返回是否保存成功的标志，保存成功一条数据返回整型1，两条返回2
     * @description 保存数据，向数据库中保存职位的同时，在保存职位角色关联数据
     */
    @Override
    public int insert(PostDto postDto) {
       // TODO 手动设置创建时间
       postDto.getPost().setCreateTime(new java.util.Date());
       int i = pfmTenantPostRepository.insert(postDto.getPost());
       String postId = postDto.getPost().getPostCd();
       int j = 0; // 仅仅作为统计条数，没有意义
       if(postDto.getRoleIds() != null){
           for(String roleId : postDto.getRoleIds()){
               PfmPostRole postRole = new PfmPostRole();
               // 手动设置职位角色ID，注意修改
               postRole.setId(StringUtils.getUUID());
               postRole.setPostId(postId);
               postRole.setRoleId(roleId);
               j = j + pfmPostRoleRepository.insert(postRole);
           }
       }
        return i+j;
    }

    /**
     * <p>真删除数据</p>
     * @author 
     * @date 2015-09-26 15:42
     * @update 2015-10-08 16:15, 删除职位的同时，更新用户的职位ID为null
     * @param String postCd 职务ID
     * @return int 返回是否删除成功的标志，删除成功一条数据返回整型1，两条返回2
     * @description 从数据库中将本条记录永久删除
     */
    @Override
    public int delete(PostDto postDto) {
//**********************************************update by *****start***
        int i = 0;  // 删除成功标志，删除和更新用户成功的条数
    	String tenantCd = postDto.getPost().getTenantCd();  // 商户ID
    	String postCd = postDto.getPost().getPostCd();      // 职位ID
    	PfmUserCriteria criteria = new PfmUserCriteria();   // 组装查询条件
    	criteria.setPostCd(postCd, Operator.equal);
    	List<PfmUser> users = userRepository.findByCriteria(criteria);   // 查询该职位下所有用户
    	for(PfmUser user : users){
    	    user.setPostCd(null);    // 设置职位ID为null
    	   i = i + userRepository.update(user);
    	}
    	i = i + pfmTenantPostRepository.delete(tenantCd,postCd);
        return i;
//**********************************************update by *****end*****
    }

    /**
     * <p>获取角色对应的菜单列表</p>
     * @author 
     * @date 2015-09-30 11:46
     * @update 2015-10-08 12:03
     * @param List<PfmRole> roles 需要组合菜单的角色列表
     * @return Map<String, Object> 由角色对应的菜单集合生成的菜单Map（去除重复）
     * @description 根据传入的角色列表，生成相应的菜单Map，如果角色为空，则返回空的Map：{}
     */
    @Override
    public Map<String, Object> findPostRoleMenu(List<PfmRole> roles) {
        // 最终要返回的数据
        Map<String, Object> menuMap = new HashMap<String, Object>();
        // 角色为空时，返回空的Map
        if(roles != null && !roles.isEmpty()){
            // 存储角色对应的所有菜单
            List<PfmMenu> menus = new ArrayList<PfmMenu>();
            for(PfmRole role : roles){   // 循环角色
                // 将角色拥有的菜单集合起来
                menus.addAll(roleQuery.findRoleMenu(role.getRoleId()));
            }
            // 存储菜单ID集合，存储第一次出现的菜单ID，与之后的菜单ID进行对比，起标志作用
            List<String> menuIds = new ArrayList<String>();
            // 循环菜单集合
            for(Iterator<PfmMenu> iter = menus.iterator();iter.hasNext();){
                PfmMenu menu = iter.next();
                // 如果当前菜单已经存在集合中，则移除此菜单，否则将菜单ID加入标志集合中
                if(menuIds.contains(menu.getMenuId())){
                    iter.remove();
                }else{
                    menuIds.add(menu.getMenuId());
                }
            }
            
            // 循环每个菜单，查找其子集，组成Map对象
            if(menus != null && !menus.isEmpty()){
                // 生成根节点Map对象
                Map<String, Object> parent = new HashMap<String, Object>();
                for(PfmMenu menu : menus){
                    // 生成临时的Map对象，作为key对应的Map对象
                    Map<String, Object> value = new HashMap<String, Object>();
                    // 判断当前菜单是否为根节点
                    if(StringUtils.isEmpty(menu.getMenuParentId())){
                        value.put("name", menu.getMenuName());
                        // 这里设置checkbox默认选中状态
                        value.put("checked", true);
                        // 这里设置子节点为空的Map,而非空对象
                        value.put("children", new HashMap<String, Object>());
                        // 为根节点增加第一个子节点"key":{"name":"name","checked":"checked","children":{}}
                        parent.put(menu.getMenuId(), value);
                        // 获得当前一级菜单下的所有子菜单Map
                        getChilds(menu.getMenuId(), menus, parent);
                    }
                }
                // 将当前一级菜单形成的Map对象加入到总的Map之中
                menuMap.putAll(parent);
            }
        }
        return menuMap;
    }
    
    /**
     * <p>递归获得菜单对应的子节点并逐级组成Map对象</p>
     * @author 
     * @date 2015-10-02 15:21
     * @param parentId 需要获取子节点的菜单ID
     * @param menus 相应角色下对应的所有菜单集合
     * @param parent 当前菜单对应的父级Map对象，用于对父级Map的children节点赋值
     */
    public void getChilds(String parentId, List<PfmMenu> menus, Map<String, Object> parent){
        List<PfmMenu> childs = new ArrayList<PfmMenu>();
        
        // 在整个菜单集合中查找当前菜单的子菜单
        for(PfmMenu menu : menus){
            if(parentId.equals(menu.getMenuParentId())){
                childs.add(menu);
            }
        }
        if(!childs.isEmpty()){  // 如果有子菜单
            for(PfmMenu child : childs){ // 循环子菜单
                // 递归调用自身方法，同时将当前菜单以及当前菜单对应的父级Map对象传入generateMenuMap方法中
                getChilds(child.getMenuId(), menus, generateMenuMap(child, parent));
            }
        }
    }
    
    /**
     * <p>生成菜单Map</p>
     * @author 
     * @date 2015-10-02 15:21
     * @param PfmMenu menu 需要加入Map的菜单对象
     * @param Map<String, Object> parent 当前菜单对应的父级Map对象，用于对父级Map的children节点赋值
     * @return Map<String, Object> 当前菜单所生成的Map节点，供下一次递归使用
     * @description 传入一个菜单和对应的父级Map对象，将当前菜单赋值到父级Map的children之后，返回当期菜单所对应的Map对象
     */
    @SuppressWarnings("unchecked")
    public Map<String, Object> generateMenuMap(PfmMenu menu, Map<String, Object> parent){
        Map<String, Object> child = new HashMap<String, Object>();
        child.put("name", menu.getMenuName());
        child.put("checked", "checked");
        child.put("children", new HashMap<String, Object>());
        // 获得当父级菜单对应的Map对象
        Map<String, Object> temp = (Map<String, Object>) parent.get(menu.getMenuParentId());
        // 获得当父级菜单对应子节点的Map对象
        Map<String, Object>  children = (Map<String, Object>) temp.get("children");
        // 将当前菜单生成的Map对象加入到父级菜单的子节点中
        children.put(menu.getMenuId(), child);
        // 返回子节点Map对象，供递归使用
        return children;
    }
    
    /**
     * <p>修改界面，查找职位，需要选中的角色列表，菜单Map信息</p>
     * @author 
     * @date 2015-10-03 08:34
     * @param PostDto postDto 包含 postCd 职位ID
     * @return PostDto 数据传输对象
     * @description 根据职位ID，查询职位信息，职位对应的角色列表，获取所有职位列表相应的菜单Map
     */
    @Override
    public PostDto findPost(PostDto postDto) {
        String postId = postDto.getPost().getPostCd();
        postDto.setPost(findOne(postId));   // 获取职位信息
        List<PfmRole> roles = roleQuery.findPostRole(postId);  // 查询角色对应的所有角色
        List<String> roleids = new ArrayList<String>();
        for(PfmRole role : roles){
            roleids.add(role.getRoleId());   // 将角色id赋值到数据传输对象中
        }
        postDto.setRoleIds(roleids);    // 根据职位ID获取，界面显示时，选中相应的角色
        postDto.setMenuMap(findPostRoleMenu(roles));   // 获取职位对应的所有菜单Map
        return postDto;
    }
    
    /**
     * <p>成员设置界面，获取职位对应的用户列表</p>
     * @author 
     * @date 2015-10-03 08:41
     * @param PostDto postDto 包含  postCd 职位ID
     * @return PostDto 返回界面需要的，数据传输对象
     * @description 根据职位ID，获取职位对应的用户列表
     */
    @Override
    public PostDto findPostUser(PostDto postDto) {
        String postId = postDto.getPost().getPostCd();
        postDto.setPost(findOne(postId));   // 获取职位信息
        PfmUserCriteria criteria = new PfmUserCriteria();   // 组装查询条件
        criteria.setPostCd(postId, Operator.equal);
        postDto.setUsers(userRepository.findByCriteria(criteria));   // 获取用户列表
       return postDto;
    }

    /**
     * <p>根据条件，查询没有职位的所有用户</p>
     * @author 
     * @date 2015-10-13 17:45
     * @param String realName 用户真实姓名
     * @return List<PfmUser> 用户集合
     * @description 成员设置界面，根据用户真实姓名查询符合条件的没有职位的用户，
     *              查询条件为空时，查询所有的没有职位的用户
     */
    @Override
    public List<PfmUser> findPfmUserOfNoPost(PostDto postDto) {
        String realName = postDto.getUser().getRealName();
       return userManageQuery.findPfmUserOfNoPost(realName);   // 获取没有职位的用户列表
    }
    /**
     * <p>为职位增加用户</p>
     * @author 
     * @date 2015-10-03 16:49
     * @param PostDto postDto 包含 将要被增加的用户ID集合
     * @return int 增加成功的标志
     */
    @Override
    public int addUserForPost(PostDto postDto) {
        int num = 0;
        String postId = postDto.getPost().getPostCd();
        List<String> userIds = postDto.getUserIds();
        if(userIds != null){
            for(String userid : userIds){
                PfmUser pfmUser = userRepository.findOne(userid);
                pfmUser.setPostCd(postId);
                num = num + userRepository.dynamicUpdate(pfmUser);
            }
        }
        return num;
    }

    /**
     * <p>组合角色对应的菜单</p>
     * @author 
     * @date 2015-10-08 11:52
     * @param PostDto postDto 包含 角色ID集合
     * @return PostDto 返回界面需要的，数据传输对象
     * @description 修改、增加界面，选中相应的角色后，发送AJAX请求，
     *              返回组合之后的菜单列表<br>
     *              postDto.roleIds 在这里仅仅利用了第一个空间，始终有值，
     *              当没有选中角色时，传入后台的是长度为0的字符转，不是null
     */
    @Override
    public PostDto assembleMenus(PostDto postDto) {
        List<PfmRole> roles = new ArrayList<PfmRole>();
        // 获取角色ID字符串进行拆分
        String [] roleIds = postDto.getRoleIds().get(0).split(",");
        // 判断数组是否有值且不是空字符串
        if(roleIds.length > 0 && StringUtils.isNotEmpty(roleIds[0])){
            for(String roleId : roleIds){
                // 获取对应的角色
                roles.add(pfmRoleRepository.findOne(roleId));
            }
            // 生成menuMap
            postDto.setMenuMap(findPostRoleMenu(roles));
        }
        return postDto;
    }

    /**
     * <p>为职位增加用户</p>
     * @author 
     * @date 2015-10-08 18:38
     * @param PostDto postDto 包含 将要被删除的用户ID集合
     * @return int 删除成功的标志
     */
    @Override
    public int delUserForPost(PostDto postDto) {
        String userId = postDto.getUser().getId();
        PfmUser pfmUser = userRepository.findOne(userId);
        pfmUser.setPostCd(null);
        return userRepository.update(pfmUser);
    }

    /**
     * <p>AJAX校验职位名称的唯一性</p>
     * @author 
     * @date 2015-10-14 11:48
     * @param data,界面传入的需要校验的参数，
     * @param postDto 包含职位名称
     * @return boolean 标志职位名称是否被占用
     * @description AJAX校验职位名称的唯一性
     */
    @Override
    public boolean checkPostName(String data, PostDto postDto) {
        
        // 标志变量
        boolean flag = true;
        
        List<PfmTenantPost> posts = null;
        if(StringUtils.isNotEmpty(data)){    // 此处防止查询全部数据，造成不必要的资源浪费
            PfmTenantPostCriteria criteria = new PfmTenantPostCriteria();
            criteria.setPostName(data, Operator.equal);
            // 查询记录
            posts = pfmTenantPostRepository.findByCriteria(criteria);
        }
        
        // 查询数据库获取当前修改的职位名称
        PfmTenantPost post = pfmTenantPostRepository.findOne(postDto.getPost().getPostCd());
        // 与用户修改后的职位名称进行比较，如果相同则清空查询到的数据，不给予验证
        if(post != null && data.equals(post.getPostName())){
            posts.clear();
        }
        if(posts != null && !posts.isEmpty()){  // 如果结果不为空则应被占用
            flag = false;
        }
        return flag;
    }
    
    /**
     * 获取职位DropdownList值
     * @author 
     * 
     * @return Map<String,String> 组织机构DropdownList Map
     */
    @Override
    public Map<String,String> getPostDDL(){
    	
    	Map<String,String> ddlMap = new HashMap<String, String>();
    	
    	PfmTenantPostCriteria criteria = new PfmTenantPostCriteria();
    	criteria.setValidFlag("1", Operator.equal);	//只查询启用状态的职位
    	
    	List<PfmTenantPost> postList = pfmTenantPostRepository.findByCriteria(criteria);
    	
    	if(postList != null && !postList.isEmpty()){
    		
    		for (PfmTenantPost pfmTenantPost : postList) {
				
    			if(pfmTenantPost != null){
    				ddlMap.put(pfmTenantPost.getPostCd(), pfmTenantPost.getPostName());
    			}
			}
    	}
    	return ddlMap;
    }
}
