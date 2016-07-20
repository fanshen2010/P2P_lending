package cn.com.p2p.ui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.framework.enumpack.SettingTypeEnum;
import cn.com.p2p.system.service.SettingService;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class GetSettingMethod implements TemplateMethodModel{
	@Autowired
	private SettingService settingService;	//系统设定 settingService 接口
    @Autowired
    private UploadFileRepository uploadFileRepository;
	@Override
	public Object exec(List argList) throws TemplateModelException {
		String value="";
		
		SysSetting sysSetting = settingService.findSysSettingBySettingCode(argList.get(0).toString());
		if (sysSetting != null) {
			value = sysSetting.getSettingValue();
			if(SettingTypeEnum.SETTING_TYPE_PICTURE.getCode().equals(sysSetting.getSettingType())){
			    UploadFile file = uploadFileRepository.findOne(value);
			    if(file != null){
			        value = file.getFileUrlOriginal();
			    }
			}
		}
		return value;
	}

}
