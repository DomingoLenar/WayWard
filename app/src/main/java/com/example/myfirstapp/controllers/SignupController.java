package com.example.myfirstapp.controllers;

import com.example.myfirstapp.models.ContactDetails;
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.views.SignupActivity;

public class SignupController {

    SignupActivity signupActivity;
    User userModel;
    ContactDetails contactDetails;
    public SignupController(SignupActivity signupActivity) {
        this.signupActivity = signupActivity;
        userModel = new User(this);
        contactDetails = new ContactDetails(this);
    }

    public void submitAccountDetails(String email, String username, String password) {
        userModel = new User(username, password, false);
        userModel.insertCurrentUser(); // store new user in database

        contactDetails = new ContactDetails(username, email);
        contactDetails.insertCurrentCD(); // store contact details of new user in database
    }

    public void displayMainActivity(SignupActivity signupActivity) { // display Home page

//        Intent i = new Intent(signupActivity, MainActivity.class);
//        signupActivity.startActivity(i);

    }
}
