package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.R;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

public class MainController {
    MainActivity mainActivity;
    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void displayMainActivity(MainActivity mainActivity) {
        return;

    }
    public void displaySearchActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, SearchActivity.class);
        mainActivity.startActivity(i);
        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayPopUpActivity(MainActivity mainActivity) {
//        Intent i = new Intent(mainActivity, PopUpFragment.class);
//        mainActivity.startActivity(i);
//        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void displayEditPlanActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, EditPlanActivity.class);
        mainActivity.startActivity(i);
        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity(MainActivity mainActivity) {
        Intent i = new Intent(mainActivity, UserProfileActivity.class);
        mainActivity.startActivity(i);
        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

}
