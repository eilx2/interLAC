package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;

import android.provider.Settings;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.mun.minh_minhh.interlac.Chat.ChatMain;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;

public class HomePage extends BasicActivity {

    public ImageButton button;


    public void init_event(){
        button = (ImageButton)findViewById(R.id.buttonE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, TheaterMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_camera(){
        button = (ImageButton) findViewById(R.id.buttonC);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, CameraMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_mess(){
        button = (ImageButton) findViewById(R.id.buttonM);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, ChatMain.class);
                startActivity(toy);
            }
        });

    }
    public void init_gallery(){
        button = (ImageButton) findViewById(R.id.buttonG);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, GalleryMain.class);
                startActivity(toy);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init_camera();
        init_event();
        init_gallery();
        init_mess();




    }


}
