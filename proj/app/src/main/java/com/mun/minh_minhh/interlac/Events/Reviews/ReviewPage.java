package com.mun.minh_minhh.interlac.Events.Reviews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mun.minh_minhh.interlac.Events.Review;
import com.mun.minh_minhh.interlac.R;

public class ReviewPage extends AppCompatActivity {
    private String pictureId;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_page);

        pictureId = getIntent().getStringExtra("id");

        Button cancelReview = (Button) findViewById(R.id.cancelReview);
        cancelReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReviewPage.super.onBackPressed();
            }
        });
        Button postReview = (Button) findViewById(R.id.postReview);
        postReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final boolean success = postReview();
                if (success) ReviewPage.this.finish();
            }
        });
    }

    private boolean postReview() {
        String authorName = ((EditText) findViewById(R.id.editName)).getText().toString();
        String reviewContent = ((EditText) findViewById(R.id.editReview)).getText().toString();
        float rating = ((RatingBar) findViewById(R.id.ratingBar)).getRating();


        if (authorName.equals("") || reviewContent.equals("")) {
            Toast.makeText(this,"Please complete all fields.", Toast.LENGTH_LONG).show();
            return false;
        }

        Review review = new Review(authorName, reviewContent, 0, 0, rating);
        DatabaseReference mRef = mDatabase.child("/reviews");
        mRef = mRef.push();
        mRef.setValue(review);


        String reviewId = mRef.getKey();
        mRef = mDatabase.child("/artworks/"+pictureId + "/review_list");
        mRef.push().setValue(reviewId);
        return true;


    }
}
