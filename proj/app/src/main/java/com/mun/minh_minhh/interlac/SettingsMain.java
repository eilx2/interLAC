package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SettingsMain extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_home:
                        Intent intent1 = new Intent(SettingsMain.this, HomePage.class);
                        startActivity(intent1);
                        break;
                    case R.id.ic_event:
                        Intent intent2 = new Intent(SettingsMain.this, EventMain.class);
                        startActivity(intent2);
                        break;
                    case R.id.ic_camera:
                        Intent intent3 = new Intent(SettingsMain.this, CameraMain.class);
                        startActivity(intent3);
                        break;
                    case R.id.ic_chat:
                        Intent intent4 = new Intent(SettingsMain.this, ChatMain.class);
                        startActivity(intent4);
                        break;
                    case R.id.ic_setting:

                        break;

                }
                return false;
            }
        });
    }
}

