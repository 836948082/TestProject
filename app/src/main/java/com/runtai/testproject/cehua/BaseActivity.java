package com.runtai.testproject.cehua;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;

import com.runtai.testproject.R;

/**
 * 作者：高炎鹏
 * 时间：2016/10/20 16:34
 * 描述：BaseActivity 携带侧滑关闭
 */
public class BaseActivity extends FragmentActivity {
    public SwipeBackLayout swipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_anim_enter, 0);

        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().getDecorView().setBackgroundDrawable(null);
        swipeBackLayout = (SwipeBackLayout) LayoutInflater.from(this).inflate(R.layout.base_swipe, null);
        swipeBackLayout.attachToActivity(this);
    }

    public void setSwipeBackEnable(boolean enable) {
        swipeBackLayout.setEnableGesture(enable);
    }

    /**
     * 返回键调用此方法
     */
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        overridePendingTransition(0, R.anim.activity_anim_exit);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_anim_exit);
    }
}
