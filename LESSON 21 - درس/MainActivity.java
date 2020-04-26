package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
   Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // هنا نقوم بوضع الأوامر الذي نرغب بتنفيذها
            Toast toast = Toast.makeText(MainActivity.this," تم تنفيذ اوامر بعد الفترة المحددة",Toast.LENGTH_SHORT);
            toast.show();
            // نقوم بوضع هذا اوامر لكي يتم تكرار تنفيذ الأوامر
          handler.postDelayed(runnable,3*1000);
        }
    };

    public void run_codes(View view){
        // عبر هذا  الجزء من الكود يمكنك تحديد مدة تاخير تنفيذ اوامر لكي يتم تنفيذها مرة واحدة
        handler.postDelayed(runnable,3*1000);
    }

    public void stop_repeat(View view){
        // من خلال هذه الاوامر نقوم بحذف الأمر تكرار تنفيذ الأوامر
        // - وبتالي عندما يتم تنفيذ هذه الأوامر سوف يتم إيقاف تكرار تنفيذ الأوامر
        // المتواجدة ضمن ميتود run المتواجد ضمن variable ال object Runnable
        handler.removeCallbacks(runnable);
    }
}
