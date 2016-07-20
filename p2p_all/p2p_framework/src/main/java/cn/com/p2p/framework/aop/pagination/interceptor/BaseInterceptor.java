package cn.com.p2p.framework.aop.pagination.interceptor;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Properties;

import javax.xml.bind.PropertyException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.plugin.Interceptor;

import cn.com.p2p.framework.aop.pagination.annotation.Paging;
import cn.com.p2p.framework.aop.pagination.bean.Page;
import cn.com.p2p.framework.aop.pagination.bean.Pagination;
import cn.com.p2p.framework.aop.pagination.db.Dialect;
import cn.com.p2p.framework.aop.pagination.db.MySQLDialect;
import cn.com.p2p.framework.aop.pagination.db.OracleSQLDialect;
import cn.com.p2p.framework.util.ReflectionUtils;
import cn.com.p2p.framework.util.StringUtils;

/**
 * <p>
 * .
 * </p>
 *
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {
    /**
     * 日志
     */
    //protected Log log = LogFactory.getLog(this.getClass());


    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";


    protected Dialect DIALECT;

    /**
     * 拦截的ID，在mapper中的id，可以匹配正则
     */
    protected String _SQL_PATTERN = "";
    
    /**
     * 拦截的ID，在mapper中的id，可以匹配正则
     */
    protected int PAGE_ROW_COUNT = 3;
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 对参数进行转换和检查
     *
     * @param parameterObject 参数对象
     * @param pageVO          参数VO
     * @return 参数VO
     * @throws NoSuchFieldException 无法找到参数
     */
    protected static Page convertParameter(Object parameterObject, Page pageVO) throws NoSuchFieldException {
        if (parameterObject instanceof Page) {
            pageVO = (Pagination) parameterObject;
        } else {
            //参数为某个实体，该实体拥有Page属性
            Paging paging = parameterObject.getClass().getAnnotation(Paging.class);
            String field = paging.field();
            Field pageField = ReflectionUtils.getAccessibleField(parameterObject, field);
            if (pageField != null) {
                pageVO = (Pagination) ReflectionUtils.getFieldValue(parameterObject, field);
                if (pageVO == null) {
                    throw new PersistenceException("分页参数不能为空");
                }
                //通过反射，对实体对象设置分页对象
                ReflectionUtils.setFieldValue(parameterObject, field, pageVO);
            } else {
                throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在分页参数属性！");
            }
        }

        return pageVO;
    }

    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * <p>
     * <code>dialectClass</code>,自定义方言类。可以不配置这项
     * <ode>dbms</ode> 数据库类型，插件支持的数据库
     * <code>sqlPattern</code> 需要拦截的SQL ID
     * </p>
     * 如果同时配置了<code>dialectClass</code>和<code>dbms</code>,则以<code>dbms</code>为主
     *
     * @param p 属性
     */
    protected void initProperties(Properties p) {
        String dialectClass = p.getProperty("dialect");
        if (StringUtils.isEmpty(dialectClass)) {
            try {
                throw new PropertyException("数据库分页方言无法找到!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        } else {
            Dialect.Type dialectType  = Dialect.Type.valueOf(dialectClass.toUpperCase());
            Dialect dialect = null;    
            switch(dialectType){
                case ORACLE:
                    dialect = new OracleSQLDialect();   
                    break;
                //需要实现MySQL的分页逻辑 
                case MYSQL:
                	dialect = new MySQLDialect();
                    break;
            }
            if (dialect == null) {
                throw new NullPointerException("方言实例错误");
            }
            DIALECT = dialect;
        }

        _SQL_PATTERN = p.getProperty("sqlPattern");
        if (StringUtils.isEmpty(_SQL_PATTERN)) {
            try {
                throw new PropertyException("sqlPattern property is not found!");
            } catch (PropertyException e) {
                e.printStackTrace();
            }
        }
        String pageRowCnt = p.getProperty("pageRowCount");
        if (StringUtils.isNotEmpty(pageRowCnt)) {
        	PAGE_ROW_COUNT = Integer.parseInt(pageRowCnt);
        }
    }
}
