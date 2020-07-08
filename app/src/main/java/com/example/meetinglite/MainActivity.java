package com.example.meetinglite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void ButtonClicked(View v) {
    if(v==findViewById(R.id.imageButtonSetMeeting)){
        Intent set_meeting = new Intent(getApplicationContext(),SetMeeting.class);
        startActivity(set_meeting);
    }

    }
}