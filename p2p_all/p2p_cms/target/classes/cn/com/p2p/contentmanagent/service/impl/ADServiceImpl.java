package cn.com.p2p.contentmanagent.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.cms.ADDto;
import cn.com.p2p.contentmanagent.service.ADService;
import cn.com.p2p.domain.cms.criteria.AdvertisementCriteria;
import cn.com.p2p.domain.cms.criteria.AdvertisingCriteria;
import cn.com.p2p.domain.cms.entity.Advertisement;
import cn.com.p2p.domain.cms.entity.Advertising;
import cn.com.p2p.domain.cms.repository.AdvertisementRepository;
import cn.com.p2p.domain.cms.repository.AdvertisingRepository;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.util.StringUtils;


/**
 * <p>广告管理接口实现类</p>
 * @author
 */
@Service
public class ADServiceImpl implements ADService {

    /** 广告版位数据组件 */
    @Autowired
    private AdvertisingRepository advertisingRepository;
    
    /** 广告内容数据组件 */
    @Autowired
    private AdvertisementRepository advertisementRepository;
    
    /** 文件上传数据组件 */
    @Autowired
    private UploadFileRepository UploadFileRepository;
    
    /**
     * <p>查询所有的广告栏目和栏目对应的广告内容</p>
     * @author 
     * @return ADDto 广告管理数据传输对象
     * @description 查询所有的广告栏目和栏目对应的广告内容
     */
    @Override
    public ADDto findAllAD() {
        ADDto adDto = new ADDto();
        AdvertisingCriteria advertisingCriteria = new AdvertisingCriteria();
        advertisingCriteria.setStatus("1", Operator.equal);
        List<Advertising> advertisings = advertisingRepository.findByCriteria(advertisingCriteria);
        List<List<Advertisement>> adLists = new ArrayList<List<Advertisement>>();
        for(Advertising advertising: advertisings){
            AdvertisementCriteria criteria = new AdvertisementCriteria();
            criteria.setAdverCode(advertising.getAdverCode(), Operator.equal);
            criteria.setSortFields(AdvertisementCriteria.OrderField.orderNum, SortType.ASC);
            adLists.add(advertisementRepository.findByCriteria(criteria));
        }
        adDto.setAdvertisings(advertisings);
        adDto.setAdLists(adLists);
        return adDto;
    }

    /**
     * <p>根据广告ID查询广告实体</p>
     * @author 
     * @param ADDto 包含 广告ID
     * @return ADDto 广告管理数据传输对象
     * @description 根据广告ID查询广告实体
     */
    @Override
    public ADDto findAD(ADDto aDDto) {
        Advertisement advertisement = advertisementRepository.findOne(aDDto.getAdvertisement().getId());
        if(advertisement != null && advertisement.getAdPic() != null){
            UploadFile uploadFile = UploadFileRepository.findOne(advertisement.getAdPic());
            if(uploadFile != null){
                aDDto.setUploadFile(uploadFile);
            }
        }
        aDDto.setAdvertisement(advertisement);
        aDDto.setAdvertising(advertisingRepository.findOne(aDDto.getAdvertising().getId()));
        return aDDto;
    }

    /**
     * <p>保存广告实体</p>
     * @author 
     * @param ADDto 包含 广告实体对象
     * @param uploadFile 存储图片属性的实体，此实体不包含ID，需手动设置
     * @return int 保存成功的条数
     */
    @Override
    public int save(ADDto dto, UploadFile uploadFile) {
        if (uploadFile == null) {
            return 0;
        }
        uploadFile.setId(StringUtils.getUUID());
        int flag = UploadFileRepository.insert(uploadFile);
        // 将上传图片ID，保存到广告是实体中
        if(flag == 1 && uploadFile != null){
            dto.getAdvertisement().setAdPic(uploadFile.getId());
        }
        // 此处手动设置ID， 请注意修改
        dto.getAdvertisement().setId(StringUtils.getUUID());
        return advertisementRepository.insert(dto.getAdvertisement());
    }

    /**
     * <p>更新广告实体</p>
     * @author 
     * @param ADDto 包含 广告实体对象
     * @param uploadFile 存储图片属性的实体，此实体不包含ID，需手动设置
     * @param fileId 存储图片ID，标志用户是否重新上传了图片
     * @return int 更新成功的条数
     * @description 如果uploadFile有值说明用户新上传了图片，无论是添加界面还是修改界面，此时fileId==null
     *              如果fileId有值说明用户在修改界面，没有对原有图片进行修改，此时uploadFile==null
     *              如果用户修改原有图片后，未上传新的图片，此时uploadFile 和 fileId 都等于null,在校验中不通过，不会走此方法
     */
    @Override
    public int update(ADDto dto, UploadFile uploadFile, String fileId) {
        
        Advertisement source =  advertisementRepository.findOne(dto.getAdvertisement().getId());
        try {
            BeanUtils.copyProperties(dto.getAdvertisement(), source, "adverCode","createTime","createUserId");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(uploadFile != null){
            uploadFile.setId(StringUtils.getUUID());
            int flag = UploadFileRepository.insert(uploadFile);
            // 将上传图片ID，保存到广告是实体中
            if(flag == 1 && uploadFile != null){
                source.setAdPic(uploadFile.getId());
            }
        }else{
            // 用户没有对原有图片进行修改时，将原有ID存入到是实体中
            source.setAdPic(fileId);
        }
        
        return advertisementRepository.update(source);
    }

    /**
     * <p>批量更新广告的排序</p>
     * @author 
     * @param ADDto 包含 广告实体对象
     * @return int 更新成功的条数
     * @description 当用户在列表页删除某一条数据的排序时，此记录的排序不给予更新
     */
    @Override
    public int updateOrder(ADDto dto) {
        int i = 0;
        if(dto.getAdvertisements() != null){
            for(Advertisement advertisement : dto.getAdvertisements()){
                // 当用户在列表页删除某一条数据的排序时，此记录的排序不给予更新
                if(advertisement.getOrderNum() != null){
                  i = i + advertisementRepository.dynamicUpdate(advertisement);
                }
            }
        }
        return i;
    }

    /**
     * <p>根据ID删除广告实体</p>
     * @author 
     * @param ADDto 包含 广告实体对象
     * @return int 删除成功的条数
     */
    @Override
    public int delete(ADDto dto) {
        return advertisementRepository.delete(dto.getAdvertisement().getId());
    }
    
    /**
     * <p>根据广告版位查询广告实体(前台首页用)</p>
     * @author 
     * @param ADDto 广告管理数据传输对象
     * @return List<ADDto> 广告管理数据传输对象List
     * @description 根据广告版位查询广告实体
     */
    @Override
    public List<ADDto> findAdByAdvertising(AdvertisementCriteria criteria) {
    	List<Advertisement> advertisementLists = new ArrayList<Advertisement>(); 
    	advertisementLists = advertisementRepository.findPageByCriteria(criteria);
    	
    	List<ADDto> lstADDto  = new ArrayList<ADDto>();
    	for (Advertisement advertisement : advertisementLists) {
			UploadFile uploadFile = UploadFileRepository.findOne(advertisement.getAdPic());
			ADDto adDto = new ADDto();
			adDto.setAdvertisement(advertisement);
			adDto.setUploadFile(uploadFile);
			lstADDto.add(adDto);
		}
        return lstADDto ;
    }

}
