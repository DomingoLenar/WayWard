package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.models.ContactDetails;
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SignupActivity;

public class SignupController {

    SignupActivity signupActivity;
    User userModel;
    ContactDetails contactDetails;
    public SignupController(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
        userModel = new User(this);
//        contactDetails = new ContactDetails(this);
    }

    public void submitAccountDetails(String email, String username, String password) {
        userModel = new User(username, password, false);
        userModel.insertCurrentUser(); // store new user in database

//        contactDetails = new ContactDetails(username, email);
        contactDetails.insertCurrentCD(); // store contact details of new user in database
    }

    public void displayMainActivity(SignupActivity signupActivity) { // display Home page

        Intent i = new Intent(signupActivity, MainActivity.class);
        signupActivity.startActivity(i);

    }

    public void submitAccountDetails(String email, String username, String password, String fName, String lName, String phoneNo) {

        if (email.equals("") || username.equals("") || password.equals("") || fName.equals("") || lName.equals("") || phoneNo.equals("")) {
            // notify user; display a view in Signupactivity
        } else {
            displayMainActivity(signupActivity); // temporary...

            userModel = new User(username, password, false, fName, null, lName);
            userModel.isUsernameValid(); // *returns boolean??

            contactDetails = new ContactDetails(username, email, phoneNo, null);
            contactDetails.insertCurrentCD(); // *returns boolean?
        }

    }

    public void usernameAvailability(boolean exist) {
        if (!exist) {
            // notify user
        } else {
            userModel.insertCurrentUser();
        }
    }
}
