package com.example.kotlinfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.firebase.database.*

class HomepageActivity : AppCompatActivity() {

    private lateinit var mRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val listView = findViewById<ListView>(R.id.list)
        val listItems = mutableListOf<String>()

        mRef = FirebaseDatabase.getInstance().getReference("users")

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems)

        mRef.addValueEventListener(object  : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                for (postSnapshot : DataSnapshot in snapshot.children){
                    listItems.add(postSnapshot.value.toString())
                    arrayAdapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
            listView.adapter = arrayAdapter
    }
}