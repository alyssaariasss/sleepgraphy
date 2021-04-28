package com.example.sleepgraphyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class ForgotPassPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_pass_page)

        val back_button = findViewById<ImageView>(R.id.back)
        back_button.setOnClickListener{
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }
}