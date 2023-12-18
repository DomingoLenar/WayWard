package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.UserProfileSettingsController;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userProfileLastName, userProfileFirstName;
    UserProfileSettingsController userProfileSettingsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        userProfileSettingsController = new UserProfileSettingsController(this);
        initViews();

    }
    private void initViews() {
        userProfileLastName = findViewById(R.id.userProfileLastName);
        userProfileFirstName = findViewById(R.id.userProfileFirstName);

        try {
            Intent intent = getIntent();
            if (intent != null) {
                String[] userInfo = intent.getStringArrayExtra("userInfo");
                assert userInfo != null;
                userProfileFirstName.setText(userInfo[0]);
                userProfileLastName.setText(userInfo[1]);
            }
        } catch (AssertionError e) {
            Log.e("UserProfileActivity" , "Empty user object", e);
        }

    }
    public void homeBtn(View view) {
        userProfileSettingsController.displayMainActivity();
    }
    public void searchBtn(View view) {
        userProfileSettingsController.displaySearchActivity();
    }
    public void popUpBtn(View view) {
        userProfileSettingsController.displayPopUpDialog();
    }
    public void editPlanBtn(View view) {
        userProfileSettingsController.displayEditPlanActivity();
    }
    public void userSettingsBtn(View view) {
        userProfileSettingsController.displayUserSettingsActivity();
    }
    public void sign_out(View view) {
        userProfileSettingsController.logoutBtnClicked();
    }
}