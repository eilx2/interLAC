package com.mun.minh_minhh.interlac.Events;


public class Review {
    //set to public to allow firebase access
    public String author,content;
    public int likes,dislikes;

    public Review() {
    }

    public Review(String author, String content, int likes, int dislikes) {
        this.author = author;
        this.content = content;
        this.likes = likes;
        this.dislikes = dislikes;
    }
}
