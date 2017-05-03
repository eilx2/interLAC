package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public  class BasicActivity extends AppCompatActivity {
    protected BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (!(BasicActivity.this instanceof HomePage))
                        startActivity(new Intent(BasicActivity.this, HomePage.class));
                    return true;
                case R.id.navigation_event:
                    if (!(BasicActivity.this instanceof EventMain))
                        startActivity(new Intent(BasicActivity.this, EventMain.class));
                    return true;
                case R.id.navigation_camera:
                    if (!(BasicActivity.this instanceof CameraMain))
                        startActivity(new Intent(BasicActivity.this, CameraMain.class));
                    return true;
                case R.id.navigation_chat:
                    if (!(BasicActivity.this instanceof ChatMain))
                        startActivity(new Intent(BasicActivity.this, ChatMain.class));
                    return true;
                case R.id.navigation_setting:
                    if (!(BasicActivity.this instanceof HomePage))
                        startActivity(new Intent(BasicActivity.this, HomePage.class));
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);



    }
}
