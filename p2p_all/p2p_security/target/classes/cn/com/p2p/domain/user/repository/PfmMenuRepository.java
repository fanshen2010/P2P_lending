/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmMenuRepository.java
 * Description:       PfmMenuRepository类定义
 *        实体 PfmMenu的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.user.entity.PfmMenu
 *        cn.com.p2p.domain.user.Criteria.PfmMenuCriteria
 *        cn.com.p2p.domain.user.repository.PfmMenuRepository.xml
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

import cn.com.p2p.domain.user.entity.PfmMenu;
import cn.com.p2p.domain.user.criteria.PfmMenuCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * PfmMenuRepository定义.
 * <p>
 * 数据访问层<商户单位MENU管理>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface PfmMenuRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String menuId
     * @return PfmMenu
     */
	public PfmMenu findOne(String menuId);

	

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<PfmMenu>
     */
	public List<PfmMenu> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  PfmMenuSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<PfmMenu> findPageAll(PfmMenuCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  PfmMenuSearchCriteria 检索条件
     * @return List<PfmMenu>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmMenu> findByCriteria(PfmMenuCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  PfmMenuSearchCriteria 检索条件
     * @return List<PfmMenu>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<PfmMenu> findPageByCriteria(PfmMenuCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  PfmMenu pfmMenu
     * @return 插入成功标志
     */
	public int insert(PfmMenu pfmMenu);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  PfmMenu pfmMenu
     * @return 更新成功标志
     */
	public int dynamicUpdate(PfmMenu pfmMenu);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  PfmMenu pfmMenu
     * @return 更新成功标志
     */
	public int update(PfmMenu pfmMenu);

    /**
     * 按主键把记录的删除flag设置为true，进行假删除
     * <p>
     * @param menuId
     * @return 假删除成功标志
     */
	public int suspend(@Param("menuId") String menuId);

    /**
     * 按主键进行删除，删除成功返回 1
     * <p>
     * @param menuId
     * @return 删除成功标志
     */
	public int delete(@Param("menuId") String menuId);

}

