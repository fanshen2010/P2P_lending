/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        UploadFile.java
 * Description:       实体UploadFile类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.system.entity;

import java.io.Serializable;

import java.util.Date;

public class UploadFile implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**文件名*/
    private String fileName;

    /**原图URL*/
    private String fileUrlOriginal;

    /**压缩后大图URL*/
    private String fileUrlBig;

    /**缩略图URL*/
    private String fileUrlThumb;

    /**类型*/
    private String fileType;

    /**创建时间*/
    private Date createTime;

    /**创建者*/
    private String createUserId;

         
    public UploadFile() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取文件名*/
    public String getFileName() {
        return fileName;
    }

    /**设置文件名*/
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    /**获取原图URL*/
    public String getFileUrlOriginal() {
        return fileUrlOriginal;
    }

    /**设置原图URL*/
    public void setFileUrlOriginal(String fileUrlOriginal) {
        this.fileUrlOriginal = fileUrlOriginal;
    }
    /**获取压缩后大图URL*/
    public String getFileUrlBig() {
        return fileUrlBig;
    }

    /**设置压缩后大图URL*/
    public void setFileUrlBig(String fileUrlBig) {
        this.fileUrlBig = fileUrlBig;
    }
    /**获取缩略图URL*/
    public String getFileUrlThumb() {
        return fileUrlThumb;
    }

    /**设置缩略图URL*/
    public void setFileUrlThumb(String fileUrlThumb) {
        this.fileUrlThumb = fileUrlThumb;
    }
    /**获取类型*/
    public String getFileType() {
        return fileType;
    }

    /**设置类型*/
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String toString() {
        return "UploadFile ["
        + ", id=" + id
        + ", fileName=" + fileName
        + ", fileUrlOriginal=" + fileUrlOriginal
        + ", fileUrlBig=" + fileUrlBig
        + ", fileUrlThumb=" + fileUrlThumb
        + ", fileType=" + fileType
        + ", createTime=" + createTime
        + ", createUserId=" + createUserId
        + "]";
    }

}
