/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        Category.java
 * Description:       实体Category类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-24             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.entity;

import java.io.Serializable;

import java.util.Date;

public class Category implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**栏目编码*/
    private String categoryCode;

    /**栏目路径*/
    private String categoryPath;

    /**上级栏目*/
    private String parentCategory;

    /**标题*/
    private String title;

    /**内容*/
    private String content;

    /**seo描述*/
    private String seoDescription;

    /**seo关键字*/
    private String seoKeywords;

    /**栏目排序*/
    private Integer orderNum;

    /**页面类别*/
    private String categoryType;

    /**打开链接方式*/
    private String target;

    /**链接路径*/
    private String targetUrl;

    /**每页显示条数*/
    private Integer perPage;

    /**状态*/
    private String status;

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

         
    public Category() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取栏目编码*/
    public String getCategoryCode() {
        return categoryCode;
    }

    /**设置栏目编码*/
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    /**获取栏目路径*/
    public String getCategoryPath() {
        return categoryPath;
    }

    /**设置栏目路径*/
    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }
    /**获取上级栏目*/
    public String getParentCategory() {
        return parentCategory;
    }

    /**设置上级栏目*/
    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }
    /**获取标题*/
    public String getTitle() {
        return title;
    }

    /**设置标题*/
    public void setTitle(String title) {
        this.title = title;
    }
    /**获取内容*/
    public String getContent() {
        return content;
    }

    /**设置内容*/
    public void setContent(String content) {
        this.content = content;
    }
    /**获取seo描述*/
    public String getSeoDescription() {
        return seoDescription;
    }

    /**设置seo描述*/
    public void setSeoDescription(String seoDescription) {
        this.seoDescription = seoDescription;
    }
    /**获取seo关键字*/
    public String getSeoKeywords() {
        return seoKeywords;
    }

    /**设置seo关键字*/
    public void setSeoKeywords(String seoKeywords) {
        this.seoKeywords = seoKeywords;
    }
    /**获取栏目排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置栏目排序*/
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**获取页面类别*/
    public String getCategoryType() {
        return categoryType;
    }

    /**设置页面类别*/
    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
    /**获取打开链接方式*/
    public String getTarget() {
        return target;
    }

    /**设置打开链接方式*/
    public void setTarget(String target) {
        this.target = target;
    }
    /**获取链接路径*/
    public String getTargetUrl() {
        return targetUrl;
    }

    /**设置链接路径*/
    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
    /**获取每页显示条数*/
    public Integer getPerPage() {
        return perPage;
    }

    /**设置每页显示条数*/
    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status) {
        this.status = status;
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
        return "Category ["
        + ", id=" + id
        + ", categoryCode=" + categoryCode
        + ", categoryPath=" + categoryPath
        + ", parentCategory=" + parentCategory
        + ", title=" + title
        + ", content=" + content
        + ", seoDescription=" + seoDescription
        + ", seoKeywords=" + seoKeywords
        + ", orderNum=" + orderNum
        + ", categoryType=" + categoryType
        + ", target=" + target
        + ", targetUrl=" + targetUrl
        + ", perPage=" + perPage
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
