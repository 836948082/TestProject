package com.runtai.testproject.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.togglebutton.ToggleButton;

/**
 * @作者：高炎鹏
 * @日期：2016/11/5时间14:52
 * @描述：状态开关按钮
 * @导入方式：使用<com.runtai.togglebutton>Module 或 compile 'com.zcw:togglebutton-library:1.0.0'
 * @使用：1.获取该按钮的状态值 togglebt.isToggleOn();
 * 2.设置该按钮的状态值 <开>togglebt.setToggleOn();<关>togglebt.setToggleOff();
 * 3.布局文件设置 toggle:tbAsDefaultOn="true"
 */
public class ToggleButtonActivity extends BaseActivity {

    ToggleButton togglebt, togglebt_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togglebutton);
        togglebt = (ToggleButton) findViewById(R.id.togglebt);
        togglebt_1 = (ToggleButton) findViewById(R.id.togglebt_1);

        togglebt_1.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Toast.makeText(ToggleButtonActivity.this, "" + on, Toast.LENGTH_SHORT).show();
            }
        });
        togglebt.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                Toast.makeText(ToggleButtonActivity.this, "" + on, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
