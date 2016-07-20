/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        ArticleCriteria.java
 * Description:       查询条件ArticleCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-01             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "ARTICLE")
public class ArticleCriteria extends BaseCriteria {

    /**文章ID*/
    @Column(name="ID")
    private String id;

    /**所属栏目*/
    @Column(name="CATEGORY")
    private String category;

    /**标题*/
    @Column(name="TITLE")
    private String title;

    /**内容*/
    @Column(name="CONTENT")
    private String content;

    /**排序*/
    @Column(name="ORDER_NUM")
    private Integer orderNum;

    /**发布时间*/
    @Column(name="POST_AT")
    private Date postAt;

    /**是否推荐*/
    @Column(name="RECOMMEND_FLAG")
    private String recommendFlag;

    /**seo描述*/
    @Column(name="SEO_DESCRIPTION")
    private String seoDescription;

    /**seo关键字*/
    @Column(name="SEO_KEYWORDS")
    private String seoKeywords;

    /**状态*/
    @Column(name="STATUS")
    private String status;

    /**标题图片*/
    @Column(name="TITLE_PIC")
    private String titlePic;

    /**访问量*/
    @Column(name="VISITS")
    private Long visits;

    /**文章来源*/
    @Column(name="ARTICLE_SOURCE")
    private String articleSource;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

    /**创建者*/
    @Column(name="CREATE_USER_ID")
    private String createUserId;

    /**更新者*/
    @Column(name="UPDATE_USER_ID")
    private String updateUserId;

    /**版本*/
    @Column(name="VERSION")
    private Integer version;

         
    public ArticleCriteria() {

    }

    /**获取文章ID*/
    public String getId() {
        return id;
    }

    /**设置文章ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取所属栏目*/
    public String getCategory() {
        return category;
    }

    /**设置所属栏目*/
    public void setCategory(String category, Operator ... oper) {
        this.category = category;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("category", param);
            }
        }
    }
    /**获取标题*/
    public String getTitle() {
        return title;
    }

    /**设置标题*/
    public void setTitle(String title, Operator ... oper) {
        this.title = title;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("title", param);
            }
        }
    }
    /**获取内容*/
    public String getContent() {
        return content;
    }

    /**设置内容*/
    public void setContent(String content, Operator ... oper) {
        this.content = content;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("content", param);
            }
        }
    }
    /**获取排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置排序*/
    public void setOrderNum(Integer orderNum, Operator ... oper) {
        this.orderNum = orderNum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("orderNum", param);
            }
        }
    }
    /**获取发布时间*/
    public Date getPostAt() {
        return postAt;
    }

    /**设置发布时间*/
    public void setPostAt(Date postAt, Operator ... oper) {
        this.postAt = postAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("postAt", param);
            }
        }
    }
    /**获取是否推荐*/
    public String getRecommendFlag() {
        return recommendFlag;
    }

    /**设置是否推荐*/
    public void setRecommendFlag(String recommendFlag, Operator ... oper) {
        this.recommendFlag = recommendFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("recommendFlag", param);
            }
        }
    }
    /**获取seo描述*/
    public String getSeoDescription() {
        return seoDescription;
    }

    /**设置seo描述*/
    public void setSeoDescription(String seoDescription, Operator ... oper) {
        this.seoDescription = seoDescription;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("seoDescription", param);
            }
        }
    }
    /**获取seo关键字*/
    public String getSeoKeywords() {
        return seoKeywords;
    }

    /**设置seo关键字*/
    public void setSeoKeywords(String seoKeywords, Operator ... oper) {
        this.seoKeywords = seoKeywords;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("seoKeywords", param);
            }
        }
    }
    /**获取状态*/
    public String getStatus() {
        return status;
    }

    /**设置状态*/
    public void setStatus(String status, Operator ... oper) {
        this.status = status;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("status", param);
            }
        }
    }
    /**获取标题图片*/
    public String getTitlePic() {
        return titlePic;
    }

    /**设置标题图片*/
    public void setTitlePic(String titlePic, Operator ... oper) {
        this.titlePic = titlePic;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("titlePic", param);
            }
        }
    }
    /**获取访问量*/
    public Long getVisits() {
        return visits;
    }

    /**设置访问量*/
    public void setVisits(Long visits, Operator ... oper) {
        this.visits = visits;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("visits", param);
            }
        }
    }
    /**获取文章来源*/
    public String getArticleSource() {
        return articleSource;
    }

    /**设置文章来源*/
    public void setArticleSource(String articleSource, Operator ... oper) {
        this.articleSource = articleSource;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("articleSource", param);
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
    /**获取更新时间*/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**设置更新时间*/
    public void setUpdateTime(Date updateTime, Operator ... oper) {
        this.updateTime = updateTime;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateTime", param);
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
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId, Operator ... oper) {
        this.updateUserId = updateUserId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("updateUserId", param);
            }
        }
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version, Operator ... oper) {
        this.version = version;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("version", param);
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
        category("CATEGORY"),
        title("TITLE"),
        content("CONTENT"),
        orderNum("ORDER_NUM"),
        postAt("POST_AT"),
        recommendFlag("RECOMMEND_FLAG"),
        seoDescription("SEO_DESCRIPTION"),
        seoKeywords("SEO_KEYWORDS"),
        status("STATUS"),
        titlePic("TITLE_PIC"),
        visits("VISITS"),
        articleSource("ARTICLE_SOURCE"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME"),
        createUserId("CREATE_USER_ID"),
        updateUserId("UPDATE_USER_ID"),
        version("VERSION");

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
