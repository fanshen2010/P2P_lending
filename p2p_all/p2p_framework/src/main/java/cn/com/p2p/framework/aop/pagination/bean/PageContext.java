package cn.com.p2p.framework.aop.pagination.bean;

import java.io.Serializable;

/**
 * <p>
 * 分页参数上下文.
 * </p>
 *
 */
public class PageContext implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 分页参数上下文，
     */
    private static final ThreadLocal<Page> PAGE_CONTEXT_THREAD_LOCAL = new ThreadLocal<Page>();


    /**
     * 取得当前的分页参数上下文
     *
     * @return 分页参数上下文
     */
    public static Page getPageContext() {
    	Page page = PAGE_CONTEXT_THREAD_LOCAL.get();
        if (page == null) {
        	page = new Pagination();
            PAGE_CONTEXT_THREAD_LOCAL.set(page);
        }
        return page;
    }
    
    public static void setPageContext(Page page) {
    	PAGE_CONTEXT_THREAD_LOCAL.set(page);
    }

    /**
     * 清理分页参数上下文
     */
    public static void removeContext() {
        PAGE_CONTEXT_THREAD_LOCAL.remove();
    }

}
