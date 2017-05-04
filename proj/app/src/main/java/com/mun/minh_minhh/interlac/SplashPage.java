package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashPage extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_page);

        Thread mySplash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent i = new Intent(getApplicationContext(), HomePage.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        mySplash.start();
    }
}
