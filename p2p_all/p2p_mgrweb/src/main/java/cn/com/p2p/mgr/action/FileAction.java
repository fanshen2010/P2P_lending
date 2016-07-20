package cn.com.p2p.mgr.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.framework.util.FileUpload;
import cn.com.p2p.framework.util.JsonPluginsUtil;
import cn.com.p2p.framework.web.action.BaseAction;
import cn.com.p2p.framework.web.util.Struts2Utils;

@Namespace("/")
@Results({
		@Result(name = BaseAction.INIT, location = "index.ftl", type = "freemarker"),
		@Result(name = "upload", location = "upload.ftl", type = "freemarker") })
public class FileAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int BUFFER_SIZE = 2 * 1024;

	@SuppressWarnings("unused")
    private void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),
						BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
			}
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Action(value = "fileUpload")
	public void upload() throws Exception {
		if(file!=null){
			Map<String ,String> map=FileUpload.fileUpload(file, fileFileName);
			Struts2Utils.renderJson(map);
		}
	}

//	@Action(value = "fileUploadSubmit")
//	public String submit() {
//		String filePath = ServletActionContext.getServletContext().getRealPath(
//				this.getSavePath());
//		System.out.println("保存文件路径： " + filePath);
//
//		HttpServletRequest request = ServletActionContext.getRequest();
//
//		result = "";
//		int count = Integer.parseInt(request.getParameter("uploader_count"));
//		for (int i = 0; i < count; i++) {
//			uploadFileName = request.getParameter("uploader_" + i + "_name");
//			name = request.getParameter("uploader_" + i + "_tmpname");
//			System.out.println(uploadFileName + " " + name);
//			try {
//				// do something with file;
//				result += uploadFileName + "导入完成. <br />";
//			} catch (Exception e) {
//				result += uploadFileName + "导入失败:" + e.getMessage()
//						+ ". <br />";
//				e.printStackTrace();
//			}
//		}
//		
//		return SUCCESS;
//	}
	
	/**
	 * 添加水印
	 * @param dstFile
	 */
//	private void appendWatermark(File dstFile){
//		//根据水印组code取出系统设定水印组
//		List<SysSetting> sysSettings = service.getListByGroupCode(Constants.SYS_SETTING_GROUP_CODE_WATERMARK);
//		//水印文字
//		SysSetting sysSetting = service.getByCode("site_name");
//		String watermarkName = sysSetting.getSettingValue();
//		//水印字体样式
//		String watermarkStyle = "微软雅黑";
//		//水印文字缩放比例
//		double watermarkSize = 0.1;
//		//水印X间距
//		int watermarkSpacingX = 80;
//		//水印Y间距
//		int watermarkSpacingY = 30;
//		try{
//			for(SysSetting setting : sysSettings){
//				if((Constants.SYS_SETTING_GROUP_CODE_WATERMARK_NAME).equals(setting.getSettingCode())){
//					watermarkName = setting.getSettingValue();
//				}else if((Constants.SYS_SETTING_GROUP_CODE_WATERMARK_STYLE).equals(setting.getSettingCode())){
//					watermarkStyle = setting.getSettingValue();
//				}else if((Constants.SYS_SETTING_GROUP_CODE_WATERMARK_SIZE).equals(setting.getSettingCode())){
//					if(setting.getSettingValue() != null){
//						double wSize = Double.parseDouble(setting.getSettingValue());
//						if(wSize >= 3.0){
//							watermarkSize = wSize / 1000;
//						}else{
//							return;
//						}
//					}
//					
//				}else if((Constants.SYS_SETTING_GROUP_CODE_WATERMARK_X_SPACING).equals(setting.getSettingCode())){
//					watermarkSpacingX = Integer.parseInt(setting.getSettingValue());
//				}else if((Constants.SYS_SETTING_GROUP_CODE_WATERMARK_Y_SPACING).equals(setting.getSettingCode())){
//					watermarkSpacingY = Integer.parseInt(setting.getSettingValue());
//				}else{
//				}
//			}
//			ImageUtil.waterMarkByText(watermarkName,dstFile.getPath(),dstFile.getPath(),-28,watermarkSpacingX,watermarkSpacingY,0.3f,watermarkStyle,watermarkSize);
//		} catch(Exception e){
//			
//		}
//	}

//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getId() {
//		return id;
//	}



	@Override
	public String init() throws Exception {
		return null;
	}
}
