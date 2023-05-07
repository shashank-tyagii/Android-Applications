package com.example.snapchat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.net.URI;
import java.util.UUID;

public class snapActivity extends AppCompatActivity {

    Button btnOpenGallery, btnChooseUser;
    ImageView snapImage;
    EditText edtmessage;
    public  static final int PICK_IMAGE = 1;
    Uri imageUri;
    StorageReference mstorageRef;
    UUID imageName = UUID.randomUUID();    //tO GENERATE RANDOM NAME TO IMAGE
    StorageTask mUploadTask;
    String downloadURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);
        btnOpenGallery = findViewById(R.id.btnOpenGallery);
        btnChooseUser = findViewById(R.id.btnSelectUser);
        edtmessage = findViewById(R.id.edtMessage);
        snapImage= findViewById(R.id.snapImage);


        btnOpenGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        btnChooseUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(snapActivity.this, chooseUser.class);
                intent.putExtra("imageName" , imageName.toString());
                intent.putExtra("imageURL" , downloadURL);
                intent.putExtra("message" , edtmessage.getText().toString());
                startActivity(intent);
            }
        });

    }

    public void openGallery(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {   // To set the selected Image
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData()!=null){
            imageUri = data.getData();
            snapImage.setImageURI(imageUri);
            uploadImage();
        }
    }
    private void uploadImage(){
        if(imageUri !=null){
            mstorageRef = FirebaseStorage.getInstance().getReference().child("Images").child(imageName + ".jpg"); // Create an image folder
            mUploadTask= mstorageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(snapActivity.this, "Upload successful", Toast.LENGTH_SHORT).show();
                    downloadURL = taskSnapshot.getStorage().getDownloadUrl().toString();
                    mstorageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            Log.i("shashank", downloadURL);
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(snapActivity.this, "Upload failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}