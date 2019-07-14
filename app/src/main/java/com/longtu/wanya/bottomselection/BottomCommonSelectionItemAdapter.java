package com.longtu.wanya.bottomselection;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BottomCommonSelectionItemAdapter extends BaseAdapter {
    private final int itemLayout;
    private final List<BottomCommonSelection> mDataSet;

    BottomCommonSelectionItemAdapter(int itemLayout, List<BottomCommonSelection> mDataSet) {
        this.itemLayout = itemLayout;
        this.mDataSet = mDataSet;
    }

    @Override
    public int getCount() {
        return mDataSet == null ? 0 : mDataSet.size();
    }

    @Override
    public BottomCommonSelection getItem(int position) {
        return mDataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        BottomCommonSelection item = mDataSet.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (holder.mImageView != null) {
            holder.mImageView.setImageResource(item.getIcon());
        }
        if (holder.mTitleView != null) {
            holder.mTitleView.setText(item.getTitle());
        }
        if (holder.mSubTitleView != null) {
            holder.mSubTitleView.setText(item.getSubTitle());
        }
        return convertView;
    }

    private static class ViewHolder {
        private ImageView mImageView;
        private TextView mTitleView, mSubTitleView;

        ViewHolder(View view) {
            mImageView = view.findViewById(android.R.id.icon);
            mTitleView = view.findViewById(android.R.id.text1);
            mSubTitleView = view.findViewById(android.R.id.text2);
        }

    }
}
