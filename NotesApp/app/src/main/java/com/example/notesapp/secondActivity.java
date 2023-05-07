package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

public class secondActivity extends AppCompatActivity {
    EditText edt; Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn = findViewById(R.id.btn);
        edt = findViewById(R.id.edt);

        Intent intent = getIntent();
        int notesID = intent.getIntExtra("notesID" , -1);
        if (notesID != -1){
            edt.setText(MainActivity.ArrayString.get(notesID));
        }
        if(notesID >0){
            MainActivity.ArrayString.remove(notesID);  // Remove the current because we will add the modified one
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On click Done, it should save the String info go back to Home screen
                String info = edt.getText().toString();
                Intent intent = new Intent(secondActivity.this,MainActivity.class);
                MainActivity.ArrayString.add(info);

                // Save this info to shared preferences memory
                SharedPreferences sharedPreferences = secondActivity.this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
                HashSet<String> set = new  HashSet<>(MainActivity.ArrayString);
                sharedPreferences.edit().putStringSet("notes" , set).apply();

                startActivity(intent);

            }
        });

    }
}