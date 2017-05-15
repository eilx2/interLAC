package com.mun.minh_minhh.interlac.Events.Music;

        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.Toast;

        import com.mun.minh_minhh.interlac.BasicActivity;
        import com.mun.minh_minhh.interlac.R;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MusicMain extends AppCompatActivity {

    private String TAG = MusicMain.class.getSimpleName();
    private ListView lv;

    ArrayList<HashMap<String, String>> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_main);

        eventList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetEvents().execute();
    }

    private class GetEvents extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MusicMain.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://luganolac.ch/export/?apikey=jz76KOe&date_from=05/01/2017";
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
                        String link = item.getString("item_permalink");
                        String creation_date = item.getString("item_creation_date");
                        String from  = item.getString("item_date_from");
                        String to = item.getString("item_date_to");
                        String picture = item.getString("item_main_picture");
                        String text = item.getString("item_text");

                        // tmp hash map for single event
                        HashMap<String, String> eventMap = new HashMap<>();

                        // adding each child node to HashMap key => value
                        eventMap.put("id", id);
                        eventMap.put("title", title);
                        eventMap.put("subtitle", subtitle);
                        eventMap.put("category", category);

                        eventMap.put("link", link);
                        eventMap.put("creation_date", creation_date);
                        eventMap.put("from", from);
                        eventMap.put("to", to);

                        eventMap.put("picture", picture);
                        eventMap.put("text", text);

                        // adding contact to contact list
                        eventList.add(eventMap);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ListAdapter adapter;
            adapter = new SimpleAdapter(MusicMain.this, eventList,
                    R.layout.list_item2, new String[]{"title","subtitle", "from", "to"},
                    new int[]{R.id.title, R.id.subtitle, R.id.from, R.id.to});
            lv.setAdapter(adapter);
        }
    }
}
