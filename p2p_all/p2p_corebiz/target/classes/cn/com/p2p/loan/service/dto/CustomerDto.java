package cn.com.p2p.loan.service.dto;

public class CustomerDto {

	private String customerName;
	private String type;
	private String creditCode;
	/**
	 * @return the creditCode
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * @param creditCode the creditCode to set
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
}
