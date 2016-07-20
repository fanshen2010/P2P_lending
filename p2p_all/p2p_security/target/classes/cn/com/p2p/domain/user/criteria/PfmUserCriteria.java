/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmUserCriteria.java
 * Description:       查询条件PfmUserCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-25             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "PFM_USER")
public class PfmUserCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**用户名*/
    @Column(name="USER_NAME")
    private String userName;

    /**姓名*/
    @Column(name="REAL_NAME")
    private String realName;

    /**手机号*/
    @Column(name="CONTACT_PHONE")
    private String contactPhone;

    /**邮箱*/
    @Column(name="EMAIL_ADDRESS")
    private String emailAddress;

    /**身份*/
    @Column(name="IDENTITY")
    private String identity;

    /**登录时间*/
    @Column(name="CURRENT_LOGIN_AT")
    private Date currentLoginAt;

    /**登录IP*/
    @Column(name="CURRENT_LOGIN_IP")
    private String currentLoginIp;

    /**帐户锁定flag*/
    @Column(name="LOCK_FLAG")
    private String lockFlag;

    /**login失败次数*/
    @Column(name="FAILURE_LOGIN_ACCOUNT")
    private Integer failureLoginAccount;

    /**帐户锁定日期*/
    @Column(name="LOCK_DATE")
    private Date lockDate;

    /**帐户有效flag*/
    @Column(name="VALID_FLAG")
    private String validFlag;

    /**登录密码*/
    @Column(name="PASSWORD")
    private String password;

    /**登录密码秘钥*/
    @Column(name="PASSWORD_SALT")
    private String passwordSalt;

    /**操作密码*/
    @Column(name="PASSWORD_OPERATION")
    private String passwordOperation;

    /**操作密码秘钥*/
    @Column(name="PASSWORD_OPERATION_SALT")
    private String passwordOperationSalt;

    /**职位CD*/
    @Column(name="POST_CD")
    private String postCd;

    /**部门CD*/
    @Column(name="DEPART_CD")
    private String departCd;

    /**商户CD*/
    @Column(name="TENANT_CD")
    private String tenantCd;

    /**是否被激活*/
    @Column(name="ACTIVE")
    private String active;

    /**最后一次登陆时间*/
    @Column(name="LAST_LOGIN_AT")
    private Date lastLoginAt;

    /**最后一次登陆IP*/
    @Column(name="LAST_LOGIN_IP")
    private String lastLoginIp;

    /**最后一次请求时间*/
    @Column(name="LAST_QUEST_AT")
    private Date lastQuestAt;

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

         
    public PfmUserCriteria() {

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
    /**获取用户名*/
    public String getUserName() {
        return userName;
    }

    /**设置用户名*/
    public void setUserName(String userName, Operator ... oper) {
        this.userName = userName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userName", param);
            }
        }
    }
    /**获取姓名*/
    public String getRealName() {
        return realName;
    }

    /**设置姓名*/
    public void setRealName(String realName, Operator ... oper) {
        this.realName = realName;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("realName", param);
            }
        }
    }
    /**获取手机号*/
    public String getContactPhone() {
        return contactPhone;
    }

    /**设置手机号*/
    public void setContactPhone(String contactPhone, Operator ... oper) {
        this.contactPhone = contactPhone;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("contactPhone", param);
            }
        }
    }
    /**获取邮箱*/
    public String getEmailAddress() {
        return emailAddress;
    }

    /**设置邮箱*/
    public void setEmailAddress(String emailAddress, Operator ... oper) {
        this.emailAddress = emailAddress;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("emailAddress", param);
            }
        }
    }
    /**获取身份*/
    public String getIdentity() {
        return identity;
    }

    /**设置身份*/
    public void setIdentity(String identity, Operator ... oper) {
        this.identity = identity;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("identity", param);
            }
        }
    }
    /**获取登录时间*/
    public Date getCurrentLoginAt() {
        return currentLoginAt;
    }

    /**设置登录时间*/
    public void setCurrentLoginAt(Date currentLoginAt, Operator ... oper) {
        this.currentLoginAt = currentLoginAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentLoginAt", param);
            }
        }
    }
    /**获取登录IP*/
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    /**设置登录IP*/
    public void setCurrentLoginIp(String currentLoginIp, Operator ... oper) {
        this.currentLoginIp = currentLoginIp;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentLoginIp", param);
            }
        }
    }
    /**获取帐户锁定flag*/
    public String getLockFlag() {
        return lockFlag;
    }

    /**设置帐户锁定flag*/
    public void setLockFlag(String lockFlag, Operator ... oper) {
        this.lockFlag = lockFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("lockFlag", param);
            }
        }
    }
    /**获取login失败次数*/
    public Integer getFailureLoginAccount() {
        return failureLoginAccount;
    }

    /**设置login失败次数*/
    public void setFailureLoginAccount(Integer failureLoginAccount, Operator ... oper) {
        this.failureLoginAccount = failureLoginAccount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("failureLoginAccount", param);
            }
        }
    }
    /**获取帐户锁定日期*/
    public Date getLockDate() {
        return lockDate;
    }

    /**设置帐户锁定日期*/
    public void setLockDate(Date lockDate, Operator ... oper) {
        this.lockDate = lockDate;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("lockDate", param);
            }
        }
    }
    /**获取帐户有效flag*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置帐户有效flag*/
    public void setValidFlag(String validFlag, Operator ... oper) {
        this.validFlag = validFlag;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("validFlag", param);
            }
        }
    }
    /**获取登录密码*/
    public String getPassword() {
        return password;
    }

    /**设置登录密码*/
    public void setPassword(String password, Operator ... oper) {
        this.password = password;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("password", param);
            }
        }
    }
    /**获取登录密码秘钥*/
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**设置登录密码秘钥*/
    public void setPasswordSalt(String passwordSalt, Operator ... oper) {
        this.passwordSalt = passwordSalt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("passwordSalt", param);
            }
        }
    }
    /**获取操作密码*/
    public String getPasswordOperation() {
        return passwordOperation;
    }

    /**设置操作密码*/
    public void setPasswordOperation(String passwordOperation, Operator ... oper) {
        this.passwordOperation = passwordOperation;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("passwordOperation", param);
            }
        }
    }
    /**获取操作密码秘钥*/
    public String getPasswordOperationSalt() {
        return passwordOperationSalt;
    }

    /**设置操作密码秘钥*/
    public void setPasswordOperationSalt(String passwordOperationSalt, Operator ... oper) {
        this.passwordOperationSalt = passwordOperationSalt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("passwordOperationSalt", param);
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
    /**获取部门CD*/
    public String getDepartCd() {
        return departCd;
    }

    /**设置部门CD*/
    public void setDepartCd(String departCd, Operator ... oper) {
        this.departCd = departCd;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("departCd", param);
            }
        }
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
    /**获取是否被激活*/
    public String getActive() {
        return active;
    }

    /**设置是否被激活*/
    public void setActive(String active, Operator ... oper) {
        this.active = active;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("active", param);
            }
        }
    }
    /**获取最后一次登陆时间*/
    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    /**设置最后一次登陆时间*/
    public void setLastLoginAt(Date lastLoginAt, Operator ... oper) {
        this.lastLoginAt = lastLoginAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("lastLoginAt", param);
            }
        }
    }
    /**获取最后一次登陆IP*/
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**设置最后一次登陆IP*/
    public void setLastLoginIp(String lastLoginIp, Operator ... oper) {
        this.lastLoginIp = lastLoginIp;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("lastLoginIp", param);
            }
        }
    }
    /**获取最后一次请求时间*/
    public Date getLastQuestAt() {
        return lastQuestAt;
    }

    /**设置最后一次请求时间*/
    public void setLastQuestAt(Date lastQuestAt, Operator ... oper) {
        this.lastQuestAt = lastQuestAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("lastQuestAt", param);
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
        userName("USER_NAME"),
        realName("REAL_NAME"),
        contactPhone("CONTACT_PHONE"),
        emailAddress("EMAIL_ADDRESS"),
        identity("IDENTITY"),
        currentLoginAt("CURRENT_LOGIN_AT"),
        currentLoginIp("CURRENT_LOGIN_IP"),
        lockFlag("LOCK_FLAG"),
        failureLoginAccount("FAILURE_LOGIN_ACCOUNT"),
        lockDate("LOCK_DATE"),
        validFlag("VALID_FLAG"),
        password("PASSWORD"),
        passwordSalt("PASSWORD_SALT"),
        passwordOperation("PASSWORD_OPERATION"),
        passwordOperationSalt("PASSWORD_OPERATION_SALT"),
        postCd("POST_CD"),
        departCd("DEPART_CD"),
        tenantCd("TENANT_CD"),
        active("ACTIVE"),
        lastLoginAt("LAST_LOGIN_AT"),
        lastLoginIp("LAST_LOGIN_IP"),
        lastQuestAt("LAST_QUEST_AT"),
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
        return "PfmUser ["
        + ", id=" + id
        + ", userName=" + userName
        + ", realName=" + realName
        + ", contactPhone=" + contactPhone
        + ", emailAddress=" + emailAddress
        + ", identity=" + identity
        + ", currentLoginAt=" + currentLoginAt
        + ", currentLoginIp=" + currentLoginIp
        + ", lockFlag=" + lockFlag
        + ", failureLoginAccount=" + failureLoginAccount
        + ", lockDate=" + lockDate
        + ", validFlag=" + validFlag
        + ", password=" + password
        + ", passwordSalt=" + passwordSalt
        + ", passwordOperation=" + passwordOperation
        + ", passwordOperationSalt=" + passwordOperationSalt
        + ", postCd=" + postCd
        + ", departCd=" + departCd
        + ", tenantCd=" + tenantCd
        + ", active=" + active
        + ", lastLoginAt=" + lastLoginAt
        + ", lastLoginIp=" + lastLoginIp
        + ", lastQuestAt=" + lastQuestAt
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + ", createUserId=" + createUserId
        + ", updateUserId=" + updateUserId
        + ", version=" + version
        + "]";
    }

}
