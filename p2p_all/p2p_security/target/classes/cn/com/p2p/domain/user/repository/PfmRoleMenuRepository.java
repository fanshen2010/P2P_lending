/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmRoleMenuRepository.java
 * Description:       PfmRoleMenuRepository类定义
 *        实体 PfmRoleMenu的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmRoleMenu
 *        cn.com.p2p.domain.user.Criteria.PfmRoleMenuCriteria
 *        cn.com.p2p.domain.user.repository.PfmRoleMenuRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-03             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.user.entity.PfmRoleMenu;
import cn.com.p2p.domain.user.criteria.PfmRoleMenuCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmRoleMenuRepository定义.
 * <p>
 * 数据访问层<菜单角色关联表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmRoleMenuRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return PfmRoleMenu
     */
	public PfmRoleMenu findOne(String id);

	
    /**
     * 按menuId 查询对应的角色集合
     * <p>
     * @param  String menuId
     * @return PfmRoleMenu
     */
	public List<PfmRoleMenu> findPfmRoleMenuByMenuId(String menuId);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmRoleMenu>
     */
	public List<PfmRoleMenu> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmRoleMenuSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmRoleMenu> findPageAll(PfmRoleMenuCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmRoleMenuSearchCriteria 检索条件
     * @return List<PfmRoleMenu>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmRoleMenu> findByCriteria(PfmRoleMenuCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmRoleMenuSearchCriteria 检索条件
     * @return List<PfmRoleMenu>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmRoleMenu> findPageByCriteria(PfmRoleMenuCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmRoleMenu pfmRoleMenu
     * @return 插入成功标志
     */
	public int insert(PfmRoleMenu pfmRoleMenu);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmRoleMenu pfmRoleMenu
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmRoleMenu pfmRoleMenu);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmRoleMenu pfmRoleMenu
     * @return 更新成功标志
     */
	public int update(PfmRoleMenu pfmRoleMenu);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param id
     * @return 假删除成功标志
     */
	public int suspend(@Param("id") String id);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param id
     * @return 删除成功标志
     */
	public int delete(@Param("id") String id);

}

