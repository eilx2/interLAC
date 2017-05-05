package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public  class BasicActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(BasicActivity.this, HomePage.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_event:
                        Intent intent2 = new Intent(BasicActivity.this, EventMain.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_camera:
                        Intent intent3 = new Intent(BasicActivity.this, CameraMain.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_chat:
                        Intent intent4 = new Intent(BasicActivity.this, ChatMain.class);
                        startActivity(intent4);
                        break;
                    case R.id.ic_setting:
                        Intent intent5 = new Intent(BasicActivity.this, SettingsMain.class);
                        startActivity(intent5);
                        break;

                }
                return false;
            }
        });
    }
}
