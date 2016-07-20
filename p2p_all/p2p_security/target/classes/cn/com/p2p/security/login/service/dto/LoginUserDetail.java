
package cn.com.p2p.security.login.service.dto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import cn.com.p2p.framework.context.UserContext;


/**
 * 登录用户实体
 */
public class LoginUserDetail implements UserDetails, UserContext {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5015597478106952082L;

	private String password;

	private String username;

	private Set<GrantedAuthority> authorities;

	private String id;

	private List<String> roleNameList = new ArrayList<String>();

	private String salt;

	private String passwordOperation;
	private String companyId;

	private String organizationId;

	private String phoneNumber;

	private String realName;

	private String identity;

	private int userType = 0;

	private boolean enabled;

	private boolean status = true;

	private boolean isManager;

	private String message;

	private String information;//发送平台消息
	
	private String passwordOperationSalt;
	
	private boolean isNotlocked = true;
	
	private String postCd;
	
	private String email;
	
	private String ciccAccountId;
	
	private String ciccDebitAccountId;

    public LoginUserDetail() {
    }

	public LoginUserDetail(String id,String username,String password,String salt,String passwordOperation,String passwordOperationSalt,
			Collection<? extends GrantedAuthority> authorities,String companyId,String organizationId,List<String> roleNameList,
			String phoneNumber,String realName,String identity,boolean enabled,boolean status, boolean isNotlocked)
	{

		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.passwordOperation = passwordOperation;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
		this.organizationId = organizationId;
		this.companyId = companyId;
		this.roleNameList = roleNameList;

		this.phoneNumber = phoneNumber;
		this.realName = realName;
		this.identity = identity;
		this.passwordOperationSalt = passwordOperationSalt;
		this.enabled = enabled;
		this.status = status;
		this.isNotlocked = isNotlocked;
	}
	public LoginUserDetail(String id,String username,String password,String salt,
			Collection<? extends GrantedAuthority> authorities,
			String phoneNumber, String email, String ciccAccountId, String ciccDebitAccountId, boolean enabled,boolean status, boolean isNotlocked)
	{

		this.id = id;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));

		this.phoneNumber = phoneNumber;
		this.email = email;
		this.ciccAccountId = ciccAccountId;
		this.ciccDebitAccountId = ciccDebitAccountId;
		this.enabled = enabled;
		this.status = status;
		this.isNotlocked = isNotlocked;
	}

	@Override
	public String getPasswordOperation(){

		return passwordOperation;
	}

	@Override
	public String getMessage(){

		return message;
	}

	@Override
	public void setMessage(String message){

		this.message = message;
	}

	@Override
	public String getInformation(){

		return information;
	}

	@Override
	public void setInformation(String information){

		this.information = information;
	}


	/**
	 * 用户类型
	 * 
	 * @return
	 */
	@Override
	public int getUserType(){

		return userType;
	}


	/**
	 * 手机号
	 * 
	 * @return
	 */
	@Override
	public String getPhoneNumber(){

		return phoneNumber;
	}


	/**
	 * 真实姓名
	 * 
	 * @return
	 */
	@Override
	public String getRealName(){

		return realName;
	}


	/**
	 * 身份证号
	 * 
	 * @return
	 */
	@Override
	public String getIdentity(){

		return identity;
	}


	/**
	 * 登录用户的公司id
	 * 
	 * @return
	 */
	@Override
	public String getCompanyId(){

		return companyId;
	}


	/**
	 * 登录用户的部门id
	 * 
	 * @return
	 */
	@Override
	public String getOrganizationId(){

		return organizationId;
	}


	/**
	 * 取得角色名称
	 * 
	 * @return
	 */
	@Override
	public List<String> getRoleNameList(){

		return roleNameList;
	}


	/**
	 * 登录id
	 * 
	 * @return
	 */
	@Override
	public String getId(){

		return id;
	}


	/**
	 * 判断是否是注册经理人
	 * 
	 * @return
	 */
	@Override
	public boolean getIsManager(){

		return isManager;
	}


	public void setManager(boolean isManager){

		this.isManager = isManager;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){

		return authorities;
	}


	public String getSalt(){

		return this.salt;
	}


	/**
	 * 密码
	 */
	@Override
	public String getPassword(){

		return password;
	}


	/**
	 * 登录名
	 */
	@Override
	public String getUsername(){

		return username;
	}


	@Override
	public boolean isAccountNonExpired(){

		return status;
	}


	@Override
	public boolean isAccountNonLocked(){

		return isNotlocked;
	}


	@Override
	public boolean isCredentialsNonExpired(){

		return true;
	}


	@Override
	public boolean isEnabled(){

		return enabled;
	}


	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities){

		Assert.notNull(authorities,"Cannot pass a null GrantedAuthority collection");
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<GrantedAuthority>(new AuthorityComparator());

		for(GrantedAuthority grantedAuthority : authorities){
			Assert.notNull(grantedAuthority,"GrantedAuthority list cannot contain any null elements");
			sortedAuthorities.add(grantedAuthority);
		}

		return sortedAuthorities;
	}


	private static class AuthorityComparator implements Comparator<GrantedAuthority>,Serializable{

		private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;


		public int compare(GrantedAuthority g1,GrantedAuthority g2){

			// Neither should ever be null as each entry is checked before
			// adding it to the set.
			// If the authority is null, it is a custom authority and should
			// precede others.
			if(g2.getAuthority() == null){
				return -1;
			}

			if(g1.getAuthority() == null){
				return 1;
			}

			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}


	@Override
	public String toString(){

		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append(": ");
		sb.append("Username: ").append(this.username).append("; ");
		sb.append("Password: [PROTECTED]; ");
		// sb.append("Enabled: ").append(this.enabled).append("; ");
		// sb.append("AccountNonExpired: ").append(this.accountNonExpired)
		// .append("; ");
		// sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired)
		// .append("; ");
		// sb.append("AccountNonLocked: ").append(this.accountNonLocked)
		// .append("; ");

		if(!authorities.isEmpty()){
			sb.append("Granted Authorities: ");

			boolean first = true;
			for(GrantedAuthority auth : authorities){
				if(!first){
					sb.append(",");
				}
				first = false;

				sb.append(auth);
			}
		} else{
			sb.append("Not granted any authorities");
		}

		return sb.toString();
	}

	@Override
	public String getPasswordOperationSalt(){

		return passwordOperationSalt;
	}


	public boolean equals(Object object){

		if(object instanceof LoginUserDetail){
			if(this.id.equals(((LoginUserDetail) object).getId()))
				return true;
		}
		return false;
	}

	@Override
	public int hashCode(){

		return this.id.hashCode();
	}


	/**
	 * @param enabled the enabled to set
	 */
	
	public void setEnabled(boolean enabled){

		this.enabled = enabled;
	}


	@Override
	public String getPasswordSalt() {
		return this.salt;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
		
	}

	@Override
	public void setPasswordOperationSalt(String passwordOperationSalt) {
		this.passwordOperationSalt = passwordOperationSalt;
		
	}

	@Override
	public void setIsManager(boolean isManager) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getPostCd() {
		return postCd;
	}
	
	@Override
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	@Override
	public String getEmail() {
		return email;
	}
	
	@Override
	public String getCiccAccountId() {
		return ciccAccountId;
	}
	
	@Override
	public String getCiccDebitAccountId() {
		return ciccDebitAccountId;
	}


}
