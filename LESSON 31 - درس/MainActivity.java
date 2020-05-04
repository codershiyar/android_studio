package com.shiyar1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView  text ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        text.setText(getString(R.string.app_name));
        // getString()  من خلال هذا ميتود نقول احصل على النص   وضمن اقواس نقوم بتحديد العنصر العنصر الذي نرغب بحصول على بياناته
        // عبر الامر R.string نقول نرغب بوصول إلى المصدر ملف النصي الذي يسمى
        // strings.xml
        // في مشروعك
        String getAppName = getString(R.string.app_name);


        // Coder Shiyar
        // لا تبخل علينا بنشر رابط القناتنا واقتراحها للاخرين
    }
}
