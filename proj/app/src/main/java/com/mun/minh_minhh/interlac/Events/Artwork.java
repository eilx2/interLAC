package com.mun.minh_minhh.interlac.Events;

import java.util.ArrayList;

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

}
