package cn.com.p2p.framework.exception;

public class DBAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public DBAccessException(String errorCode) {
	     this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
