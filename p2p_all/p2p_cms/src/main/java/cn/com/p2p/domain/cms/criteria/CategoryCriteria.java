/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        CategoryCriteria.java
 * Description:       查询条件CategoryCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-24             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.cms.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "CATEGORY")
public class CategoryCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**栏目编码*/
    @Column(name="CATEGORY_CODE")
    private String categoryCode;

    /**栏目路径*/
    @Column(name="CATEGORY_PATH")
    private String categoryPath;

    /**上级栏目*/
    @Column(name="PARENT_CATEGORY")
    private String parentCategory;

    /**标题*/
    @Column(name="TITLE")
    private String title;

    /**内容*/
    @Column(name="CONTENT")
    private String content;

    /**seo描述*/
    @Column(name="SEO_DESCRIPTION")
    private String seoDescription;

    /**seo关键字*/
    @Column(name="SEO_KEYWORDS")
    private String seoKeywords;

    /**栏目排序*/
    @Column(name="ORDER_NUM")
    private Integer orderNum;

    /**页面类别*/
    @Column(name="CATEGORY_TYPE")
    private String categoryType;

    /**打开链接方式*/
    @Column(name="TARGET")
    private String target;

    /**链接路径*/
    @Column(name="TARGET_URL")
    private String targetUrl;

    /**每页显示条数*/
    @Column(name="PER_PAGE")
    private Integer perPage;

    /**状态*/
    @Column(name="STATUS")
    private String status;

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

         
    public CategoryCriteria() {

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
    /**获取栏目编码*/
    public String getCategoryCode() {
        return categoryCode;
    }

    /**设置栏目编码*/
    public void setCategoryCode(String categoryCode, Operator ... oper) {
        this.categoryCode = categoryCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("categoryCode", param);
            }
        }
    }
    /**获取栏目路径*/
    public String getCategoryPath() {
        return categoryPath;
    }

    /**设置栏目路径*/
    public void setCategoryPath(String categoryPath, Operator ... oper) {
        this.categoryPath = categoryPath;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("categoryPath", param);
            }
        }
    }
    /**获取上级栏目*/
    public String getParentCategory() {
        return parentCategory;
    }

    /**设置上级栏目*/
    public void setParentCategory(String parentCategory, Operator ... oper) {
        this.parentCategory = parentCategory;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("parentCategory", param);
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
    /**获取栏目排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置栏目排序*/
    public void setOrderNum(Integer orderNum, Operator ... oper) {
        this.orderNum = orderNum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("orderNum", param);
            }
        }
    }
    /**获取页面类别*/
    public String getCategoryType() {
        return categoryType;
    }

    /**设置页面类别*/
    public void setCategoryType(String categoryType, Operator ... oper) {
        this.categoryType = categoryType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("categoryType", param);
            }
        }
    }
    /**获取打开链接方式*/
    public String getTarget() {
        return target;
    }

    /**设置打开链接方式*/
    public void setTarget(String target, Operator ... oper) {
        this.target = target;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("target", param);
            }
        }
    }
    /**获取链接路径*/
    public String getTargetUrl() {
        return targetUrl;
    }

    /**设置链接路径*/
    public void setTargetUrl(String targetUrl, Operator ... oper) {
        this.targetUrl = targetUrl;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("targetUrl", param);
            }
        }
    }
    /**获取每页显示条数*/
    public Integer getPerPage() {
        return perPage;
    }

    /**设置每页显示条数*/
    public void setPerPage(Integer perPage, Operator ... oper) {
        this.perPage = perPage;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("perPage", param);
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
        categoryCode("CATEGORY_CODE"),
        categoryPath("CATEGORY_PATH"),
        parentCategory("PARENT_CATEGORY"),
        title("TITLE"),
        content("CONTENT"),
        seoDescription("SEO_DESCRIPTION"),
        seoKeywords("SEO_KEYWORDS"),
        orderNum("ORDER_NUM"),
        categoryType("CATEGORY_TYPE"),
        target("TARGET"),
        targetUrl("TARGET_URL"),
        perPage("PER_PAGE"),
        status("STATUS"),
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
