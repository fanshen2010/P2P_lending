package cn.com.p2p.framework.aop.pagination.db;

import cn.com.p2p.framework.aop.pagination.bean.Page;


/**
 * Mysql的实现
 *
 */
public class OracleSQLDialect implements Dialect {

    /**  
     * 获取Oracle数据库的分页查询语句  
     * @param page 分页对象  
     * @param sqlBuffer 包含原sql语句的StringBuffer对象  
     * @return Oracle数据库的分页查询语句  
     */
    public String getLimitString(Page page, String sql) {
        StringBuffer pagingSelect = new StringBuffer(sql);
        //计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
        int offset = (page.getCurrentPage() - 1) * page.getDefalutPageRows() + 1;    
        pagingSelect.insert(0, "select u.*, rownum r from (").append(") u where rownum < ").append(offset + page.getDefalutPageRows());    
        pagingSelect.insert(0, "select * from (").append(") where r >= ").append(offset);    
        //上面的Sql语句拼接之后大概是这个样子：    
        //select * from (select u.*, rownum r from (select * from t_user) u where rownum < 31) where r >= 16
        return pagingSelect.toString();
    }
}
