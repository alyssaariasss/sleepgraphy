package com.example.sleepgraphyapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_page)

        auth = FirebaseAuth.getInstance()

        val back_button = findViewById<ImageView>(R.id.back)
        back_button.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val next_button = findViewById<Button>(R.id.next_button)

        next_button.setOnClickListener {
            userSignUp()
        }
    }

    private fun userSignUp() {

        val nameInput = findViewById<EditText>(R.id.name_input)
        val ageInput = findViewById<EditText>(R.id.age_input)
        val genderInput = findViewById<EditText>(R.id.gender_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val passInput = findViewById<EditText>(R.id.pass_input)

        val name = nameInput.text.toString()
        val age = ageInput.text.toString()
        val gender = genderInput.text.toString()
        val email = emailInput.text.toString()
        val pass = passInput.text.toString()

        if(name.isEmpty()){
            nameInput.error = "Please enter name."
            nameInput.requestFocus()
            return
        }

        if(age.isEmpty()){
            ageInput.error = "Please enter age."
            ageInput.requestFocus()
            return
        }

        if(gender.isEmpty()){
            genderInput.error = "Please enter gender."
            genderInput.requestFocus()
            return
        }

        if (email.isEmpty()) {
            emailInput.error = "Please enter email."
            emailInput.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.error = "Please enter a valid email."
            emailInput.requestFocus()
            return
        }

        if (pass.isEmpty()) {
            passInput.error = "Please enter password."
            passInput.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var user = UserData(
                        name, age, gender, email
                    )

                    FirebaseAuth.getInstance().currentUser?.getUid()?.let {
                        FirebaseDatabase.getInstance().getReference("Users")
                            .child(it)
                            .setValue(user).addOnCompleteListener(this) { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        baseContext, "Successfully registered.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    }

                    startActivity(Intent(this, LoginPage::class.java))
                    finish()

                }

                else {
                    Toast.makeText(
                        baseContext, "Sign up failed. Please try again.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}
