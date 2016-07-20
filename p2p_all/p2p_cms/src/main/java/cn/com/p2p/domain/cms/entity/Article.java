/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        Article.java
 * Description:       实体Article类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-01             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.entity;

import java.io.Serializable;

import java.util.Date;

public class Article implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**文章ID*/
    private String id;

    /**所属栏目*/
    private String category;

    /**标题*/
    private String title;

    /**内容*/
    private String content;

    /**排序*/
    private Integer orderNum;

    /**发布时间*/
    private Date postAt;

    /**是否推荐*/
    private String recommendFlag;

    /**seo描述*/
    private String seoDescription;

    /**seo关键字*/
    private String seoKeywords;

    /**状态*/
    private String status;

    /**标题图片*/
    private String titlePic;

    /**访问量*/
    private Long visits;

    /**文章来源*/
    private String articleSource;

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

         
    public Article() {

    }

    /**获取文章ID*/
    public String getId() {
        return id;
    }

    /**设置文章ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取所属栏目*/
    public String getCategory() {
        return category;
    }

    /**设置所属栏目*/
    public void setCategory(String category) {
        this.category = category;
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
    /**获取排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置排序*/
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**获取发布时间*/
    public Date getPostAt() {
        return postAt;
    }

    /**设置发布时间*/
    public void setPostAt(Date postAt) {
        this.postAt = postAt;
    }
    /**获取是否推荐*/
    public String getRecommendFlag() {
        return recommendFlag;
    }

    /**设置是否推荐*/
    public void setRecommendFlag(String recommendFlag) {
        this.recommendFlag = recommendFlag;
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
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status) {
        this.status = status;
    }
    /**获取标题图片*/
    public String getTitlePic() {
        return titlePic;
    }

    /**设置标题图片*/
    public void setTitlePic(String titlePic) {
        this.titlePic = titlePic;
    }
    /**获取访问量*/
    public Long getVisits() {
        return visits;
    }

    /**设置访问量*/
    public void setVisits(Long visits) {
        this.visits = visits;
    }
    /**获取文章来源*/
    public String getArticleSource() {
        return articleSource;
    }

    /**设置文章来源*/
    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
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
        return "Article ["
        + ", id=" + id
        + ", category=" + category
        + ", title=" + title
        + ", content=" + content
        + ", orderNum=" + orderNum
        + ", postAt=" + postAt
        + ", recommendFlag=" + recommendFlag
        + ", seoDescription=" + seoDescription
        + ", seoKeywords=" + seoKeywords
        + ", status=" + status
        + ", titlePic=" + titlePic
        + ", visits=" + visits
        + ", articleSource=" + articleSource
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
