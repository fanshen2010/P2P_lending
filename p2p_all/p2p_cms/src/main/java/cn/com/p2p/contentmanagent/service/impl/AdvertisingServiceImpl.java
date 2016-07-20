package cn.com.p2p.contentmanagent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.contentmanagent.service.AdvertisingService;
import cn.com.p2p.domain.cms.criteria.AdvertisingCriteria;
import cn.com.p2p.domain.cms.entity.Advertising;
import cn.com.p2p.domain.cms.repository.AdvertisingRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.StringUtils;

/**
 * <p>广告位管理接ServiceImpl</p>
 * @author 
 */
@Service
public class AdvertisingServiceImpl implements AdvertisingService {

    /** 广告位管理组件 */
    @Autowired
    private AdvertisingRepository advertisingRepository;
    
    /**
     * <p>查询所有的广告位实体集合</p>
     * @author 
     * @return List<Advertising> 广告位实体集合
     * @description 无条件查询所有
     */
    @Override
    public List<Advertising> findAll() {
        return advertisingRepository.findAll();
    }

    /**
     * <p>保存广告位实体</p>
     * @author 
     * @param advertising 广告位实体
     * @return int 保存成功的条数
     * @description 无
     */
    @Override
    public int save(Advertising advertising) {
        // TODO 手动设置ID
        advertising.setId(StringUtils.getUUID());
        return advertisingRepository.insert(advertising);
    }

    /**
     * <p>动态更新广告位实体</p>
     * @author 
     * @param advertising 广告位实体
     * @return int 更新成功的条数
     * @description 如果待更新的实体属性值为空("",null)，则不进行该字段的更新
     */
    @Override
    public int dynamicUpdate(Advertising advertising) {
        return advertisingRepository.dynamicUpdate(advertising);
    }

    /**
     * <p>删除广告位实体</p>
     * @author 
     * @param id 广告位实体ID
     * @return int 删除成功的条数
     * @description 无
     */
    @Override
    public int delete(String id) {
        return advertisingRepository.delete(id);
    }

    /**
     * <p>查找广告位实体</p>
     * @author 
     * @param id 广告位实体ID
     * @return Advertising 查询到的广告位实体
     * @description 无
     */
    @Override
    public Advertising findOne(String id) {
        return advertisingRepository.findOne(id);
    }

    /**
     * <p>广告位编码唯一性校验</p>
     * @author 
     * @param adverCode 广告位实体Code
     * @param id 广告位实体ID
     * @return boolean 是否可用的标志
     * @description 比较用户修改后的Code和原有Code，相同时，校验通过
     */
    @Override
    public boolean checkAdvertisingCode(String adverCode, String id) {
        boolean isUsable = true;   // 是否可用的标志
        // 存储根据编码查询得到的实体集合
        List<Advertising> advertisings = null;
        // 过滤  "" null 防止查询全部数据，当code=""时，返回true，
        // 但是这两种情况同时也被前台和后台校验过滤,所以会被拦截
        if(StringUtils.isNotEmpty(adverCode)){
            // 根据code查询实体集合
            AdvertisingCriteria advertisingCriteria = new AdvertisingCriteria();
            advertisingCriteria.setAdverCode(adverCode, Operator.equal);
            advertisings = advertisingRepository.findByCriteria(advertisingCriteria);
        }
        // 根据ID查询实体，在修改界面，用户未修改code时，给予通过
        Advertising advertising = advertisingRepository.findOne(id);
        
        // 判断用户是否修改了原code，如果未修改，则清除查询到的数据
        if(advertising != null && adverCode.equals(advertising.getAdverCode())){
            advertisings.clear();
        }
        // 当查询到的实体集合有元素时，则拦截
        if(advertisings != null && !advertisings.isEmpty()){  // 如果结果不为空则应被占用
            isUsable = false;
        }
        return isUsable;
    }
}
