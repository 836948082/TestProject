package com.runtai.testproject.viewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.testproject.R;
import com.runtai.testproject.RegActivity;
import com.runtai.testproject.utils.ToastUtil;

import java.util.ArrayList;

public abstract class PagerScrollerActivity extends BaseActivity implements OnPageChangeListener, View.OnClickListener{

    private ViewPager viewPager;
    protected FragmentsAdapter adapter;
    protected ArrayList<TabInfo> tabs;
    private TitleIndicator title;
    private int defaultTab = 0;
    private TextView reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_pager);
        reg = (TextView) findViewById(R.id.reg);
        reg.setOnClickListener(this);
        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.reg:
                ToastUtil.showShort(this, "进入、退出注册界面携带进场、退场动画");
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initView() throws IndexOutOfBoundsException, NullPointerException {
        setTabsAndAdapter();
        viewPager = (ViewPager) findViewById(R.id.vPager);
        // 设置viewpager内部页面之间的间距
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.fragment_viewpager_margin));
        // 设置viewpager内部页面间距的drawable
        viewPager.setPageMarginDrawable(R.color.blue_pager);

        viewPager.setAdapter(adapter);

        //必须让viewPager设置此OnPageChangeListener的实现类，才能对滑动和页面状态监听
        viewPager.setOnPageChangeListener(this);

        title = (TitleIndicator) findViewById(R.id.title);
        title.init(tabs, viewPager);

        //判断默认页面数值是否溢出
        try {
            if (0 > defaultTab || defaultTab >= tabs.size()) {
                Log.v("default", String.valueOf(defaultTab));
                throw new IndexOutOfBoundsException();
            } else {
                title.setDefaultTab(defaultTab);
                viewPager.setCurrentItem(defaultTab);
            }
        } catch (NullPointerException e) {
            throw e;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //读者可在纸上画出多个页面的示意图，自己进行计算便能得出这个结果
        title.onScroll((viewPager.getWidth() + viewPager.getPageMargin()) * position + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        title.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setDefaultTab(int index) {
        this.defaultTab = index;
    }

    protected abstract void setTabsAndAdapter();
}
