package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var mRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = Firebase.auth
        mRef = FirebaseDatabase.getInstance().getReference("users")

        var edtUsername = findViewById<EditText>(R.id.edtUsername)
        var edtPassword = findViewById<EditText>(R.id.edtPassword)
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var btnSignup = findViewById<Button>(R.id.btnSignup)

        btnLogin.setOnClickListener {
            var email : String = edtUsername.text.toString()
            var password : String = edtPassword.text.toString()
            loginUser(email,password)
        }

        btnSignup.setOnClickListener {
            var email : String = edtUsername.text.toString()
            var password : String = edtPassword.text.toString()
            signUpUser(email, password)
        }

    }
    fun loginUser(email : String , password : String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomepageActivity ::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Login Successful" , Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Not Successful" , Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun signUpUser(email : String , password : String){

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, HomepageActivity ::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Sign Up Successful" , Toast.LENGTH_SHORT).show()
                    addUserToDatabase(email)
                } else {
                    Toast.makeText(this, "Not Successful" , Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun addUserToDatabase(email: String){
        mRef.push().setValue(email)
    }
}