package com.runtai.testproject.activity.pinnedheaderlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pinnedheaderlistview.PinnedHeaderListView;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2016/11/2时间15:23
 * @描述：
 */
public class PinnedHeaderListViewActivity extends BaseActivity {

    private String[] leftStr = new String[]{"面食类", "盖饭", "寿司", "烧烤", "酒水", "凉菜", "小吃", "粥", "休闲", "面食类", "盖饭", "寿司", "烧烤", "酒水", "凉菜", "小吃", "粥", "休闲", "面食类", "盖饭", "寿司", "烧烤", "酒水", "凉菜", "小吃", "粥", "休闲"};
    private String[][] rightStr = new String[][]{{"热干面", "臊子面", "烩面"},
            {"番茄鸡蛋", "红烧排骨", "农家小炒肉"},
            {"芝士", "丑小丫", "金枪鱼"}, {"羊肉串", "烤鸡翅", "烤羊排"}, {"长城干红", "燕京鲜啤", "青岛鲜啤"},
            {"拌粉丝", "大拌菜", "菠菜花生"}, {"小食组", "紫薯"},
            {"小米粥", "大米粥", "南瓜粥", "玉米粥", "紫米粥"}, {"儿童小汽车", "悠悠球", "熊大", " 熊二", "光头强"},
            {"热干面", "臊子面", "烩面"},
            {"番茄鸡蛋", "红烧排骨", "农家小炒肉"},
            {"芝士", "丑小丫", "金枪鱼"}, {"羊肉串", "烤鸡翅", "烤羊排"}, {"长城干红", "燕京鲜啤", "青岛鲜啤"},
            {"拌粉丝", "大拌菜", "菠菜花生"}, {"小食组", "紫薯"},
            {"小米粥", "大米粥", "南瓜粥", "玉米粥", "紫米粥"}, {"儿童小汽车", "悠悠球", "熊大", " 熊二", "光头强"},
            {"热干面", "臊子面", "烩面"},
            {"番茄鸡蛋", "红烧排骨", "农家小炒肉"},
            {"芝士", "丑小丫", "金枪鱼"}, {"羊肉串", "烤鸡翅", "烤羊排"}, {"长城干红", "燕京鲜啤", "青岛鲜啤"},
            {"拌粉丝", "大拌菜", "菠菜花生"}, {"小食组", "紫薯"},
            {"小米粥", "大米粥", "南瓜粥", "玉米粥", "紫米粥"}, {"儿童小汽车", "悠悠球", "熊大", " 熊二", "光头强"}
    };
    private boolean[] flagArray;

    ListView left_listview;
    PinnedHeaderListView right_listview;

    LeftListAdapter adapter;
    private boolean isScroll = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinnedheaderlistview);

        initData();
        initView();
    }


    private void initData() {
        flagArray = new boolean[leftStr.length];
        for (int i = 0; i < flagArray.length; i++) {
            flagArray[i] = i == 0;
        }
    }

    private void initView() {
        left_listview = (ListView) findViewById(R.id.left_listview);
        right_listview = (PinnedHeaderListView) findViewById(R.id.right_listview);

        final TestSectionedAdapter sectionedAdapter = new TestSectionedAdapter(this, leftStr, rightStr);
        right_listview.setAdapter(sectionedAdapter);

        adapter = new LeftListAdapter(this, leftStr, flagArray);
        left_listview.setAdapter(adapter);

        left_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {

                isScroll = false;
                for (int i = 0; i < leftStr.length; i++) {
                    flagArray[i] = i == position;
                }

                left_listview.smoothScrollToPositionFromTop(position, 0);

                adapter.notifyDataSetChanged();
                int rightSection = 0;
                for (int i = 0; i < position; i++) {
                    rightSection += sectionedAdapter.getCountForSection(i) + 1;
                }
                right_listview.setSelection(rightSection);
            }
        });

        right_listview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView arg0, int scrollState) {
                switch (scrollState) {
                    // 当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        // 判断滚动到底部
                        if (right_listview.getLastVisiblePosition() == (right_listview.getCount() - 1)) {
                            left_listview.setSelection(ListView.FOCUS_DOWN);
                        }

                        // 判断滚动到顶部
                        if (right_listview.getFirstVisiblePosition() == 0) {
                            left_listview.setSelection(0);
                        }
                        break;
                }
            }

            int y = 0;
            int x = 0;
            int z = 0;

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (isScroll) {

                    left_listview.smoothScrollToPositionFromTop(sectionedAdapter.getSectionForPosition(right_listview.getFirstVisiblePosition()), 0);

                    for (int i = 0; i < rightStr.length; i++) {
                        if (i == sectionedAdapter.getSectionForPosition(right_listview.getFirstVisiblePosition())) {
                            flagArray[i] = true;
                            x = i;
                        } else {
                            flagArray[i] = false;
                        }
                    }
                    if (x != y) {
                        adapter.notifyDataSetChanged();
                        y = x;
                        if (y == left_listview.getLastVisiblePosition()) {
                            left_listview.setSelection(z);
                        }
                        if (x == left_listview.getFirstVisiblePosition()) {
                            left_listview.setSelection(z);
                        }
                        if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                            left_listview.setSelection(ListView.FOCUS_DOWN);
                        }
                    }
                } else {
                    isScroll = true;
                }
            }
        });
    }
}
