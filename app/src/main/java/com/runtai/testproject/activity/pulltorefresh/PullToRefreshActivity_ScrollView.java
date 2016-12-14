package com.runtai.testproject.activity.pulltorefresh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.GridView;

import com.runtai.pulltorefresh.PullToRefreshLayout;
import com.runtai.pulltorefresh.PullableScrollView;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者：高炎鹏
 * @日期：2016/11/21时间13:47
 * @描述：
 */
public class PullToRefreshActivity_ScrollView extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    private PullToRefreshLayout pulltorefresh;
    private PullableScrollView pullablescrollview;
    private GridView gridview;
    private List<Map<String, Object>> list;
    private GridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh_scrollview);
        initView();
    }

    private void initView() {
        pulltorefresh = (PullToRefreshLayout) findViewById(R.id.scroll_pulltorefresh);
        pulltorefresh.setOnRefreshListener(this);
        pullablescrollview = (PullableScrollView) findViewById(R.id.scroll_pullablescrollview);
        gridview = (GridView) findViewById(R.id.scroll_gridview);
        gridview.setFocusable(false);
        getData();

        pullablescrollview.setOnScrollToBottomListener(new PullableScrollView.OnScrollBottomListener() {
            @Override
            public void srollToBottom() {
                Log.e("滑动到了底部", "自动加载");
                pulltorefresh.autoLoad();
            }
        });
    }

    private void getData() {
        if (list == null) {
            list = new ArrayList<>();
        }

        for (int i = 0; i < 100; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "gridview" + i);
            list.add(map);
        }

        if (adapter == null) {
            adapter = new GridViewAdapter(this, list);
            gridview.setAdapter(adapter);
        } else {
            adapter.setData(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // TODO Auto-generated method stub
        // 下拉刷新操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.e("下拉刷新", "下拉刷新");
                if (list != null) {
                    if (!list.isEmpty()) {
                        list.clear();
                    }
                }
                getData();
                // 千万别忘了告诉控件刷新完毕了哦！
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 200);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        // TODO Auto-generated method stub
        // 上拉加载操作
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Log.e("上拉加载", "上拉加载");
                getData();
                // 千万别忘了告诉控件加载完毕了哦！
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
            }
        }.sendEmptyMessageDelayed(0, 200);
    }
}
