package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.LoginActivity;
import com.example.myfirstapp.views.SigninActivity;
import com.example.myfirstapp.views.SignupActivity;

public class LoginController {
    LoginActivity loginActivity;

    public LoginController(LoginActivity loginActivity) {
        this.loginActivity = loginActivity;
    }

    public void displaySignInActivity(LoginActivity loginActivity){ // display Sign In Activity

        Intent i = new Intent(loginActivity, EditPlanActivity.class);
        loginActivity.startActivity(i);

//        Intent i = new Intent(loginActivity, SigninActivity.class);
//        loginActivity.startActivity(i);
    }

    public void displaySignUpActivity(LoginActivity loginActivity) { // display Sign Up Activity
        Intent i = new Intent(loginActivity, SignupActivity.class);
        loginActivity.startActivity(i);
    }
}
