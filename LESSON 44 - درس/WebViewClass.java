package com.shiyarlive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WebViewClass extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        SharedPreferences dataSaver = this.getSharedPreferences("dataSaver", Context.MODE_PRIVATE);

        webView = (WebView) findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient(){

                @Override
                public void onPageFinished(WebView view, String url) {
                    TextView link = (TextView) findViewById(R.id.link);
                    link.setText(webView.getUrl());

                    // نقوم بإنشاء ملف بالاسم الذي نحدده في حال كان يوجد سابقا ملف بنفس اسم فسيتم تحديد ذلك الملف بدل انشاء ملف جديد بذلك اسم

                    SharedPreferences.Editor editor = dataSaver.edit();  // هنا نطلب تحرير الملف المحدد
                    editor.putString("link", webView.getUrl());	// هنا نقوم بتخزين ببعض بيانات
                    editor.apply();	// هنا نطلب حفظ التغيرات


                    super.onPageFinished(view, url);
            }

        });

        // loadUrl
        // للحصول على البيانات بمخزنة في ذاكرة تخزين التطبيق - بنفس طريقة نحدد الملف
        SharedPreferences prefs = this.getSharedPreferences("dataSaver", Context.MODE_PRIVATE);
        String link = prefs.getString("link", null); // هنا نحدد بيانات اي متغير نرغب بحصول عليها من ذاكرة تخزين المؤاقتة

        if(link!= null){
            webView.loadUrl(link);
        }else{
            webView.loadUrl("https://academy.codershiyar.com");
        }

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

        webView.setWebChromeClient(new MyChrome());

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                ActivityCompat.requestPermissions(WebViewClass.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE  }, 1);

// طلب حصول على ملف من رابط لتحميلها عند الطلب
                DownloadManager.Request myRequest = new DownloadManager.Request(Uri.parse(url));
// طلب فحص الملف
                myRequest.allowScanningByMediaScanner();
// طلب تخزين نوع الملف الذي سيتم تحميلها في المتغير من نوع البيانات النصي
                String fileExtension = MimeTypeMap.getFileExtensionFromUrl(url);
//طلب تحديد المسار للحفظ الملف  مع اسم وصيغة الملف
                myRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,("FILE."+fileExtension));
// طلب عرض اشعار للمستخدم يفيد ان تحميل الملف اكتملت
                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
// طلب من إدارة تحميلات في الهاتف بتنزيل الملف وحفظه
                DownloadManager myFile = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
// هنا يتم تحديد اي ملف يجب يتنزيلها
                myFile.enqueue(myRequest);
// هنا مجرد نص ارغب بان يظهر على شاشة المستخدم يفيد ان الملف بدء تحميلها
                Toast.makeText(WebViewClass.this, "جاري تحميل الملف ....", Toast.LENGTH_LONG).show();
            }
        });
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
    public void copyLink(View view){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy",webView.getUrl());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "تم نسخ " +  webView.getUrl() + "", Toast.LENGTH_LONG).show();
    }

    public void share(View view){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TITLE,"Shiyar Live - مشاركة تطبيق");
    //  sendIntent.putExtra(Intent.EXTRA_SUBJECT,"موضوع");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://shiyarjemo.com/app/");
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    // كلاس لسماح وضع ملئ الشاشة
    private class MyChrome extends WebChromeClient {

        private View mCustomView;
        private CustomViewCallback mCustomViewCallback;
        protected FrameLayout mFullscreenContainer;
        private int mOriginalOrientation;
        private int mOriginalSystemUiVisibility;

        MyChrome() {}

        public Bitmap getDefaultVideoPoster()
        {
            if (mCustomView == null) {
                return null;
            }
            return BitmapFactory.decodeResource(getApplicationContext().getResources(), 2130837573);
        }

        public void onHideCustomView()
        {
            ((FrameLayout)getWindow().getDecorView()).removeView(this.mCustomView);
            this.mCustomView = null;
            getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
            setRequestedOrientation(this.mOriginalOrientation);
            this.mCustomViewCallback.onCustomViewHidden();
            this.mCustomViewCallback = null;
        }

        public void onShowCustomView(View paramView, CustomViewCallback paramCustomViewCallback)
        {
            if (this.mCustomView != null)
            {
                onHideCustomView();
                return;
            }
            this.mCustomView = paramView;
            this.mOriginalSystemUiVisibility = getWindow().getDecorView().getSystemUiVisibility();
            this.mOriginalOrientation = getRequestedOrientation();
            this.mCustomViewCallback = paramCustomViewCallback;
            ((FrameLayout)getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1));
            getWindow().getDecorView().setSystemUiVisibility(3846);
        }
    }


}