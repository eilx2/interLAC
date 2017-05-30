package com.mun.minh_minhh.interlac.Chat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.mun.minh_minhh.interlac.BasicAct.BasicActivity;
import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.UserData;

import java.util.HashMap;
import java.util.Map;

import static android.view.Gravity.CENTER;

public class ChatRoom extends BasicActivity {
    ImageButton sendBtn;
    EditText sendMes;
    ScrollView scrollView;
    LinearLayout layout;



    //Data Reference

    DatabaseReference rootRoomName;

    //String field
    String roomName;
    String userName;
    private String chatUserName;
    private String chatMessage;
    private String chatUid;

    @Override
    protected void onResume() {
        super.onResume();
        BottomNavigationViewEx bottomNavigationView = (BottomNavigationViewEx) findViewById(R.id.bottomNav);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        super.initBottomNavigation();


        scrollView = (ScrollView)findViewById(R.id.scrollView);
        layout = (LinearLayout) findViewById(R.id.layout1);


        sendBtn = (ImageButton) findViewById(R.id.sendMessBtn);
        sendMes = (EditText) findViewById(R.id.sendMes);

        roomName = getIntent().getExtras().get("Room_name").toString();
        userName = UserData.getName();

        TextView room = (TextView) findViewById(R.id.roomName);
        room.setText(roomName);




        //Set Act title to room name

        setTitle(roomName);
        rootRoomName = FirebaseDatabase.getInstance().getReference().child("/Chat/"+roomName );

        //OnClickListener
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference childRoot = rootRoomName.push();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", userName);
                map.put("message", sendMes.getText().toString());
                map.put("uid", UserData.getUid());
                childRoot.updateChildren(map);
                sendMes.setText("");
            }
        });

        rootRoomName.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                update_Mess(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                update_Mess(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });
        



    }

    private void update_Mess(DataSnapshot dataSnapshot) {
        chatUserName = (String) dataSnapshot.child("name").getValue();
        chatMessage= (String) dataSnapshot.child("message").getValue();
        chatUid = (String) dataSnapshot.child("uid").getValue();

        if(chatUid.equals(UserData.getUid())){
            addMessageBox(chatMessage ,2);
        }
        else{
            addMessageBox(chatUserName + ": " + chatMessage ,1);

        }

    }
    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatRoom.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            lp2.topMargin = 20;
            lp2.leftMargin = 50;
            textView.setBackgroundResource(R.drawable.bubble_out);
            textView.setTextColor(Color.BLACK);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(25, 10, 25, 10);
            textView.setTextSize(20);

        }
        else{
            lp2.gravity = Gravity.RIGHT;
            lp2.topMargin = 20;
            lp2.rightMargin = 30;
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.bubble_in);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(25, 10, 25, 10);
            textView.setTextSize(20);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
