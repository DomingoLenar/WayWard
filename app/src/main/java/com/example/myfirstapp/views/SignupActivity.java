package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SignupController;

public class SignupActivity extends AppCompatActivity {

    SignupController signUpController;
    private EditText signUpEmailAddress, signUpUsername, signUpPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signUpController = new SignupController(this);

        initViews();

    }

    private void initViews() {
        TextView signUpTitle = findViewById(R.id.signUpTitle);
        TextView signUpDescription = findViewById(R.id.signUpDescription);
        TextView signUpNA = findViewById(R.id.signUpNA);
        TextView signUpEmailAddressTitle = findViewById(R.id.signUpEmailAddressTitle);
        TextView signUpUsernameTitle = findViewById(R.id.signUpUsernameTitle);

        signUpEmailAddress = findViewById(R.id.signUpEmailAddress);
        signUpUsername = findViewById(R.id.signUpUsername);
        signUpPassword = findViewById(R.id.signUpPassword);

        Button signInButton = findViewById(R.id.signUpButton);

        ImageView signUpLogo = findViewById(R.id.signUpLogo);
        ImageView signUpAvatar = findViewById(R.id.signUpAvatar);
        ImageView signUpPasswordLogo = findViewById(R.id.signUpPasswordLogo);
        ImageView signUpEmailAddressLogo = findViewById(R.id.signUpEmailAddressLogo);
        ImageView signUpUsernameLogo = findViewById(R.id.signUpUsernameLogo);
    }

    public void signUpNewUser(View view) {

        if (signUpEmailAddress.getText().toString().equals("") || signUpUsername.getText().toString().equals("")
                || signUpPassword.getText().toString().equals("")) {
            // notify user
        } else {
            // display main activity
            String email = signUpEmailAddress.getText().toString();
            String username = signUpUsername.getText().toString();
            String password = signUpPassword.getText().toString();

            signUpController.accountDetails(email, username, password);
            signUpController.displayMainActivity(this);
        }

    }
}
