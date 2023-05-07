package com.example.delay;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Method 1 :
        Handler handler = new Handler();
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("message", "A second passed");
                handler.postDelayed(this,1000);
            }
        };

        // Method 2
          new CountDownTimer(10000, 1000){
              public void onTick(long miliSecUntilDone){
                  Log.i("Time left :", String.valueOf(miliSecUntilDone/1000));
              }
              public void onFinish(){
                  Log.i("message", "We are done !");
              }
          }
    }
}