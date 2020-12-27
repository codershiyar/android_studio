package com.shiyarlive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView version = (TextView) findViewById(R.id.version);
        version.setText("Version" + BuildConfig.VERSION_NAME);


    }

    public void openWebViewActivity(View view){
        Intent openActivity = new Intent(this,WebViewClass.class);
        startActivity(openActivity);
    }
}