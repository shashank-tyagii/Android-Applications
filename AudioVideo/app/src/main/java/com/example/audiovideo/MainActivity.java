package com.example.audiovideo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = findViewById(R.id.play);                                        // Play button
        Button pause = findViewById(R.id.pause);                                      // Pause Button
        MediaPlayer mediaplayer = MediaPlayer.create(this, R.raw.audio);       // Media Player for starting and pausing the media
        AudioManager audiomanager = (AudioManager) getSystemService(AUDIO_SERVICE);   // Audio manager for adjusting the volume
        SeekBar vol = findViewById(R.id.volume);   // Seekbar for controlling the volume
        int maxVol = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);      // To get the max Volume of Device
        vol.setMax(maxVol); // Setting device max volume to seekbar max volume
        // The below logic is to read the current volume level in the device and display in the seekbar
        int currVol = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);
        vol.setProgress(currVol);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.pause();
            }
        });

        vol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}