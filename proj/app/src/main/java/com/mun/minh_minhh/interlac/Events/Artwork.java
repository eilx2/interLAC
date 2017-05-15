package com.mun.minh_minhh.interlac.Events;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mun.minh_minhh.interlac.R;
import com.squareup.picasso.Picasso;

public class Artwork {
    //set to public to allow Firebase access
    public String name, author, picture;
    public int year;
    ArrayList<String> reviews;

    public Artwork() {
    }

    public Artwork(String author, String name, String picture, int year, ArrayList<String> reviews) {
        this.author = author;
        this.name = name;
        this.picture = picture;
        this.year = year;
        this.reviews = reviews;
    }

    public void draw(Activity activity) {
        TextView name = (TextView) activity.findViewById(R.id.name);
        name.setText(this.name);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://interlac-bb03f.appspot.com").child("lisa.jpg");

        ImageView imgView = (ImageView) activity.findViewById(R.id.picView);
        Log.d("Artwork",storageRef.getDownloadUrl().toString());
        Picasso.with(activity.getApplicationContext()).load("https://firebasestorage.googleapis.com/v0/b/interlac-bb03f.appspot.com/o/louvre-allegorie-fortune-fortuna-marina.jpg?alt=media&token=3b996773-b1f6-4bdf-bcd1-7debe5e8858f").into(imgView);
    }

}
