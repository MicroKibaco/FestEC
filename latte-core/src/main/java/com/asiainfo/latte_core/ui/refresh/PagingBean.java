package com.asiainfo.latte_core.ui.refresh;

public final class PagingBean {

    // 当前是第几页
    private int mPageIndex = 0;

    // 总数据条数
    private int mTotal = 0;

    // 一条显示几条数据
    private int mPageSize = 0;

    // 当前已经显示几条数据
    private int mCurrentCount = 0;

    // 加载延迟
    private int mDelayed = 0;

    public int getPageIndex() {
        return mPageIndex;
    }

    public PagingBean setPageIndex(int pageIndex) {
        this.mPageIndex = pageIndex;
        return this;
    }

    public int getTotal() {
        return mTotal;
    }

    public PagingBean setTotal(int total) {
        this.mTotal = total;
        return this;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public PagingBean setPageSize(int pageSize) {
        this.mPageSize = pageSize;
        return this;
    }

    public int getCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setCurrentCount(int currentCount) {
        this.mCurrentCount = currentCount;
        return this;
    }

    public int getDelayed() {
        return mDelayed;
    }

    public PagingBean setDelayed(int delayed) {
        this.mDelayed = delayed;
        return this;
    }

    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }
}
