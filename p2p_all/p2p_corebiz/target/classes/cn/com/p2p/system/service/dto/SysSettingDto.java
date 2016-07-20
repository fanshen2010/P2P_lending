package cn.com.p2p.system.service.dto;

import java.io.Serializable;
import java.util.List;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.domain.system.entity.UploadFile;


public class SysSettingDto implements Serializable{

    private static final long serialVersionUID = -6891380842743406572L;

    /** 基本设置*/
    private List<SysSetting> basicSetting;
    
    /** 基础费用相关参数、债权转让相关参数、时间相关参数、合同参数对应的集合 */
    private List<List<SysSetting>> settingLists;
    
    /**settingListName =[基础费用相关参数,债权转让相关参数,时间相关参数,合同参数] */
    private List<String> settingListName;
    
    /** 图片 */
    private List<UploadFile> proLogoUploadFile;
    
    /** 系统参数为图片时对应的参数名 */
    private List<String> picSettingName;
    
    /** 系统参数为图片时对应的参数编码 */
    private List<String> picSettingCode;
    
    public List<SysSetting> getBasicSetting() {
        return basicSetting;
    }
    
    public void setBasicSetting(List<SysSetting> basicSetting) {
        this.basicSetting = basicSetting;
    }
    
    public List<UploadFile> getProLogoUploadFile() {
        return proLogoUploadFile;
    }

    public void setProLogoUploadFile(List<UploadFile> proLogoUploadFile) {
        this.proLogoUploadFile = proLogoUploadFile;
    }

    public List<String> getPicSettingName() {
        return picSettingName;
    }

    public void setPicSettingName(List<String> picSettingName) {
        this.picSettingName = picSettingName;
    }

    public List<String> getPicSettingCode() {
        return picSettingCode;
    }

    public void setPicSettingCode(List<String> picSettingCode) {
        this.picSettingCode = picSettingCode;
    }

    public List<List<SysSetting>> getSettingLists() {
        return settingLists;
    }

    public void setSettingLists(List<List<SysSetting>> settingLists) {
        this.settingLists = settingLists;
    }

    public List<String> getSettingListName() {
        return settingListName;
    }

    public void setSettingListName(List<String> settingListName) {
        this.settingListName = settingListName;
    }

}
