package com.runtai.testproject.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.runtai.countdown.CountDownView;
import com.runtai.countdown.RoundProgressBar;
import com.runtai.testproject.R;

/**
 * @作者：高炎鹏
 * @时间：2016/10/27 10:44
 * @描述：广告倒计时View界面
 */
public class CountdownActivity extends Activity {

    private RoundProgressBar mRoundProgressBar;
    private CountDownView count_down_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        initView();
    }

    private void initView() {
        mRoundProgressBar = (RoundProgressBar) findViewById(R.id.roundProgressBar);
        mRoundProgressBar
                .setCountDownTimerListener(new RoundProgressBar.CountDownTimerListener() {
                    @Override
                    public void onStartCount() {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onFinishCount() {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onStop() {
                        // TODO Auto-generated method stub

                    }
                });
        mRoundProgressBar.start();

        count_down_view = (CountDownView) findViewById(R.id.countDownView);
        count_down_view
                .setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
                    @Override
                    public void onStartCount() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "开始计时",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinishCount() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "计时结束",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onStop() {
                        // TODO Auto-generated method stub
                        Toast.makeText(getApplicationContext(), "计时停止",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        count_down_view.start();
    }
}
