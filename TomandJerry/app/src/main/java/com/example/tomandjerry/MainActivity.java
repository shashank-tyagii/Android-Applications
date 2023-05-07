package com.example.tomandjerry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean isTom = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView jerry = findViewById(R.id.jerry);
        ImageView tom = findViewById(R.id.tom);
        jerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTom == false){
                    isTom = true;
                jerry.animate().alpha(0).setDuration(2000);
                tom.animate().alpha(1).setDuration(2000);
                }
                else {
                    jerry.animate().alpha(1).setDuration(2000);
                    tom.animate().alpha(0).setDuration(2000);
                    isTom = false;
                }
            }
        });


    }
}