package com.mun.minh_minhh.interlac.Chat;

import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.UserData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatMain extends BasicActivity {
    EditText roomName;
    ListView roomList;
    Button creatRoom;
    ArrayList<String> roomArray;
    ArrayAdapter<String> roomAdapter;
    private String userName;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_main);
        super.initBottomNavigation();

        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNav);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        roomName = (EditText) findViewById(R.id.editText);
        creatRoom = (Button) findViewById(R.id.button3);
        roomList = (ListView) findViewById(R.id.roomList);

        roomArray = new ArrayList<String>();
        roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, roomArray);

        roomList.setAdapter(roomAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Chat");

        request_name();

        creatRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(roomName.getText().toString(),"");
                databaseReference.updateChildren(map);
                roomName.setText("");
            }
        });
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator iterator = dataSnapshot.getChildren().iterator();
                Set<String> set = new HashSet<String>();
                while (iterator.hasNext()){
                    //Get name off all the room one by one from data base
                    set.add((String) ((DataSnapshot)iterator.next()).getKey());
                }
                roomArray.clear();
                roomArray.addAll(set);
                roomAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        roomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChatMain.this, ChatRoom.class);
                intent.putExtra("Room_name", ((TextView)view).getText().toString());
                intent.putExtra("User_name", userName);
                startActivity(intent);

            }
        });

    }

    private void request_name() {
        if (UserData.getName()!="") {
            userName = UserData.getName();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your name");
        final EditText editText = new EditText(this);
        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                userName = editText.getText().toString();
                UserData.setName(userName);
                if (!TextUtils.isEmpty(userName))
                {

                }
                else
                {
                    request_name();
                }

                }

            }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                request_name();

            }
        });

        builder.show();
        }
        
    }

