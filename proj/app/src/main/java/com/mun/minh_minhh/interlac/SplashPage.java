package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashPage extends BasicActivity {
    private static int Splash_time = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashPage.this, HomePage.class);
                startActivity(homeIntent);
                finish();
            }
        },Splash_time);
    }
}
