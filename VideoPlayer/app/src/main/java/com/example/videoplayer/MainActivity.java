package com.example.videoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView videoview = findViewById(R.id.videoView);
        videoview.setVideoPath("android.resource://" + getPackageName()+ "/" + R.raw.myvideo);

        // Use media player
        MediaController mediacontroller = new MediaController(this);
        mediacontroller.setAnchorView(videoview);
        videoview.setMediaController(mediacontroller);
        videoview.start();
    }
}