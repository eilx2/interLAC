package com.mun.minh_minhh.interlac.Events;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mun.minh_minhh.interlac.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(Context context, ArrayList<Event> musicEvents) {
        super(context,0,musicEvents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item2, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.title)).setText(Html.fromHtml(event.getTitle()));
        ((TextView) convertView.findViewById(R.id.subtitle)).setText(Html.fromHtml(event.getSubtitle()));
        ((TextView) convertView.findViewById(R.id.from)).setText(Html.fromHtml(event.getDate_from()));
        ((TextView) convertView.findViewById(R.id.to)).setText(Html.fromHtml(event.getDate_to()));

        ImageView picture = (ImageView) convertView.findViewById(R.id.evt_img);
        Picasso.with(getContext()).load(event.getPicture()).into(picture);
        return convertView;
    }

}


