package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
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


        TextView userProfileDescription = findViewById(R.id.userProfileDescription);

        ImageView userProfileAvatar = findViewById(R.id.userProfileAvatar);

        Button userProfilePreviousTravels = findViewById(R.id.userProfilePreviousTravels);
        Button userProfileLocationServices = findViewById(R.id.userProfileLocationServices);
        Button userProfileProfileSettings = findViewById(R.id.userProfileProfileSettings);
        Button userProfilePrivacy = findViewById(R.id.userProfilePrivacy);
        Button userProfileLogout = findViewById(R.id.userProfileLogout);

        ImageButton userProfileHomeButton = findViewById(R.id.userProfileHomeButton);
        ImageButton userProfileSearchButton = findViewById(R.id.userProfileSearchButton);
        ImageButton userProfileMainButton = findViewById(R.id.userProfileMainButton);
        ImageButton userProfileEditButton = findViewById(R.id.userProfileEditButton);
        ImageButton userProfileUPButton = findViewById(R.id.userProfileUPButton);

        CardView userProfileBottomBarOrange = findViewById(R.id.userProfileBottomBarOrange);
        CardView userProfileBottomBarDarkCream = findViewById(R.id.userProfileBottomBarDarkCream);

        LinearLayout userProfileBottomBarContents = findViewById(R.id.userProfileBottomBarContents);
    }
    private void initViews() {
        userProfileLastName = findViewById(R.id.userProfileLastName);
        userProfileFirstName = findViewById(R.id.userProfileFirstName);
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