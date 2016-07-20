package cn.com.p2p.framework.aop.pagination.db;

import cn.com.p2p.framework.aop.pagination.bean.Page;


/**
 * Mysql的实现
 *
 */
public class MySQLDialect implements Dialect {


   /**  
    * 获取Mysql数据库的分页查询语句  
    * @param page 分页对象  
    * @param sqlBuffer 包含原sql语句的StringBuffer对象  
    * @return Mysql数据库分页语句  
    */
	@Override
    public String getLimitString(Page page, String sql) {    
        //计算第一条记录的位置，Mysql中记录的位置是从0开始的。    
        //     System.out.println("page:"+page.getPage()+"-------"+page.getRows());
        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        pagingSelect.append(sql);
        int offset = (page.getCurrentPage() - 1) * page.getDefalutPageRows();    
        pagingSelect.append(" limit ").append(offset).append(",").append(page.getDefalutPageRows()); 
        return pagingSelect.toString();    
   } 
}
