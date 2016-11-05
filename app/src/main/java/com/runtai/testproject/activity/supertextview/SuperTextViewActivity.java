package com.runtai.testproject.activity.supertextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.runtai.testproject.R;
import com.runtai.testproject.activity.supertextview.Super_Activity_1;
import com.runtai.testproject.activity.supertextview.Super_Activity_2;
import com.runtai.testproject.activity.supertextview.Super_Activity_3;
import com.runtai.testproject.activity.supertextview.Super_Activity_4;
import com.runtai.testproject.activity.supertextview.Super_Activity_5;
import com.runtai.testproject.activity.supertextview.Super_Activity_6;
import com.runtai.testproject.activity.supertextview.Super_Activity_7;

/**
 * 作者：高炎鹏
 * 时间：2016/10/25 16:23
 * 描述：测试功能强大的TextView
 */
public class SuperTextViewActivity extends Activity implements View.OnClickListener {

    private Button button1, button2, button3, button4, button5, button6, button7;
    private Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supertext);
        initView();
    }

    public void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                intent.setClass(this, Super_Activity_1.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent.setClass(this, Super_Activity_2.class);
                startActivity(intent);
                break;
            case R.id.button3:
                intent.setClass(this, Super_Activity_3.class);
                startActivity(intent);
                break;
            case R.id.button4:
                intent.setClass(this, Super_Activity_4.class);
                startActivity(intent);
                break;
            case R.id.button5:
                intent.setClass(this, Super_Activity_5.class);
                startActivity(intent);
                break;
            case R.id.button6:
                intent.setClass(this, Super_Activity_6.class);
                startActivity(intent);
                break;
            case R.id.button7:
                intent.setClass(this, Super_Activity_7.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    /**
     * 可以通过链式设置大部分常用的属性值
     */
//    public void setSuperTextView() {
//        superTextView.setLeftIcon(drawable)
//                .setLeftString("")
//                .setLeftTVColor(0)
//                .setLeftTopString("")
//                .setLeftTopTVColor(0)
//                .setLeftBottomString("")
//                .setLeftBottomTVColor(0)
//                .setLeftBottomString2("")
//                .setLeftBottomTVColor2(0)
//                .setRightString("")
//                .setRightTVColor(0)
//                .setCbChecked(true)
//                .setCbBackground(drawable)
//                .setRightIcon(drawable)
//                .setRightString("")
//                .setRightTVColor(0)
//                .setLeftString("")
//                .setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
//                    @Override
//                    public void onSuperTextViewClick() {
//                        super.onSuperTextViewClick();
//                        //do something
//                    }
//
//                    @Override
//                    public void onLeftTopClick() {
//                        super.onLeftTopClick();
//                        //do something
//                    }
//
//                    @Override
//                    public void onLeftBottomClick() {
//                        super.onLeftBottomClick();
//                        //do something
//                    }
//
//                    @Override
//                    public void onLeftBottomClick2() {
//                        super.onLeftBottomClick2();
//                        //do something
//                    }
//                });
//    }
}
