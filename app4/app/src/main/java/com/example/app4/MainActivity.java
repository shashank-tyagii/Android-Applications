package com.example.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userid = findViewById(R.id.userid);
                Toast.makeText(MainActivity.this, userid.getText().toString(), Toast.LENGTH_SHORT).show();
                EditText password = findViewById(R.id.password);

                Toast.makeText(MainActivity.this, password.getText().toString(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}