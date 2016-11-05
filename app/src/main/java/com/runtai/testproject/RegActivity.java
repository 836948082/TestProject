package com.runtai.testproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.runtai.testproject.cehua.BaseActivity;

/**
 * 作者：高炎鹏
 * 时间：2016/10/19 10:13
 * 描述：注册页面
 *        如果填写错误应增加抖动
 */
public class RegActivity extends BaseActivity implements View.OnClickListener{

    private RelativeLayout zhuce_back;
    private EditText zc_sjhm, zc_dlmm, zc_qrmm;
    private Button refill, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.me_zhuce);
        initView();
    }

    private void initView() {
        zhuce_back = (RelativeLayout) findViewById(R.id.zhuce_back);
        zhuce_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.zhuce_back:
                finish();
                break;
        }
    }

}
