package com.runtai.testproject.activity.bowen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.runtai.testproject.R;

/**
 * @作者：高炎鹏
 * @日期：2017/1/5时间11:06
 * @描述：水波纹效果
 */
public class BoWenActivity extends Activity implements View.OnClickListener {

    private Button bt_wave, bt_xfermode, bt_waterwave;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bowen);
        initViews();
    }

    private void initViews() {
        bt_wave = (Button) findViewById(R.id.bt_wave);
        bt_wave.setOnClickListener(this);
        bt_xfermode = (Button) findViewById(R.id.bt_xfermode);
        bt_xfermode.setOnClickListener(this);
        bt_waterwave = (Button) findViewById(R.id.bt_waterwave);
        bt_waterwave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wave:
                intent = new Intent(this, WaveActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_xfermode:
                intent = new Intent(this, PorterDuffXfermodeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_waterwave://水波纹效果(动态调整)
                intent = new Intent(this, WaterWaveActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
