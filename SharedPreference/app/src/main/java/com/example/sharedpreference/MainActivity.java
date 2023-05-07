package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Shared preference is used to store small amount of data
        /* SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreference", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("Username" , "Shashank").apply();     //Key is username, data is Shashank
        String username = sharedPreferences.getString("Username" , "");  // Key value and default value
        Log.i("This is username : " , username);   */

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreference", Context.MODE_PRIVATE);
        Set<String> friends = new HashSet<>();
        friends.add("Shashank");
        friends.add("Vinank");
        friends.add("Priyank");

        sharedPreferences.edit().putStringSet("friends", friends).apply();
        Set<String> newFriends = sharedPreferences.getStringSet("friends" , new HashSet<String>());
        Log.i("This is set of friends : ", newFriends.toString());


    }
}