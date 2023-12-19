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
import com.example.myfirstapp.models.User;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.LoginActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.PostActivity;
import com.example.myfirstapp.views.ProfileSettingsActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

public class UserProfileSettingsController {
    UserProfileActivity userProfileActivity;
    User userModel;
    public UserProfileSettingsController(UserProfileActivity userProfileActivity) {
        this.userProfileActivity= userProfileActivity;
        userModel = new User(this);
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
    public void displayPopUpDialog() {
        final Dialog dialog = new Dialog(userProfileActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom_dialog_menu);
        ImageView postBtn = dialog.findViewById(R.id.F_postBtn);
        ImageView rateBtn = dialog.findViewById(R.id.F_rateBtn);
        ImageView cancelButton = dialog.findViewById(R.id.F_exitBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(userProfileActivity, PostActivity.class);
                userProfileActivity.startActivity(i);
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(userProfileActivity, EditPlanActivity.class);
                userProfileActivity.startActivity(i);
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

    public void displayEditPlanActivity() {
        Intent i = new Intent(userProfileActivity, EditPlanActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity() {
        return;
    }

    public void displayProfileSettingsActivity(Intent intent) {
        Intent i = new Intent(userProfileActivity, ProfileSettingsActivity.class).putExtra("userInfo", intent.getStringArrayExtra("userInfo"));
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayProfileSettingsActivity() {
        Intent i = new Intent(userProfileActivity, ProfileSettingsActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in, R.anim.slide_in);
    }

    public void logoutBtnClicked() {
        boolean valid = userModel.logoutUser();

        if (valid){
            System.out.println("Success!");
            displayLoginActivity();
        } else {
            System.out.println("Failed");
        }
    }

    private void displayLoginActivity() {
        Intent i = new Intent(userProfileActivity, LoginActivity.class);
        userProfileActivity.startActivity(i);
        userProfileActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


}
