package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.provider.MediaStore;

import com.example.myfirstapp.views.EditPlanActivity;

public class EditPlanController {

    EditPlanActivity editPlanActivity;

    public EditPlanController (EditPlanActivity editPlanActivity) {
        this.editPlanActivity = editPlanActivity;
    }

    public void uploadImage (int GALLERY_REQ_CODE) {
        Intent iImage1 = new Intent(Intent.ACTION_PICK);
        iImage1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        editPlanActivity.startActivityForResult(iImage1, GALLERY_REQ_CODE);
    }
}
