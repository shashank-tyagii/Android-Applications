package com.example.eggtimerapp;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    TextView txt;
    boolean isTimerActive = false;
    CountDownTimer countdowntimer;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);
        txt = findViewById(R.id.txt);
        txt.setText("00 : 00");
        SeekBar seekbar = findViewById(R.id.seekbar);
        seekbar.setMin(1);
        seekbar.setMax(600);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTimerActive){
                    isTimerActive = false;
                    btn.setText("Start");
                    countdowntimer.cancel();
                    seekbar.setEnabled(true);
                } else {
                isTimerActive = true;
                btn.setText("Stop");
                seekbar.setEnabled(false);

                countdowntimer = new CountDownTimer(seekbar.getProgress() *1000 , 1000) {
                    @Override
                    public void onTick(long l) {
                        updateTimer((int) l /1000);
                    }

                    @Override
                    public void onFinish() {
                        MediaPlayer mediaplayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
                        mediaplayer.start();
                    }
                }.start();}
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {            }
        });
    }
    public void updateTimer (int timeInSec){
        int min = timeInSec / 60;
        int sec  = timeInSec % 60;
        String secInString = String.valueOf(sec);
        if (secInString.length() == 1){
            secInString = "0" + secInString;
        }
        txt.setText(min + " : " + secInString);
    }

}