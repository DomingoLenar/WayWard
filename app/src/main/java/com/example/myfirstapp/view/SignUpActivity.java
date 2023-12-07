package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextView signUpTitle = findViewById(R.id.signUpTitle);
        TextView signUpDescription = findViewById(R.id.signUpDescription);
        TextView signUpNA = findViewById(R.id.signUpNA);
        TextView signUpEmailAddressTitle = findViewById(R.id.signUpEmailAddressTitle);
        TextView signUpUsernameTitle = findViewById(R.id.signUpUsernameTitle);

        EditText signUpPassword = findViewById(R.id.signUpPassword);
        EditText signUpEmailAddress = findViewById(R.id.signUpEmailAddress);
        EditText signUpUsername = findViewById(R.id.signUpUsername);

        Button signInButton = findViewById(R.id.signUpButton);

        ImageView signUpLogo = findViewById(R.id.signUpLogo);
        ImageView signUpAvatar = findViewById(R.id.signUpAvatar);
        ImageView signUpPasswordLogo = findViewById(R.id.signUpPasswordLogo);
        ImageView signUpEmailAddressLogo = findViewById(R.id.signUpEmailAddressLogo);
        ImageView signUpUsernameLogo = findViewById(R.id.signUpUsernameLogo);
    }
}
