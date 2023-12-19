package com.example.myfirstapp.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.PostActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

public class SearchController {

    SearchActivity searchActivity;
    public SearchController(SearchActivity searchActivity) {
        this.searchActivity = searchActivity;
    }
    public void displayMainActivity(Intent pIntent) {
        Intent i = new Intent(searchActivity, MainActivity.class).putExtra("userInfo", pIntent.getStringArrayExtra("userInfo"));
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displaySearchActivity() {
        return;
    }
    public void displayPopUpDialog() {
        final Dialog dialog = new Dialog(searchActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom_dialog_menu);
        ImageView postBtn = dialog.findViewById(R.id.F_postBtn);
        ImageView rateBtn = dialog.findViewById(R.id.F_rateBtn);
        ImageView cancelButton = dialog.findViewById(R.id.F_exitBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(searchActivity, PostActivity.class);
                searchActivity.startActivity(i);
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(searchActivity, EditPlanActivity.class);
                searchActivity.startActivity(i);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void displayEditPlanActivity(Intent pIntent) {
        Intent i = new Intent(searchActivity, EditPlanActivity.class).putExtra("userInfo", pIntent.getStringArrayExtra("userInfo"));
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity(Intent pIntent) {
        Intent i = new Intent(searchActivity, UserProfileActivity.class).putExtra("userInfo", pIntent.getStringArrayExtra("userInfo"));
        searchActivity.startActivity(i);
        searchActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
