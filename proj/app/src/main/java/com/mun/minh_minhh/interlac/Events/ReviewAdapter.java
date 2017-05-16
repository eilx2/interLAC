package com.mun.minh_minhh.interlac.Events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        TextView content = (TextView) convertView.findViewById(R.id.rowTextView);
        content.setText(review.content);
        return convertView;
    }
}
