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

        if (username.equals("") || password.equals("")){
            Toast.makeText(signinActivity.getApplicationContext(), "Please enter the required input field", Toast.LENGTH_SHORT).show();
        } else {
            com.example.myfirstapp.modelsV2.User userModel = new com.example.myfirstapp.modelsV2.User(username, password);
            DataBaseAPI dbAPI = new DataBaseAPI();
            Retrofit retrofit = dbAPI.getClient();
            DataBaseAPI.UserCallback userCallback = new DataBaseAPI.UserCallback() {
                @Override
                public void onUserReceived(com.example.myfirstapp.modelsV2.User user) {
                    if (user.getUsername().equals(userModel.getUsername()) &&
                            user.getPassword().equals(userModel.getPassword())) {
                        Toast.makeText(signinActivity.getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
                        displayMainActivity(signinActivity);
                    }
                }
                @Override
                public void onError(String errorMessage) {

                }
            };
            dbAPI.getUser(userModel, retrofit, userCallback);
        }




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
