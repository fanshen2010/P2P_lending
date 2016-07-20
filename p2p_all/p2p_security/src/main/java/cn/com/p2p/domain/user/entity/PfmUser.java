/* ---------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        PfmUser.java
 * Description:       实体PfmUser类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-25             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.user.entity;

import java.io.Serializable;

import java.util.Date;

public class PfmUser implements Serializable {

    /**
     * SVU
     */
    private static final long serialVersionUID = 1L;

    /**ID*/
    private String id;

    /**用户名*/
    private String userName;

    /**姓名*/
    private String realName;

    /**手机号*/
    private String contactPhone;

    /**邮箱*/
    private String emailAddress;

    /**身份*/
    private String identity;

    /**登录时间*/
    private Date currentLoginAt;

    /**登录IP*/
    private String currentLoginIp;

    /**帐户锁定flag*/
    private String lockFlag;

    /**login失败次数*/
    private Integer failureLoginAccount;

    /**帐户锁定日期*/
    private Date lockDate;

    /**帐户有效flag*/
    private String validFlag;

    /**登录密码*/
    private String password;

    /**登录密码秘钥*/
    private String passwordSalt;

    /**操作密码*/
    private String passwordOperation;

    /**操作密码秘钥*/
    private String passwordOperationSalt;

    /**职位CD*/
    private String postCd;

    /**部门CD*/
    private String departCd;

    /**商户CD*/
    private String tenantCd;

    /**是否被激活*/
    private String active;

    /**最后一次登陆时间*/
    private Date lastLoginAt;

    /**最后一次登陆IP*/
    private String lastLoginIp;

    /**最后一次请求时间*/
    private Date lastQuestAt;

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    /**创建者*/
    private String createUserId;

    /**更新者*/
    private String updateUserId;

    /**版本*/
    private Integer version;

         
    public PfmUser() {

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
    public String getUserName() {
        return userName;
    }

    /**设置用户名*/
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**获取姓名*/
    public String getRealName() {
        return realName;
    }

    /**设置姓名*/
    public void setRealName(String realName) {
        this.realName = realName;
    }
    /**获取手机号*/
    public String getContactPhone() {
        return contactPhone;
    }

    /**设置手机号*/
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    /**获取邮箱*/
    public String getEmailAddress() {
        return emailAddress;
    }

    /**设置邮箱*/
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**获取身份*/
    public String getIdentity() {
        return identity;
    }

    /**设置身份*/
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    /**获取登录时间*/
    public Date getCurrentLoginAt() {
        return currentLoginAt;
    }

    /**设置登录时间*/
    public void setCurrentLoginAt(Date currentLoginAt) {
        this.currentLoginAt = currentLoginAt;
    }
    /**获取登录IP*/
    public String getCurrentLoginIp() {
        return currentLoginIp;
    }

    /**设置登录IP*/
    public void setCurrentLoginIp(String currentLoginIp) {
        this.currentLoginIp = currentLoginIp;
    }
    /**获取帐户锁定flag*/
    public String getLockFlag() {
        return lockFlag;
    }

    /**设置帐户锁定flag*/
    public void setLockFlag(String lockFlag) {
        this.lockFlag = lockFlag;
    }
    /**获取login失败次数*/
    public Integer getFailureLoginAccount() {
        return failureLoginAccount;
    }

    /**设置login失败次数*/
    public void setFailureLoginAccount(Integer failureLoginAccount) {
        this.failureLoginAccount = failureLoginAccount;
    }
    /**获取帐户锁定日期*/
    public Date getLockDate() {
        return lockDate;
    }

    /**设置帐户锁定日期*/
    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }
    /**获取帐户有效flag*/
    public String getValidFlag() {
        return validFlag;
    }

    /**设置帐户有效flag*/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }
    /**获取登录密码*/
    public String getPassword() {
        return password;
    }

    /**设置登录密码*/
    public void setPassword(String password) {
        this.password = password;
    }
    /**获取登录密码秘钥*/
    public String getPasswordSalt() {
        return passwordSalt;
    }

    /**设置登录密码秘钥*/
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }
    /**获取操作密码*/
    public String getPasswordOperation() {
        return passwordOperation;
    }

    /**设置操作密码*/
    public void setPasswordOperation(String passwordOperation) {
        this.passwordOperation = passwordOperation;
    }
    /**获取操作密码秘钥*/
    public String getPasswordOperationSalt() {
        return passwordOperationSalt;
    }

    /**设置操作密码秘钥*/
    public void setPasswordOperationSalt(String passwordOperationSalt) {
        this.passwordOperationSalt = passwordOperationSalt;
    }
    /**获取职位CD*/
    public String getPostCd() {
        return postCd;
    }

    /**设置职位CD*/
    public void setPostCd(String postCd) {
        this.postCd = postCd;
    }
    /**获取部门CD*/
    public String getDepartCd() {
        return departCd;
    }

    /**设置部门CD*/
    public void setDepartCd(String departCd) {
        this.departCd = departCd;
    }
    /**获取商户CD*/
    public String getTenantCd() {
        return tenantCd;
    }

    /**设置商户CD*/
    public void setTenantCd(String tenantCd) {
        this.tenantCd = tenantCd;
    }
    /**获取是否被激活*/
    public String getActive() {
        return active;
    }

    /**设置是否被激活*/
    public void setActive(String active) {
        this.active = active;
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
    /**获取最后一次请求时间*/
    public Date getLastQuestAt() {
        return lastQuestAt;
    }

    /**设置最后一次请求时间*/
    public void setLastQuestAt(Date lastQuestAt) {
        this.lastQuestAt = lastQuestAt;
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
    /**获取创建者*/
    public String getCreateUserId() {
        return createUserId;
    }

    /**设置创建者*/
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }
    /**获取更新者*/
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**设置更新者*/
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**获取版本*/
    public Integer getVersion() {
        return version;
    }

    /**设置版本*/
    public void setVersion(Integer version) {
        this.version = version;
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
