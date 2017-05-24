package com.mun.minh_minhh.interlac.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mun.minh_minhh.interlac.R;

import java.util.HashMap;
import java.util.Map;

import static com.mun.minh_minhh.interlac.R.id.editText;

public class ChatRoom extends AppCompatActivity {
    Button sendBtn;
    TextView receiveMes;
    EditText sendMes;

    //Data Reference

    DatabaseReference rootRoomName;

    //String field
    String roomName;
    String userName;
    private String chatUserName;
    private String chatMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        sendBtn = (Button) findViewById(R.id.sendMessBtn);
        receiveMes = (TextView) findViewById(R.id.receive);
        sendMes = (EditText) findViewById(R.id.sendMes);

        roomName = getIntent().getExtras().get("Room_name").toString();
        userName = getIntent().getExtras().get("User_name").toString();

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

        receiveMes.append(chatUserName + ":" + chatMessage + "\n\n");


    }
}
