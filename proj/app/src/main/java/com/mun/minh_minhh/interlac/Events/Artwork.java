package com.mun.minh_minhh.interlac.Events;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mun.minh_minhh.interlac.R;
import com.squareup.picasso.Picasso;

public class Artwork {
    //set to public to allow Firebase access
    public String name, author, pictureName, description;
    public int year;

    public Artwork() {
    }

    public Artwork(String author, String name, String picture, int year, String description, ArrayList<Review> review_list) {
        this.author = author;
        this.name = name;
        this.pictureName = picture;
        this.year = year;
        this.description = description;
    }

    public void draw(final Activity activity) {
        TextView nameView = (TextView) activity.findViewById(R.id.name);
        nameView.setText(this.name);

        TextView descriptionView = (TextView) activity.findViewById(R.id.description);
        descriptionView.setText(this.description);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference(this.pictureName);

        storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageView imgView = (ImageView) activity.findViewById(R.id.picView);
                Picasso.with(activity.getApplicationContext()).load(uri.toString()).into(imgView);
            }
        });


    }





}
