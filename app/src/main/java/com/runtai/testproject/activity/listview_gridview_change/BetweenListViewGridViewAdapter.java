package com.runtai.testproject.activity.listview_gridview_change;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.runtai.testproject.R;

import java.util.List;
import java.util.Map;

/**
 * @作者：高炎鹏
 * @时间：2016/10/29 10:03
 * @描述：BetweenListViewGridViewActivity类的适配器
 */
public class BetweenListViewGridViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> mapList;
    private Context context;
    private boolean isGridLayout = false;

    public BetweenListViewGridViewAdapter(List<Map<String, Object>> mapList, Context context) {
        super();
        this.mapList = mapList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (!isGridLayout) {
            ListViewHolder listViewHolder;
            if (convertView != null && convertView.getTag() instanceof ListViewHolder) {
                listViewHolder = (ListViewHolder) convertView.getTag();
            } else {
                listViewHolder = new ListViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.blg_list_item, null, false);
                listViewHolder.id_iv_list = (ImageView) convertView.findViewById(R.id.id_iv_list);
                listViewHolder.id_tv_list = (TextView) convertView.findViewById(R.id.id_tv_list);
                convertView.setTag(listViewHolder);
            }
            listViewHolder.id_tv_list.setText(mapList.get(position).get("name").toString());
        } else {
            GridViewHolder gridViewHolder;
            if (convertView != null && convertView.getTag() instanceof GridViewHolder) {
                gridViewHolder = (GridViewHolder) convertView.getTag();
            } else {
                gridViewHolder = new GridViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.blg_gird_item, null, false);
                gridViewHolder.id_iv_grid = (ImageView) convertView.findViewById(R.id.id_iv_grid);
                gridViewHolder.id_tv_grid = (TextView) convertView.findViewById(R.id.id_tv_grid);

                gridViewHolder.id_tv_grid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "GridView_子控件触发 --->> position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });

                convertView.setTag(gridViewHolder);
            }
            gridViewHolder.id_tv_grid.setText(mapList.get(position).get("name").toString());
        }
        return convertView;
    }

    class ListViewHolder {
        ImageView id_iv_list;
        TextView id_tv_list;
    }

    class GridViewHolder {
        ImageView id_iv_grid;
        TextView id_tv_grid;
    }

    public boolean isGridLayout() {
        return isGridLayout;
    }

    public void setIsGridLayout(boolean isGridLayout) {
        this.isGridLayout = isGridLayout;
    }
}
