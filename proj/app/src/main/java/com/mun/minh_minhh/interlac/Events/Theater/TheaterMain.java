package com.mun.minh_minhh.interlac.Events.Theater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.BottomNavHelp;
import com.mun.minh_minhh.interlac.EventMain;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class TheaterMain extends BasicActivity {public Button button;



    public void init_music_button(){
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(TheaterMain.this, MusicMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_art_button(){
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(TheaterMain.this, ArtMain.class);
                startActivity(toy);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theater_main);
        super.initBottomNavigation();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        init_art_button();
        init_music_button();

    }
}

