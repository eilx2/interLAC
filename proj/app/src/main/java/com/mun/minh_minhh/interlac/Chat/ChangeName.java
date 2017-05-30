package com.mun.minh_minhh.interlac.Chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mun.minh_minhh.interlac.R;
import com.mun.minh_minhh.interlac.UserData;

public class ChangeName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width,height);
        setupButtons();
    }

    private void setupButtons() {
        Button change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editName);
                String newName = editText.getText().toString();

                if (newName.equals("")) {
                    Toast.makeText(ChangeName.this,"Name can't be empty.", Toast.LENGTH_LONG).show();
                }
                else {
                    UserData.setName(newName);
                    finish();
                }
            }
        });

        Button cancel = (Button) findViewById(R.id.cancelChange);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
