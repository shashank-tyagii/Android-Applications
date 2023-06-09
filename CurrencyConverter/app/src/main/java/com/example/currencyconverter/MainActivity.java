package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

                EditText edt = findViewById(R.id.input);

                String value = edt.getText().toString();
                int valInUSD = Integer.parseInt(value);
                int valInINR = valInUSD * 75;
                String output = Integer.toString(valInINR);
                Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT).show();

            }
        });
    }
}