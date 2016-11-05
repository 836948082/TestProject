package com.runtai.testproject;

import com.runtai.testproject.viewpager.Fragment_DX;
import com.runtai.testproject.viewpager.FragmentsAdapter;
import com.runtai.testproject.viewpager.PagerScrollerActivity;
import com.runtai.testproject.viewpager.TabInfo;
import com.runtai.testproject.viewpager.Fragment_KL;

import java.util.ArrayList;

/**
 * 作者：高炎鹏
 * 时间：2016/9/27 09:07
 * 描述：登录页面
 */
public class LoginActivity extends PagerScrollerActivity {

    @Override
    protected void setTabsAndAdapter() {
        tabs = new ArrayList<>();
        tabs.add(new TabInfo(0, "动态口令登录", new Fragment_KL()));
        tabs.add(new TabInfo(1, "短信验证登录", new Fragment_DX()));
        this.adapter = new FragmentsAdapter(this, getSupportFragmentManager(), tabs);
    }
}
