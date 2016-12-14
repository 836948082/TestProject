package com.runtai.testproject.activity.pulltorefresh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2016/11/21时间12:13
 * @描述：多控件刷新、加载
 */
public class PullToRefreshActivity extends BaseActivity implements View.OnClickListener {

    private Intent intent;
    private Button pulltorefresh_listview, pulltorefresh_gridview, pulltorefresh_scrollview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        pulltorefresh_listview = (Button) findViewById(R.id.pulltorefresh_listview);
        pulltorefresh_listview.setOnClickListener(this);
        pulltorefresh_gridview = (Button) findViewById(R.id.pulltorefresh_gridview);
        pulltorefresh_gridview.setOnClickListener(this);
        pulltorefresh_scrollview = (Button) findViewById(R.id.pulltorefresh_scrollview);
        pulltorefresh_scrollview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pulltorefresh_listview:
                intent = new Intent(this, PullToRefreshActivity_ListView.class);
                startActivity(intent);
                break;
            case R.id.pulltorefresh_gridview:
                intent = new Intent(this, PullToRefreshActivity_GridView.class);
                startActivity(intent);
                break;
            case R.id.pulltorefresh_scrollview:
                intent = new Intent(this, PullToRefreshActivity_ScrollView.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
