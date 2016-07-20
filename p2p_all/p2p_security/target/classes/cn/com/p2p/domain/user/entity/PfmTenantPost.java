/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantPost.java
 * Description:       实体PfmTenantPost类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;

import java.util.Date;

public class PfmTenantPost implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**商户CD*/
    private String tenantCd;

    /**职位CD*/
    private String postCd;

    /**职位名*/
    private String postName;

    /**职位说明*/
    private String postMem;

    /**职位是否有效*/
    private String validFlag;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者*/
    private String createUserId;

    /**更新者*/
    private String updateUserId;

    /**版本*/
    private Integer version;

         
    public PfmTenantPost() {

    }

    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }
    /**获取职位CD*/
    public String getPostCd() {
        return postCd;
    }

    /**设置职位CD*/
    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }
    /**获取职位名*/
    public String getPostName() {
        return postName;
    }

    /**设置职位名*/
    public void setPostName(String postName) {
        this.postName = postName;
    }
    /**获取职位说明*/
    public String getPostMem() {
        return postMem;
    }

    /**设置职位说明*/
    public void setPostMem(String postMem) {
        this.postMem = postMem;
    }
    /**获取职位是否有效*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置职位是否有效*/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }
    /**获取创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**设置创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version) {
        this.version = version;
    }

    public String toString() {
        return "PfmTenantPost ["
        + ", tenantCd=" + tenantCd
        + ", postCd=" + postCd
        + ", postName=" + postName
        + ", postMem=" + postMem
        + ", validFlag=" + validFlag
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
