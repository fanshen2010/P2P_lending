/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        WebUserCriteria.java
 * Description:       查询条件WebUserCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-19             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.criteria;

import java.util.Date;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Column;
import cn.com.p2p.framework.dao.Table;

@Table(name = "WEB_USER")
public class WebUserCriteria extends BaseCriteria {

    /**ID*/
    @Column(name="ID")
    private String id;

    /**用户名*/
    @Column(name="LOGIN")
    private String login;

    /**手机号*/
    @Column(name="CELPHONE")
    private String celphone;

    /**邮箱*/
    @Column(name="EMAIL")
    private String email;

    /**身份*/
    @Column(name="IDENTITY")
    private String identity;

    /**密码*/
    @Column(name="PASSWORD")
    private String password;

    /**秘钥*/
    @Column(name="PASSWORD_SALT")
    private String passwordSalt;

    /**当前登陆时间*/
    @Column(name="CURRENT_LOGIN_AT")
    private Date currentLoginAt;

    /**当前登陆IP*/
    @Column(name="CURRENT_LOGIN_IP")
    private String currentLoginIp;

    /**失败登陆次数*/
    @Column(name="FAILED_LOGIN_COUNT")
    private Integer failedLoginCount;

    /**最后一次登陆时间*/
    @Column(name="LAST_LOGIN_AT")
    private Date lastLoginAt;

    /**最后一次登陆IP*/
    @Column(name="LAST_LOGIN_IP")
    private String lastLoginIp;

    /**登陆次数*/
    @Column(name="LOGIN_COUNT")
    private Integer loginCount;

    /**用户类型*/
    @Column(name="USER_TYPE")
    private Integer userType;

    /**中金支付账户ID*/
    @Column(name="CICC_ACCOUNT_ID")
    private String ciccAccountId;

    /**融资人签约账户ID*/
    @Column(name="CICC_DEBITACCOUNT_ID")
    private String ciccDebitaccountId;

    /**注册IP*/
    @Column(name="REGIST_IP")
    private String registIp;

    /**状态*/
    @Column(name="ACTIVE")
    private Long active;

    /**帐户有效flag*/
    @Column(name="VALID_FLAG")
    private String validFlag;

    /**推荐人ID*/
    @Column(name="RECOMMENDED_ID")
    private String recommendedId;

    /**创建时间*/
    @Column(name="CREATE_TIME")
    private Date createTime;

