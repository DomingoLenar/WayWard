package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SigninController;
import com.example.myfirstapp.modelsV2.DataBaseAPI;

import retrofit2.Retrofit;

public class SigninActivity extends AppCompatActivity {
    SigninController signinController;
    private EditText usernameField, passwordField;
    private TextView usernameLabel, passwordLabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        signinController = new SigninController(this);

        initViews();

    }

    private void initViews() {

        usernameField = findViewById(R.id.SI_usernameField);
        passwordField = findViewById(R.id.SI_passwordField);

        usernameLabel = findViewById(R.id.SI_usernameLabel);
        passwordLabel = findViewById(R.id.SI_passwordLabel);

    }
    public void SI_signIn(View view) {
        com.example.myfirstapp.modelsV2.User userModel = new com.example.myfirstapp.modelsV2.User(usernameField.getText().toString(), passwordField.getText().toString());
        DataBaseAPI dbAPI = new DataBaseAPI();
        Retrofit retrofit = dbAPI.getClient();
        DataBaseAPI.UserCallback userCallback = new DataBaseAPI.UserCallback() {
            @Override
            public void onUserReceived(com.example.myfirstapp.modelsV2.User user) {
//                if (user.getUsername().equals(userModel.getUsername()) &&
//                        user.getPassword().equals(userModel.getPassword())) {
//                    Toast.makeText(signinActivity.getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
//                    displayMainActivity(signinActivity);
//                }
            }
            @Override
            public void onError(String errorMessage) {

            }
        };
        dbAPI.getUser(userModel, retrofit, userCallback);


//        signinController.submitAccountDetails(usernameField.getText().toString(), passwordField.getText().toString());
    }
}
