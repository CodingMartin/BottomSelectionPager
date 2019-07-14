package com.longtu.wanya.bottomselection;

import java.util.List;

class Paging<T extends BottomCommonSelection> {
    private List<T> mDataSet;
    private int mTotalCnt, mPageCnt, mPageSize;

    Paging(List<T> dataSet, int pageSize) {
        this.mDataSet = dataSet;
        this.mTotalCnt = dataSet.size();
        this.mPageSize = pageSize;
        mPageCnt = mTotalCnt % mPageSize == 0 ? mTotalCnt / mPageSize : mTotalCnt / mPageSize + 1;
    }

    /**
     * 获取总页数
     */
    int getPageCnt() {
        return mPageCnt;
    }

    /**
     * 获取分页数据
     */
    List<T> getPagingDataSet(int position) {
        int fromIndex = mPageSize * position;
        int toIndex;
        if (position < (mPageCnt - 1)) {
            toIndex = fromIndex + mPageSize;
        } else {
            toIndex = fromIndex + mTotalCnt % mPageSize;
        }
        return mDataSet.subList(fromIndex, toIndex);
    }
}
