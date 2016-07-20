package cn.com.p2p.system.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.domain.system.repository.UploadFileRepository;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.system.service.UploadFileService;
@Service
public class UploadFileServiceImpl implements UploadFileService{

	
	@Autowired
	private UploadFileRepository uploadFileRepository;
	
	@Override
	public UploadFile findUploadFileById(String id) {
		
		return uploadFileRepository.findOne(id);
	}

	@Override
	public int insertUploadFile(UploadFile uploadFile) {
		
		int retVal = 0;
		
		//存在文件则进行插入操作
		if(uploadFile.getFileUrlOriginal() != null){
			uploadFile.setId(StringUtils.getUUID());
			uploadFile.setCreateTime(new Date());
			uploadFile.setCreateUserId("当前登录人ID");
			retVal = uploadFileRepository.insert(uploadFile);
		}
		return retVal;
	}

	@Override
	public void qualificationUploadFile(List<UploadFile> uploadFiles) {
		
		if(uploadFiles != null && !uploadFiles.isEmpty() && uploadFiles.size()>0){
			for(int i=0;i<uploadFiles.size();i++){
				uploadFiles.get(i).setId(StringUtils.getUUID());
				//TODO 预防转json报错 暂时注释
				//uploadFiles.get(i).setCreateTime(new Date());	
				uploadFiles.get(i).setCreateUserId("当前登录人ID");
				uploadFileRepository.insert(uploadFiles.get(i));
				uploadFiles.get(i).setCreateTime(null);
			}
		}
	}


}
