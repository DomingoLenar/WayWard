package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView loginTitle = findViewById(R.id.loginTitle);
        TextView loginDescription = findViewById(R.id.loginDescription);

        Button loginSignInButton = findViewById(R.id.loginSignInButton);
        Button loginSignUpButton = findViewById(R.id.loginSignUpButton);

        ImageView loginLogo = findViewById(R.id.loginLogo);
    }
}