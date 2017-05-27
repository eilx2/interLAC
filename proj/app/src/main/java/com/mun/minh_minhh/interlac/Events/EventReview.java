package com.mun.minh_minhh.interlac.Events;

public class EventReview {
    //set to public to allow firebase access
    public String author,content;
    public float rating;
    public String title;

    public EventReview() {
    }

    public EventReview(String author, String content, float rating, String title) {
        this.author = author;
        this.content = content;
        this.rating = rating;
        this.title = title;
    }

}

