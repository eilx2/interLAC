package com.mun.minh_minhh.interlac.Events;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.R;

public class ArtworkViewMain extends BasicActivity {
    private int pictureId;
    private Artwork artwork;
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private String TAG = "ArtworkViewMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_view_main);


        pictureId = getIntent().getIntExtra("id",1);
        getAndDrawArtwork();
    }

    private void getAndDrawArtwork() {
        DatabaseReference mRef = mDatabase.child("/artworks/"+pictureId);


        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                artwork = dataSnapshot.getValue(Artwork.class);
                Log.d(TAG,dataSnapshot.toString());
                draw(artwork);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.toString());
            }
        });

    }

    private void draw(Artwork artwork) {
        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(artwork.name);
    }

}
