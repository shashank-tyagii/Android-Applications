package com.example.timetable;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SeekBar seekNumber = findViewById(R.id.seekNumber);
        seekNumber.setMax(20);
        seekNumber.setMin(1);
        generatelist(1);
        seekNumber.setProgress(1);
        seekNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //Log.i("message" , String.valueOf(i));
                generatelist(i); }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    }
    public void generatelist(int n){
        ArrayList<String> tables = new ArrayList<>();
        ListView listview = findViewById(R.id.listview);
        for (int x= 1; x<=10; x++){
            tables.add(String.valueOf(n*x));
        }
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,tables);
        listview.setAdapter(arrayadapter);
    }
}