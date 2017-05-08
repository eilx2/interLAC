package com.mun.minh_minhh.interlac;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import android.util.Log;

public class Server {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private final String TAG = "Server";

    private Review reviewHolder;

    public Server() {
    }

    public static void loginUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

    }
    public static int getReviews() {
       // DatabaseReference ref = mDatabase.getRef("reviews");
        return 1;
    }


    private Review getReview(int id) {
        DatabaseReference mRef = mDatabase.child("/reviews/"+id);


        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reviewHolder = dataSnapshot.getValue(Review.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });


        return null;
    }

    public static void writeReview(int artworkId, Review review) {
        mDatabase.child("reviews").push().setValue(review);
    }
}
