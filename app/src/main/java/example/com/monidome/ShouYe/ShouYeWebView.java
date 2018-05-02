package example.com.monidome.ShouYe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import example.com.monidome.R;

public class ShouYeWebView extends AppCompatActivity {

    private WebView mShouyeWebview;
    private String webViewUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_ye_web_view);

        initView();
    }

    private void initView() {
        mShouyeWebview = (WebView) findViewById(R.id.shouye_webview);
        Intent intent = getIntent();
        webViewUrl = intent.getStringExtra("WebViewUrl");
        //WebView加载web资源
        mShouyeWebview.loadUrl(webViewUrl);
      /*  //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        mShouyeWebview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });*/
    }
}
