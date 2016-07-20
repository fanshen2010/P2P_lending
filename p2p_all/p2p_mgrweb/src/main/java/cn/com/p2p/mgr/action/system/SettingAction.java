package cn.com.p2p.mgr.action.system;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.common.dto.FileDto;
import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.system.service.SettingService;
import cn.com.p2p.system.service.UploadFileService;
import cn.com.p2p.system.service.dto.SysSettingDto;
import cn.com.p2p.utils.UploadUtils;

@Namespace("/system/setting")
@Results({
	@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
	@Result(name = BaseAction.EDIT, location = "editBusiness.ftl", type = "freemarker"),
	@Result(name = BaseAction.UPDATE, location = "index.htm", type = "redirect")
	})
public class SettingAction extends BaseAction{

    private static final long serialVersionUID = 8780364711196287423L;

    @Autowired
	private SettingService settingService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	private SysSettingDto sysSettingDto;
	
	private SysSetting sysSetting;
	
	/** 编码*/
	private List<String> picSettingCodes;
	
	private List<FileDto> fileDtos = new ArrayList<FileDto>();
	
	private List<String> settingCode;
	
	private List<String> settingValue;
	
	@Action(value="index")
	@Override
	public String init() throws Exception {
	    sysSettingDto = settingService.findSysSettingAll(getLoginuser().getCompanyId());
		return INIT;
	}
	
	/**
	 * @description 
	 */
	@Action(value="updateBasic")
	public String updateBasic() throws Exception {
	    sysSetting = new SysSetting();
	    
        if(picSettingCodes != null){   // 系统参数类型为图片的的是否为空
            for(int i=0; i<picSettingCodes.size(); i++) {   // 图片的编码的数量始终与数据库保持一致，而上传图片的个数却会变化
                UploadFile uploadFile = null;
                FileDto fileDto = null;
                if(i < fileDtos.size()){         // 这里上传图片时，fileDtos会变化，如果你仅仅上传第三个图片，那么size=3,[null,null,file]
                    fileDto = fileDtos.get(i);
                }
                if(fileDto != null &&  fileDto.getFileId() == null){
                    if(fileDto.getFile() != null){
                        uploadFile = UploadUtils.upload(fileDto.getFile(), fileDto.getFileFileName(), fileDto.getFileContentType());
                    }
                }
                if(uploadFile != null){
                    int flag = uploadFileService.insertUploadFile(uploadFile);
                    if(flag == 1){
                        sysSetting.setSettingValue(uploadFile.getId());
                    }
                }else{
                    // 用户没有对原有图片进行修改时，将原有ID存入到是实体中
                    if(fileDto != null && fileDto.getFileId() != null){
                        sysSetting.setSettingValue(fileDto.getFileId());
                    }else{
                        sysSetting.setSettingValue("");
                    }
                }
                sysSetting.setSettingCode(picSettingCodes.get(i));
                settingService.updateBusiness(sysSetting);
            }
        }
	    if(settingCode != null && settingValue != null){
	        for(int i=0; i<settingCode.size(); i++) {
	            sysSetting.setSettingCode(settingCode.get(i));
	            sysSetting.setSettingValue(settingValue.get(i));
	            settingService.updateBusiness(sysSetting);
	        }
	    }
        return UPDATE;
    }

	@Action(value="editBusiness")
	public String editBusiness() throws Exception{
	    sysSetting = settingService.findSysSettingBySettingCode(sysSetting.getSettingCode());
		return EDIT;
	}
	
	@Action(value="updateBusiness")
	public String updateBusiness() throws Exception{
	    int result = settingService.updateBusiness(sysSetting);
	    if(result > 0){
	        System.out.println("成功");
	    }else{
	        System.out.println("失败");
	    }
	    return UPDATE;
	}
	
	public SysSetting getSysSetting() {
		return sysSetting;
	}

	public void setSysSetting(SysSetting sysSetting) {
		this.sysSetting = sysSetting;
	}

	public SysSettingDto getSysSettingDto() {
		return sysSettingDto;
	}

	public void setSysSettingDto(SysSettingDto sysSettingDto) {
		this.sysSettingDto = sysSettingDto;
	}

    public List<String> getPicSettingCodes() {
        return picSettingCodes;
    }

    public void setPicSettingCodes(List<String> picSettingCodes) {
        this.picSettingCodes = picSettingCodes;
    }

    public List<FileDto> getFileDtos() {
        return fileDtos;
    }

    public void setFileDtos(List<FileDto> fileDtos) {
        this.fileDtos = fileDtos;
    }

    public List<String> getSettingCode() {
        return settingCode;
    }

    public void setSettingCode(List<String> settingCode) {
        this.settingCode = settingCode;
    }

    public List<String> getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(List<String> settingValue) {
        this.settingValue = settingValue;
    }

}
