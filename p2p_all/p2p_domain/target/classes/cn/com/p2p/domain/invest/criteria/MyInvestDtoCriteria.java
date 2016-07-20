/* --------------------------------------------------------------------------------------------------
 * 
 *
 * Title:        InvestCriteria.java
 * Description:       查询条件InvestCriteria类定义
 *
 * Dependencies:
 * 
 * History:
 *     Date                   Modifier              Log
 *     2015-10-07             fero auto             Created
 *
 * ---------------------------------------------------------------------------------------------------
 */
package cn.com.p2p.domain.invest.criteria;

import cn.com.p2p.framework.dao.BaseCriteria;
import cn.com.p2p.framework.dao.Table;

@Table(name = "INVEST")
public class MyInvestDtoCriteria extends BaseCriteria {

    /**  */
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	private String userId;

	/** 获取status */
	public String getStatus() {
		return status;
	}

	/** 设置status */
	public void setStatus(String status) {
		this.status = status;
	}

	/** 获取userId */
	public String getUserId() {
		return userId;
	}

	/** 设置userId */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
