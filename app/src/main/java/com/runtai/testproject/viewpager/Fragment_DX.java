package com.runtai.testproject.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.runtai.testproject.R;

public class Fragment_DX extends Fragment implements View.OnClickListener {

    private Activity activity;
    private View view;
    private TextView dxyz;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        view = inflater.inflate(R.layout.login_duanxin, null);
        dxyz = (TextView) view.findViewById(R.id.dxyz);
        dxyz.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dxyz:
                dxyz.setClickable(false); //设置不可点击
//                dxyz.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的
                dxyz.setText("(" + countSeconds + ")秒后可重新获取");
                handler.sendEmptyMessageDelayed(1, 1000);
                break;
            default:
                break;
        }
    }

    private int countSeconds = 10;//倒计时秒数
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (countSeconds > 0) {
                    --countSeconds;
                    dxyz.setText("(" + countSeconds + ")秒后可重新获取");
                    handler.sendEmptyMessageDelayed(1, 1000);
                } else {
                    countSeconds = 10;
                    dxyz.setText("重新获取验证码");
                    dxyz.setClickable(true);
                    //dxyz.setBackgroundResource(R.drawable.bg_identify_code_press);
                }
            }
        }
    };
}
