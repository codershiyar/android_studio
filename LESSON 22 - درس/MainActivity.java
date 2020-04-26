package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        // loadUrl
        webView.loadUrl("https://academy.codershiyar.com/login.php");
        webView.setWebViewClient(new WebViewClient(){
            //  يتم استدعاء هذا ميتود في كل مرة يبدا تحميل  WebView (عارض الويب)
            //  ويتم تنفيذ اوامر الذي يتواجد ضمن  method الذي تحددها بنفسك

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Toast toast = Toast.makeText(getApplicationContext(),"بدء تحميل الصفحة",Toast.LENGTH_SHORT);
                toast.show();
                super.onPageStarted(view, url, favicon);
            }
            //  يتم استدعاء هذا ميتود في كل مرة يكتمل تحميل WebView (عارض الويب)
            //   ويتم تنفيذ اوامر الذي يتواجد ضمن  method الذي تحددها بنفسك
            @Override
            public void onPageFinished(WebView view, String url) {
                Toast toast = Toast.makeText(getApplicationContext(),"أكتملت تحميل الصفحة",Toast.LENGTH_SHORT);
                toast.show();
                super.onPageFinished(view, url);
            }
        });


        WebSettings webSettings = webView.getSettings();
        // سماح للجافا  سكربت بالعمل
        webSettings.setJavaScriptEnabled(true);
        // لسماح لتحزين
        webSettings.setDomStorageEnabled(true);
        // لتمكين اعدادت viewPort
        webSettings.setUseWideViewPort(true);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
