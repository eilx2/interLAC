package com.mun.minh_minhh.interlac.Events.ArtWork;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.Events.Review;
import com.mun.minh_minhh.interlac.Events.ReviewAdapter;
import com.mun.minh_minhh.interlac.Events.Reviews.ReviewPage;
import com.squareup.picasso.Picasso;
import com.mun.minh_minhh.interlac.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ArtworkViewMain extends BasicActivity {
    private String pictureId;
    private Artwork artwork;
    private Review review;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String TAG = "ArtworkViewMain";
    private ReviewAdapter reviewsAdapter;
    private ArrayList<Review> reviewList = new ArrayList<>();
    private HashMap<String,Integer> keyToReviewPos = new HashMap<>();
    ImageButton btn;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_splash);
        pictureId = getIntent().getStringExtra("id");
        Log.d("asf",pictureId);
        loadMainInfo();



    }


    private void loadMainInfo() {
        final DatabaseReference mRef = mDatabase.child("/artworks/"+pictureId);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG,dataSnapshot.toString());
                artwork = dataSnapshot.getValue(Artwork.class);

                loadPictureAndDrawInterface();
                mRef.removeEventListener(this);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });


    }
    
    private void drawInterface() {
        setContentView(R.layout.activity_artwork_view_main);
        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(artwork.name);

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(artwork.description);

        final ScrollView layout_review = (ScrollView) findViewById(R.id.layout_review) ;
        final LinearLayout layout_review2 = (LinearLayout) findViewById(R.id.layout_review2) ;


        Button reviewButton1 = (Button) findViewById(R.id.reviewButton1);
        reviewButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_review.setVisibility(View.GONE);
                layout_review2.setVisibility(View.VISIBLE);

            }
        });

        ImageButton button_back = (ImageButton) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layout_review.setVisibility(View.VISIBLE);
                layout_review2.setVisibility(View.GONE);

            }
        });



        Button reviewButton = (Button) findViewById(R.id.reviewButton);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ArtworkViewMain.this,ReviewPage.class);
                intent.putExtra("id",pictureId);
                startActivity(intent);
            }
        });



    }

    private void loadPictureAndDrawInterface() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference(artwork.pictureName);
        final File localFile;

        try {
            localFile = File.createTempFile("images", "jpg");
        }
        catch (IOException e) {
            drawInterface();
            attachReviewsListener();
            return;
        }

        storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                drawInterface();

                ImageView imgView = (ImageView) findViewById(R.id.picView);
                Picasso.with(getApplicationContext()).load(localFile).into(imgView);

                attachReviewsListener();
            }
        });
    }

    private void attachReviewsListener() {
        reviewsAdapter = new ReviewAdapter(this,reviewList,"reviews");
        ListView reviewList = (ListView) findViewById(R.id.reviewList);
        reviewList.setAdapter(reviewsAdapter);


        final DatabaseReference mRef = mDatabase.child("/artworks/"+pictureId+"/review_list");
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
        final DatabaseReference mRef = mDatabase.child("/reviews/"+reviewId);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Review review = dataSnapshot.getValue(Review.class);
                String key = dataSnapshot.getKey();
                review.id = key;

                if (keyToReviewPos.get(dataSnapshot.getKey())!=null) {
                    int pos = keyToReviewPos.get(key);
                    reviewList.set(pos,review);
                    reviewsAdapter.notifyDataSetChanged();
                }
                else{
                    keyToReviewPos.put(key,reviewsAdapter.getCount());
                    reviewsAdapter.add(review);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
