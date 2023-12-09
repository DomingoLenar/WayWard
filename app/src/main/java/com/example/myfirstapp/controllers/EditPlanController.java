package com.example.myfirstapp.controllers;

import android.content.Intent;

import com.example.myfirstapp.models.*;
import com.example.myfirstapp.view.EditPlanActivity;
import com.example.myfirstapp.view.MainActivity;
import com.example.myfirstapp.view.UserProfileActivity;

public class EditPlanController {
    EditPlanActivity editPlanActivity;
    User user;
    TravelPlan newPlan = new TravelPlan("Title",null,user.getUsername(),"Set Duration","Set Cost",
            "Set Description","Set Destinations");

    public EditPlanController(User currentUser, EditPlanActivity editPlanActivity){
        this.editPlanActivity = editPlanActivity;
        this.user = currentUser;
    }

    public void postPlan(){

        newPlan.insertTravelPlan();
    }

    public void displayHomePage(EditPlanActivity editPlanActivity){
        editPlanActivity.startActivity(new Intent(editPlanActivity, MainActivity.class));
    }

    public void displayUserPage(EditPlanActivity editPlanActivity){
        editPlanActivity.startActivity(new Intent(editPlanActivity, UserProfileActivity.class));
    }
}
