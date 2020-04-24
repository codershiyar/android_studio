package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toast();
    }


    public void toast(){
        // LENGHT_SHORT  رسالة تظهر لمدة قصيرة
        // LENGHT_LONG   رسالة تظهر لمدة طويلة
        Toast message = Toast.makeText(getApplicationContext(),"شكرا لاستخدامك لتطبيقنا" ,Toast.LENGTH_LONG);
        //BOTTOM - اسفل
        //CENTER - منتصف
        // TOP  - اعلى

        // RIGHT - جهة اليمين
        // LEFT - جهة اليسار
        message.setGravity(Gravity.CENTER|Gravity.LEFT,0,0);
        message.show();
    }





}
