/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        SysSettingRepository.java
 * Description:       SysSettingRepository类定义
 *        实体 SysSetting的持久化处理
 * Dependencies:
 *        cn.com.p2p.domain.system.entity.SysSetting
 *        cn.com.p2p.domain.system.Criteria.SysSettingCriteria
 *        cn.com.p2p.domain.system.repository.SysSettingRepository.xml
 *        cn.com.p2p.framework.dao.DynamicQuerySqlBuilder
 * History:
 *     Date                 Modifier             Log
 *     2015-10-14             fero auto          Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.system.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.system.criteria.SysSettingCriteria;
import cn.com.p2p.framework.dao.DynamicQuerySqlBuilder;


/**
 * SysSettingRepository定义.
 * <p>
 * 数据访问层<系统设定>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author fero auto
 */
public interface SysSettingRepository {

    /**
     * 按主键进行查询，返回唯一一条记录
     * <p>
     * @param  String id
     * @return SysSetting
     */
	public SysSetting findOne(String id);

	
    /**
     * 按业务主键进行查询，返回唯一一条记录
     * <p>
     * @param  String settingCode
     * @return SysSetting
     */
	public SysSetting findSysSettingBySettingCode(String settingCode);

    /**
     * 无条件检索，返回实体集合
     * <p>
     * @return List<SysSetting>
     */
	public List<SysSetting> findAll();

    /**
     * 无条件分页检索，返回实体集合
     * <p>
     * @param  SysSettingSearchCriteria 检索Page信息
     * @return List<EventName>
     */
	public List<SysSetting> findPageAll(SysSettingCriteria criteria);

    /**
     * 按条件动态检索，返回实体集合
     * <p>
     * @param  SysSettingSearchCriteria 检索条件
     * @return List<SysSetting>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<SysSetting> findByCriteria(SysSettingCriteria criteria);
    
    /**
     * 按条件动态翻页检索，返回实体集合
     * <p>
     * @param  SysSettingSearchCriteria 检索条件
     * @return List<SysSetting>
     */
    @SelectProvider(type=DynamicQuerySqlBuilder.class,method="findByCriteriaSql")
	public List<SysSetting> findPageByCriteria(SysSettingCriteria criteria);
	
	/**
     * 插入一条新纪录，正确插入时返回值为 1
     * <p>
     * @param  SysSetting sysSetting
     * @return 插入成功标志
     */
	public int insert(SysSetting sysSetting);

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * <p>
     * @param  SysSetting sysSetting
     * @return 更新成功标志
     */
	public int dynamicUpdate(SysSetting sysSetting);

    /**
     * 按主键进行更新，根据实体的实际属性值进行更新
     * <p>
     * @param  SysSetting sysSetting
     * @return 更新成功标志
     */
	public int update(SysSetting sysSetting);

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

