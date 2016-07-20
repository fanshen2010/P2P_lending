/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        UploadFileCriteria.java
 * Description:       查询条件UploadFileCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.system.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "UPLOAD_FILE")
public class UploadFileCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**文件名*/
    @Column(name="FILE_NAME")
    private String fileName;

    /**原图URL*/
    @Column(name="FILE_URL_ORIGINAL")
    private String fileUrlOriginal;

    /**压缩后大图URL*/
    @Column(name="FILE_URL_BIG")
    private String fileUrlBig;

    /**缩略图URL*/
    @Column(name="FILE_URL_THUMB")
    private String fileUrlThumb;

    /**类型*/
    @Column(name="FILE_TYPE")
    private String fileType;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**创建者*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

         
    public UploadFileCriteria() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取文件名*/
    public String getFileName() {
        return fileName;
    }

    /**设置文件名*/
    public void setFileName(String fileName, Operator ... oper) {
        this.fileName = fileName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("fileName", param);
            }
        }
    }
    /**获取原图URL*/
    public String getFileUrlOriginal() {
        return fileUrlOriginal;
    }

    /**设置原图URL*/
    public void setFileUrlOriginal(String fileUrlOriginal, Operator ... oper) {
        this.fileUrlOriginal = fileUrlOriginal;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("fileUrlOriginal", param);
            }
        }
    }
    /**获取压缩后大图URL*/
    public String getFileUrlBig() {
        return fileUrlBig;
    }

    /**设置压缩后大图URL*/
    public void setFileUrlBig(String fileUrlBig, Operator ... oper) {
        this.fileUrlBig = fileUrlBig;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("fileUrlBig", param);
            }
        }
    }
    /**获取缩略图URL*/
    public String getFileUrlThumb() {
        return fileUrlThumb;
    }

    /**设置缩略图URL*/
    public void setFileUrlThumb(String fileUrlThumb, Operator ... oper) {
        this.fileUrlThumb = fileUrlThumb;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("fileUrlThumb", param);
            }
        }
    }
    /**获取类型*/
    public String getFileType() {
        return fileType;
    }

    /**设置类型*/
    public void setFileType(String fileType, Operator ... oper) {
        this.fileType = fileType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("fileType", param);
            }
        }
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime, Operator ... oper) {
        this.createTime = createTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createTime", param);
            }
        }
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId, Operator ... oper) {
        this.createUserId = createUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createUserId", param);
            }
        }
    }

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        fileName("FILE_NAME"),
        fileUrlOriginal("FILE_URL_ORIGINAL"),
        fileUrlBig("FILE_URL_BIG"),
        fileUrlThumb("FILE_URL_THUMB"),
        fileType("FILE_TYPE"),
        createTime("CREATE_TIME"),
        createUserId("CREATE_USER_ID");

    	private String value = "";
    	private OrderField(String value){
    		this.value = value;
    	}

		@Override
		public String getValue() {
			return value;
		}
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
