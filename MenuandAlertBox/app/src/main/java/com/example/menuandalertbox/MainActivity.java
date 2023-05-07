package com.example.menuandalertbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creating menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Code for activities after some item is clicked from menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);    // remove return option from this default

        switch(item.getItemId()){
            case R.id.setting:
                 Toast.makeText(this, "Setting is clicked", Toast.LENGTH_SHORT).show();
                 return true;
            case R.id.help:
                Toast.makeText(this, "Help is clicked", Toast.LENGTH_SHORT).show();
                return true;
            default :
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code for dialog box
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_background)
                .setTitle("Choose an option")
                .setMessage("Are you sure, you want to do this?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("No", null)
                .show();

    }
}