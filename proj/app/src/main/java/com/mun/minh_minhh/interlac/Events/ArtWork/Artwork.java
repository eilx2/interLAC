package com.mun.minh_minhh.interlac.Events.ArtWork;

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
    public int likes;
    public String name, author, pictureName, description;
    public int year;


    public Artwork() {
    }

    public Artwork(String author, String name, String picture, int year, String description,int likes) {
        this.author = author;
        this.name = name;
        this.pictureName = picture;
        this.year = year;
        this.description = description;
        this.likes = likes;
    }

}
