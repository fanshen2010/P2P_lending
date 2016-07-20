package cn.com.p2p.usermangent.service;

import java.util.List;
import java.util.Map;

import cn.com.p2p.domain.user.entity.PfmRole;
import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.security.login.service.dto.PostDto;

public interface PostService {

    /**
     * <p>查询所有的职务记录和职位对应的角色列表，分页显示</p>
     * @author 
     * @date 2015-09-26 13:27
     * @param postDto 包含检索条件实体PfmTenantPostCriteria
     * @return PostDto 包含 List<PfmTenantPost> 职务集合
     * @description 查询职务记录，并按照分页限制的条数显示
     */
    PostDto findPostRole(PostDto postDto);

    /**
     * <p>根据职务记录ID，查询唯一一条记录</p>
     * @author 
     * @date 2015-09-26 13:27
     * @param String postCd 职务记录ID
     * @return PfmTenantPost 职务对象
     * @description 根据职务记录ID，查询唯一一条记录
     */
    PfmTenantPost findOne(String postCd);

    /**
     * <p>动态更新，更新职位信息及相应的角色职位关联表</p>
     * @author 
     * @date 2015-09-26 14:32
     * @param PfmTenantPost post 职务对象
     * @return int 返回是否更新成功的标志，更新成功一条数据返回整型1，两条返回2
     * @description 根据职务记录ID，动态的更新数据，如果相应的属性值为空，则不更新此属性对应的字段
     */
    int dynamicUpdate(PostDto postDto);

    /**
     * <p>保存新数据</p>
     * @author 
     * @date 2015-09-26 15:16
     * @param PostDto postDto 数据传输对象
     * @return int 返回是否保存成功的标志，保存成功一条数据返回整型1，两条返回2
     * @description 保存数据，向数据库中插入插入一条数据
     */
    int insert(PostDto postDto);

    /**
     * <p>真删除数据</p>
     * @author 
     * @date 2015-09-26 15:42
     * @param PostDto postDto 包含 postCd 职务ID
     * @return int 返回是否删除成功的标志，删除成功一条数据返回整型1，两条返回2
     * @description 从数据库中将本条记录永久删除
     */
    int delete(PostDto postDto);

    /**
     * <p>获取角色对应的菜单列表</p>
     * @author 
     * @date 2015-09-30 11:46
     * @update 2015-10-08 12:03
     * @param List<PfmRole> roles 需要组合菜单的角色列表
     * @return Map<String, Object> 由角色对应的菜单集合生成的菜单Map（去除重复）
     * @description 根据传入的角色列表，生成相应的菜单Map，如果角色为空，则返回空的Map：{}
     */
    Map<String, Object> findPostRoleMenu(List<PfmRole> roles);

    /**
     * <p>修改界面，查找职位，角色列表，菜单Map信息</p>
     * @author 
     * @date 2015-10-03 08:34
     * @param PostDto postDto 包含 postCd 职位ID
     * @return PostDto 数据传输对象
     * @description 根据职位ID，查询职位信息，职位对应的角色列表，获取所有职位列表相应的菜单Map
     */
    PostDto findPost(PostDto postDto);

    /**
     * <p>成员设置界面，获取职位对应的用户列表</p>
     * @author 
     * @date 2015-10-03 08:41
     * @param PostDto postDto 包含 postCd 职位ID
     * @return PostDto 返回界面需要的，数据传输对象
     * @description 根据职位ID，获取职位对应的用户列表
     */
    PostDto findPostUser(PostDto postDto);

    /**
     * <p>为职位增加用户</p>
     * @author 
     * @date 2015-10-03 16:49
     * @param PostDto postDto 包含 将要被增加的用户ID集合
     * @return PostDto 返回界面需要的，数据传输对象
     */
    int addUserForPost(PostDto postDto);

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
    PostDto assembleMenus(PostDto postDto);

    /**
     * <p>为职位增加用户</p>
     * @author 
     * @date 2015-10-08 18:38
     * @param PostDto postDto 包含 将要被删除的用户ID集合
     * @return int 删除成功的标志
     */
    int delUserForPost(PostDto postDto);

    /**
     * <p>根据条件，查询没有职位的所有用户</p>
     * @author 
     * @date 2015-10-13 17:45
     * @param PostDto postDto 包含 realName 用户真实姓名
     * @return List<PfmUser> 用户集合
     * @description 成员设置界面，根据用户真实姓名查询符合条件的没有职位的用户，
     *              查询条件为空时，查询所有的没有职位的用户
     */
    List<PfmUser> findPfmUserOfNoPost(PostDto postDto);

    /**
     * <p>AJAX校验职位名称的唯一性</p>
     * @author 
     * @date 2015-10-14 11:48
     * @param data,界面传入的需要校验的参数，
     * @param postDto 包含职位名称
     * @return boolean 标志职位名称是否被占用
     * @description AJAX校验职位名称的唯一性
     */
    boolean checkPostName(String data, PostDto postDto);

    
    /**
     * 获取职位DropdownList值
     * @author 
     * 
     * @return Map<String,String> 组织机构DropdownList Map
     */
	public Map<String, String> getPostDDL();

}
