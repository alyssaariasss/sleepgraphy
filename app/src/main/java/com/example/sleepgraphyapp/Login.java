package com.example.sleepgraphyapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText email_txt, pass_txt;
    private Button button_login;
    private TextView button_frgtpass;
    private ImageView button_back;
    private ProgressBar progressBar;

    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        email_txt = findViewById(R.id.email_input);
        pass_txt = findViewById(R.id.pass_input);
        button_login = findViewById(R.id.login_button);
        button_frgtpass = findViewById(R.id.frgtpass_button);
        button_back = findViewById(R.id.back_button);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = email_txt.getText().toString().trim();
                password = pass_txt.getText().toString().trim();

                if (email.isEmpty()) {
                    email_txt.setError("Email address is required.");
                    email_txt.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pass_txt.setError("Password is required.");
                    pass_txt.requestFocus();
                    return;
                }

                //    progressbar VISIBLE
                progressBar.setVisibility(View.VISIBLE);

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //    progressbar GONE
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this, Homepage.class));
                            finish();
                        } else {

                            //    progressbar GONE
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }

        });

        //        handle forgot button
        button_frgtpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPassPage.class);
                startActivity(intent);
            }
        });


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, LoginActivity.class));
            }
        });
    }
}