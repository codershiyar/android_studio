package com.example.academyshiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        // loadUrl
        webView.loadUrl("https://academy.codershiyar.com");


        // loadUrl()
        // goBack()

        // reload()
        // goForward()


        WebSettings webSettings = webView.getSettings();
        // سماح للجافا  سكربت بالعمل
        webSettings.setJavaScriptEnabled(true);
        // لسماح لتحزين
        webSettings.setDomStorageEnabled(true);
        // لتمكين اعدادت viewPort
        webSettings.setUseWideViewPort(true);

        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);

        webSettings.setDisplayZoomControls(true);
        webSettings.setSupportZoom(true);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
        webView.goBack();
        return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void goFoward(View view){
        webView.goForward();
    }
    public void reload(View view){
        webView.reload();
    }
    public void goBack(View view){
        webView.goBack();
    }
    public void loadHome(View view){
        webView.loadUrl("https://academy.codershiyar.com");
    }
    public  void loadUrl(View view){
        webView.loadUrl("https://academy.codershiyar.com/about.php");
    }
}
