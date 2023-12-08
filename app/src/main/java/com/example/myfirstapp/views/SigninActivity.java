package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SigninController;

public class SigninActivity extends AppCompatActivity {

    SigninController signinController;
    private EditText signInUser, signInPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signinController = new SigninController(this);

        initViews();

    }

    private void initViews() {

        TextView signInTitle = findViewById(R.id.signInTitle);
        TextView signInDescription = findViewById(R.id.signInDescription);
        TextView signInWB = findViewById(R.id.signInWB);
        TextView signInPasswordTitle = findViewById(R.id.signInPasswordTitle);

//        signInUser = findViewById(R.id.signInUser);
        signInPassword = findViewById(R.id.signInPassword);

        Button signInButton = findViewById(R.id.signInButton);

        ImageView signInLogo = findViewById(R.id.signInLogo);
        ImageView signInAvatar = findViewById(R.id.signInAvatar);
        ImageView signInPasswordLogo = findViewById(R.id.signInPasswordLogo);

    }
}
