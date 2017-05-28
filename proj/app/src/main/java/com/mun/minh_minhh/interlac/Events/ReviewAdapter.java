package com.mun.minh_minhh.interlac.Events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mun.minh_minhh.interlac.Events.Review;
import com.mun.minh_minhh.interlac.R;

import java.util.ArrayList;

/**
 * Created by marcel on 16.05.17.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {
    public ReviewAdapter(Context context, ArrayList<Review> reviews) {
        super(context,0, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Review review = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_layout, parent, false);
        }

        if (review != null) {
            TextView likes = (TextView) convertView.findViewById(R.id.like);

            likes.setText(String.valueOf(review.likes));

            TextView content = (TextView) convertView.findViewById(R.id.rowTextView);
            content.setText(review.content);

            TextView author = (TextView) convertView.findViewById(R.id.review_author);
            author.setText(review.author);

            RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.reviewRatingBar);
            ratingBar.setRating(review.rating);
        }

        return convertView;
    }
}
