package com.mun.minh_minhh.interlac.Events.Music;

import android.content.Intent;
import android.os.AsyncTask;
        import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

        import com.mun.minh_minhh.interlac.BasicActivity;
import com.mun.minh_minhh.interlac.BottomNavHelp;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.EventAdapter;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;
import com.mun.minh_minhh.interlac.Events.HttpHandler;
import com.mun.minh_minhh.interlac.Events.Event;
import com.mun.minh_minhh.interlac.R;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MusicMain extends BasicActivity {

    private String TAG = MusicMain.class.getSimpleName();
    private ListView lv;
    private EventAdapter adapter;
    private ArrayList<Event> musicEvents;

    ArrayList<HashMap<String, String>> eventList;
    public Button button;


    public void init_theater_button(){
        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MusicMain.this, TheaterMain.class);
                startActivity(toy);
            }
        });

    }



    public void init_art_button(){
        button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MusicMain.this, ArtMain.class);
                startActivity(toy);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_layout);
        super.initBottomNavigation();


        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        BottomNavHelp.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        init_theater_button();
        init_art_button();

        eventList = new ArrayList<>();
        musicEvents = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);


        new GetEvents().execute();
    }

    private class GetEvents extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MusicMain.this,"Data is downloading",Toast.LENGTH_LONG).show();

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

                        Event musicEvent = new Event(id,title,subtitle,category,from,to,picture,text);

                       if(category.equals("Musica"))
                           musicEvents.add(musicEvent);


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
            adapter = new EventAdapter(MusicMain.this, musicEvents);
            lv.setAdapter(adapter);
        }
    }
}
