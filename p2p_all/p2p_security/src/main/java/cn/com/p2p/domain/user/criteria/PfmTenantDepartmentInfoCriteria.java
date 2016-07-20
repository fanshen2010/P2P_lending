/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmTenantDepartmentInfoCriteria.java
 * Description:       查询条件PfmTenantDepartmentInfoCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-13             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_TENANT_DEPARTMENT_INFO")
public class PfmTenantDepartmentInfoCriteria extends BaseCriteria {

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**部门CD*/
    @Column(name="DEPARTMENT_CD")
    private String departmentCd;

    /**注册资金*/
    @Column(name="REGISTERED_CAPITAL")
    private String registeredCapital;

    /**成立日*/
    @Column(name="CREATED_DATE")
    private Date createdDate;

    /**合作开始日*/
    @Column(name="COOPERATE_DATE")
    private Date cooperateDate;

    /**资质图片，关联image表*/
    @Column(name="PIC")
    private String pic;

    /**地址*/
    @Column(name="ADDRESS")
    private Integer address;

    /**简介*/
    @Column(name="INTRODUCTION")
    private String introduction;

    /**还款途径*/
    @Column(name="REPAY_WAY")
    private String repayWay;

    /**电子签章*/
    @Column(name="ELECTRONIC_SEAL")
    private String electronicSeal;

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

         
    public PfmTenantDepartmentInfoCriteria() {

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
    /**获取部门CD*/
    public String getDepartmentCd() {
        return departmentCd;
    }

    /**设置部门CD*/
    public void setDepartmentCd(String departmentCd, Operator ... oper) {
        this.departmentCd = departmentCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("departmentCd", param);
            }
        }
    }
    /**获取注册资金*/
    public String getRegisteredCapital() {
        return registeredCapital;
    }

    /**设置注册资金*/
    public void setRegisteredCapital(String registeredCapital, Operator ... oper) {
        this.registeredCapital = registeredCapital;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("registeredCapital", param);
            }
        }
    }
    /**获取成立日*/
    public Date getCreatedDate() {
        return createdDate;
    }

    /**设置成立日*/
    public void setCreatedDate(Date createdDate, Operator ... oper) {
        this.createdDate = createdDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("createdDate", param);
            }
        }
    }
    /**获取合作开始日*/
    public Date getCooperateDate() {
        return cooperateDate;
    }

    /**设置合作开始日*/
    public void setCooperateDate(Date cooperateDate, Operator ... oper) {
        this.cooperateDate = cooperateDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("cooperateDate", param);
            }
        }
    }
    /**获取资质图片，关联image表*/
    public String getPic() {
        return pic;
    }

    /**设置资质图片，关联image表*/
    public void setPic(String pic, Operator ... oper) {
        this.pic = pic;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("pic", param);
            }
        }
    }
    /**获取地址*/
    public Integer getAddress() {
        return address;
    }

    /**设置地址*/
    public void setAddress(Integer address, Operator ... oper) {
        this.address = address;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("address", param);
            }
        }
    }
    /**获取简介*/
    public String getIntroduction() {
        return introduction;
    }

    /**设置简介*/
    public void setIntroduction(String introduction, Operator ... oper) {
        this.introduction = introduction;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("introduction", param);
            }
        }
    }
    /**获取还款途径*/
    public String getRepayWay() {
        return repayWay;
    }

    /**设置还款途径*/
    public void setRepayWay(String repayWay, Operator ... oper) {
        this.repayWay = repayWay;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("repayWay", param);
            }
        }
    }
    /**获取电子签章*/
    public String getElectronicSeal() {
        return electronicSeal;
    }

    /**设置电子签章*/
    public void setElectronicSeal(String electronicSeal, Operator ... oper) {
        this.electronicSeal = electronicSeal;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("electronicSeal", param);
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
        departmentCd("DEPARTMENT_CD"),
        registeredCapital("REGISTERED_CAPITAL"),
        createdDate("CREATED_DATE"),
        cooperateDate("COOPERATE_DATE"),
        pic("PIC"),
        address("ADDRESS"),
        introduction("INTRODUCTION"),
        repayWay("REPAY_WAY"),
        electronicSeal("ELECTRONIC_SEAL"),
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
        return "PfmTenantDepartmentInfo ["
        + ", tenantCd=" + tenantCd
        + ", departmentCd=" + departmentCd
        + ", registeredCapital=" + registeredCapital
        + ", createdDate=" + createdDate
        + ", cooperateDate=" + cooperateDate
        + ", pic=" + pic
        + ", address=" + address
        + ", introduction=" + introduction
        + ", repayWay=" + repayWay
        + ", electronicSeal=" + electronicSeal
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
