package com.runtai.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import com.runtai.logger.Logger;
import com.runtai.okhttputils.OkHttpUtils;
import com.runtai.okhttputils.callback.StringCallback;
import com.runtai.testproject.activity.CountdownActivity;
import com.runtai.testproject.activity.DatePickerActivity;
import com.runtai.testproject.activity.FlowLayoutActivity;
import com.runtai.testproject.activity.JavaInteractiveJS;
import com.runtai.testproject.activity.NiftyDialogEffectsActivity;
import com.runtai.testproject.activity.ScrollShowTitleActivity;
import com.runtai.testproject.activity.ToggleButtonActivity;
import com.runtai.testproject.activity.animationbutton.AnimationButtonActivity;
import com.runtai.testproject.activity.checkfourmark.CheckFourMark;
import com.runtai.testproject.activity.clickdeflect.ClickDeflectActivity;
import com.runtai.testproject.activity.connectlistview.ConnectListViewActivity;
import com.runtai.testproject.activity.expandablelistview.ExpandableListViewActivity;
import com.runtai.testproject.activity.expandpoptabview.ExpandPopTabViewActivity;
import com.runtai.testproject.activity.labeleffect.LabelEffectActivity;
import com.runtai.testproject.activity.listview_gridview_change.BetweenListViewGridViewActivity;
import com.runtai.testproject.activity.listview_item_effect.ListView_itemActivity;
import com.runtai.testproject.activity.pinnedheaderlistview.PinnedHeaderListViewActivity;
import com.runtai.testproject.activity.pulltorefresh.PullToRefreshActivity;
import com.runtai.testproject.activity.stellarmap.StellarMapActivity;
import com.runtai.testproject.activity.supertextview.SuperTextViewActivity;
import com.runtai.testproject.activity.xedittext.XEditTextActivity;
import com.runtai.testproject.activity.xradiobutton.XRadioButtonActivity;
import com.runtai.testproject.utils.OpenOtherPackageActivityUtil;
import com.runtai.testproject.utils.httputil.HttpUtils;
import com.runtai.testproject.view.AutoScrollTextView;
import com.runtai.testproject.view.RunningTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * 作者：高炎鹏
 * 时间：2016/09/22 08:19
 * 描述：主界面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private AutoScrollTextView notice;
    private RunningTextView runningtextview;
    private Intent intent;
    private Button network,login, contacts, supertext, countdown, checkfourmark, datepicker, list_open_close, flowlayout,
            dialog_anim, list_item_anim, list_grid_anim, list_connect, scroll_isshow, animationbutton, xEditText,
            XRadioButton, two_list_linkage, label_effect, expandPopTabView, toggleButton, pulltorefresh, stellarMap,
            clickdeflect, switchbutton, javainteractivejs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

        runningtextview = (RunningTextView) findViewById(R.id.runningtextview);
        runningtextview.setFormat("#0.00");
        runningtextview.playNumber(Double.parseDouble("12345.67"));

        notice = (AutoScrollTextView) findViewById(R.id.notice);
        getWH();

        network = (Button) findViewById(R.id.network);
        network.setOnClickListener(this);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        contacts = (Button) findViewById(R.id.contacts);
        contacts.setOnClickListener(this);
        supertext = (Button) findViewById(R.id.supertext);
        supertext.setOnClickListener(this);
        countdown = (Button) findViewById(R.id.countdown);
        countdown.setOnClickListener(this);
        checkfourmark = (Button) findViewById(R.id.checkfourmark);
        checkfourmark.setOnClickListener(this);
        datepicker = (Button) findViewById(R.id.datepicker);
        datepicker.setOnClickListener(this);
        list_open_close = (Button) findViewById(R.id.list_open_close);
        list_open_close.setOnClickListener(this);
        flowlayout = (Button) findViewById(R.id.flowlayout);
        flowlayout.setOnClickListener(this);
        dialog_anim = (Button) findViewById(R.id.dialog_anim);
        dialog_anim.setOnClickListener(this);
        list_item_anim = (Button) findViewById(R.id.list_item_anim);
        list_item_anim.setOnClickListener(this);
        list_grid_anim = (Button) findViewById(R.id.list_grid_anim);
        list_grid_anim.setOnClickListener(this);
        list_connect = (Button) findViewById(R.id.list_connect);
        list_connect.setOnClickListener(this);
        scroll_isshow = (Button) findViewById(R.id.scroll_isshow);
        scroll_isshow.setOnClickListener(this);
        animationbutton = (Button) findViewById(R.id.animationbutton);
        animationbutton.setOnClickListener(this);
        xEditText = (Button) findViewById(R.id.xEditText);
        xEditText.setOnClickListener(this);
        XRadioButton = (Button) findViewById(R.id.XRadioButton);
        XRadioButton.setOnClickListener(this);
        two_list_linkage = (Button) findViewById(R.id.two_list_linkage);
        two_list_linkage.setOnClickListener(this);
        label_effect = (Button) findViewById(R.id.label_effect);
        label_effect.setOnClickListener(this);
        expandPopTabView = (Button) findViewById(R.id.expandPopTabView);
        expandPopTabView.setOnClickListener(this);
        toggleButton = (Button) findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(this);
        pulltorefresh = (Button) findViewById(R.id.pulltorefresh);
        pulltorefresh.setOnClickListener(this);
        stellarMap = (Button) findViewById(R.id.stellarMap);
        stellarMap.setOnClickListener(this);
        clickdeflect = (Button) findViewById(R.id.clickdeflect);
        clickdeflect.setOnClickListener(this);
        switchbutton = (Button) findViewById(R.id.switchbutton);
        switchbutton.setOnClickListener(this);
        javainteractivejs = (Button) findViewById(R.id.javainteractivejs);
        javainteractivejs.setOnClickListener(this);
    }

    /**
     * 获取自定义控件的宽度
     */
    public void getWH() {
        ViewTreeObserver vto = notice.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                start(notice.getWidth());
                notice.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    /**
     * 开始执行TextView文字滚动动画
     */
    public void start(int Width) {
        notice.setDate("!!!123456789abcdefghijklmnopqrstuvwxyz123456789!!!");
        notice.init(Width, getWindowManager());
        notice.startScroll();
    }

    /**
     * 网络请求
     */
    public void goods_id() {
        // TODO Auto-generated method stub
        String URL = "http://api.zzhenghua.cn/sm/love?id=12&sign=123";
        OkHttpUtils.get().url(URL).build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(okhttp3.Call call, Exception e) {
                        Logger.e(""+call);
                        Toast.makeText(MainActivity.this,"call"+call,Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            Logger.e(json.toString());
                            Toast.makeText(MainActivity.this,"response"+response,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                runningtextview.playNumber(Double.parseDouble("12345.67"));
                break;
            case R.id.network://网络请求
                //使用OKHttp网络请求
                goods_id();
                //使用原生网络请求(需要开启线程)
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            String jieguo = HttpUtils.getRequest("http://api.zzhenghua.cn/sm/love?id=12&sign=123");
                            Logger.e(jieguo);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            case R.id.login://登录、注册界面
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.contacts://获取联系人界面
                intent = new Intent(this, ContactsActivity.class);
                startActivity(intent);
                break;
            case R.id.supertext://展示功能强大的SuperTextView界面
                intent = new Intent(this, SuperTextViewActivity.class);
                startActivity(intent);
                break;
            case R.id.countdown://展示广告倒计时View界面
                intent = new Intent(this, CountdownActivity.class);
                startActivity(intent);
                break;
            case R.id.checkfourmark://展示验证、生成验证码界面
                intent = new Intent(this, CheckFourMark.class);
                startActivity(intent);
                break;
            case R.id.datepicker://日期、时间选择器界面
                intent = new Intent(this, DatePickerActivity.class);
                startActivity(intent);
                break;
            case R.id.list_open_close://列表展开、关闭界面
                intent = new Intent(this, ExpandableListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.flowlayout://展示热门标签流式布局界面
                intent = new Intent(this, FlowLayoutActivity.class);
                startActivity(intent);
                break;
            case R.id.dialog_anim://展示多种Dialog动画效果界面
                intent = new Intent(this, NiftyDialogEffectsActivity.class);
                startActivity(intent);
                break;
            case R.id.list_item_anim://ListView item飞入动画效果(携带ListView滑动方向监控)
                intent = new Intent(this, ListView_itemActivity.class);
                startActivity(intent);
                break;
            case R.id.list_grid_anim://ListView GridView相互切换动画效果
                intent = new Intent(this, BetweenListViewGridViewActivity.class);
                startActivity(intent);
                break;
            case R.id.list_connect://几个ListView一起联动
                intent = new Intent(this, ConnectListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.scroll_isshow://ListView滑动时显示、隐藏标题
                intent = new Intent(this, ScrollShowTitleActivity.class);
                startActivity(intent);
                break;
            case R.id.animationbutton://一个平滑酷炫的按钮动画
                intent = new Intent(this, AnimationButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.xEditText://多功能EditText控件(携带清除按钮)
                intent = new Intent(this, XEditTextActivity.class);
                startActivity(intent);
                break;
            case R.id.XRadioButton://自定义单选项(可用于支付)
                intent = new Intent(this, XRadioButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.two_list_linkage://两个ListView左右联动
                intent = new Intent(this, PinnedHeaderListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.label_effect://贴纸效果
                intent = new Intent(this, LabelEffectActivity.class);
                startActivity(intent);
                break;
            case R.id.expandPopTabView://分类筛选
                intent = new Intent(this, ExpandPopTabViewActivity.class);
                startActivity(intent);
                break;
            case R.id.toggleButton://状态开关按钮
                intent = new Intent(this, ToggleButtonActivity.class);
                startActivity(intent);
                break;
            case R.id.pulltorefresh://多控件刷新、加载
                intent = new Intent(this, PullToRefreshActivity.class);
                startActivity(intent);
                break;
            case R.id.stellarMap://关键词飞入飞出效果
                intent = new Intent(this, StellarMapActivity.class);
                startActivity(intent);
                break;
            case R.id.clickdeflect://图片触摸点击偏转(偏转效果)
                intent = new Intent(this, ClickDeflectActivity.class);
                startActivity(intent);
                break;
            case R.id.switchbutton://SwitchButton
//                OpenOtherPackageActivityUtil.openApp(MainActivity.this, "com.runtai.switchbutton");
                OpenOtherPackageActivityUtil.openApp(MainActivity.this, "com.runtai.switchbutton", "com.runtai.switchbutton.StyleActivity");
                break;
            case R.id.javainteractivejs://java、js交互
                intent = new Intent(this, JavaInteractiveJS.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}