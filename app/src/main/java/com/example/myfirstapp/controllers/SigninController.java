package com.example.myfirstapp.controllers;

import android.content.Intent;

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
//        if (){
//            // notify user
//        } else {
//        }
        displayMainActivity(signinActivity); // temporary...

        userModel = new User(username, password, false);
        userModel.authenticate();


    }

    public void authorize(boolean permit) {
        if (!permit) {
            // notify user that username || password incorrect
        } else {
            displayMainActivity(signinActivity);
        }
    }
}
