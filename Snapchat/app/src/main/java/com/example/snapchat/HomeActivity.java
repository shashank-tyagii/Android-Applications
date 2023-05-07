package com.example.snapchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
        ListView snapListView;
        ArrayList<String> snapsList;
        ArrayList<DataSnapshot> snapShotList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        snapListView = findViewById((R.id.snapList));
        snapsList = new ArrayList<>();
        snapShotList= new ArrayList<>();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,snapsList);
        snapListView.setAdapter(arrayAdapter);

        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("snaps").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapsList.clear();
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    String email = postSnapshot.child("from").getValue().toString();
                    snapsList.add(email);
                    snapShotList.add(postSnapshot);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        snapListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DataSnapshot snapshot = snapShotList.get(i);
                Intent intent = new Intent(HomeActivity.this,snapsDetail.class);
                intent.putExtra("imageURL", snapshot.child("url").getValue().toString());
                intent.putExtra("message", snapshot.child("message").getValue().toString());
                intent.putExtra("key", snapshot.getKey());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {      // Merging menu file on to home activity
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.snapmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  // On clicking item from menu
        if(item.getItemId() == R.id.takesnap){
            Intent intent = new Intent(HomeActivity.this, snapActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.logout){
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}