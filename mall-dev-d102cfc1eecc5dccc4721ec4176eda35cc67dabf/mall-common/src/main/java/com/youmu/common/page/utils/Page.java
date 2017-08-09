package com.youmu.common.page.utils;

import java.util.List;

/**
 * 分页对象
 *
 * @author zh
 */
public class Page<E> {

    public static final int DEFAULT_PAGE_NUM = 1;

    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 页码，从1开始
     */
    private int pageNum;
    
    /**
     * 页面大小
     */
    private int pageSize;
    
    /**
     * 总数
     */
    private long total;
    
    /** 参数 */
    private List<E> data;
    
    public Page() {
        super();
    }

    public Page(Integer pageNum, Integer pageSize) {
        this.pageNum = (pageNum == null ? DEFAULT_PAGE_NUM : pageNum);
        this.pageSize = (pageSize == null || pageSize >=Integer.MAX_VALUE ? DEFAULT_PAGE_SIZE : pageSize);
        // 不分页
        if(pageNum == null && pageSize == null) {
            this.pageSize = Integer.MAX_VALUE;
        }
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total == null ? 0L : total;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
    
}