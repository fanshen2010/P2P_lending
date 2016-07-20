package cn.com.p2p.framework.aop.pagination.bean;


/**
 * <p>
 * 查询参数.
 * </p>
 *
 */
public class Pagination implements Page {
    /**
     * 序列化ID
     */
    private static final long serialVersionUID = 1L;
    /**
     * 每页默认10条数据
     */
    protected int defalutPageRows = 0;
    /**
     * 当前页
     */
    protected int currentPage = 1;
    /**
     * 总页数
     */
    protected int totalPages = 0;
    /**
     * 总数据数
     */
    protected int totalRecord = 0;
    /**
     * 每页的起始行数
     */
    protected int pageStartRow = 0;
    /**
     * 每页显示数据的终止行数
     */
    protected int pageEndRow = 0;
    /**
     * 是否有下一页
     */
    boolean next = false;
    /**
     * 是否有前一页
     */
    boolean previous = false;

    public Pagination(int rows, int pageSize) {
        this.init(rows, pageSize);
    }

    public Pagination() {

    }

    /**
     * 初始化分页参数:需要先设置totalRows
     */
    public void init(int rows, int pageSize) {

        this.defalutPageRows = pageSize;

        this.totalRecord = rows;

        if ((totalRecord % pageSize) == 0) {
            totalPages = totalRecord / pageSize;
        } else {
            totalPages = totalRecord / pageSize + 1;
        }

    }

    @Override
    public void init(int rows, int pageSize, int currentPage) {

        this.defalutPageRows = pageSize;

        this.totalRecord = rows;

        if ((totalRecord % pageSize) == 0) {
            totalPages = totalRecord / pageSize;
        } else {
            totalPages = totalRecord / pageSize + 1;
        }
        if (currentPage != 0)
            gotoPage(currentPage);
    }

    /**
     * 计算当前页的取值范围：pageStartRow和pageEndRow
     */
    private void calculatePage() {
        previous = (currentPage - 1) > 0;

        next = currentPage < totalPages;

        if (currentPage * defalutPageRows < totalRecord) { // 判断是否为最后一页
            pageEndRow = currentPage * defalutPageRows;
            pageStartRow = pageEndRow - defalutPageRows;
        } else {
            pageEndRow = totalRecord;
            pageStartRow = defalutPageRows * (totalPages - 1);
        }

    }

    /**
     * 直接跳转到指定页数的页面
     *
     * @param page
     */
    public void gotoPage(int page) {

        currentPage = page;

        calculatePage();

        // debug1();
    }

    public String debugString() {

        return "共有数据数:" + totalRecord + "共有页数:" + totalPages + "当前页数为:"
                + currentPage + "是否有前一页:" + previous + "是否有下一页:"
                + next + "开始行数:" + pageStartRow + "终止行数:" + pageEndRow;

    }


    @Override
    public int getCurrentPage() {
        return currentPage;
    }


    @Override
    public boolean isNext() {
        return next;
    }


    @Override
    public boolean isPrevious() {
        return previous;
    }


    @Override
    public int getPageEndRow() {
        return pageEndRow;
    }


    @Override
    public int getDefalutPageRows() {
        return defalutPageRows;
    }


    @Override
    public int getPageStartRow() {
        return pageStartRow;
    }


    @Override
    public int getTotalPages() {
        return totalPages;
    }


    @Override
    public int getTotalRecord() {
        return totalRecord;
    }


    @Override
    public void setTotalPages(int i) {
        totalPages = i;
    }


    @Override
    public void setCurrentPage(int i) {
        currentPage = i;
    }


    @Override
    public void setNext(boolean b) {
        next = b;
    }


    @Override
    public void setPrevious(boolean b) {
        previous = b;
    }


    @Override
    public void setPageEndRow(int i) {
        pageEndRow = i;
    }


    @Override
    public void setDefalutPageRows(int i) {
        defalutPageRows = i;
    }


    @Override
    public void setPageStartRow(int i) {
        pageStartRow = i;
    }


    @Override
    public void setTotalRecord(int i) {
        totalRecord = i;
    }

}
