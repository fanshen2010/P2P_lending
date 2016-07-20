package cn.com.p2p.framework.aop.pagination.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import cn.com.p2p.framework.aop.pagination.bean.Page;
import cn.com.p2p.framework.aop.pagination.bean.PageContext;
import cn.com.p2p.framework.util.ReflectionUtils;

/**
 * <p>
 * 数据库分页插件，只拦截查询语句.
 * </p>
 *
 */
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //对于StatementHandler其实只有两个实现类，一个是RoutingStatementHandler，另一个是抽象类BaseStatementHandler，    
        //BaseStatementHandler有三个子类，分别是SimpleStatementHandler，PreparedStatementHandler和CallableStatementHandler，    
        //SimpleStatementHandler是用于处理Statement的，PreparedStatementHandler是处理PreparedStatement的，而CallableStatementHandler是    
        //处理CallableStatement的。Mybatis在进行Sql语句处理的时候都是建立的RoutingStatementHandler，而在RoutingStatementHandler里面拥有一个    
        //StatementHandler类型的delegate属性，RoutingStatementHandler会依据Statement的不同建立对应的BaseStatementHandler，即SimpleStatementHandler、    
        //PreparedStatementHandler或CallableStatementHandler，在RoutingStatementHandler里面所有StatementHandler接口方法的实现都是调用的delegate对应的方法。    
        //我们在PageInterceptor类上已经用@Signature标记了该Interceptor只拦截StatementHandler接口的prepare方法，又因为Mybatis只有在建立RoutingStatementHandler的时候    
        //是通过Interceptor的plugin方法进行包裹的，所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。  
        if(invocation.getTarget() instanceof RoutingStatementHandler){
            RoutingStatementHandler statementHandler = (RoutingStatementHandler)invocation.getTarget();    
            StatementHandler delegate = (StatementHandler) ReflectionUtils.getFieldValue(statementHandler, "delegate");    
            
            //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性    
            MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getFieldValue(delegate, "mappedStatement");
            
            //拦截需要分页的SQL
            if (mappedStatement.getId().matches(_SQL_PATTERN)) {
            	BoundSql boundSql = delegate.getBoundSql();
            	
                //拦截到的prepare方法参数是一个Connection对象    
                Connection connection = (Connection)invocation.getArgs()[0];
                Object parameterObject = boundSql.getParameterObject();
                
                if (parameterObject instanceof Map) {
                	Map paramObjMap = (Map) parameterObject;
                	Collection coll = paramObjMap.values();
                	parameterObject = coll.toArray()[0];
                }
                
                //分页参数--上下文传参
                Page page = null;
                
                //map传参每次都将currentPage重置
                if (parameterObject != null) {
                    page = convertParameter(parameterObject, page);
                }

                //分页参数--context参数里的Page传参
    		    if (page == null) {
    		        page =  PageContext.getPageContext();
    		    }

    		    int defaultPageRows = page.getDefalutPageRows();
    		    if (defaultPageRows == 0) {
        	    	page.setDefalutPageRows(PAGE_ROW_COUNT);
    		    }
    	    	
    		    //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句    
                String sql = boundSql.getSql();    
                //给当前的page参数对象设置总记录数    
                this.setTotalRecord(page, sql, boundSql, parameterObject, mappedStatement, connection);
                //后面用到了context
                if (page != null) {
                    int totPage = page.getTotalRecord();
                    //分页计算
                    page.init(totPage, page.getDefalutPageRows(), page.getCurrentPage());
                    PageContext.setPageContext(page);
                }
                
                //获取分页Sql语句 
                String pageSql = this.DIALECT.getLimitString(page, sql);    
                //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
                ReflectionUtils.setFieldValue(boundSql, "sql", pageSql);
            }
        }
        return invocation.proceed();
    }

    /**  
     * 给当前的参数对象page设置总记录数  
     *  
     * @param page Mapper映射语句对应的参数对象  
     * @param mappedStatement Mapper映射语句  
     * @param connection 当前的数据库连接  
     */    
    private void setTotalRecord(Page page,String sql, BoundSql boundSql, Object parameterObject,
       MappedStatement mappedStatement, Connection connection) {    
   
       //通过查询Sql语句获取到对应的计算总记录数的sql语句    
       String countSql = this.getCountSql(sql);    
       //通过BoundSql获取对应的参数映射    
       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();    
       //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。    
       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, parameterObject);    
       //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象    
       //ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBoundSql);    
       
       //通过connection建立一个countSql对应的PreparedStatement对象。    
       PreparedStatement pstmt = null;    
       ResultSet rs = null;    
       try {    
           pstmt = connection.prepareStatement(countSql); 
           //通过parameterHandler给PreparedStatement对象设置参数 
           setParameters(pstmt, mappedStatement, countBoundSql, parameterObject);
           //parameterHandler.setParameters(pstmt);    
           
           //之后就是执行获取总记录数的Sql语句和获取结果了。    
           rs = pstmt.executeQuery();    
           if (rs.next()) {    
              int totalRecord = rs.getInt(1);    
              //给当前的参数page对象设置总记录数    
              page.setTotalRecord(totalRecord);    
           }    
       } catch (SQLException e) {    
           e.printStackTrace();    
       } finally {    
           try {    
              if (rs != null)    
                  rs.close();    
               if (pstmt != null)    
                  pstmt.close();    
           } catch (SQLException e) {    
              e.printStackTrace();    
           }    
       }    
    }  

    /**
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
     *
     * @param ps              表示预编译的 SQL 语句的对象。
     * @param mappedStatement MappedStatement
     * @param boundSql        SQL
     * @param parameterObject 参数对象
     * @throws java.sql.SQLException 数据库异常
     */
    @SuppressWarnings("unchecked")
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            MetaObject metaObject = parameterObject == null ? null :
                    configuration.newMetaObject(parameterObject);
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
                    if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
                        value = boundSql.getAdditionalParameter(prop.getName());
                        if (value != null) {
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
                        }
                    } else {
                    	int prexIdx = propertyName.indexOf(".");
                    	if (prexIdx > 0) {
                    		propertyName = propertyName.substring(prexIdx + 1);
                    	}
                        value = metaObject == null ? null : metaObject.getValue(propertyName);
                        if (value == null) {
                        	value = new String("");
                        }
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    if (typeHandler == null) {
                        throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
                }
            }
        }
    }
    /**  
     * 根据原Sql语句获取对应的查询总记录数的Sql语句  
     * @param sql  
     * @return  
     */    
    private String getCountSql(String sql) {    
       int index = sql.toUpperCase().indexOf(" FROM ");    
       return "select count(*) " + sql.substring(index);    
    }

    @Override
    public Object plugin(Object target) {
    	if (target instanceof StatementHandler) {
    	    return Plugin.wrap(target, this);
    	} else {
    	 return target;
    	}
    }

    @Override
    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

}
