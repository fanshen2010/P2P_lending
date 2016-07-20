package cn.com.p2p.framework.dao;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.p2p.framework.aop.pagination.annotation.Paging;
import cn.com.p2p.framework.aop.pagination.bean.Page;
import cn.com.p2p.framework.aop.pagination.bean.Pagination;

@Paging(field = "page")
public class BaseCriteria implements Serializable {

	/**
	 * default SVU
	 */
	private static final long serialVersionUID = 1L;

	/**翻页信息*/
    private Page page = new Pagination();
    
    /**字段与操作运算关系*/
    protected Map<String, Operator> operatorMap = new HashMap<String, Operator>();
    
    /**OderBy 字段管理*/
    protected List<String> oderbyList = new ArrayList<String>();
    
    /**sort*/
    protected Map<String, String> sortMap = new LinkedHashMap<String, String>();

    /** 
     * 获取查询条件类对应的表名 
     * 需要Criteria中的属性定义@Table(name) 
     * @return
     */  
    public String tablename() {
    	String tablename = "";
        Table table = this.getClass().getAnnotation(Table.class);
        if(table != null) {
        	tablename = table.name();
        }
        return tablename;	
    }

    /** 
     * 获取用于WHERE的 有值字段表
     * @return
     */  
    public String getWhere() {
        Field[] fields = this.getClass().getDeclaredFields();    
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for(Field field : fields) {
        	Column column = field.getAnnotation(Column.class);
            if(!isNull(field) && column != null) {
                String columnName = column.name();
                WhereColumn whereCol = new WhereColumn(columnName, field.getName(), operatorMap.get(field.getName())); 
                if (cnt > 0) {
                	sb.append(" AND ");
                }
                sb.append(whereCol.toCondition());
                cnt++;
            }
        }
        return sb.toString();
    }
    
    /**
     * 动态设置排序
     * @param columns 排序字段
     */
    public void setOrderBy(OrderColumn ...columns) {
    	for (OrderColumn col : columns) {
    		oderbyList.add(col.getValue());
    	}
    	
    }
    
    /**
     * 
     * @param column 字段枚举类型
     * @param sortType 类型（ASC／DESC）
     */
    public void setSortFields(OrderColumn column, SortType sortType) {
    	sortMap.put(column.getValue(), sortType.getValue());
    }
    
    /**
     * 获取oderby动态sql文部分
     * 
     * @return oderby部分sql文
     */
    public String getSortOrderBy() {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        Iterator<String> keys = sortMap.keySet().iterator();

        while (keys.hasNext()) {
            String key = keys.next();
            String sortType = this.sortMap.get(key);
            String orderby = key + " " + sortType;
            if (cnt > 0) {
                sb.append(", " + orderby);
            } else {
                sb.append(orderby);
            }
            cnt++;
        }
        return sb.toString();
    }
    /**
     * 获取oderby动态sql文部分
     * @return oderby部分sql文
     */
    @Deprecated
    public String getOrderBy() {
    	StringBuilder sb = new StringBuilder();
    	int cnt = 0;
    	for (String col : oderbyList) {
    		String orderby = col;
    		//排序类型取得
    		String sortType = "";
    		if (sortMap.containsKey(col)) {
    			sortType = this.sortMap.get(col);
    		}
    		orderby = orderby + " " + sortType;
    		if (cnt > 0) {
    			sb.append(", " + orderby);
    		} else {
    			sb.append(orderby);
    		}
    		
    		cnt++;
    	}
    	return sb.toString();
    }
    /**
     * 判断条件字段值是否为空
     * @param field
     * @return
     */
	private boolean isNull(Field field) {
		boolean result = false;
		try {
		    field.setAccessible(true);
		    if (field.get(this) == null) {
		    	result = true;
		    } else {
		    	Object value = field.get(this);
		    	if (value instanceof String) {
		    		if ("".equals(value.toString())) {
		    			result = true;
		    		}
		    	}
		    }
		    return result;
		} catch (SecurityException e) {
		    e.printStackTrace();
		} catch (IllegalArgumentException e) {
		    e.printStackTrace();
		} catch (IllegalAccessException e) {
		    e.printStackTrace();
		}
		return false;
	}

    /**
     * 获取翻页信息
     * @return
     */
    public Page getPage() {
		return page;
	}
    
    /**
     * 获取翻页信息
     * @param
     */
	public void setCurrentPage(int pageNo) {
		this.page.setCurrentPage(pageNo);
	}
	
	/**
	 * 
	 * @param page
	 */
    public void setPage(Page page) {
		this.page = page;
	}

    public static enum SortType {
        ASC("ASC"),
        DESC("DESC");
        private String value;
        private SortType(String value) {
            this.value = value;
        }
        
        public String getValue(){
            return this.value;
        }
    }

	/**
     * 
     * 操作逻辑符号
     *
     * @author 
     *
     */
    public static enum Operator {    
    	/**大于*/
    	greaterThan(">"),
    	/**小于*/
    	lessThan("<"),
    	/**等于*/
    	equal("="),
    	/**大于等于*/
    	greaterThanAndEqual(">="),
        /**小于等于*/
    	lessThanAndEqual("<="),
    	/**不等于*/
    	notEqual("!="),
    	/**LIKE*/
    	like("like"),
    	/**in*/
    	in("in");
    	
    	private String value = "";
    	private Operator(String value){
    		this.value = value;
    	}

    	public String getValue(){
    		return this.value;
    	}
    }

    /** 
     * Where条件信息 
     * @author 
     * 
     */  
    public class WhereColumn {
    	private String column;
    	private String name;
    	private Operator oper;

        public WhereColumn(String column, String name, Operator oper) {
        	this.column = column;
            this.name = name;
            this.oper = oper;
        }
        public String toCondition() {
        	StringBuilder sb = new StringBuilder(100);
        	sb.append(column);
        	sb.append(" ");
        	sb.append(oper.value);
        	sb.append(" ");
        	if(Operator.like.getValue().equals(oper.value)) {
        		sb.append("CONCAT (CONCAT('%', #{").append(name).append("}),'%')");
        	} else {
        		sb.append("#{").append(name).append('}');	
        	}
        	
        	return sb.toString();
        }
    }
    
    /**
     * oderby 枚举类型接口
     * @author 
     *
     */
    public interface OrderColumn {

    	/**
    	 * 获取oderby列名
    	 * @return
    	 */
    	public String getValue();
    }
}
