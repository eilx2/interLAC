package com.mun.minh_minhh.interlac;

import android.os.AsyncTask;

import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ListView;

import com.google.gson.Gson;
import com.mun.minh_minhh.interlac.Chat.Chat.Helper.HttpDataHandler;
import com.mun.minh_minhh.interlac.Chat.Chat.Adapter.CustomAdapter;
import com.mun.minh_minhh.interlac.Chat.Chat.Model.ChatModel;
import com.mun.minh_minhh.interlac.Chat.Chat.Model.SenderModel;

import java.util.ArrayList;
import java.util.List;

public class ChatMain extends BasicActivity {
    ListView listView;
    EditText editText;
    List<ChatModel> list_chat = new ArrayList<>();
    FloatingActionButton btn_send_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);
        super.initBottomNavigation();

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        listView = (ListView)findViewById(R.id.list_of_message);
        editText = (EditText)findViewById(R.id.user_message);
        btn_send_message = (FloatingActionButton)findViewById(R.id.hoho);

        btn_send_message.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                ChatModel model = new ChatModel(text,true); //user send
                list_chat.add(model);
                new ChatAPI().execute(list_chat);

                //remove user mess
                editText.setText("");

            }
        });
    }

    private class ChatAPI extends AsyncTask<List<ChatModel>,Void,String> {
        String stream = null;
        List<ChatModel> models;
        String text = editText.getText().toString();

        @Override
        protected String doInBackground(List<ChatModel>... params) {
            String url = String.format("http://sandbox.api.simsimi.com/request.p?key=%s&lc=en&ft=1.0&text=%s",getString(R.string.simsimi_api),text);
            models = params[0];
            HttpDataHandler httpDataHandler = new HttpDataHandler();
            stream = httpDataHandler.GetHTTPData(url);
            return stream;
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            SenderModel response = gson.fromJson(s,SenderModel.class);

            ChatModel chatModel = new ChatModel(response.getResponse(),false);
            models.add(chatModel);
            CustomAdapter adapter = new CustomAdapter(models,getApplicationContext());
            listView.setAdapter(adapter);

        }
    }
}