package cn.com.p2p.loan.service.dto;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.p2p.domain.system.entity.UploadFile;

/**
 * 个人基本信息Dto
 * @author 
 *
 */
public class LoanPersonalBasicDto {
	
	/** 客户姓名 */
	private String name;
	
	/** 身份证号 */
	private String identity;
	
	/** 性别 */
	private String gender;//TODO
	
	/** 出生日期 */
	private Date birthdayDate;
	
	/** 用户籍贯	 */
	private String birthplace;
	
	/** 手机号码 */
	private String cellphone;

	/** 电子邮件 */
	private String email;
	
	/** 用户职业	 */
	private String occupation;
	
	/** 联系地址*/
	private String address;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
