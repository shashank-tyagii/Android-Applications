package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase myDatabase = this.openOrCreateDatabase("User", MODE_PRIVATE,null);

      //  myDatabase.execSQL("CREATE TABLE IF NOT EXISTS NewUser(Name VARCHAR,age INT(3))");
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS NewUser1(Name VARCHAR,age INT(3), id INTEGER PRIMARY KEY)");

        myDatabase.execSQL("INSERT INTO NewUser1(Name,age) VALUES ('Shashank', 23)");
        myDatabase.execSQL("INSERT INTO NewUser1(Name,age) VALUES ('Vinank', 26)");
        myDatabase.execSQL("INSERT INTO NewUser1(Name,age) VALUES ('Priyank', 25)");
        myDatabase.execSQL("INSERT INTO NewUser1(Name,age) VALUES ('Shashank', 25)");
        myDatabase.execSQL("INSERT INTO NewUser1(Name,age) VALUES ('Amit', 27)");
       //  myDatabase.execSQL("DELETE FROM NewUser WHERE Name = 'Amit' ");
       //  myDatabase.execSQL("DELETE FROM NEWUSER WHERE age >25");
       //  myDatabase.execSQL("DELETE FROM NEWUSER WHERE name LIKE 'P%'");                    // Name starting from P
       //  myDatabase.execSQL("DELETE FROM NEWUSER WHERE name LIKE '%T'");                    // Name ending with T
        // myDatabase.execSQL("DELETE FROM NEWUSER WHERE name LIKE '%y%'");                   // Name HAVING y
        Cursor c = myDatabase.rawQuery("SELECT * FROM NewUser1" , null);

        int nameIndex = c.getColumnIndex("Name");
        int ageIndex = c.getColumnIndex("age");
        int idIndex = c.getColumnIndex("id");

        while(c.moveToNext()){
            Log.i("Name" , c.getString(nameIndex));
            Log.i("age" , c.getString(ageIndex));
            Log.i("age" , c.getString(idIndex));            // It will automatically get the ID
        }

    }
}