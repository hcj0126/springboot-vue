package com.hcj.comm;

import java.util.List;
import java.util.Map;

/**
 * PageInfo
 *
 * @author hcj
 * @date 2023-06-16
 */
public class PageInfo {
    private Long pageIndex;
    private Long pageSize;
    private Long pageTotal;
    private Long count;
    private List<Map<String, Object>> data;

    public PageInfo() {
    }

    public Long getPageIndex() {
        return this.pageIndex;
    }

    public void setPageIndex(Long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Long getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageTotal() {
        return this.pageTotal;
    }

    public void setPageTotal(Long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getCount() {
        return this.count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Map<String, Object>> getData() {
        return this.data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}