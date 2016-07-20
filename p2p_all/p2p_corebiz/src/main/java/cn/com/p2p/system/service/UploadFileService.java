package cn.com.p2p.system.service;

import java.util.List;

import cn.com.p2p.domain.system.entity.UploadFile;

public interface UploadFileService {
	/**
	 * 
	  * 
	  * <p> 根据ID查找上传文件* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param id 上传文件ID
	  *
	 */
	public UploadFile findUploadFileById(String id);
	/**
	 * 
	  * 
	  * <p> 上传文件插入* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param uploadFile 上传文件实体
	  *
	 */
	public int insertUploadFile(UploadFile uploadFile);
	
	/**
	 * 
	  * 
	  * <p> 图片批量上传* </p>.<br> 说明：
	  * 
	  * author：<br> 
	  * ===================================
	  * @param uploadFiles 上传文件list
	  *
	 */
	public void qualificationUploadFile(List<UploadFile> uploadFiles);
	
	
}
