package com.mun.minh_minhh.interlac.Events.Theater;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.Event;
import com.mun.minh_minhh.interlac.Events.EventSpecific;
import com.mun.minh_minhh.interlac.Events.HttpHandler;
import com.mun.minh_minhh.interlac.Events.Music.MusicMain;
import com.mun.minh_minhh.interlac.Events.EventAdapter;
import com.mun.minh_minhh.interlac.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Example of using Folding Cell with ListView and ListAdapter
 */
public class TheaterMain extends BasicActivity {public Button button;

    private String TAG = TheaterMain.class.getSimpleName();
    private ListView lv;
    private EventAdapter adapter;
    private ArrayList<Event> theaterEvents;

    ArrayList<HashMap<String, String>> eventList;
    //public Button button;

    public void init_music_button(){
        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(TheaterMain.this, MusicMain.class);
                startActivity(toy);
            }
        });

    }

    public void init_art_button(){
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(TheaterMain.this, ArtMain.class);
                startActivity(toy);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNav);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        TextView tv = (TextView)findViewById(R.id.textView3);
        tv.setText("Theater & Dance");
        Button btn1 = (Button)findViewById(R.id.button2);
        Button btn2 = (Button)findViewById(R.id.button3);
        ImageView im1 = (ImageView)findViewById(R.id.lin2);
        ImageView im2 = (ImageView)findViewById(R.id.lin3);
        im1.setBackgroundColor(Color.WHITE);
        im2.setBackgroundColor(Color.WHITE);
        btn1.setTextColor(Color.BLACK);
        btn1.setTextSize(14);
        btn2.setTextColor(Color.BLACK);
        btn2.setTextSize(14);
        super.initBottomNavigation();


        init_art_button();
        init_music_button();

        eventList = new ArrayList<>();
        theaterEvents = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);


        new TheaterMain.GetEvents().execute();
    }

    private class GetEvents extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://luganolac.ch/export/?apikey=jz76KOe&date_from=05/24/2017";
            String jsonStrUncut = sh.makeServiceCall(url);
            String jsonStr = jsonStrUncut.substring(1, jsonStrUncut.length()-1);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray events = new JSONArray(jsonStr);

                    // looping through All the events
                    for (int i = 0; i < events.length(); i++) {
                        JSONObject event = events.getJSONObject(i);

                        // Every Item is an object, first we get the Current Item
                        JSONObject item = event.getJSONObject("item");

                        //And then we get the data from it
                        String id = item.getString("item_ID");
                        String title = item.getString("item_title");
                        String subtitle = item.getString("item_subtitle");
                        String category = item.getString("item_category");
                        String from  = item.getString("item_date_from");
                        String to = item.getString("item_date_to");
                        String picture = item.getString("item_main_picture");
                        String text = item.getString("item_text");

                        Event theaterEvent = new Event(id,title,subtitle,category,from,to,picture,text);

                        if(category.equals("Teatro & Danza"))
                            theaterEvents.add(theaterEvent);


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "An error has occured: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "An error has occured: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get data from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get data from server",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            adapter = new EventAdapter(TheaterMain.this, theaterEvents);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(TheaterMain.this, EventSpecific.class);
                    intent.putExtra("title",theaterEvents.get(i).getTitle());
                    intent.putExtra("picture_url",theaterEvents.get(i).getPicture());
                    intent.putExtra("text", theaterEvents.get(i).getText());

                    startActivity(intent);
                }
            });
        }
    }
}
