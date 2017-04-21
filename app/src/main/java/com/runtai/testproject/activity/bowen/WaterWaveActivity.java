package com.runtai.testproject.activity.bowen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.runtai.testproject.R;
import com.runtai.testproject.activity.bowen.ui.WaterWaveView;

public class WaterWaveActivity extends Activity {

    private WaterWaveView waterWaveView;
    private TextView tv_show;
    private SeekBar seek_bar1, seek_bar2, seek_bar3, seek_bar4;
    private int value1, value2, value3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waterwave);
        initViews();
    }

    private void initViews() {
        waterWaveView = (WaterWaveView) findViewById(R.id.wwv);
        tv_show = (TextView) findViewById(R.id.tv_show);
        tv_show.setText(String.format("移动速度:%s,波峰高度:%s,omega(波长):%s", value3, value2, value1));

        seek_bar1 = (SeekBar) findViewById(R.id.seek_bar1);
        seek_bar2 = (SeekBar) findViewById(R.id.seek_bar2);
        seek_bar3 = (SeekBar) findViewById(R.id.seek_bar3);
        seek_bar4 = (SeekBar) findViewById(R.id.seek_bar4);
        seek_bar1.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seek_bar2.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seek_bar3.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seek_bar4.setOnSeekBarChangeListener(onSeekBarChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            switch (seekBar.getId()) {
                case R.id.seek_bar1:
                    value1 = progress;
                    waterWaveView.setOmegaByProgress(progress);
                    break;
                case R.id.seek_bar2:
                    value2 = progress;
                    waterWaveView.setWaveHeightByProgress(progress);
                    break;
                case R.id.seek_bar3:
                    value3 = progress;
                    waterWaveView.setMoveSpeedByProgress(progress);
                    break;
                case R.id.seek_bar4:
                    waterWaveView.setHeightOffsetByProgress(progress);
                    break;
                default:
                    break;
            }
            tv_show.setText(String.format("移动速度:%s,波峰高度:%s,omega(波长):%s", value3, value2, value1));
        }
    };

}
