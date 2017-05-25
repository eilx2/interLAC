package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.Chat.ChatMain;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;

public  class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initBottomNavigation() {
        BottomNavigationViewEx btv = (BottomNavigationViewEx) findViewById(R.id.bottomNav);
        btv.enableAnimation(false);
        btv.enableShiftingMode(false);
        btv.enableItemShiftingMode(false);
        btv.setTextVisibility(false);
        btv.setIconSize(32,32);
        btv.setItemHeight(BottomNavigationViewEx.dp2px(this, 50));




        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        if (BasicActivity.this instanceof HomePage)
                            return true;
                        startActivity(new Intent(BasicActivity.this,HomePage.class));
                        break;
                    case R.id.ic_event:
                        if (BasicActivity.this instanceof TheaterMain)
                            return true;
                        startActivity(new Intent(BasicActivity.this,TheaterMain.class));
                        break;
                    case R.id.ic_camera:
                        if (BasicActivity.this instanceof CameraMain)
                            return true;
                        startActivity(new Intent(BasicActivity.this,CameraMain.class));
                        break;
                    case R.id.ic_chat:
                        if (BasicActivity.this instanceof ChatMain)
                            return true;
                        startActivity(new Intent(BasicActivity.this,ChatMain.class));
                        break;
                    case R.id.ic_gallery:
                        if (BasicActivity.this instanceof GalleryMain)
                            return true;
                        startActivity(new Intent(BasicActivity.this,GalleryMain.class));
                        break;
                    default:
                        return false;

                }
                return true;
            }
        });


    }
}
