package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.codershiyar.BuildConfig;

import com.example.codershiyar.BuildConfig;

public class Screen_Splash extends AppCompatActivity {
Handler handler = new Handler();

Button app_name;
TextView version;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.screen__splash);

        app_name = (Button) findViewById(R.id.app_name);
        app_name.setText(R.string.app_name);
        version = (TextView) findViewById(R.id.version);
        version.setText(BuildConfig.VERSION_NAME);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                run_activity();
            }
        };
        handler.postDelayed(runnable,2*1000);

    }
    public void run_activity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
