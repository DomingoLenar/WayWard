package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SigninController;

public class SigninActivity extends AppCompatActivity {

    SigninController signinController;
    private EditText usernameField, passwordField;
    private TextView usernameLabel, passwordLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signinController = new SigninController(this);

        initViews();

    }

    private void initViews() {

        usernameField = findViewById(R.id.SI_usernameField);
        passwordField = findViewById(R.id.SI_passwordField);

        usernameLabel = findViewById(R.id.SI_usernameLabel);
        passwordLabel = findViewById(R.id.SI_passwordLabel);

    }

    public void SI_signIn(View view) {

        if (usernameField.getText().toString().equals("") || passwordField.getText().toString().equals("")){
            // notify user
        } else {
            signinController.submitAccountDetails(usernameField.getText().toString(), passwordField.getText().toString());
        }

    }
}
