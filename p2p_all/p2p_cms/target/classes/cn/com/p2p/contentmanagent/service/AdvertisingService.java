package cn.com.p2p.contentmanagent.service;

import java.util.List;

import cn.com.p2p.domain.cms.entity.Advertising;

/**
 * <p>广告位管理接Service</p>
 * @author 
 * @date 2015-10-23 09：40
 */
public interface AdvertisingService {

    /**
     * <p>查询所有的广告位实体集合</p>
     * @author 
     * @date 2015-10-23 13:25
     * @return List<Advertising> 广告位实体集合
     * @description 无条件查询所有
     */
    List<Advertising> findAll();

    /**
     * <p>保存广告位实体</p>
     * @author 
     * @date 2015-10-23 13:27
     * @param advertising 广告位实体
     * @return int 保存成功的条数
     * @description 无
     */
    int save(Advertising advertising);

    /**
     * <p>动态更新广告位实体</p>
     * @author 
     * @date 2015-10-23 13:32
     * @param advertising 广告位实体
     * @return int 更新成功的条数
     * @description 如果待更新的实体属性值为空("",null)，则不进行该字段的更新
     */
    int dynamicUpdate(Advertising advertising);

    /**
     * <p>删除广告位实体</p>
     * @author 
     * @date 2015-10-23 13:32
     * @param id 广告位实体ID
     * @return int 删除成功的条数
     * @description 无
     */
    int delete(String id);

    /**
     * <p>查找广告位实体</p>
     * @author 
     * @date 2015-10-23 13:40
     * @param id 广告位实体ID
     * @return Advertising 查询到的广告位实体
     * @description 无
     */
    Advertising findOne(String id);

    /**
     * <p>广告位编码唯一性校验</p>
     * @author 
     * @date 2015-10-23 13:45
     * @param adverCode 广告位实体Code
     * @param id 广告位实体ID
     * @return boolean 是否可用的标志
     * @description 比较用户修改后的Code和原有Code，相同时，校验通过
     */
    boolean checkAdvertisingCode(String adverCode, String id);

}
