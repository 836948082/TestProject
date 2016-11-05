package com.runtai.testproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2016/10/29时间16:40
 * @描述：ListView滑动时显示、隐藏标题
 */
public class ScrollShowTitleActivity extends BaseActivity {

    private static final String TAG = "ScrollShowTitleActivity";

    private ListView sst_list;
    private LinearLayout ll_top;

    private MyAdapter myAdapter;
    private boolean scrollFlag = false;// 标记是否滑动
    private int lastVisibleItemPosition;// 标记上次滑动位置

    private Animation anim_hide;
    private Animation anim_show;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollshowtitle);
        initView();
        initAnimation();
        initListView();
    }

    private void initAnimation() {
        anim_hide = AnimationUtils.loadAnimation(this, R.anim.translate_hide);
        anim_show = AnimationUtils.loadAnimation(this, R.anim.translate_show);
    }

    private void initListView() {
        myAdapter = new MyAdapter();
        sst_list.setAdapter(myAdapter);
//        View inflate = LayoutInflater.from(ScrollShowTitleActivity.this).inflate(R.layout.listview_head_empty, null, false);
//        sst_list.addHeaderView(inflate);
    }

    private void initView() {
        sst_list = (ListView) findViewById(R.id.sst_list);
        ll_top = (LinearLayout) findViewById(R.id.ll_top);

        sst_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        Log.i(TAG, "SCROLL_STATE_TOUCH_SCROLL");
                        //触摸滑动
                        scrollFlag = true;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        Log.i(TAG, "SCROLL_STATE_IDLE");
                        //空闲状态
                        scrollFlag = false;
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        Log.i(TAG, "SCROLL_STATE_FLING");
                        //惯性滑动
                        scrollFlag = true;
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (scrollFlag) {
                    if (firstVisibleItem > lastVisibleItemPosition) {
                        Log.i(TAG, "上滑");
                        //隐藏搜索
                        dismissTop();
                    }
                    if (firstVisibleItem < lastVisibleItemPosition) {
                        Log.i(TAG, "下滑");
                        //显示搜索
                        showTop();
                    }
                    if (firstVisibleItem == lastVisibleItemPosition) {
                        return;
                    }
                    lastVisibleItemPosition = firstVisibleItem;
                }
            }
        });
    }

    private boolean dismissFlag = true;

    private void dismissTop() {
        if (ll_top.getVisibility() == View.VISIBLE) {
            if (!dismissFlag) {
                return;
            }
            dismissFlag = false;
            if (anim_hide != null) {
                anim_hide.cancel();
            }
            ll_top.setVisibility(View.GONE);
            ll_top.startAnimation(anim_hide);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissFlag = true;
                }
            }, 500);
        }
    }

    private boolean flag = true;

    private void showTop() {
        if (!(ll_top.getVisibility() == View.VISIBLE)) {
            if (!flag) {
                return;
            }
            flag = false;
            if (anim_show != null) {
                anim_show.cancel();
            }
            ll_top.setVisibility(View.VISIBLE);
            ll_top.startAnimation(anim_show);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    flag = true;
                }
            }, 500);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 60;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(ScrollShowTitleActivity.this).inflate(R.layout.list_item_search_area, parent, false);
                holder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.textView.setText("显示、隐藏搜索栏" + position);
            return convertView;
        }
    }

    class ViewHolder {
        TextView textView;
    }

}
