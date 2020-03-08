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
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        // loadUrl
        webView.loadUrl("https://academy.codershiyar.com");
        webView.setWebViewClient(new WebViewClient());

        text = (TextView) findViewById(R.id.text);
        text.setText(webView.getUrl());

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()  == MotionEvent.ACTION_UP){
                    text.setText(webView.getUrl());
                }
               return false;
            }
        });


        // reload()
        // goForward()
        // loadUrl()
        // getTitle()
        // getUrl()

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

    public  void reload(View view){
        webView.reload();
    }

    public  void back(View view){
        if(webView.canGoBack()== true){
            webView.goBack();
        }
    }
    public void forward(View view){
        if(webView.canGoForward() == true){
            webView.goForward();
        }
    }
}
