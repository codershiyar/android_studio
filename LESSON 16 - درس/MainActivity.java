package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.web);
        webView.loadUrl("file:///android_asset/index.html");

        webView.setWebViewClient(new WebViewClient());


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

}
