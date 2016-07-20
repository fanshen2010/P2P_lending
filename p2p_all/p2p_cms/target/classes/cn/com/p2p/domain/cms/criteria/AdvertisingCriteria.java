/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        AdvertisingCriteria.java
 * Description:       查询条件AdvertisingCriteria类定义
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

@Table(name = "ADVERTISING")
public class AdvertisingCriteria extends BaseCriteria {

    /**广告版位ID*/
    @Column(name="ID")
    private String id;

    /**版位编码*/
    @Column(name="ADVER_CODE")
    private String adverCode;

    /**版位名*/
    @Column(name="ADVER_NAME")
    private String adverName;

    /**备注*/
    @Column(name="REMARK")
    private String remark;

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

         
    public AdvertisingCriteria() {

    }

    /**获取广告版位ID*/
    public String getId() {
        return id;
    }

    /**设置广告版位ID*/
    public void setId(String id, Operator ... oper) {
        this.id = id;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("id", param);
            }
        }
    }
    /**获取版位编码*/
    public String getAdverCode() {
        return adverCode;
    }

    /**设置版位编码*/
    public void setAdverCode(String adverCode, Operator ... oper) {
        this.adverCode = adverCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("adverCode", param);
            }
        }
    }
    /**获取版位名*/
    public String getAdverName() {
        return adverName;
    }

    /**设置版位名*/
    public void setAdverName(String adverName, Operator ... oper) {
        this.adverName = adverName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("adverName", param);
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
        adverCode("ADVER_CODE"),
        adverName("ADVER_NAME"),
        remark("REMARK"),
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
        return "Advertising ["
        + ", id=" + id
        + ", adverCode=" + adverCode
        + ", adverName=" + adverName
        + ", remark=" + remark
        + ", status=" + status
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
