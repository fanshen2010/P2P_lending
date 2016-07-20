package cn.com.p2p.framework.aop.pagination.bean;

import java.io.Serializable;

/**
 * <p>
 * 分页接口.
 * </p>
 *
 */
public interface Page extends Serializable {

    int getCurrentPage();

    boolean isNext();

    boolean isPrevious();

    int getPageEndRow();

    int getDefalutPageRows();

    int getPageStartRow();

    int getTotalPages();

    int getTotalRecord();

    void setTotalPages(int i);

    void setCurrentPage(int i);

    void setNext(boolean b);

    void setPrevious(boolean b);

    void setPageEndRow(int i);

    void setDefalutPageRows(int i);

    void setPageStartRow(int i);

    void setTotalRecord(int i);

    void init(int rows, int pageSize, int currentPage);
}
