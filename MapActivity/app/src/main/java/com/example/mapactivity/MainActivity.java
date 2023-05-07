package com.example.mapactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn; EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        edt = findViewById(R.id.edt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = edt.getText().toString();
                Intent intent = new Intent(MainActivity.this,secondactivity.class);
                intent.putExtra("message", info);    // First word is key, second is the data which we need to transfer
                startActivity(intent);

            }
        });
    }
}