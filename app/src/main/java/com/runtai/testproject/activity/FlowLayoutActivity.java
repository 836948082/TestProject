package com.runtai.testproject.activity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.runtai.flowlayoutlibrary.FlowLayout;
import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.Random;

/**
 * @作者：高炎鹏
 * @时间：2016/10/28 08:30
 * @描述：
 */
public class FlowLayoutActivity extends BaseActivity {

    /**
     * 显示的文字
     */
    private String[] data = new String[]{"QQ",
            "视频",
            "放开那三国",
            "电子书",
            "酒店",
            "单机",
            "小说",
            "斗地主",
            "优酷",
            "网游",
            "WIFI万能钥匙",
            "播放器",
            "捕鱼达人2",
            "机票",
            "游戏",
            "熊出没之熊大快跑",
            "美图秀秀",
            "浏览器",
            "单机游戏",
            "我的世界",
            "电影电视",
            "QQ空间",
            "旅游",
            "免费游戏",
            "2048",
            "刀塔传奇",
            "壁纸",
            "节奏大师",
            "锁屏",
            "装机必备",
            "天天动听",
            "备份",
            "网盘",
            "海淘网",
            "大众点评",
            "爱奇艺视频",
            "腾讯手机管家",
            "百度地图",
            "猎豹清理大师",
            "谷歌地图",
            "hao123上网导航",
            "京东",
            "有你",
            "万年历-农历黄历",
            "支付宝钱包"};
    private FlowLayout flow_layout;
    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowlayout);
        flow_layout = (FlowLayout) findViewById(R.id.flow_layout);
        setFlowLayout(flow_layout, data);
    }

    public void setFlowLayout(FlowLayout flow_layout, String[] names) {
        Random random = new Random();
        // 遍历标签名称数组
        for (String name : names) {
            addTextView(flow_layout, random, name);
        }
    }

    /**
     * 添加一条记录到流式布局中
     *
     * @param random 随机
     * @param tvName 需要显示的文本内容
     */
    public void addTextView(FlowLayout flow_layout, Random random, final String tvName) {
        final TextView view = new TextView(this);
        view.setText(tvName);
        view.setTextColor(Color.WHITE);
        view.setPadding(5, 5, 5, 5);
        view.setGravity(Gravity.CENTER);
        view.setTextSize(14);

        // 设置点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = view.getText().toString().trim();
                Toast.makeText(FlowLayoutActivity.this, text, Toast.LENGTH_SHORT).show();
                getIndex(text);
            }
        });

        // 设置彩色背景
        GradientDrawable normalDrawable = new GradientDrawable();
        normalDrawable.setShape(GradientDrawable.RECTANGLE);
        int a = 255;
        int r = 50 + random.nextInt(150);
        int g = 50 + random.nextInt(150);
        int b = 50 + random.nextInt(150);
        normalDrawable.setColor(Color.argb(a, r, g, b));

        // 设置按下的灰色背景
        GradientDrawable pressedDrawable = new GradientDrawable();
        pressedDrawable.setShape(GradientDrawable.RECTANGLE);
        pressedDrawable.setColor(Color.GRAY);

        // 背景选择器
        StateListDrawable stateDrawable = new StateListDrawable();
        stateDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        stateDrawable.addState(new int[]{}, normalDrawable);

        // 设置背景选择器到TextView上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(stateDrawable);
        }
        flow_layout.addView(view);
    }

    public void getIndex(String text) {
        for (int i = 0; i < data.length; i++) {
            if (text.equals(data[i])) {
                Toast.makeText(FlowLayoutActivity.this, text + "---位置" + i, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
