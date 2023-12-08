package com.example.myfirstapp.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapp.R;


public class SignupActivity extends AppCompatActivity {

    private ValueAnimator valueAnimator;
    private EditText usernameField, passwordField, emailField, fnameField, lNameField, phoneNoField;
    private TextView usernameLabel, passwordLabel, emailLabel, fNameLabel, lNameLabel, phoneNoLabel;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();

        valueAnimator = ValueAnimator.ofInt(0, -1000);

        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                        int value = (int) animation.getAnimatedValue();

                        usernameField.setTranslationX(value);
                        passwordField.setTranslationX(value);
                        emailField.setTranslationX(value);
                        usernameLabel.setTranslationX(value);
                        passwordLabel.setTranslationX(value);
                        emailLabel.setTranslationX(value);

                        fnameField.setTranslationX(value);
                        lNameField.setTranslationX(value);
                        phoneNoField.setTranslationX(value);
                        fNameLabel.setTranslationX(value + 30);
                        lNameLabel.setTranslationX(value + 45);
                        phoneNoLabel.setTranslationX(value + 45);
                    }
                }
        );

    }

    public void SU_signUp(View view) {
        valueAnimator.start(); // start animation

        signUpButton.setText(R.string.sign_up);
    }

    private void initViews() {
        findViewById(R.id.SU_newaccLabel);

        signUpButton = findViewById(R.id.SU_signUpButton);
        usernameField = findViewById(R.id.SU_usernameField);
        passwordField = findViewById(R.id.SU_passwordField);
        emailField= findViewById(R.id.SU_emailField);
        fnameField = findViewById(R.id.SU_firstNameField);
        lNameField = findViewById(R.id.SU_lastnameField);
        phoneNoField = findViewById(R.id.SU_phoneField);

        emailLabel = findViewById(R.id.SU_emailLabel);
        passwordLabel = findViewById(R.id.SU_passwordLabel);
        usernameLabel = findViewById(R.id.SU_usernameLabel);
        fNameLabel = findViewById(R.id.SU_firstNameLabel);
        lNameLabel = findViewById(R.id.SU_lastNameLabel);
        phoneNoLabel = findViewById(R.id.SU_phoneLabel);

    }
}