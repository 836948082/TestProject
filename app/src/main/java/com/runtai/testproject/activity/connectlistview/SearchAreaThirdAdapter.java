package com.runtai.testproject.activity.connectlistview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.runtai.testproject.R;

import java.util.List;

/**
 * Created by maning on 16/1/25.
 */
public class SearchAreaThirdAdapter extends BaseAdapter {

    private List<String> mDatas;
    private Context mContext;
    private final LayoutInflater layoutInflater;
    private int mPosition;
    private int type;


    public SearchAreaThirdAdapter(Context mContext, List<String> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        layoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list_item_search_area, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mPosition == position) {
            viewHolder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorItemTextRed));
        } else {
            viewHolder.textView.setTextColor(ContextCompat.getColor(mContext, R.color.colorItemTextBlack));
        }
        viewHolder.textView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorThirdItemBackground));
        viewHolder.textView.setText(mDatas.get(position));

        return convertView;
    }

    public void setSelectPosition(int position) {
        mPosition = position;
    }

    public String getCurrentPositionItem() {
        return mDatas.get(mPosition);
    }

    public void setDatas(List<String> mDatas) {
        this.mDatas = mDatas;
    }

    private static class ViewHolder {
        TextView textView;
    }

}
