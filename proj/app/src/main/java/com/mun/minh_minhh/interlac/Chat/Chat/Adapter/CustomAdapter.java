package com.mun.minh_minhh.interlac.Chat.Chat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.library.bubbleview.BubbleTextView;
import com.mun.minh_minhh.interlac.Chat.Chat.Model.ChatModel;
import com.mun.minh_minhh.interlac.R;

import java.util.List;

/**
 * Created by Minh_Minhh on 17.05.17.
 */

public class CustomAdapter extends BaseAdapter{

    private List<ChatModel> list_chat_model;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(List<ChatModel> list_chat_model, Context context) {
        this.list_chat_model = list_chat_model;
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list_chat_model.size();
    }

    @Override
    public Object getItem(int position) {
        return list_chat_model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null)
        {
            if(list_chat_model.get(position).isSend)
                view = layoutInflater.inflate(R.layout.list_item_send, null);
            else
                view = layoutInflater.inflate(R.layout.list_item_receive, null);

            BubbleTextView text_message =  (BubbleTextView)view.findViewById(R.id.text_message);
            text_message.setText(list_chat_model.get(position).message);
        }
        return view;
    }
}