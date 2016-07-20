package cn.com.p2p.framework.aop.update.interceptor;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;

import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.web.util.Struts2Utils;

/**
 * <p>
 * 数据库分页插件，只拦截查询语句.
 * </p>
 *
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class }) })
public class UpdateQueueInterceptor implements Interceptor, Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 拦截的ID，在mapper中的id，可以匹配正则
     */
    protected String _SQL_PATTERN = "";
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();
        setOperatorValue(configuration, boundSql, mappedStatement.getSqlCommandType());

        Object obj = invocation.proceed();
//        // 只拦截update
//        if (isUpdateMethod(invocation)) {
//            System.out.println("排他处理判断场所：" + obj.toString());
//        }
        return obj;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

//    /**
//    * <p>
//    * 判断该操作是否是update操作
//    * </p>
//    * 
//    * @param invocation
//    * @return 是否是update操作
//    */
//    private boolean isUpdateMethod(Invocation invocation) {
//    	boolean isUpdate = false;
//	    if (invocation.getArgs()[0] instanceof MappedStatement) {
//	        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
//	        Object parameter = null;
//	        if (invocation.getArgs().length > 1) {
//	            parameter = invocation.getArgs()[1];
//	        }
//	        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//	        Configuration configuration = mappedStatement.getConfiguration();
//	        
//	        if (SqlCommandType.UPDATE.equals(mappedStatement.getSqlCommandType())) {
//	        	isUpdate = true;
//	        }
//        }
//        return isUpdate;
//    }
    private void setOperatorValue(Configuration configuration, BoundSql boundSql, SqlCommandType operator) {
    	
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings.size() > 0 && parameterObject != null) {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (SqlCommandType.INSERT == operator) {
                    if ("createTime".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, DateUtils.getCurrentDateTime());
                    } else if ("createUserId".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, Struts2Utils.getLoginUserId());
                    } else if ("updateTime".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, DateUtils.getCurrentDateTime());
                    } else if ("updateUserId".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, Struts2Utils.getLoginUserId());
                    }
                } else if (SqlCommandType.UPDATE == operator) {
                    if ("updateTime".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, DateUtils.getCurrentDateTime());
                    } else if ("updateUserId".equals(propertyName) && metaObject.hasSetter(propertyName)) {
                    	metaObject.setValue(propertyName, Struts2Utils.getLoginUserId());
                    }                	
                }
            }
        }
    }
    /**
     * 设置属性，支持自定义方言类和制定数据库的方式
     * <p>
     * <code>sqlPattern</code> 需要拦截的SQL ID
     * </p>
     *
     * @param p 属性
     */
	@Override
	public void setProperties(Properties p) {
	}

}
