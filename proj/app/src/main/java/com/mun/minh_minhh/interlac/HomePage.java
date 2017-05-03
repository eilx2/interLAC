package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;

public class HomePage extends BasicActivity {

    private static int Splash_time = 2000;
    public Button button;
    public void init_events_button(){
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, EventMain.class);
                startActivity(toy);
            }
        });

    }
    public void init_camera_button(){
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, CameraMain.class);
                startActivity(toy);
            }
        });

    }
    public void init_chat_button(){
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, ChatMain.class);
                startActivity(toy);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init_events_button();
        init_camera_button();
        init_chat_button();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
