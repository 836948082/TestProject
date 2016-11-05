package com.runtai.testproject.activity.animationbutton;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;
import com.runtai.testproject.utils.httputil.HttpUtils;

import java.io.IOException;

/**
 * @作者：高炎鹏
 * @日期：2016/10/29时间17:23
 * @描述：一个平滑酷炫的按钮动画
 */
public class AnimationButtonActivity extends BaseActivity {
    AnimationButtonView anim_button;
    boolean istrue = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationbutton);
        anim_button = (AnimationButtonView) findViewById(R.id.anim_button);
        anim_button.setTitle("发送");
        anim_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (istrue) {
                    anim_button.startAnimation();
                    httpThread();
                } else {
                    anim_button.reset();
                }
                istrue = !istrue;
            }
        });
    }

    public void httpThread() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                http();
            }
        }.start();
    }

    public void http() {
        String url = "http://api-b2b.yubianli.com/Fa8E0Bdca7/Be3E66E556";
        try {
            result = HttpUtils.postRequst(url);
            handler.sendEmptyMessage(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String result;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(AnimationButtonActivity.this, result, Toast.LENGTH_SHORT).show();
        }
    };
}
