/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        AdvertisementCriteria.java
 * Description:       查询条件AdvertisementCriteria类定义
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

@Table(name = "ADVERTISEMENT")
public class AdvertisementCriteria extends BaseCriteria {

    /**广告ID*/
    @Column(name="ID")
    private String id;

    /**版位*/
    @Column(name="ADVER_CODE")
    private String adverCode;

    /**标题*/
    @Column(name="TITLE")
    private String title;

    /**链接*/
    @Column(name="CONNECT_URL")
    private String connectUrl;

    /**描述*/
    @Column(name="DESCRIPTION")
    private String description;

    /**开始时间*/
    @Column(name="START_AT")
    private Date startAt;

    /**结束时间*/
    @Column(name="END_AT")
    private Date endAt;

    /**图片*/
    @Column(name="AD_PIC")
    private String adPic;

    /**打开方式*/
    @Column(name="OPENS")
    private String opens;

    /**广告排序*/
    @Column(name="ORDER_NUM")
    private Integer orderNum;

    /**状态*/
    @Column(name="STATUS")
    private String status;

    /**备注*/
    @Column(name="REMARK")
    private String remark;

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

         
    public AdvertisementCriteria() {

    }

    /**获取广告ID*/
    public String getId() {
        return id;
    }

    /**设置广告ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取版位*/
    public String getAdverCode() {
        return adverCode;
    }

    /**设置版位*/
    public void setAdverCode(String adverCode, Operator ... oper) {
        this.adverCode = adverCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("adverCode", param);
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
    /**获取链接*/
    public String getConnectUrl() {
        return connectUrl;
    }

    /**设置链接*/
    public void setConnectUrl(String connectUrl, Operator ... oper) {
        this.connectUrl = connectUrl;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("connectUrl", param);
            }
        }
    }
    /**获取描述*/
    public String getDescription() {
        return description;
    }

    /**设置描述*/
    public void setDescription(String description, Operator ... oper) {
        this.description = description;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("description", param);
            }
        }
    }
    /**获取开始时间*/
    public Date getStartAt() {
        return startAt;
    }

    /**设置开始时间*/
    public void setStartAt(Date startAt, Operator ... oper) {
        this.startAt = startAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("startAt", param);
            }
        }
    }
    /**获取结束时间*/
    public Date getEndAt() {
        return endAt;
    }

    /**设置结束时间*/
    public void setEndAt(Date endAt, Operator ... oper) {
        this.endAt = endAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("endAt", param);
            }
        }
    }
    /**获取图片*/
    public String getAdPic() {
        return adPic;
    }

    /**设置图片*/
    public void setAdPic(String adPic, Operator ... oper) {
        this.adPic = adPic;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("adPic", param);
            }
        }
    }
    /**获取打开方式*/
    public String getOpens() {
        return opens;
    }

    /**设置打开方式*/
    public void setOpens(String opens, Operator ... oper) {
        this.opens = opens;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("opens", param);
            }
        }
    }
    /**获取广告排序*/
    public Integer getOrderNum() {
        return orderNum;
    }

    /**设置广告排序*/
    public void setOrderNum(Integer orderNum, Operator ... oper) {
        this.orderNum = orderNum;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("orderNum", param);
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
    /**获取备注*/
    public String getRemark() {
        return remark;
    }

    /**设置备注*/
    public void setRemark(String remark, Operator ... oper) {
        this.remark = remark;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("remark", param);
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
        adverCode("ADVER_CODE"),
        title("TITLE"),
        connectUrl("CONNECT_URL"),
        description("DESCRIPTION"),
        startAt("START_AT"),
        endAt("END_AT"),
        adPic("AD_PIC"),
        opens("OPENS"),
        orderNum("ORDER_NUM"),
        status("STATUS"),
        remark("REMARK"),
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
        return "Advertisement ["
        + ", id=" + id
        + ", adverCode=" + adverCode
        + ", title=" + title
        + ", connectUrl=" + connectUrl
        + ", description=" + description
        + ", startAt=" + startAt
        + ", endAt=" + endAt
        + ", adPic=" + adPic
        + ", opens=" + opens
        + ", orderNum=" + orderNum
        + ", status=" + status
        + ", remark=" + remark
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
