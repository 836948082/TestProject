package com.runtai.testproject.activity.baidupullanim;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;

import com.runtai.testproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @作者：高炎鹏
 * @日期：2017/1/6时间14:50
 * @描述：
 */
public class BaiduPullActivity extends Activity implements BaiDuRefreshListView.OnBaiduRefreshListener {

    private BaiDuRefreshListView mListView;
    private List<String> mDatas;
    private ArrayAdapter<String> mAdapter;

    private final static int REFRESH_COMPLETE = 0;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mListView.setOnRefreshComplete();
                    mAdapter.notifyDataSetChanged();
                    mListView.setSelection(0);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidupull);

        mListView = (BaiDuRefreshListView) findViewById(R.id.lv);
        String[] data = new String[]{"a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"};
        mDatas = new ArrayList<>(Arrays.asList(data));
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDatas);
        mListView.setAdapter(mAdapter);
        mListView.setOnBaiduRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    mDatas.add(0, "new data");
                    mHandler.sendEmptyMessage(REFRESH_COMPLETE);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
