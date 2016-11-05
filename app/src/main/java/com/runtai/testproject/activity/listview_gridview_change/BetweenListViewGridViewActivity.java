package com.runtai.testproject.activity.listview_gridview_change;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者：高炎鹏
 * @时间：2016/10/29 09:52
 * @描述：ListView和GridView相互切换动画效果
 */
public class BetweenListViewGridViewActivity extends BaseActivity {

    List<Map<String, Object>> list;
    ListView blg_lv;
    GridView blg_gv;
    TextView tv_change;
    BetweenListViewGridViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betweenlistviewgridview);
        initData();
        initAnimation();
        initView();

        if (adapter == null) {
            adapter = new BetweenListViewGridViewAdapter(list, this);
        }
        adapter.setIsGridLayout(true);
        changeAnimation();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (list == null) {
            list = new ArrayList<>();
        }
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", "测试数据" + i);
            list.add(map);
        }
    }

    /**
     * 初始化控件、并设置监听
     */
    private void initView() {
        blg_lv = (ListView) findViewById(R.id.blg_lv);
        blg_gv = (GridView) findViewById(R.id.blg_gv);
        tv_change = (TextView) findViewById(R.id.tv_change);
        tv_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.setIsGridLayout(!adapter.isGridLayout());
                changeAnimation();
            }
        });
    }

    /**
     * ListView、GridView动画切换
     */
    private void changeAnimation() {
        // GridView 控件动画
        if (adapter.isGridLayout()) {
            blg_lv.setVisibility(View.GONE);
            blg_gv.setVisibility(View.VISIBLE);
            blg_gv.setAdapter(adapter);
            blg_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(BetweenListViewGridViewActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            blg_gv.setSelection(blg_lv.getFirstVisiblePosition());//设置到置顶位置
            //blg_gv.startAnimation(alphaAnimation);//ListView、GridView整体动画，不是每个item的动画
            blg_gv.setLayoutAnimation(lac_1);
            tv_change.setText("切换到ListView");
        } else {
            // ListView 控件动画
            blg_lv.setVisibility(View.VISIBLE);
            blg_gv.setVisibility(View.GONE);
            blg_lv.setAdapter(adapter);
            blg_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(BetweenListViewGridViewActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
                }
            });
            blg_lv.setSelection(blg_gv.getFirstVisiblePosition());//设置到置顶位置
            //blg_lv.startAnimation(animSet);//ListView、GridView整体动画，不是每个item的动画
            blg_lv.setLayoutAnimation(lac);//(平移+透明)item逐条变化
            tv_change.setText("切换到GridView");
        }
    }

    LayoutAnimationController lac;
    LayoutAnimationController lac_1;
    AnimationSet animSet;
    AlphaAnimation alphaAnimation;

    /**
     * 初始化动画 两种动画集
     * AnimationSet 动画集(叠加效果)
     * LayoutAnimationController item动画(也可以设置成叠加效果)
     * lac      (AlphaAnimation + TranslateAnimation)
     * lac_1    (AlphaAnimation)
     */
    private void initAnimation() {
        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(800);

        TranslateAnimation translateAnimation = new TranslateAnimation(150, 0, 0, 0);
        translateAnimation.setDuration(800);//设置动画持续时间

        animSet = new AnimationSet(true);
        animSet.addAnimation(alphaAnimation);
        animSet.addAnimation(translateAnimation);

        lac = new LayoutAnimationController(animSet, 0.1f);//子项动画时间间隔
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);

        lac_1 = new LayoutAnimationController(alphaAnimation);//子项动画时间间隔
        lac_1.setOrder(LayoutAnimationController.ORDER_NORMAL);
        lac_1.setDelay(0.1f);
    }
}
