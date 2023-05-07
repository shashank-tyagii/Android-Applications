package com.example.mapactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class secondactivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondactivity);
        txt = findViewById(R.id.txt);

        Intent intent = getIntent();
        String messageReceived = intent.getStringExtra("message");   //Key set in previous activity

        txt.setText(messageReceived);
    }
}