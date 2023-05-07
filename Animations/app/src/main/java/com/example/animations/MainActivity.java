package com.example.animations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int color = 1;  // 0- white , 1 - red , 2 - yellow
    int[] grid = {0,0,0,0,0,0,0,0,0};
    int[][] win = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void dropIn (View view){

        ImageView counter = (ImageView) view ;
        int tapColor = Integer.parseInt(counter.getTag().toString());

            grid[tapColor]= color;
            if (color == 1) {
                counter.setImageResource(R.drawable.red);
                color = 2;
            } else {
                counter.setImageResource(R.drawable.yellow);
                color = 1;
            }
           // counter.setTranslationY(-1500);
           // counter.animate().translationYBy(1500).setDuration(300);
        for (int[] winningPosition : win ){
            if (grid[winningPosition[0]]== grid[winningPosition[1]] && grid[winningPosition[1]]== grid[winningPosition[2]] && grid[winningPosition[0]] !=0 ){
                Toast.makeText(this, "You won", Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}