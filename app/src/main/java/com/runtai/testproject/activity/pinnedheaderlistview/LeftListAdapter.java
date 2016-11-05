package com.runtai.testproject.activity.pinnedheaderlistview;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.runtai.testproject.R;

/**
 * 基本功能：左侧Adapter
 * 创建：王杰
 * 创建时间：16/4/14
 * 邮箱：w489657152@gmail.com
 */
public class LeftListAdapter extends BaseAdapter {

    private static final int GRAY = 0XCCF0F0F0;
    private static final int WHITE = 0XCCFFFFFF;

    private String[] leftStr;
    boolean[] flagArray;
    private Context context;

    public LeftListAdapter(Context context, String[] leftStr, boolean[] flagArray) {
        this.leftStr = leftStr;
        this.context = context;
        this.flagArray = flagArray;
    }

    @Override
    public int getCount() {
        return leftStr.length;
    }

    @Override
    public Object getItem(int arg0) {
        return leftStr[arg0];
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.pinnedheader_left_list_item, null);
            holder.left_list_item = (TextView) view.findViewById(R.id.left_list_item);
            holder.left_background_item = (TextView) view.findViewById(R.id.left_background_item);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.updataView(position);
        return view;
    }

    class ViewHolder {
        private TextView left_list_item;
        private TextView left_background_item;

        public void updataView(int position) {
            left_list_item.setText(leftStr[position]);
            if (flagArray[position]) {
                left_list_item.setBackgroundColor(WHITE);
                left_background_item.setVisibility(View.VISIBLE);
                left_list_item.setTextColor(ContextCompat.getColor(context, R.color.red_text_2));
            } else {
                left_list_item.setBackgroundColor(GRAY);
                left_background_item.setVisibility(View.GONE);
                left_list_item.setTextColor(ContextCompat.getColor(context, R.color.text));
            }
        }
    }
}
