package com.mun.minh_minhh.interlac;

/**
 * Created by marcel on 05.05.17.
 */

public class Review {
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