package cn.com.p2p.ui.control;


import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class HeaderContentControl implements TemplateMethodModel{
//	@Autowired
//	private SysSettingService sysSettingService;	//系统设定 sysSettingService 接口

	@Override
	public Object exec(List argList) throws TemplateModelException {
		String value="";
		if (argList.get(0) != null) {
			value = "test";
		}
//		SysSetting sysSetting = sysSettingService.getByCode(argList.get(0).toString());
//		if (sysSetting != null) {
//			value = sysSetting.getSettingValue();
//		}
		return value;
	}

}
