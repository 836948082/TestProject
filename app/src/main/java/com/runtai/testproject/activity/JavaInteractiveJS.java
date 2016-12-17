package com.runtai.testproject.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.runtai.testproject.R;
import com.runtai.testproject.cehua.BaseActivity;

/**
 * @作者：高炎鹏
 * @日期：2016/12/14时间13:49
 * @描述：
 */
public class JavaInteractiveJS extends BaseActivity implements View.OnClickListener {

    private WebView webview;
    private TextView show;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_javainteractivejs);
        initView();
        init();
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void init() {
        // 启用javascript
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("file:///android_asset/wst.html");
        webview.addJavascriptInterface(this, "wst");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("url:", url);
                view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
                return true;//返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
            }
        });
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
        show = (TextView) findViewById(R.id.show);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                // 无参数调用
                webview.loadUrl("javascript:javacalljs()");
                // 传递参数调用
                webview.loadUrl("javascript:javacalljswithargs(" + "'hello world'" + ")");
                break;
            default:
                break;
        }
    }

    /**
     * js调用java控件(无参)(方法上需要加上@JavascriptInterface)
     */
    @JavascriptInterface
    public void startFunction() {
        Toast.makeText(this, "js调用了java函数", Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show.setText(show.getText() + "\njs调用了java函数");
            }
        });
    }

    /**
     * js调用java控件(有参)(方法上需要加上@JavascriptInterface)
     */
    @JavascriptInterface
    public void startFunction(final String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                show.setText(show.getText() + "\njs调用了java函数传递参数：" + str);
            }
        });
    }

    /**
     * 如果希望浏览的网页后退而不是退出浏览器，需要WebView覆盖URL加载，让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();// 返回上一页面
                return true;
            } else {
                this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
