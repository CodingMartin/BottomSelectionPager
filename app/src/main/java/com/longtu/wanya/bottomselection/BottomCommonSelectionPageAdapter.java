package com.longtu.wanya.bottomselection;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.longtu.wanya.R;

import java.util.ArrayList;
import java.util.logging.Logger;

public class BottomCommonSelectionPageAdapter extends PagerAdapter implements AdapterView.OnItemClickListener {
    private final int mHorizontalSpacing, mVerticalSpacing, mColumnCnt;
    private final int mItemLayout;
    private final Paging<BottomCommonSelection> mPaging;
    private OnBottomCommonSelectionItemClickListener mOnItemClickListener;

    BottomCommonSelectionPageAdapter(int itemLayout,
                                     ArrayList<BottomCommonSelection> mDataSet,
                                     OnBottomCommonSelectionItemClickListener listener,
                                     int rowCnt, int columnCnt, int horizontalSpacing,
                                     int verticalSpacing) {
        this.mOnItemClickListener = listener;
        this.mItemLayout = itemLayout;
        this.mHorizontalSpacing = horizontalSpacing;
        this.mVerticalSpacing = verticalSpacing;
        this.mColumnCnt = columnCnt;
        //分页
        int pageCnt = rowCnt * columnCnt;
        mPaging = new Paging<>(mDataSet, pageCnt);

    }

    @Override
    public int getCount() {
        return mPaging.getPageCnt();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_bottom_common_selection_grid, container, false);
        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setHorizontalSpacing(mHorizontalSpacing);
        gridView.setVerticalSpacing(mVerticalSpacing);
        gridView.setNumColumns(mColumnCnt);
        gridView.setAdapter(new BottomCommonSelectionItemAdapter(mItemLayout, mPaging.getPagingDataSet(position)));
        gridView.setOnItemClickListener(this);
        container.addView(view);
        return gridView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = container.findViewById(R.id.gridView);
        if (view instanceof GridView) {
            ((GridView) view).setOnItemClickListener(null);
        }
        if (object instanceof View) {
            container.removeView((View) object);
            Log.d("BottomSelection", "remove view from container");
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mOnItemClickListener != null) {
            BottomCommonSelection item = (BottomCommonSelection) parent.getItemAtPosition(position);
            mOnItemClickListener.onItemClick(view, item);
        }
    }

    public int getPageCnt() {
        return mPaging.getPageCnt();
    }
}
