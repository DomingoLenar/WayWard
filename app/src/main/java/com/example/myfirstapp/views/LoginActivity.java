package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    LoginController loginController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(this);

        initViews();

    }

    private void initViews() {

        TextView loginTitle = findViewById(R.id.loginTitle);
        TextView loginDescription = findViewById(R.id.loginDescription);

        Button loginSignInButton = findViewById(R.id.loginSignInButton);
        Button loginSignUpButton = findViewById(R.id.loginSignUpButton);

        ImageView loginLogo = findViewById(R.id.loginLogo);

    }

    public void signIn(View view) {
        loginController.displaySignInActivity(this);
    }

    public void signUp(View view) {
        loginController.displaySignUpActivity(this);
    }

}