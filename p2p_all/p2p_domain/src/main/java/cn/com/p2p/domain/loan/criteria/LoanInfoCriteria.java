/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        LoanInfoCriteria.java
 * Description:       查询条件LoanInfoCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.loan.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "LOAN_INFO")
public class LoanInfoCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**融资编号*/
    @Column(name="LOAN_CODE")
    private String loanCode;

    /**融资信息Json*/
    @Column(name="LOAN_MSG")
    private String loanMsg;

    /**项目说明书*/
    @Column(name="INSTRUCTIONS")
    private String instructions;

    /**委托合同json*/
    @Column(name="ENTRUST_JSON")
    private String entrustJson;

    /**商户ID*/
    @Column(name="TENANT_ID")
    private String tenantId;

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

         
    public LoanInfoCriteria() {

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
    /**获取融资编号*/
    public String getLoanCode() {
        return loanCode;
    }

    /**设置融资编号*/
    public void setLoanCode(String loanCode, Operator ... oper) {
        this.loanCode = loanCode;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanCode", param);
            }
        }
    }
    /**获取融资信息Json*/
    public String getLoanMsg() {
        return loanMsg;
    }

    /**设置融资信息Json*/
    public void setLoanMsg(String loanMsg, Operator ... oper) {
        this.loanMsg = loanMsg;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loanMsg", param);
            }
        }
    }
    /**获取项目说明书*/
    public String getInstructions() {
        return instructions;
    }

    /**设置项目说明书*/
    public void setInstructions(String instructions, Operator ... oper) {
        this.instructions = instructions;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("instructions", param);
            }
        }
    }
    /**获取委托合同json*/
    public String getEntrustJson() {
        return entrustJson;
    }

    /**设置委托合同json*/
    public void setEntrustJson(String entrustJson, Operator ... oper) {
        this.entrustJson = entrustJson;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("entrustJson", param);
            }
        }
    }
    /**获取商户ID*/
    public String getTenantId() {
        return tenantId;
    }

    /**设置商户ID*/
    public void setTenantId(String tenantId, Operator ... oper) {
        this.tenantId = tenantId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("tenantId", param);
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
        loanCode("LOAN_CODE"),
        loanMsg("LOAN_MSG"),
        instructions("INSTRUCTIONS"),
        entrustJson("ENTRUST_JSON"),
        tenantId("TENANT_ID"),
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
        return "LoanInfo ["
        + ", id=" + id
        + ", loanCode=" + loanCode
        + ", loanMsg=" + loanMsg
        + ", instructions=" + instructions
        + ", entrustJson=" + entrustJson
        + ", tenantId=" + tenantId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
