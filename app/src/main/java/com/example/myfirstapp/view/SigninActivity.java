package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        TextView signInTitle = findViewById(R.id.signInTitle);
        TextView signInDescription = findViewById(R.id.signInDescription);
        TextView signInWB = findViewById(R.id.signInWB);
        TextView signInPasswordTitle = findViewById(R.id.signInPasswordTitle);

        EditText signInPassword = findViewById(R.id.signInPassword);

        Button signInButton = findViewById(R.id.signInButton);

        ImageView signInLogo = findViewById(R.id.signInLogo);
        ImageView signInAvatar = findViewById(R.id.signInAvatar);
        ImageView signInPasswordLogo = findViewById(R.id.signInPasswordLogo);

    }
}
