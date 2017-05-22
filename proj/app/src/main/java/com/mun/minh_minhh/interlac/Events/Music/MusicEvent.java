package com.mun.minh_minhh.interlac.Events.Music;

/**
 * Created by marcel on 22.05.17.
 */

public class MusicEvent {
    private String id, title, subtitle, category, date_from, date_to, picture, text;


    public MusicEvent(String id, String title, String subtitle, String category, String date_from, String date_to, String picture, String text) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
        this.category = category;
        this.date_from = date_from;
        this.date_to = date_to;
        this.picture = picture;
        this.text = text;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getCategory() {
        return category;
    }

    public String getDate_from() {
        return date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public String getPicture() {
        return picture;
    }

    public String getText() {
        return text;
    }
}
