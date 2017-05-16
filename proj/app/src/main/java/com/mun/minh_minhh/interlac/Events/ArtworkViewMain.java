package com.mun.minh_minhh.interlac.Events;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.R;

import java.util.ArrayList;

public class ArtworkViewMain extends BasicActivity {
    private int pictureId;
    private Artwork artwork;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String TAG = "ArtworkViewMain";
    private ReviewAdapter reviewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_view_main);


        pictureId = getIntent().getIntExtra("id",1);
        reviewsAdapter = new ReviewAdapter(this,new ArrayList<Review>());
        ListView reviewList = (ListView) findViewById(R.id.reviewList);
        reviewList.setAdapter(reviewsAdapter);
        getAndDrawArtwork();
        setReviewListener();
    }

    private void getAndDrawArtwork() {
        DatabaseReference mRef = mDatabase.child("/artworks/"+pictureId);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,dataSnapshot.toString());
                artwork = dataSnapshot.getValue(Artwork.class);

                artwork.draw(ArtworkViewMain.this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });


    }

    private void setReviewListener() {
        DatabaseReference mRef = mDatabase.child("/artworks/"+pictureId+"/review_list");

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("review",dataSnapshot.toString());
                reviewsAdapter.add(dataSnapshot.getValue(Review.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
