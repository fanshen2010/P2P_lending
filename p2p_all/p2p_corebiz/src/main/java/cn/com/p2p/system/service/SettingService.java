package cn.com.p2p.system.service;

import java.util.List;

import cn.com.p2p.domain.system.entity.SysSetting;
import cn.com.p2p.system.service.dto.SysSettingDto;

public interface SettingService {
	/**
	 * 
	  * 
	  * <p>根据组编码查找信息 * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param groupCode 组编码
	  *	@return SysSetting类型集合
	 */
	public SysSettingDto findSysSettingAll(String tenantCd);
	
	/**
	 * 
	  * 
	  * <p> * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param 
	  * @param 
	  * @param 
	  *
	 */
	public void updateSysSettingBySettingCode(List<SysSetting> sysSetting);
	
	/**
	 * 
	  * 
	  * <p> * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param 
	  * @param 
	  * @param 
	  *
	 */
	public SysSetting findSysSettingBySettingCode(String settingCode);
	
	/**
	 * 
	  * 
	  * <p> * </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param 
	  * @param 
	  * @param 
	  *
	 */
	public int updateBusiness(SysSetting sysSetting);

    /**
     * <p> * </p>.<br> 说明：清除图片时需要全部更新
     * <p/>
     * author：<br>
     * ===================================
     *
     * @param
     * @param
     * @param
     */
    public void updateAll(List<SysSetting> sysSetting);
}
