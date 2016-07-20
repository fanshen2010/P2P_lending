package cn.com.p2p.common.dto;

import java.io.File;

/**
 * Created by  on 2015/4/16.
 * <p/>
 * File数据传输对象,封装了待上传的file对象及其标识id.
 * 用于多文件标签的页面,action中使用 List<FileDto>  接收
 */
public class FileDto {

    private File file;
    private String fileCode;
    private String fileId;
    private String fileFileName;
    private String fileContentType;

    /*
     * ==================================================================
     * ===========================Get/Set方法============================
     * ==================================================================
     */

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public File getFile() {
        return file;
    }


    public void setFile(File file) {
        this.file = file;
    }


    public String getFileId() {
        return fileId;
    }


    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public String getFileFileName() {
        return fileFileName;
    }


    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }


    public String getFileContentType() {
        return fileContentType;
    }


    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
}
