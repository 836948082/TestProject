package com.runtai.testproject.activity.expandablelistview;

import android.os.Bundle;
import android.widget.ExpandableListView;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @作者：高炎鹏
 * @时间：2016/10/28 09:01
 * @描述：列表展开、关闭界面
 */
public class ExpandableListViewActivity extends BaseActivity {

    private ExpandableListView expandable_listview;
    private ExpandableListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandablelistview);
        expandable_listview = (ExpandableListView) findViewById(R.id.expandable_listview);

        // 设置系统自带箭头图标null。
        expandable_listview.setGroupIndicator(null);
        setItems();
    }

    private void setItems() {
        List<String> header = new ArrayList<>();

        List<String> child1 = new ArrayList<>();
        List<String> child2 = new ArrayList<>();
        List<String> child3 = new ArrayList<>();
        List<String> child4 = new ArrayList<>();

        HashMap<String, List<String>> hashMap = new HashMap<>();

        //添加父元素条目
        for (int i = 1; i < 5; i++) {
            header.add("Group " + i);
        }

        //添加每项子元素中条目
        for (int i = 1; i < 5; i++) {
            child1.add("Group 1  " + " : Child" + i);
        }
        for (int i = 1; i < 5; i++) {
            child2.add("Group 2  " + " : Child" + i);
        }
        for (int i = 1; i < 6; i++) {
            child3.add("Group 3  " + " : Child" + i);
        }
        for (int i = 1; i < 7; i++) {
            child4.add("Group 4  " + " : Child" + i);
        }

        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);

        adapter = new ExpandableListViewAdapter(ExpandableListViewActivity.this, header, hashMap);
        expandable_listview.setAdapter(adapter);
    }

    /**
     * 将所有父元素项目设置成展开
     */
    public void setGroupOpen() {
        int groupCount = expandable_listview.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandable_listview.expandGroup(i);
        }
    }

    /**
     * 将所有父元素项目设置成关闭
     */
    public void setGroupClose() {
        int groupCount = expandable_listview.getCount();
        for (int i = 0; i < groupCount; i++) {
            expandable_listview.collapseGroup(i);
        }
    }
}
