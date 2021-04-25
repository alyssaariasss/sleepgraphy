package com.example.sleepgraphyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        auth = FirebaseAuth.getInstance()

        val frgtpass_button = findViewById<TextView>(R.id.frgtpass_button)
        frgtpass_button.setOnClickListener{
            val intent = Intent(this, ForgotPassPage::class.java)
            startActivity(intent)
        }

        val back_button = findViewById<ImageView>(R.id.back)
        back_button.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload(currentUser)
        }
    }

    fun reload(currentUser: FirebaseUser?){

    }
}