package com.mun.minh_minhh.interlac.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.Events.ArtWork.ArtworkViewMain;
import com.mun.minh_minhh.interlac.R;

public class GalleryMain extends BasicActivity {

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNav);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_main);
        super.initBottomNavigation();



        ImageButton button = (ImageButton) findViewById(R.id.image1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(GalleryMain.this, ArtworkViewMain.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        });

        button = (ImageButton) findViewById(R.id.image2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(GalleryMain.this, ArtworkViewMain.class);
                intent.putExtra("id","2");
                startActivity(intent);

            }
        });

        button = (ImageButton) findViewById(R.id.image3);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(GalleryMain.this, ArtworkViewMain.class);
                intent.putExtra("id","3");
                startActivity(intent);

            }
        });


        button = (ImageButton) findViewById(R.id.image4);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(GalleryMain.this, ArtworkViewMain.class);
                intent.putExtra("id","4");
                startActivity(intent);

            }
        });
    }
}

