package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
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

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE  }, 1);

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl("http://academy.codershiyar.com");

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
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

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
                Toast.makeText(MainActivity.this, "جاري تحميل الملف ....", Toast.LENGTH_LONG).show();
            }
        });

    }
}