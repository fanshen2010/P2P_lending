package cn.com.p2p.contentmanagent.service;

import java.util.List;

import cn.com.p2p.cms.ADDto;
import cn.com.p2p.domain.cms.criteria.AdvertisementCriteria;
import cn.com.p2p.domain.system.entity.UploadFile;


/**
 * <p>广告管理接口类</p>
 * @author g
 * @date 2015-10-03 17:48
 */
public interface ADService {

    /**
     * <p>查询所有的广告栏目和栏目对应的广告内容</p>
     * @author 
     * @date 2015-10-03 18:03
     * @return ADDto 广告管理数据传输对象
     * @description 查询所有的广告栏目和栏目对应的广告内容
     */
    ADDto findAllAD();

    /**
     * <p>根据广告ID查询广告实体</p>
     * @author 
     * @date 2015-10-07 10:25
     * @param ADDto 包含 广告ID
     * @return ADDto 广告管理数据传输对象
     * @description 根据广告ID查询广告实体
     */
    ADDto findAD(ADDto aDDto);

    /**
     * <p>保存广告实体</p>
     * @author 
     * @date 2015-10-07 16:25
     * @param ADDto 包含 广告实体对象
     * @param uploadFile 存储图片属性的实体，此实体不包含ID，需手动设置
     * @return int 保存成功的条数
     */
    int save(ADDto dto, UploadFile uploadFile);

    /**
     * <p>更新广告实体</p>
     * @author 
     * @date 2015-10-07 16:42
     * @param ADDto 包含 广告实体对象
     * @param uploadFile 存储图片属性的实体，此实体不包含ID，需手动设置
     * @param fileId 存储图片ID，标志用户是否重新上传了图片
     * @return int 更新成功的条数
     * @description 如果uploadFile有值说明用户新上传了图片，无论是添加界面还是修改界面，此时fileId==null
     *              如果fileId有值说明用户在修改界面，没有对原有图片进行修改，此时uploadFile==null
     *              如果用户修改原有图片后，未上传新的图片，此时uploadFile 和 fileId 都等于null,在校验中不通过，不会走此方法
     */
    int update(ADDto dto, UploadFile uploadFile, String fileId);

    /**
     * <p>批量更新广告的排序</p>
     * @author 
     * @date 2015-10-07 16:58
     * @param ADDto 包含 广告实体对象
     * @return int 更新成功的条数
     */
    int updateOrder(ADDto dto);

    /**
     * <p>根据ID删除广告实体</p>
     * @author 
     * @date 2015-10-07 17:18
     * @param ADDto 包含 广告实体对象
     * @return int 删除成功的条数
     */
    int delete(ADDto dto);
    
    /**
     * <p>根据广告版位查询广告实体</p>
     * @author 
     * @date 2015-10-14 11:42
     * @param ADDto 广告管理数据传输对象
     * @return List<ADDto> 广告管理数据传输对象List
     * @description 根据广告版位查询广告实体
     */
    List<ADDto> findAdByAdvertising(AdvertisementCriteria criteria);

}
