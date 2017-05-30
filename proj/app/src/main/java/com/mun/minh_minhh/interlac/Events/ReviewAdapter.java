package com.mun.minh_minhh.interlac.Events;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.mun.minh_minhh.interlac.Events.Review;
import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.UserData;

import java.util.ArrayList;

/**
 * Created by marcel on 16.05.17.
 */

public class ReviewAdapter extends ArrayAdapter<Review> {
    private final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private  String reviewType;
    public ReviewAdapter(Context context, ArrayList<Review> reviews, String reviewType) {
        super(context,0, reviews);
        this.reviewType = reviewType;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Review review = getItem(position);

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


            final ImageButton likeButton = (ImageButton) convertView.findViewById(R.id.likeButton);

            if (UserData.containsReview(review.id)) likeButton.setBackgroundResource(R.drawable.thumbs_up1);
            else likeButton.setBackgroundResource(R.drawable.thumbs_up);

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDatabase.child("/" +reviewType +"/"+review.id).runTransaction(new Transaction.Handler() {
                        @Override

                        public Transaction.Result doTransaction(MutableData mutableData) {
                            String id = review.id;
                            Review review = mutableData.getValue(Review.class);

                            if (review==null) {
                                return Transaction.success(mutableData);
                            }

                            if (UserData.containsReview(id)) {
                                review.likes--;
                                UserData.removeReview(id);

                            }
                            else {
                                review.likes++;
                                UserData.addReview(id);

                            }

                            mutableData.setValue(review);
                            return Transaction.success(mutableData);
                        }

                        @Override
                        public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                            Log.d("transaction","complete "+databaseError);
                        }
                    });
                }
            });
        }

        return convertView;
    }
}
