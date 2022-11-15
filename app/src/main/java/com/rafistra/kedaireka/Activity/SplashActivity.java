package com.rafistra.kedaireka.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.rafistra.kedaireka.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(3000);
                    actUserLogin();
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }

    private void actUserLogin(){
        SharedPreferences _objpref=getSharedPreferences("WFH",MODE_PRIVATE);

        if(_objpref.getString("token","")!=""){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        } else {
            Intent login = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(login);
        }

    }

}