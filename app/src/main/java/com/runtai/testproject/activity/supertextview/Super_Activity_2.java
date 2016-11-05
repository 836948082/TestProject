package com.runtai.testproject.activity.supertextview;

import android.app.Activity;
import android.os.Bundle;

import com.runtai.supertextview.SuperTextView;
import com.runtai.testproject.R;

/**
 * 作者：高炎鹏
 * 时间：2016/10/26 14:53
 * 描述：组合2
 */
public class Super_Activity_2 extends Activity {

    private SuperTextView stv_2_1, stv_2_2, stv_2_3, stv_2_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_2);
    }

}
