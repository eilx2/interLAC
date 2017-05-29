package com.mun.minh_minhh.interlac.Events;


public class Review {
    //set to public to allow firebase access
    public String author,content,id;
    public int likes,dislikes;
    public float rating;

    public Review() {
    }

    public Review(String author, String content, int likes, int dislikes, float rating) {
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
        this.rating = rating;
    }

}
