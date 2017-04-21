package com.runtai.testproject.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2017/1/6时间11:15
 * @描述：购物车--子View超出父View效果
 */
public class ShopCarBottomActivity extends BaseActivity {

    private TextView explain;
    String str = "子View超出父View(clipChildren属性的使用)\n\n首先在布局根节点设置android:clipChildren=\"false\"，再使用android:layout_gravity=\"xxx\"控制超出部分。";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopcar);
        initViews();
    }

    public void initViews() {
        explain = (TextView) findViewById(R.id.explain);
        explain.setText(str);
    }

}
