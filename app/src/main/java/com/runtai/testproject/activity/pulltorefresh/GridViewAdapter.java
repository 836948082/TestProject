package com.runtai.testproject.activity.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.runtai.testproject.R;

import java.util.List;
import java.util.Map;

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;

    public GridViewAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<Map<String, Object>> list) {
        this.list = list;
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
        if (list != null) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int index, View view, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
            holder.show_tv = (CheckBox) view.findViewById(R.id.show_tv);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.show_tv.setText(list.get(index).get("name").toString());
        return view;
    }

    class ViewHolder {
        public CheckBox show_tv;
    }
}
