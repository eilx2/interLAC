package com.mun.minh_minhh.interlac;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by marcel on 29.05.17.
 */

public class UserData extends BasicActivity {
    private static TreeSet<String> likedReviews = new TreeSet<>();
    private static final String PREFS_NAME="interLAC_settings";
    private static String uid;
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    public static Context context;
    private static boolean isInitialized = false;

    private static String randomString() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int length = 30;
        char tempChar;
        for (int i = 0; i < length; i++){
            tempChar = (char) (generator.nextInt(26) + 97);
            randomStringBuilder.append(tempChar);
        }

        return randomStringBuilder.toString();
    }

    private static void get_uid() {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = preferences.edit();
        editor2.clear();
        editor2.apply();

        uid = preferences.getString("userId","");
        if (uid.equals("")) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            uid = randomString();
            editor.putString("userId",uid);
            editor.apply();
        }
    }

    public static void getUserData() {
        if (isInitialized) return;
        isInitialized = true;

        get_uid();

        mDatabase.child("/users/"+uid+"/reviews").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> data = (ArrayList<String>) (dataSnapshot.getValue());

                if (data != null) likedReviews = new TreeSet<>(data);

                mDatabase.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void addReview(String reviewId) {
        likedReviews.add(reviewId);
        postData();
    }

    public static void postData() {
        mDatabase.child("/users/"+uid+"/reviews").setValue(new ArrayList<>(likedReviews));
    }

    public static String getUid() {
        return uid;
    }

    public static boolean containsReview(String reviewId) {
        Log.d("user_data","hey");
        return likedReviews.contains(reviewId);
    }

    public static void removeReview(String reviewId) {
        likedReviews.remove(reviewId);
        postData();
    }
}
