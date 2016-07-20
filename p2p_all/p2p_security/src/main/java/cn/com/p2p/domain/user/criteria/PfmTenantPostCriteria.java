/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantPostCriteria.java
 * Description:       查询条件PfmTenantPostCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-03             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_TENANT_POST")
public class PfmTenantPostCriteria extends BaseCriteria {

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**职位CD*/
    @Column(name="POST_CD")
    private String postCd;

    /**职位名*/
    @Column(name="POST_NAME")
    private String postName;

    /**职位说明*/
    @Column(name="POST_MEM")
    private String postMem;

    /**职位是否有效*/
    @Column(name="VALID_FLAG")
    private String validFlag;

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

         
    public PfmTenantPostCriteria() {

    }

    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd, Operator ... oper) {
        this.tenantCd = tenantCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantCd", param);
            }
        }
    }
    /**获取职位CD*/
    public String getPostCd() {
        return postCd;
    }

    /**设置职位CD*/
    public void setPostCd(String postCd, Operator ... oper) {
        this.postCd = postCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("postCd", param);
            }
        }
    }
    /**获取职位名*/
    public String getPostName() {
        return postName;
    }

    /**设置职位名*/
    public void setPostName(String postName, Operator ... oper) {
        this.postName = postName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("postName", param);
            }
        }
    }
    /**获取职位说明*/
    public String getPostMem() {
        return postMem;
    }

    /**设置职位说明*/
    public void setPostMem(String postMem, Operator ... oper) {
        this.postMem = postMem;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("postMem", param);
            }
        }
    }
    /**获取职位是否有效*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置职位是否有效*/
    public void setValidFlag(String validFlag, Operator ... oper) {
        this.validFlag = validFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("validFlag", param);
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

        tenantCd("TENANT_CD"),
        postCd("POST_CD"),
        postName("POST_NAME"),
        postMem("POST_MEM"),
        validFlag("VALID_FLAG"),
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
