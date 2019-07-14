package com.longtu.wanya.bottomselection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.longtu.wanya.R;

import java.util.ArrayList;

public class BottomCommonSelectionDialog extends BottomSheetDialogFragment implements ViewPager.OnPageChangeListener {

    private static final String KEY_ROW = "key_row";
    private static final String KEY_COLUMN = "key_column";
    private static final String KEY_VERTICAL_SPACING = "key_vertical_spacing";
    private static final String KEY_HORIZONTAL_SPACING = "key_horizontal_spacing";
    private static final String KEY_DATA_SET = "key_data_set";
    private static final String KEY_TITLE = "key_title";
    private static final String KEY_SHOW_TITLE = "key_show_title";
    private static final String KEY_GRID_HEIGHT = "key_grid_height";
    private static final String KEY_ITEM_LAYOUT = "key_item_layout";
    private ViewPager mViewPager;
    private LinearLayout mIndicatorLayout;
    private ArrayList<BottomCommonSelection> mDataSet;
    private int mGridHeight, mRowCnt, mColumnCnt, mHorizontalSpacing, mVerticalSpacing;
    private OnBottomCommonSelectionItemClickListener mOnItemClickListener;
    private BottomCommonSelectionPageAdapter mSelectionPageAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBottomCommonSelectionItemClickListener) {
            mOnItemClickListener = ((OnBottomCommonSelectionItemClickListener) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_bottom_common_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleView = view.findViewById(R.id.titleView);
        mViewPager = view.findViewById(R.id.viewPager);
        mIndicatorLayout = view.findViewById(R.id.indicatorView);
        //取值
        Bundle args = getArguments();
        assert args != null;
        mRowCnt = args.getInt(KEY_ROW, 2);
        mColumnCnt = args.getInt(KEY_COLUMN, 4);
        mHorizontalSpacing = args.getInt(KEY_HORIZONTAL_SPACING, 0);
        mVerticalSpacing = args.getInt(KEY_HORIZONTAL_SPACING, 0);
        mGridHeight = args.getInt(KEY_GRID_HEIGHT, 600);
        if (args.getBoolean(KEY_SHOW_TITLE)) {
            titleView.setVisibility(View.VISIBLE);
            titleView.setText(args.getString(KEY_TITLE));
        } else {
            titleView.setVisibility(View.GONE);
        }
        int itemLayout = args.getInt(KEY_ITEM_LAYOUT, 0);
        mDataSet = args.getParcelableArrayList(KEY_DATA_SET);
        if (mDataSet == null) mDataSet = new ArrayList<>(0);
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        lp.height = mGridHeight;
        mViewPager.setLayoutParams(lp);
        mSelectionPageAdapter = new BottomCommonSelectionPageAdapter(itemLayout, mDataSet, mOnItemClickListener, mRowCnt, mColumnCnt, mHorizontalSpacing, mVerticalSpacing);
        mViewPager.setAdapter(mSelectionPageAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);

        int pageCnt = mSelectionPageAdapter.getPageCnt();
        setupIndicatorLayout(pageCnt);
    }

    private void setupIndicatorLayout(int pageCnt) {
        mIndicatorLayout.removeAllViews();
        if (pageCnt > 1) {
            for (int i = 0; i < pageCnt; i++) {
                ImageView imageView = new ImageView(getContext());
                imageView.setImageResource(R.drawable.indicator_bottom_common_selection);
                ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp.leftMargin = 20;
                imageView.setSelected(i == 0);
                mIndicatorLayout.addView(imageView, lp);
            }
        }
    }

    private void unselectIndicator() {
        if (mIndicatorLayout.getChildCount() > 0) {
            for (int i = 0; i < mIndicatorLayout.getChildCount(); i++) {
                mIndicatorLayout.getChildAt(i).setSelected(false);
            }
        }
    }

    public static BottomCommonSelectionDialog newInstance() {
        Bundle args = new Bundle();
        BottomCommonSelectionDialog fragment = new BottomCommonSelectionDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public BottomCommonSelectionDialog setRow(int rowCount) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_ROW, rowCount);
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setColumn(int columnCount) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_COLUMN, columnCount);
            setArguments(arguments);
        }
        return this;
    }

    public <T extends BottomCommonSelection> BottomCommonSelectionDialog setDataSet(ArrayList<T> dataSet) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putParcelableArrayList(KEY_DATA_SET, dataSet);
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setTitle(@Nullable String title) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (TextUtils.isEmpty(title)) {
                arguments.putBoolean(KEY_SHOW_TITLE, false);
            } else {
                arguments.putString(KEY_TITLE, title);
                arguments.putBoolean(KEY_SHOW_TITLE, true);
            }
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setGridHeight(int height) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_GRID_HEIGHT, height);
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setVerticalSpacing(int spacing) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_VERTICAL_SPACING, spacing);
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setHorizontalSpacing(int spacing) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_HORIZONTAL_SPACING, spacing);
            setArguments(arguments);
        }
        return this;
    }

    public BottomCommonSelectionDialog setItemLayout(@LayoutRes int layout) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.putInt(KEY_ITEM_LAYOUT, layout);
            setArguments(arguments);
        }
        return this;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        unselectIndicator();
        if (i < mIndicatorLayout.getChildCount()) {
            mIndicatorLayout.getChildAt(i).setSelected(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onDestroyView() {
        if (mViewPager != null) mViewPager.removeOnPageChangeListener(this);
        if (mDataSet != null) {
            mDataSet.clear();
            mDataSet = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mOnItemClickListener = null;
        super.onDetach();
    }
}
