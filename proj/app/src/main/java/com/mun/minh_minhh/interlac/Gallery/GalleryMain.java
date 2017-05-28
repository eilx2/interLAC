package com.mun.minh_minhh.interlac.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.Events.ArtWork.ArtworkViewMain;
import com.mun.minh_minhh.interlac.R;

public class GalleryMain extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_main);
        super.initBottomNavigation();

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        final Button button = (Button) findViewById(R.id.image1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Server.writeReview(1,new Review("john","lol,shit",0,2));
                Intent intent = new Intent(GalleryMain.this, ArtworkViewMain.class);
                intent.putExtra("id","1");
                startActivity(intent);

            }
        });
    }
}

