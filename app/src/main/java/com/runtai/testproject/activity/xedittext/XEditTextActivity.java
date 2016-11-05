package com.runtai.testproject.activity.xedittext;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.xedittext.XEditText;

/**
 * @作者：高炎鹏
 * @日期：2016/11/2时间09:56
 * @描述：多功能EditText控件(携带清除按钮)
 */
public class XEditTextActivity extends BaseActivity{

    private final static String Explain = "①EditText输入电话号码、银行卡号自动添加空格分割\n" +
            "②自定义分割符号、分割模式\n" +
            "③右侧默认添加清除功能图标，可自定义图标并设置点击监听，配合PopupWindow等进行输入提示\n" +
            "④仿iOS输入风格\n" +
            "主要方法如下：\n" +
            "x_separator，分隔符，默认是一个空格\n" +
            "x_customizeMarkerEnable ，是否自定义Marker标记\n" +
            "x_showMarkerTime，显示Marker标记的时间：after_input(default), before_input, always\n" +
            "x_iOSStyleEnable ，是否使用iOS风格";
    TextView text_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xedittext);
        text_text = (TextView) findViewById(R.id.text_text);
        text_text.setText(Explain);

        initView();
    }

    private XEditText defaultXEdit;
    private XEditText clearXEdit;
    private XEditText customXEdit;
    private XEditText showXEdit;
    private XEditText customMarkerEdit1;
    private TextView textView1, textView2;

    private void initView() {
        defaultXEdit = (XEditText) findViewById(R.id.default_edit_text);
        clearXEdit = (XEditText) findViewById(R.id.clear_marker_edit_text);
        customXEdit = (XEditText) findViewById(R.id.custom_edit_text);
        showXEdit = (XEditText) findViewById(R.id.show_separator_edit_text);
        customMarkerEdit1 = (XEditText) findViewById(R.id.custom_marker_edit_text1);
        textView1 = (TextView) findViewById(R.id.text1);
        textView2 = (TextView) findViewById(R.id.text2);
        Button button = (Button) findViewById(R.id.show_pattern_btn);

        defaultXEdit.setRightMarkerDrawable(null); // hide clear icon
        defaultXEdit.setMaxLength(20); //set max length of contents
        customXEdit.setPattern(new int[]{4, 4, 4, 4});

        clearXEdit.setOnXTextChangeListener(new XEditText.OnXTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView1.setText(clearXEdit.getNonSeparatorText());
            }
        });
        customXEdit.setOnXTextChangeListener(new XEditText.OnXTextChangeListener() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                textView2.setText(customXEdit.getNonSeparatorText());
            }
        });

        showXEdit.setSeparator(" ");
        showXEdit.setPattern(new int[]{3, 4, 4});
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showXEdit.setTextToSeparate("13800000000");
                }
            });
        }

        customMarkerEdit1.setCustomizeMarkerEnable(true);
        customMarkerEdit1.setOnMarkerClickListener(new XEditText.OnMarkerClickListener() {
            @Override
            public void onMarkerClick(float x, float y) {
                new MarkerPopupWindow(XEditTextActivity.this, customMarkerEdit1, (int) x, (int) y);
            }
        });
    }
}
