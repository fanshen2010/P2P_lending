package cn.com.p2p.system.service.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.system.criteria.SysSettingCriteria;
import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.repository.SysSettingRepository;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.GroupCodeEnum;
import cn.com.p2p.framework.enumpack.SettingTypeEnum;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.system.service.dto.SysSettingDto;
import cn.com.p2p.utils.Constants;

@Service
public class SettingServiceImpl implements SettingService{

	@Autowired
	SysSettingRepository sysSettingRepository;
	
	@Autowired
	private UploadFileRepository uploadFileRepository;
	
	@Override
	public SysSettingDto findSysSettingAll(String tenantCd) {
	    
        SysSettingDto sysSettingDto = new SysSettingDto();
        // 实例化统设定组集合列表
        sysSettingDto.setSettingLists(new LinkedList<List<SysSetting>>());
        // 实例化系统设定组名称集合
        sysSettingDto.setSettingListName(new LinkedList<String>());
        
        // 查询实体
		SysSettingCriteria settingCriteria = new SysSettingCriteria();
		
        // 图片参数对应的上传文件集合
        List<UploadFile> uploadFiles = new LinkedList<UploadFile>();
        
        // 图片参数对应的参数名
        List<String> picSettingName = new LinkedList<String>();
        
        // 图片参数对应的参数编码
        List<String> picSettingCode = new LinkedList<String>();
		
		//基本设置
        settingCriteria.setGroupCode(Constants.BASIC_SETTING, Operator.equal);
        List<SysSetting> basic = sysSettingRepository.findByCriteria(settingCriteria);
        for(Iterator<SysSetting> iter = basic.iterator(); iter.hasNext();){
            SysSetting setting = iter.next();
            // 判断是否是图片参数，如果是，则加入到图片列表里
            if(SettingTypeEnum.SETTING_TYPE_PICTURE.getCode().equals(setting.getSettingType())){
                uploadFiles.add(uploadFileRepository.findOne(setting.getSettingValue()));
                picSettingName.add(setting.getSettingTitle());
                picSettingCode.add(setting.getSettingCode());
                iter.remove();     // 将图片参数移除，防止重复显示
            }
        }
        sysSettingDto.setBasicSetting(basic);
        
        sysSettingDto.setProLogoUploadFile(uploadFiles);
        sysSettingDto.setPicSettingName(picSettingName);
        sysSettingDto.setPicSettingCode(picSettingCode);
        
		// 基础费用相关参数
        settingCriteria.setGroupCode(GroupCodeEnum.GROUP_CODE_COST_SETTING.getCode(), Operator.equal);
        sysSettingDto.getSettingLists().add(sysSettingRepository.findByCriteria(settingCriteria));
        sysSettingDto.getSettingListName().add(GroupCodeEnum.GROUP_CODE_COST_SETTING.getValue());
        
		// 时间相关参数
		settingCriteria.setGroupCode(GroupCodeEnum.GROUP_CODE_DATE_SETTING.getCode(), Operator.equal);
		sysSettingDto.getSettingLists().add(sysSettingRepository.findByCriteria(settingCriteria));
		sysSettingDto.getSettingListName().add(GroupCodeEnum.GROUP_CODE_DATE_SETTING.getValue());
        
		return sysSettingDto;
	}

	@Override
	public void updateSysSettingBySettingCode(List<SysSetting> sysSetting) {
		if(sysSetting.size()>0){
			for(int i=0;i<sysSetting.size();i++){
				sysSettingRepository.dynamicUpdate(sysSetting.get(i));
			}
		}
		
	}

	@Override
	public SysSetting findSysSettingBySettingCode(String settingCode) {
		return sysSettingRepository.findSysSettingBySettingCode(settingCode);
	}

	@Override
	public int updateBusiness(SysSetting sysSetting) {
		return sysSettingRepository.dynamicUpdate(sysSetting);
	}


    @Override
    public void updateAll(List<SysSetting> sysSetting) {
        if(sysSetting.size()>0){
            for(int i=0;i<sysSetting.size();i++){
                sysSettingRepository.update(sysSetting.get(i));
            }
        }
    }
}
