package com.mun.minh_minhh.interlac.Events.Music;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.Events.Arts.ArtMain;
import com.mun.minh_minhh.interlac.Events.EventAdapter;
import com.mun.minh_minhh.interlac.Events.EventSpecific;
import com.mun.minh_minhh.interlac.Events.Theater.TheaterMain;
import com.mun.minh_minhh.interlac.Events.HttpHandler;
import com.mun.minh_minhh.interlac.Events.Event;
import com.mun.minh_minhh.interlac.R;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MusicMain extends BasicActivity {

    private String TAG = MusicMain.class.getSimpleName();
    private ListView lv;
    private EventAdapter adapter;
    private ArrayList<Event> musicEvents;

    public Button button;
    String today = new SimpleDateFormat("MM/dd/yyyy").format(new Date());


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
        tv.setText("Music");
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button3);
        btn1.setBackgroundResource(R.color.colorWhite);
        btn1.setTextColor(Color.BLACK);
        btn1.setTextSize(14);
        btn2.setBackgroundResource(R.color.colorWhite);
        btn2.setTextColor(Color.BLACK);
        btn2.setTextSize(14);
        super.initBottomNavigation();



        init_theater_button();
        init_art_button();

        musicEvents = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);


        new GetEvents().execute();
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
            String url = "http://luganolac.ch/export/?apikey=jz76KOe&date_from=20/05/2017";
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
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(MusicMain.this, EventSpecific.class);
                    intent.putExtra("title",musicEvents.get(i).getTitle());
                    intent.putExtra("picture_url",musicEvents.get(i).getPicture());
                    intent.putExtra("text", musicEvents.get(i).getText());

                    startActivity(intent);
                }
            });
        }
    }
}
