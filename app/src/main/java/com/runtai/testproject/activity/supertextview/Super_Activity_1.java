package com.runtai.testproject.activity.supertextview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.runtai.supertextview.SuperTextView;
import com.runtai.testproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：高炎鹏
 * 时间：2016/10/26 14:53
 * 描述：组合1
 */
public class Super_Activity_1 extends Activity {

    private SuperTextView stv_1, stv_2, stv_3;
    private Button bt;
    private TextView tv_1_show, tv;
    private List<SuperTextView> list;
    private String SHOW = "stv:sLineShow = \"both\"//设置控件上下线<有四种显示方式 none、top、bottom、both(无、上、下、上下)>\n" +
            "stv:sRightCheckBoxShow=\"true\"//设置控件水波效果\n" +
            "stv:sRightCheckBoxShow=\"true\"//设置控件右边显示CheckBox控件\n" +
            "stv:sRightCheckBoxRes=\"@drawable/circular_check_bg\"//设置CheckBox控件check状态";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_1);
        initView();
    }

    public void initView() {
        stv_1 = (SuperTextView) findViewById(R.id.stv_1);
        stv_2 = (SuperTextView) findViewById(R.id.stv_2);
        stv_3 = (SuperTextView) findViewById(R.id.stv_3);
        bt = (Button) findViewById(R.id.bt);
        tv_1_show = (TextView) findViewById(R.id.tv_1_show);
        tv_1_show.setText(SHOW);
        tv = (TextView) findViewById(R.id.tv);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getCbisChecked()) {
                        Log.e("选择了第几项？", "===" + i);
                        tv.setText("选择了第" + i + "项");
                        return;
                    }
                }
            }
        });
        addList();
        setOnSuperTextViewClickListener();
    }

    public void addList() {
        list = new ArrayList<>();
        list.add(stv_1);
        list.add(stv_2);
        list.get(0).setCbChecked(true); //默认选择了第一项
    }

    public void setOnSuperTextViewClickListener() {
        for (final SuperTextView superTextView : list) {
            superTextView.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
                @Override
                public void onCheckClick(CheckBox rightCheckBox) {
                    super.onCheckClick(rightCheckBox);
                    setAllfalse();
                    superTextView.setCbChecked(true);
                }

                @Override
                public void onSuperTextViewClick() {
                    super.onSuperTextViewClick();
                    setAllfalse();
                    superTextView.setCbChecked(true);
                }

                @Override
                public void onLeftBottomClick2() {
                    super.onLeftBottomClick2();
                    Log.e("onLefdtBottomClick2", "onLefdtBottomClick2");
                    if (superTextView.getId() == stv_1.getId()) {
                        Log.e("stv_1", "onLefdtBottomClick2");
                    } else if (superTextView.getId() == stv_2.getId()) {
                        Log.e("stv_2", "onLefdtBottomClick2");
                    }
                }
            });
        }
    }

    public void setAllfalse() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCbChecked(false);
        }
    }

}
