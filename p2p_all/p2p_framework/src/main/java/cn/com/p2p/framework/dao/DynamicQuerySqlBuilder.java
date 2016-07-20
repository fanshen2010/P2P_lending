package cn.com.p2p.framework.dao;


public class DynamicQuerySqlBuilder {

	public String findByCriteriaSql(BaseCriteria criteria) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(criteria.tablename());
		
		//where条件
		String where = criteria.getWhere();
		if (where.length() > 0) {
			sb.append(" WHERE ");
			sb.append(where);
		}
		
		//orderby排序
		String orderby = "";
		String sortOrderby = criteria.getSortOrderBy();
		if (sortOrderby.length() > 0) {
		    orderby = sortOrderby;
		} else {
		    orderby = criteria.getOrderBy();
		}

		if (orderby.length() > 0) {
			sb.append(" ORDER BY ");
			sb.append(orderby);
		}
		return sb.toString();
	}
}
