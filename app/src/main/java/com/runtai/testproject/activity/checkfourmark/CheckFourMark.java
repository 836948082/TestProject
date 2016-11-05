package com.runtai.testproject.activity.checkfourmark;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @时间：2016/10/27 16:23
 * @描述：生成炫酷验证码
 *      equals             比较两个字符串的值是否相等(区分大小写)
 *      equalsIgnoreCase   比较两个字符串的值时，它会认为A-Z和a-z是一样的(忽略大小写)
 */
public class CheckFourMark extends BaseActivity implements OnClickListener {

    private ImageView vc_image;
    private Button refresh, confirm;
    private String getCode;
    private EditText vc_code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourmark);
        initView();
    }

    public void initView() {
        vc_image = (ImageView) findViewById(R.id.vc_image);
        vc_image.setImageBitmap(CodeUtils.getInstance().getBitmap());
        vc_code = (EditText) findViewById(R.id.vc_code);
        getCode = CodeUtils.getInstance().getCode(); // 获取显示的验证码
        refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(this);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        switch (view.getId()) {
            case R.id.refresh:
                vc_image.setImageBitmap(CodeUtils.getInstance().getBitmap());
                getCode = CodeUtils.getInstance().getCode();
                break;
            case R.id.confirm:
                String v_code = vc_code.getText().toString().trim();
                if ("".equals(v_code)) {
                    Toast.makeText(CheckFourMark.this, "验证码为空", Toast.LENGTH_SHORT).show();
                } else if (!v_code.equalsIgnoreCase(getCode)) {
                    Toast.makeText(CheckFourMark.this, "验证码错误", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CheckFourMark.this, "验证成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}

