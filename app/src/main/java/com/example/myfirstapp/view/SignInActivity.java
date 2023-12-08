package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        TextView signInTitle = findViewById(R.id.signInTitle);
        TextView signInDescription = findViewById(R.id.signInDescription);
        TextView signInWB = findViewById(R.id.signInWB);
        TextView signInPasswordTitle = findViewById(R.id.signInPasswordTitle);

        EditText signInPassword = findViewById(R.id.signInPassword);
        EditText signInUsername = findViewById(R.id.signInUsername);

        Button signInButton = findViewById(R.id.signInButton);

        ImageView signInLogo = findViewById(R.id.signInLogo);
        ImageView signInAvatar = findViewById(R.id.signInAvatar);
        ImageView signInPasswordLogo = findViewById(R.id.signInPasswordLogo);
        ImageView signInUsernameLogo = findViewById(R.id.signInUsernameLogo);

        CardView signInWelcomeBack = findViewById(R.id.signInWelcomeBack);

        LinearLayout signInWelcomeBackContents1 = findViewById(R.id.signInWelcomeBackContents1);
        LinearLayout signInWelcomeBackContents2 = findViewById(R.id.signInWelcomeBackContents2);
        LinearLayout signInWelcomeBackContents3 = findViewById(R.id.signInWelcomeBackContents3);
        LinearLayout signInWelcomeBackContents4 = findViewById(R.id.signInWelcomeBackContents4);

    }
}
