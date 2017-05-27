package com.mun.minh_minhh.interlac.Events;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mun.minh_minhh.interlac.Events.Reviews.ReviewPage;
import com.mun.minh_minhh.interlac.R;
import com.squareup.picasso.Picasso;

public class EventSpecific extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_view_main);
        
        setUp();
        /*
        Button postReview = (Button) findViewById(R.id.postReview);
        postReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean success = postReview();
                if (success) ReviewPage.this.finish();
            }
        });
        */
        }


    private void setUp() {

            TextView main_title = (TextView) findViewById(R.id.textView3);
            main_title.setText("Event");

            TextView event_title_tView = (TextView) findViewById(R.id.name);
            TextView description_tView = (TextView) findViewById(R.id.description);
            ImageView picture = (ImageView) findViewById(R.id.picView);

            Bundle mBundle = getIntent().getExtras();

            if (mBundle != null) {
                String title = mBundle.getString("title");
                String picture_url = mBundle.getString("picture_url");
                String text = mBundle.getString("text");


                Picasso.with(getBaseContext()).load(picture_url).into(picture);
                event_title_tView.setText(title);
                description_tView.setText(text);

        }

    }

}
