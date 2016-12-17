package com.runtai.testproject.activity.listview_item_effect;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
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
 * @描述：ListView item飞入动画效果(携带ListView滑动方向监控)
 */
public class ListView_itemActivity extends BaseActivity {

    ListView list_item;
    List<String> list = new ArrayList<>();
    ListView_itemAdapter adapter;
    private float mLastY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_item);
        initView();
        list_item = (ListView) findViewById(R.id.list_item);
        adapter = new ListView_itemAdapter(this, list);
        list_item.setAdapter(adapter);
        startAnimation(true);
        list_item.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                // TODO Auto-generated method stub
                final int action = event.getAction();
                switch (action & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_MOVE:
                        float y = event.getY();
                        if (y > mLastY) {
                            Log.e("向下", "向下");
                        } else {
                            Log.e("向上", "向上");
                        }
                        mLastY = y;
                        break;
                }
                return false;
            }
        });
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
