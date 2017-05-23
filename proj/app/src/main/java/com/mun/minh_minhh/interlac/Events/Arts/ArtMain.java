package com.mun.minh_minhh.interlac.Events.Arts;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alexvasilkov.android.commons.texts.SpannableBuilder;
import com.alexvasilkov.android.commons.utils.Views;
import com.alexvasilkov.foldablelayout.UnfoldableView;
import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.BottomNavHelp;
import com.mun.minh_minhh.interlac.EventMain;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;
import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.Events.Arts.items.Painting;
import com.mun.minh_minhh.interlac.Events.Arts.items.PaintingsAdapter;
import com.mun.minh_minhh.interlac.Events.Arts.utils.GlideHelper;

public class ArtMain extends BasicActivity {

    private ListView mListView;
    private View mListTouchInterceptor;
    private View mDetailsLayout;
    private UnfoldableView mUnfoldableView;
    public Button button;


    public void init_theater_button(){
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(ArtMain.this, TheaterMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_music_button(){
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(ArtMain.this, MusicMain.class);
                startActivity(toy);
            }
        });

    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_main);
        super.initBottomNavigation();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        init_music_button();
        init_theater_button();

        mListView = (ListView) findViewById(R.id.list_view);
        mListView.setAdapter(new PaintingsAdapter(this));
        mListTouchInterceptor = findViewById(R.id.touch_interceptor_view);
        mListTouchInterceptor.setClickable(false);

        mDetailsLayout = findViewById(R.id.details_layout);
        mDetailsLayout.setVisibility(View.INVISIBLE);

        mUnfoldableView = (UnfoldableView) findViewById(R.id.unfoldable_view);

        mUnfoldableView.setOnFoldingListener(new UnfoldableView.SimpleFoldingListener() {
            @Override
            public void onUnfolding(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
                mDetailsLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onUnfolded(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
            }

            @Override
            public void onFoldingBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(true);
            }

            @Override
            public void onFoldedBack(UnfoldableView unfoldableView) {
                mListTouchInterceptor.setClickable(false);
                mDetailsLayout.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mUnfoldableView != null
                && (mUnfoldableView.isUnfolded() || mUnfoldableView.isUnfolding())) {
            mUnfoldableView.foldBack();
        } else {
            super.onBackPressed();
        }
    }

    public void openDetails(View coverView, Painting painting) {
        final ImageView image = Views.find(mDetailsLayout, R.id.details_image);
        final TextView title = Views.find(mDetailsLayout, R.id.details_title);
        final TextView description = Views.find(mDetailsLayout, R.id.details_text);

        GlideHelper.loadPaintingImage(image, painting);
        title.setText(painting.getTitle());

        SpannableBuilder builder = new SpannableBuilder(this);
        builder
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("Year").append(": ")
                .clearStyle()
                .append(painting.getYear()).append("\n")
                .createStyle().setFont(Typeface.DEFAULT_BOLD).apply()
                .append("Location").append(": ")
                .clearStyle()
                .append(painting.getLocation());
        description.setText(builder.build());

        mUnfoldableView.unfold(coverView, mDetailsLayout);

    }
}
