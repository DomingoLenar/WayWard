package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.R;
import com.example.myfirstapp.views.OldEditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

public class UserProfileSettingsController {
    UserProfileActivity userProfileActivity;
    public UserProfileSettingsController(UserProfileActivity userProfileActivity) {
        this.userProfileActivity= userProfileActivity;
    }

    public void displayMainActivity() {
        Intent i = new Intent(userProfileActivity, MainActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displaySearchActivity() {
        Intent i = new Intent(userProfileActivity, SearchActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayPopUpActivity() {
//        Intent i = new Intent(mainActivity, PopUpFragment.class);
//        mainActivity.startActivity(i);
//        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void displayEditPlanActivity() {
        Intent i = new Intent(userProfileActivity, OldEditPlanActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity() {
        return;
    }
}
