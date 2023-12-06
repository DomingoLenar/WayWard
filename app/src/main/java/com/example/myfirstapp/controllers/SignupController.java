package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.views.SignupActivity;

public class SignupController {

    SignupActivity signupActivity;
    public SignupController(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
    }

    public void accountDetails(String email, String username, String password) {
        // transfer to model
    }

    public void displayMainActivity(SignupActivity signupActivity) { // display Home page

        Intent i = new Intent(signupActivity, MainActivity.class);
        signupActivity.startActivity(i);

    }
}
