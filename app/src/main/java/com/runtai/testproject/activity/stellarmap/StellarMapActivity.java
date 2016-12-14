package com.runtai.testproject.activity.stellarmap;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @作者：高炎鹏
 * @日期：2016/11/22时间09:59
 * @描述：关键词飞入飞出效果
 * @准备：复制4个类 1:布局+获取StellarMap对象 2:设置adapter 3:设置StellarMap参数
 */
public class StellarMapActivity extends BaseActivity{

    private Context context;
    private StellarMap stleeMap;
    private int padding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stellarmap);
        context = this;
        padding = (int) getResources().getDimension(R.dimen.stellar_map_padding);
        initView();
        displayBriefMemory();
    }

    private void displayBriefMemory() {
        final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.w("tag", "当前设备总内存:" + (info.totalMem >> 10) + "k");
        Log.w("tag", "系统剩余内存:" + (info.availMem >> 10) + "k");
        Log.w("tag", "系统是否处于低内存运行：" + info.lowMemory);
        Log.w("tag", "当系统剩余内存低于" + (info.threshold >> 10) + "k" + "时就看成低内存运行,就会有可能触发LMK，系统开始杀进程了");
    }

    private void initView() {
        // 1获取StellarMap对象
        stleeMap = (StellarMap) findViewById(R.id.stleeMap);
        // 2 设置adapter
        MyAdapter adapter = new MyAdapter(getData());
        stleeMap.setAdapter(adapter);
        // 若想第一页就显示数据，那么方法setGroup(0, true);必须放在setAdapter(adapter)后面，其他方法顺序无所谓;
        // 3 设置StellarMap参数
        initStellarMap();
    }

    /**
     * 设置StellarMap参数
     */
    public void initStellarMap() {
        stleeMap.setGroup(0, true);
        stleeMap.setInnerPadding(padding, padding, padding, padding);// 设置textView的内边距
        stleeMap.setRegularity(15, 15);
    }

    /**
     * 模拟数据
     * @return List<List<String>>
     */
    public List<List<String>> getData() {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<String> item = new ArrayList<>();
            for (int j = 0; j < 20; j++) {
                item.add(i + "组" + j + "项");
            }
            list.add(item);
        }
        return list;
    }

    class MyAdapter implements StellarMap.Adapter {// 注意：Adapter是stellMap包下的，是interface

        public List<List<String>> list;

        public MyAdapter(List<List<String>> list) {
            this.list = list;
        }

        // 共多少组数据
        @Override
        public int getGroupCount() {
            Log.e("", "" + list.size());
            return list.size();
        }

        // 每一组有多少条数据
        @Override
        public int getCount(int group) {
            return list.get(group).size();
        }

        @Override
        public View getView(int group, int position, View convertView) {
            final TextView tv = new TextView(context);
            tv.setText(list.get(group).get(position));
            // 1 设置字体大小
            Random random = new Random();
            int textSize = random.nextInt(8) + 15;
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

            // 2 设置字体颜色
            int red = random.nextInt(150) + 50;
            int green = random.nextInt(150) + 50;
            int blue = random.nextInt(150) + 50;
            int textColor = Color.rgb(red, green, blue);
            tv.setTextColor(textColor);
            tv.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    // TODO Auto-generated method stub
                    String str = tv.getText().toString();
                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                }
            });
            return tv;
        }

        // 没什么作用
        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;
        }

        // 下一个页面要显示的数据
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return (group + 1) % getGroupCount();// 确保循环显示
        }
    }
}
