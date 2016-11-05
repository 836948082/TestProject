package com.runtai.testproject.activity.xradiobutton;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.xradiobutton.PayRadioGroup;
import com.runtai.xradiobutton.PayRadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2016/11/2时间10:52
 * @描述：自定义单选项(可用于支付)
 */
public class XRadioButtonActivity extends BaseActivity {

    private Button btn;
    PayRadioButton a;
    PayRadioButton b;
    PayRadioButton c;
    PayRadioButton d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xradiobutton);
        initView();
    }

    List<PayRadioButton> list;
    String textTiTle;

    private void initView() {
        PayRadioGroup group = (PayRadioGroup) findViewById(R.id.genderGroup);
        a = (PayRadioButton) findViewById(R.id.p1);
        b = (PayRadioButton) findViewById(R.id.p2);
        c = (PayRadioButton) findViewById(R.id.p3);
        d = (PayRadioButton) findViewById(R.id.p4);

        setDefault(a);

        list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);

        group.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {

                int radioButtonId = group.getCheckedRadioButtonId();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == radioButtonId) {
                        textTiTle = list.get(i).getTextTitle();
                    }
                }

                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioButton) group.getChildAt(i)).setChangeImg(checkedId);
                }
            }
        });

        btn = (Button) findViewById(R.id.btn_pay);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(XRadioButtonActivity.this, textTiTle, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 设置默认选项
     * @param radio
     */
    public void setDefault(PayRadioButton radio) {
        radio.setChangeImg(radio.getId());
        textTiTle = radio.getTextTitle();
    }
}
