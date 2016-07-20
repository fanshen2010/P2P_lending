/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        WebUser.java
 * Description:       实体WebUser类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-19             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;

import java.util.Date;

public class WebUser implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**用户名*/
    private String login;

    /**手机号*/
    private String celphone;

    /**邮箱*/
    private String email;

    /**身份*/
    private String identity;

    /**密码*/
    private String password;

    /**秘钥*/
    private String passwordSalt;

    /**当前登陆时间*/
    private Date currentLoginAt;

    /**当前登陆IP*/
    private String currentLoginIp;

    /**失败登陆次数*/
    private Integer failedLoginCount;

    /**最后一次登陆时间*/
    private Date lastLoginAt;

    /**最后一次登陆IP*/
    private String lastLoginIp;

    /**登陆次数*/
    private Integer loginCount;

    /**用户类型*/
    private Integer userType;

    /**中金支付账户ID*/
    private String ciccAccountId;

    /**融资人签约账户ID*/
    private String ciccDebitaccountId;

    /**注册IP*/
    private String registIp;

    /**状态*/
    private Long active;

    /**帐户有效flag*/
    private String validFlag;

    /**推荐人ID*/
    private String recommendedId;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

         
    public WebUser() {

    }

    /**获取ID*/
    public String getId() {
        return id;
    }

    /**设置ID*/
    public void setId(String id) {
        this.id = id;
    }
    /**获取用户名*/
    public String getLogin() {
        return login;
    }

    /**设置用户名*/
    public void setLogin(String login) {
        this.login = login;
    }
    /**获取手机号*/
    public String getCelphone() {
        return celphone;
    }

    /**设置手机号*/
    public void setCelphone(String celphone) {
        this.celphone = celphone;
    }
    /**获取邮箱*/
    public String getEmail() {
        return email;
    }

    /**设置邮箱*/
    public void setEmail(String email) {
        this.email = email;
    }
    /**获取身份*/
    public String getIdentity() {
        return identity;
    }

    /**设置身份*/
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    /**获取密码*/
    public String getPassword() {
        return password;
    }

    /**设置密码*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**获取秘钥*/
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**设置秘钥*/
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
    /**获取当前登陆时间*/
    public Date getCurrentLoginAt() {
        return currentLoginAt;
    }

    /**设置当前登陆时间*/
    public void setCurrentLoginAt(Date currentLoginAt) {
        this.currentLoginAt = currentLoginAt;
    }
    /**获取当前登陆IP*/
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    /**设置当前登陆IP*/
    public void setCurrentLoginIp(String currentLoginIp) {
        this.currentLoginIp = currentLoginIp;
    }
    /**获取失败登陆次数*/
    public Integer getFailedLoginCount() {
        return failedLoginCount;
    }

    /**设置失败登陆次数*/
    public void setFailedLoginCount(Integer failedLoginCount) {
        this.failedLoginCount = failedLoginCount;
    }
    /**获取最后一次登陆时间*/
    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    /**设置最后一次登陆时间*/
    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
    /**获取最后一次登陆IP*/
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**设置最后一次登陆IP*/
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    /**获取登陆次数*/
    public Integer getLoginCount() {
        return loginCount;
    }

    /**设置登陆次数*/
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }
    /**获取用户类型*/
    public Integer getUserType() {
        return userType;
    }

    /**设置用户类型*/
    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    /**获取中金支付账户ID*/
    public String getCiccAccountId() {
        return ciccAccountId;
    }

    /**设置中金支付账户ID*/
    public void setCiccAccountId(String ciccAccountId) {
        this.ciccAccountId = ciccAccountId;
    }
    /**获取融资人签约账户ID*/
    public String getCiccDebitaccountId() {
        return ciccDebitaccountId;
    }

    /**设置融资人签约账户ID*/
    public void setCiccDebitaccountId(String ciccDebitaccountId) {
        this.ciccDebitaccountId = ciccDebitaccountId;
    }
    /**获取注册IP*/
    public String getRegistIp() {
        return registIp;
    }

    /**设置注册IP*/
    public void setRegistIp(String registIp) {
        this.registIp = registIp;
    }
    /**获取状态*/
    public Long getActive() {
        return active;
    }

    /**设置状态*/
    public void setActive(Long active) {
        this.active = active;
    }
    /**获取帐户有效flag*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置帐户有效flag*/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }
    /**获取推荐人ID*/
    public String getRecommendedId() {
        return recommendedId;
    }

    /**设置推荐人ID*/
    public void setRecommendedId(String recommendedId) {
        this.recommendedId = recommendedId;
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
