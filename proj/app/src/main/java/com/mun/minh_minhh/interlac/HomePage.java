package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class HomePage extends BasicActivity {

    private static int Splash_time = 2000;
    public Button button;
    private CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel)
                .addSubMenu(Color.parseColor("#30A400"), R.mipmap.icon_search)
                .addSubMenu(Color.parseColor("#FF4B32"), R.mipmap.icon_notify)
                .addSubMenu(Color.parseColor("#8A39FF"), R.mipmap.icon_setting)
                .addSubMenu(Color.parseColor("#FF6A00"), R.mipmap.icon_gps)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {}

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {}

        });
        circleMenu.setOnMenuSelectedListener (new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {
                switch (i){
                    case 0:
                        startActivity(new Intent(HomePage.this,EventMain.class));
                        break;
                    case 1:
                        startActivity(new Intent(HomePage.this,CameraMain.class));
                        break;
                    case 2:
                        startActivity(new Intent(HomePage.this,ChatMain.class));
                        break;
                    case 3:
                        startActivity(new Intent(HomePage.this,SettingsMain.class));
                        break;


                }

            }


        });


        /*button
        init_events_button();
        init_camera_button();
        init_chat_button();*/


    }
}
