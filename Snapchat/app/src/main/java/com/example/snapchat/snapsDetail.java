package com.example.snapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class snapsDetail extends AppCompatActivity {

    ImageView imgSnap; TextView txtMessage;
    String url,message,key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snaps_detail);
        imgSnap = findViewById(R.id.imgSnap);
        txtMessage = findViewById(R.id.txtmessage);

        Intent intent = getIntent();
        url = intent.getExtras().getString("imageURL");
        message = intent.getExtras().getString("message");
        key = intent.getStringExtra("key");
        txtMessage.setText(message);
        Picasso.get().load(url).into(imgSnap);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("snaps").child(key).removeValue();
    }
}