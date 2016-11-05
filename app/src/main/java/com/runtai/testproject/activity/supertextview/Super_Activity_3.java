package com.runtai.testproject.activity.supertextview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.runtai.testproject.R;

/**
 * 作者：高炎鹏
 * 时间：2016/10/26 14:53
 * 描述：组合3
 */
public class Super_Activity_3 extends Activity {

    private TextView tv_3_show;
    private String SHOW = "stv:sBottomLineMargin=\"0dp\"//设置控件下面的线的Margin\n" +
            "stv:sCenterTextString=\"中间\"//设置控件中间文字\n" +
            "stv:sLeftTextString=\"左边\"//设置控件左边文字\n" +
            "stv:sRightTextString=\"右边\"//设置控件右边文字";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_3);
        tv_3_show = (TextView) findViewById(R.id.tv_3_show);
        tv_3_show.setText(SHOW);
    }

}
