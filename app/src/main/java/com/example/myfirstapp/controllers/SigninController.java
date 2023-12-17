package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.example.myfirstapp.models.MyDatabase;
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SigninActivity;

import retrofit2.Retrofit;

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
        com.example.myfirstapp.modelsV2.User userModel = new com.example.myfirstapp.modelsV2.User(username, password);





//        MyDatabase db = new MyDatabase(signinActivity.getApplicationContext());
//        boolean credentials = db.checkUsernamePassword(userModel);
//
//        if (credentials) {
//            Toast.makeText(signinActivity.getApplicationContext(), "Login Success!", Toast.LENGTH_SHORT).show();
//            displayMainActivity(signinActivity);
//        } else {
//            Toast.makeText(signinActivity.getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
//        }
    }
    public void authorize(boolean permit) {
        if (!permit) {
            Toast.makeText(signinActivity.getApplicationContext(), "Wrong email and password", Toast.LENGTH_SHORT).show();
        } else {
            displayMainActivity(signinActivity);
        }
    }
}
