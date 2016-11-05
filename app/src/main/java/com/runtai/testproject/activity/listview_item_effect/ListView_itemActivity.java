package com.runtai.testproject.activity.listview_item_effect;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;

import com.runtai.logger.Logger;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：高炎鹏
 * @时间：2016/10/29 09:50
 * @描述：ListView item飞入动画效果
 */
public class ListView_itemActivity extends BaseActivity {

    ListView list_item;
    List<String> list = new ArrayList<>();
    ListView_itemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_item);
        initView();
        list_item = (ListView) findViewById(R.id.list_item);
        adapter = new ListView_itemAdapter(this, list);
        list_item.setAdapter(adapter);
        startAnimation(true);
    }

    /**
     * 设置ListView的item飞入效果动画的两种方式
     * @param istrue
     */
    private void startAnimation(boolean istrue) {
        if (!istrue) {
            Logger.w("XML布局文件加载ListView的item飞入效果动画");
            list_item.startLayoutAnimation();
        } else {
            Logger.w("动态加载ListView的item飞入效果动画");
            //通过加载XML动画设置文件来创建一个Animation对象；
            Animation animation = AnimationUtils.loadAnimation(this, R.anim.list_anim);
            //得到一个LayoutAnimationController对象；
            LayoutAnimationController lac = new LayoutAnimationController(animation);
            //设置控件显示的顺序；ORDER_NORMAL(0 默认), ORDER_REVERSE(1 倒序), ORDER_RANDOM(2 随机)
            lac.setOrder(LayoutAnimationController.ORDER_NORMAL);
            //设置控件显示间隔时间；
            lac.setDelay(0.4f);
            //为ListView设置LayoutAnimationController属性；
            list_item.setLayoutAnimation(lac);
        }
    }

    private void initView() {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < 20; i++) {
            list.add("item == " + i);
        }
    }
}
