package com.mun.minh_minhh.interlac.Events;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mun.minh_minhh.interlac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class EventSpecific extends AppCompatActivity {
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    String title;
    Bundle mBundle;
    //private String TAG = "EventSpecific";
    private EventReviewAdapter reviewsAdapter;
    private ArrayList<EventReview> reviewList = new ArrayList<>();
    private HashMap<String, Integer> keyToReviewPos = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_view_main);
        setUp();


        Button reviewButton = (Button) findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventSpecific.this, EventReviewPage.class);
                intent.putExtra("title", mBundle.getString("title"));
                startActivity(intent);
            }
        });

    }


    private void setUp() {

        TextView main_title = (TextView) findViewById(R.id.textView3);
        main_title.setText("Event");

        TextView event_title_tView = (TextView) findViewById(R.id.name);
        TextView description_tView = (TextView) findViewById(R.id.description);
        ImageView picture = (ImageView) findViewById(R.id.picView);

        mBundle = getIntent().getExtras();

        if (mBundle != null) {

            title = mBundle.getString("title");
            String picture_url = mBundle.getString("picture_url");
            String text = mBundle.getString("text");

            Picasso.with(getBaseContext()).load(picture_url).into(picture);
            event_title_tView.setText(title);
            description_tView.setText(text);
        }


        attachReviewsListener();
    }


    private void attachReviewsListener() {
        reviewsAdapter = new EventReviewAdapter(this, reviewList);
        ListView reviewList = (ListView) findViewById(R.id.reviewList);
        reviewList.setAdapter(reviewsAdapter);

        final DatabaseReference mRef = mDatabase.child("/events/"+ title + "/review_list");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                addReview(dataSnapshot.getValue(String.class));
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

    private void addReview(String reviewId) {
        final DatabaseReference mRef = mDatabase.child("/event_reviews/" + reviewId);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EventReview review = dataSnapshot.getValue(EventReview.class);
                String key = dataSnapshot.getKey();

                if (keyToReviewPos.get(dataSnapshot.getKey()) != null) {
                    int pos = keyToReviewPos.get(key);
                    reviewList.set(pos, review);
                    reviewsAdapter.notifyDataSetChanged();
                } else {
                    keyToReviewPos.put(key, reviewsAdapter.getCount());
                    reviewsAdapter.add(review);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
