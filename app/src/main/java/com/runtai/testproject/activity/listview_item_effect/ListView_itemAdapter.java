package com.runtai.testproject.activity.listview_item_effect;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.runtai.testproject.R;

import java.util.List;

/**
 * @作者：高炎鹏
 * @时间：2016/10/29 10:03
 * @描述：ListView_itemActivity类的适配器
 */
public class ListView_itemAdapter extends BaseAdapter {

    Context context;
    List<String> list;

    public ListView_itemAdapter(Context context, List<String> list) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_item, null);
            holder.item_tv = (TextView) convertView.findViewById(R.id.listview_item_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_tv.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView item_tv;
    }
}
