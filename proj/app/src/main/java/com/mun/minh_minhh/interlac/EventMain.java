package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;

public class EventMain extends BasicActivity {
    public Button button;


    public void init_theater_button(){
        button = (Button)findViewById(R.id.testbut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(EventMain.this, TheaterMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_music_button(){
        button = (Button)findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(EventMain.this, MusicMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_art_button(){
        button = (Button)findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(EventMain.this, ArtMain.class);
                startActivity(toy);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_main);
        super.initBottomNavigation();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        init_theater_button();
        init_music_button();
        init_art_button();


    }
}
