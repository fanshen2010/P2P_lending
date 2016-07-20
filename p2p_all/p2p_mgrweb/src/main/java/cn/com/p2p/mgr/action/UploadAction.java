package cn.com.p2p.mgr.action;

import java.io.File;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import cn.com.p2p.framework.util.FileUpload;
import cn.com.p2p.framework.util.Struts2Utils;
import cn.com.p2p.framework.web.action.BaseAction;

@Namespace("/")
public class UploadAction extends BaseAction {
    //TODO  暂时没用到
    //private static final int BUFFER_SIZE = 2 * 1024;
    private static final long serialVersionUID = 1L;
    private File uploadify;
    private String uploadifyFileName;

    @Action(value = "upload")
    public void uploadFile() throws Exception {
        Struts2Utils.renderJson(FileUpload.imgUpload(uploadify, uploadifyFileName));
    }

    @Override
    public String init() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    public File getUploadify() {
        return uploadify;
    }

    public void setUploadify(File uploadify) {
        this.uploadify = uploadify;
    }

    public String getUploadifyFileName() {
        return uploadifyFileName;
    }

    public void setUploadifyFileName(String uploadifyFileName) {
        this.uploadifyFileName = uploadifyFileName;
    }
}
