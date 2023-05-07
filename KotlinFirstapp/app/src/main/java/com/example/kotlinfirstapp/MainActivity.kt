package com.example.kotlinfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textview = findViewById<TextView>(R.id.txt)
        textview.text = "0"

        var btn1 = findViewById<Button>(R.id.btn1)  //Increase
        var btn2 = findViewById<Button>(R.id.btn2)  //Reset

        var count : Int = 0;
        btn1.setOnClickListener {
            count++
            textview.text = count.toString()
        }
        btn2.setOnClickListener {
            count = 0
            textview.text = count.toString()
        }
    }
}