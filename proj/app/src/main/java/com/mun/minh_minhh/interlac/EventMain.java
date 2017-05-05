package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;

public class EventMain extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(EventMain.this, HomePage.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_event:
                        break;
                    case R.id.ic_camera:
                        Intent intent3 = new Intent(EventMain.this, CameraMain.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_chat:
                        Intent intent4 = new Intent(EventMain.this, ChatMain.class);
                        startActivity(intent4);
                        break;
                    case R.id.ic_setting:
                        Intent intent5 = new Intent(EventMain.this, SettingsMain.class);
                        startActivity(intent5);
                        break;

                }
                return false;
            }
        });
    }
}
