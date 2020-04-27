package com.example.codershiyar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
            }

            // ضمن هذه الجملة الشرطية -  يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
            // في حال كان مستخدم غير متصل بالواي فاي او الشبكة بيانات الهاتف
            if(!isMobileConn && !isWifiConn){
                Toast toast = Toast.makeText(getApplicationContext(),"انت غير متصل بالشبكة , قم بتحقق من اتصالك بالشبكة",Toast.LENGTH_LONG);
                toast.show();
            }
            // ضمن هذه الجملة الشرطية -  يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
            // في حال كان مستخدم متصل بالشبكة الواي فاي او بالشبكة بيانات الهاتف
            if (isMobileConn || isWifiConn){
                Toast toast = Toast.makeText(getApplicationContext(),"انت متصل بالشبكة",Toast.LENGTH_LONG);
                toast.show();
            }
            // ضمن هذه الجملة الشرطية - يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
            // إذا كان المستخدم غير متصل بالشبكة واي فاي
            if (!isWifiConn){

            }
            // ضمن هذه الجملة الشرطية - يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
            // إذا كان المستخدم غير متصل بالشبكة بيانات الهاتف
            if (!isMobileConn){

            }

        // ضمن هذه الجملة الشرطية - يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
        // إذا كان المستخدم متصل بالشبكة واي فاي
        if (isWifiConn){

        }
        // ضمن هذه الجملة الشرطية - يمكنك ان تقوم بوضع اوامر الذي ترغب بتنفيذها
        // إذا كان المستخدم متصل بالشبكة بيانات الهاتف
        if (isMobileConn){

        }
    }



}
