package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    WebView webView;
    Button setTitle;
    EditText setUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle = (Button) findViewById(R.id.title);
        setUrl = (EditText) findViewById(R.id.url);

        webView = (WebView) findViewById(R.id.webView);
        // loadUrl
        webView.loadUrl("https://academy.codershiyar.com/login.php");
        webView.setWebViewClient(new WebViewClient()
        {

            @Override
            public void onPageFinished(WebView view, String url) {
              //  String url_website = webView.getUrl();
              //  String title = webView.getTitle();

                setTitle.setText(webView.getTitle());
                setUrl.setText(webView.getUrl());

                if (webView.getUrl().equals("https://academy.codershiyar.com/validity-certificate/index.php")){
                    Toast toast = Toast.makeText(getApplicationContext(),"تم تحميل الصفحة Validity Certificate",Toast.LENGTH_LONG);
                    toast.show();
                }
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
