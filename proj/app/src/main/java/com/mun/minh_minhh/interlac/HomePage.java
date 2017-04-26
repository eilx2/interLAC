package com.mun.minh_minhh.interlac;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.Button;

public class HomePage extends BasicActivity {

    public Button button;
    public void init(){
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomePage.this, EventMain.class);
                startActivity(toy);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        init();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
