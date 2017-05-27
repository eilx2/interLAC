package com.mun.minh_minhh.interlac.Events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mun.minh_minhh.interlac.R;

import java.util.ArrayList;


public class EventReviewAdapter extends ArrayAdapter<EventReview> {
    public EventReviewAdapter(Context context, ArrayList<EventReview> reviews) {
        super(context,0, reviews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EventReview review = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.review_layout, parent, false);
        }


        TextView content = (TextView) convertView.findViewById(R.id.rowTextView);
        content.setText(review.content);


        TextView author = (TextView) convertView.findViewById(R.id.review_author);
        author.setText(review.author);

        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.reviewRatingBar);
        ratingBar.setRating(review.rating);

        return convertView;
    }
}
