package com.mun.minh_minhh.interlac.Events.Theater;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.BottomNavHelp;
import com.mun.minh_minhh.interlac.R;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class TheaterMain extends BasicActivity {

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

    }
}

