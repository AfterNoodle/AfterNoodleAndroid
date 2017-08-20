package com.yamae.yamaeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.yamae.yamaeapp.R;

/**
 * Created by HyunWook Kim on 2017-07-12.
 */

public class SplashActivity extends AppCompatActivity{

    Context mContext;
    Intent intent;
    final int SPLASH_TIME = 1300;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        mContext=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        intent = new Intent(SplashActivity.this,MainActivity.class);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
