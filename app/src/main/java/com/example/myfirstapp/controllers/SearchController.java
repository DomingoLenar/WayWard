package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.R;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

public class SearchController {

    SearchActivity searchActivity;

    public SearchController(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
    }

    public void displayMainActivity() {
        Intent i = new Intent(searchActivity, MainActivity.class);
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displaySearchActivity() {
        return;
    }
    public void displayPopUpActivity() {
//        Intent i = new Intent(mainActivity, PopUpFragment.class);
//        mainActivity.startActivity(i);
//        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void displayEditPlanActivity() {
        Intent i = new Intent(searchActivity, EditPlanActivity.class);
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity() {
        Intent i = new Intent(searchActivity, UserProfileActivity.class);
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