    /**更新时间*/
    @Column(name="UPDATE_TIME")
    private Date updateTime;

         
    public WebUserCriteria() {

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
    public String getLogin() {
        return login;
    }

    /**设置用户名*/
    public void setLogin(String login, Operator ... oper) {
        this.login = login;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("login", param);
            }
        }
    }
    /**获取手机号*/
    public String getCelphone() {
        return celphone;
    }

    /**设置手机号*/
    public void setCelphone(String celphone, Operator ... oper) {
        this.celphone = celphone;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("celphone", param);
            }
        }
    }
    /**获取邮箱*/
    public String getEmail() {
        return email;
    }

    /**设置邮箱*/
    public void setEmail(String email, Operator ... oper) {
        this.email = email;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("email", param);
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
    /**获取密码*/
    public String getPassword() {
        return password;
    }

    /**设置密码*/
    public void setPassword(String password, Operator ... oper) {
        this.password = password;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("password", param);
            }
        }
    }
    /**获取秘钥*/
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**设置秘钥*/
    public void setPasswordSalt(String passwordSalt, Operator ... oper) {
        this.passwordSalt = passwordSalt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("passwordSalt", param);
            }
        }
    }
    /**获取当前登陆时间*/
    public Date getCurrentLoginAt() {
        return currentLoginAt;
    }

    /**设置当前登陆时间*/
    public void setCurrentLoginAt(Date currentLoginAt, Operator ... oper) {
        this.currentLoginAt = currentLoginAt;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentLoginAt", param);
            }
        }
    }
    /**获取当前登陆IP*/
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    /**设置当前登陆IP*/
    public void setCurrentLoginIp(String currentLoginIp, Operator ... oper) {
        this.currentLoginIp = currentLoginIp;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("currentLoginIp", param);
            }
        }
    }
    /**获取失败登陆次数*/
    public Integer getFailedLoginCount() {
        return failedLoginCount;
    }

    /**设置失败登陆次数*/
    public void setFailedLoginCount(Integer failedLoginCount, Operator ... oper) {
        this.failedLoginCount = failedLoginCount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("failedLoginCount", param);
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
    /**获取登陆次数*/
    public Integer getLoginCount() {
        return loginCount;
    }

    /**设置登陆次数*/
    public void setLoginCount(Integer loginCount, Operator ... oper) {
        this.loginCount = loginCount;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("loginCount", param);
            }
        }
    }
    /**获取用户类型*/
    public Integer getUserType() {
        return userType;
    }

    /**设置用户类型*/
    public void setUserType(Integer userType, Operator ... oper) {
        this.userType = userType;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("userType", param);
            }
        }
    }
    /**获取中金支付账户ID*/
    public String getCiccAccountId() {
        return ciccAccountId;
    }

    /**设置中金支付账户ID*/
    public void setCiccAccountId(String ciccAccountId, Operator ... oper) {
        this.ciccAccountId = ciccAccountId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("ciccAccountId", param);
            }
        }
    }
    /**获取融资人签约账户ID*/
    public String getCiccDebitaccountId() {
        return ciccDebitaccountId;
    }

    /**设置融资人签约账户ID*/
    public void setCiccDebitaccountId(String ciccDebitaccountId, Operator ... oper) {
        this.ciccDebitaccountId = ciccDebitaccountId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("ciccDebitaccountId", param);
            }
        }
    }
    /**获取注册IP*/
    public String getRegistIp() {
        return registIp;
    }

    /**设置注册IP*/
    public void setRegistIp(String registIp, Operator ... oper) {
        this.registIp = registIp;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("registIp", param);
            }
        }
    }
    /**获取状态*/
    public Long getActive() {
        return active;
    }

    /**设置状态*/
    public void setActive(Long active, Operator ... oper) {
        this.active = active;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("active", param);
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
    /**获取推荐人ID*/
    public String getRecommendedId() {
        return recommendedId;
    }

    /**设置推荐人ID*/
    public void setRecommendedId(String recommendedId, Operator ... oper) {
        this.recommendedId = recommendedId;
        if (oper != null) {
            for (Operator param : oper) {
                this.operatorMap.put("recommendedId", param);
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

    /**
     * 排序字段枚举定义
     * @author fero auto
     *
     */
    public static enum OrderField implements OrderColumn {

        id("ID"),
        login("LOGIN"),
        celphone("CELPHONE"),
        email("EMAIL"),
        identity("IDENTITY"),
        password("PASSWORD"),
        passwordSalt("PASSWORD_SALT"),
        currentLoginAt("CURRENT_LOGIN_AT"),
        currentLoginIp("CURRENT_LOGIN_IP"),
        failedLoginCount("FAILED_LOGIN_COUNT"),
        lastLoginAt("LAST_LOGIN_AT"),
        lastLoginIp("LAST_LOGIN_IP"),
        loginCount("LOGIN_COUNT"),
        userType("USER_TYPE"),
        ciccAccountId("CICC_ACCOUNT_ID"),
        ciccDebitaccountId("CICC_DEBITACCOUNT_ID"),
        registIp("REGIST_IP"),
        active("ACTIVE"),
        validFlag("VALID_FLAG"),
        recommendedId("RECOMMENDED_ID"),
        createTime("CREATE_TIME"),
        updateTime("UPDATE_TIME");

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
        return "WebUser ["
        + ", id=" + id
        + ", login=" + login
        + ", celphone=" + celphone
        + ", email=" + email
        + ", identity=" + identity
        + ", password=" + password
        + ", passwordSalt=" + passwordSalt
        + ", currentLoginAt=" + currentLoginAt
        + ", currentLoginIp=" + currentLoginIp
        + ", failedLoginCount=" + failedLoginCount
        + ", lastLoginAt=" + lastLoginAt
        + ", lastLoginIp=" + lastLoginIp
        + ", loginCount=" + loginCount
        + ", userType=" + userType
        + ", ciccAccountId=" + ciccAccountId
        + ", ciccDebitaccountId=" + ciccDebitaccountId
        + ", registIp=" + registIp
        + ", active=" + active
        + ", validFlag=" + validFlag
        + ", recommendedId=" + recommendedId
        + ", createTime=" + createTime
        + ", updateTime=" + updateTime
        + "]";
    }

}
