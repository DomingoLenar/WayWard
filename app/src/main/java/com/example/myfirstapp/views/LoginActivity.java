package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {

    LoginController loginController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginController = new LoginController(this);

        initViews();


    }

    private void initViews() {

    }

    /**
     * - Launch Sign In Activity
     * @param view
     */

    public void signIn(View view) {
        loginController.displaySignInActivity(this);
    }

    /**
     * - Launch Sign Up Activity
     * @param view
     */
    public void signUp(View view) {
        loginController.displaySignUpActivity(this);
    }
}