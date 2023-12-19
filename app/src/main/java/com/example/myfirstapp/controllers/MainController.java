package com.example.myfirstapp.controllers;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.PostActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;
import com.squareup.picasso.Picasso;

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
    public void displayPopUpDialog() {
        final Dialog dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom_dialog_menu);
        ImageView postBtn = dialog.findViewById(R.id.F_postBtn);
        ImageView rateBtn = dialog.findViewById(R.id.F_rateBtn);
        ImageView cancelButton = dialog.findViewById(R.id.F_exitBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(mainActivity, PostActivity.class);
                mainActivity.startActivity(i);
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(mainActivity, EditPlanActivity.class);
                mainActivity.startActivity(i);
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

    public void displayEditPlanActivity(MainActivity mainActivity, Intent intent) {
        Intent i = new Intent(mainActivity, EditPlanActivity.class).putExtra("userInfo", intent.getStringArrayExtra("datum"));
        mainActivity.startActivity(i);
        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayUserSettingsActivity(MainActivity mainActivity, Intent intent) {
        Intent i = new Intent(mainActivity, UserProfileActivity.class).putExtra("userInfo", intent.getStringArrayExtra("datum"));
        mainActivity.startActivity(i);
        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void preloadImages(String imageUrl, ImageButton imageBtn1) {
        Picasso.get().load(imageUrl).into(imageBtn1);
    }
}
