package com.example.myfirstapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.UserProfileSettingsController;

public class UserProfileActivity extends AppCompatActivity {

    private TextView userProfileLastName, userProfileFirstName;
    UserProfileSettingsController userProfileSettingsController;
    Intent pIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        pIntent = getIntent();
        userProfileSettingsController = new UserProfileSettingsController(this);
        initViews();

    }
    private void initViews() {
        userProfileLastName = findViewById(R.id.userProfileLastName);
        userProfileFirstName = findViewById(R.id.userProfileFirstName);

        try {
            if (pIntent != null) {
                String[] userInfo = pIntent.getStringArrayExtra("userInfo");
                assert userInfo != null;
                userProfileFirstName.setText(userInfo[0]);
                userProfileLastName.setText(userInfo[1]);
            }
        } catch (AssertionError e) {
            Log.e("UserProfileActivity" , "Empty user object", e);
        }

    }
    public void U_homeBtn(View view) {
        userProfileSettingsController.displayMainActivity(pIntent);
    }
    public void U_searchBtn(View view) {
        userProfileSettingsController.displaySearchActivity(pIntent);
    }
    public void U_popUpBtn(View view) {
        userProfileSettingsController.displayPopUpDialog();
    }
    public void U_editPlanBtn(View view) {
        userProfileSettingsController.displayEditPlanActivity(pIntent);
    }
    public void U_userSettingsBtn(View view){
        userProfileSettingsController.displayUserSettingsActivity();
    }
    public void U_profileSettingsBtn(View view) {
        userProfileSettingsController.displayProfileSettingsActivity(pIntent);
    }
//    public void profileSettingsBtn(View view){
//    }
    public void sign_out(View view) {
        userProfileSettingsController.logoutBtnClicked();
    }
}