package com.mun.minh_minhh.interlac;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

public class Server {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public Server() {
    }

    public static void loginUser() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

    }
    public static int getReviews() {
       // DatabaseReference ref = mDatabase.getRef("reviews");
        return 1;
    }

    private int getReview(int id) {
        DatabaseReference mRef = mDatabase.child("/reviews/"+id);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return 1;
    }

    public static void writeReview(int artworkId, Review review) {
        mDatabase.child("reviews").push().setValue(review);
    }
}
