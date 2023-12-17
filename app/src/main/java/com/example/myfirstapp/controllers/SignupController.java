package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.widget.Toast;

import com.example.myfirstapp.models.ContactDetails;
import com.example.myfirstapp.models.MyDatabase;
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SignupActivity;

import retrofit2.Retrofit;

public class SignupController {

    SignupActivity signupActivity;
    User userModel;
    ContactDetails contactDetails;
    public SignupController(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
        userModel = new User(this);
    }

//    public void submitAccountDetails(String email, String username, String password) {
//        userModel = new User(username, password, false);
//        userModel.insertCurrentUser();
//
//        contactDetails = new ContactDetails(username, email, null, null);
//        contactDetails.insertCurrentCD();
//    }

    public void displayMainActivity(SignupActivity signupActivity) { // display Home page

        Intent i = new Intent(signupActivity, MainActivity.class);
        signupActivity.startActivity(i);

    }

    public void submitAccountDetails(String email, String username, String password, String fName, String lName, String phoneNo) {

        if (email.equals("") || username.equals("") || password.equals("") || fName.equals("") || lName.equals("") || phoneNo.equals("")) {
            Toast.makeText(signupActivity.getApplicationContext(), "Please enter the required input field", Toast.LENGTH_SHORT).show();
        } else {
            userModel = new User(username, password, false, fName, null, lName);

            DataBaseAPI dbAPI = new DataBaseAPI();
            Retrofit retrofit = dbAPI.getClient();
            com.example.myfirstapp.modelsV2.User newUser = new com.example.myfirstapp.modelsV2.User(username,
                    password, false,
                    fName,
                    "",
                    lName);
            DataBaseAPI.UserCallback userCallback = new DataBaseAPI.UserCallback() {
                @Override
                public void onUserReceived(com.example.myfirstapp.modelsV2.User user) {

                }
                @Override
                public void onError(String errorMessage) {

                }
            };
            dbAPI.insertUser(newUser, retrofit, userCallback);
        }

    }

    private void authorize(boolean valid) {
        if (!valid) {
            Toast.makeText(signupActivity.getApplicationContext(), "Username already exist!", Toast.LENGTH_SHORT).show();
        } else {
            userModel.insertCurrentUser();
            displayMainActivity(signupActivity);
        }
    }
}
