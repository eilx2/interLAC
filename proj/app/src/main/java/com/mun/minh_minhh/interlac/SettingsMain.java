package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mun.minh_minhh.interlac.Events.ArtWork.ArtworkViewMain;

public class SettingsMain extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);
        super.initBottomNavigation();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        final Button button = (Button) findViewById(R.id.testbut);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Server.writeReview(1,new Review("john","lol,shit",0,2));
                Intent intent = new Intent(SettingsMain.this, ArtworkViewMain.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        });
    }
}

