package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.example.myfirstapp.models.User;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SigninActivity;

public class SigninController {

    SigninActivity signinActivity;
    User userModel;


    public SigninController(SigninActivity signinActivity) {
        this.signinActivity = signinActivity;
        userModel = new User(this);
    }


    public void displayMainActivity(SigninActivity signinActivity) {

        Intent i = new Intent(signinActivity, MainActivity.class);
        signinActivity.startActivity(i);

    }

    public void submitAccountDetails(String username, String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userModel = new User(username, password, false);
                authorize(userModel.authenticate());
            }
        });

    }

    public void authorize(boolean permit) {
        if (!permit) {
            Toast.makeText(signinActivity.getApplicationContext(), "Wrong email and password", Toast.LENGTH_SHORT).show();
        } else {
            displayMainActivity(signinActivity);
        }
    }
}
