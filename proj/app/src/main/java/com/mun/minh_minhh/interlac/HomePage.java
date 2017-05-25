package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.graphics.Color;

import android.graphics.PixelFormat;
import android.os.Bundle;

import android.provider.Settings;

import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.mun.minh_minhh.interlac.Chat.ChatMain;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;

public class HomePage extends BasicActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    /** Called when the activity is first created. */
    Thread splashTread;

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
        StartAnimations();




    }
    private void StartAnimations() {




        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.imageView4);
        iv.clearAnimation();
        iv.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv1 = (ImageView) findViewById(R.id.imageView4);
        iv1.clearAnimation();
        iv1.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        LinearLayout ln1 = (LinearLayout) findViewById(R.id.frow);
        ln1.clearAnimation();
        ln1.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        LinearLayout ln2 = (LinearLayout) findViewById(R.id.srow);
        ln2.clearAnimation();
        ln2.startAnimation(anim);


    }


}
