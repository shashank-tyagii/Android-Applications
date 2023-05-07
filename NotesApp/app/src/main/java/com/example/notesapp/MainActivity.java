package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    ListView listView ;
    static ArrayList <String> ArrayString = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creating menu
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Code for activities after some item is clicked from menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);    // remove return option from this default

        switch(item.getItemId()){
            case R.id.addNote:
                Intent intent = new Intent(MainActivity.this,secondActivity.class);
                startActivity(intent);
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
        listView = findViewById(R.id.listView);

        // Code to initialise shared pereferences
        sharedPreferences = MainActivity.this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);

        //Code to get the previous values from memory to list
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",new HashSet<String>());
        ArrayString = new ArrayList<>(set);

        // Adapter between Arraylist and listView
        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ArrayString);
        listView.setAdapter(arrayadapter);

        // Long press on any item in the list
       listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
               new AlertDialog.Builder(MainActivity.this)
                       .setIcon(R.drawable.ic_launcher_foreground)
                       .setTitle("Delete Item")
                       .setMessage("Do you want to delete this item ? ")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int n) {
                               ArrayString.remove(i);
                              arrayadapter.notifyDataSetChanged();
                               HashSet<String> set = new  HashSet<>(MainActivity.ArrayString);  // Update the Hashset
                               sharedPreferences.edit().putStringSet("notes" , set).apply(); // Put Hashset into shared preferences memory
                           }
                       }).setNegativeButton("No", null)
                       .show();
               return true;
           }
       });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {     // i= position

               Intent intent = new Intent(MainActivity.this,secondActivity.class);
               intent.putExtra("notesID",i);
               startActivity(intent);

           }
       });

   }
}