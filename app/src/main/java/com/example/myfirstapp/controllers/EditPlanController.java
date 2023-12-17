package com.example.myfirstapp.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.TravelPlan;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.OldEditPlanActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EditPlanController {
    public String imageType;
    public String imagePath;
    EditPlanActivity editPlanActivity;
//    OldEditPlanActivity oldEditPlanActivity;
    TravelPlan travelPlan;

//    public EditPlanController(ScrollingActivity scrollingActivity) {
//        this.scrollingActivity = scrollingActivity;
//    }

    public EditPlanController(EditPlanActivity editPlanActivity) {
        this.editPlanActivity = editPlanActivity;
    }

    public void displayMainActivity() {
        Intent i = new Intent(editPlanActivity, MainActivity.class);
        editPlanActivity.startActivity(i);
        editPlanActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displaySearchActivity() {
        Intent i = new Intent(editPlanActivity, SearchActivity.class);
        editPlanActivity.startActivity(i);
        editPlanActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    public void displayPopUpActivity() {
//        Intent i = new Intent(mainActivity, PopUpFragment.class);
//        mainActivity.startActivity(i);
//        mainActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void displayEditPlanActivity() {
       return;
    }
    public void displayUserSettingsActivity() {
        Intent i = new Intent(editPlanActivity, UserProfileActivity.class);
        editPlanActivity.startActivity(i);
        editPlanActivity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    public void loadImage(int GALLERY_REQ_CODE) {
        Intent iImage1 = new Intent(Intent.ACTION_PICK);
        iImage1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        editPlanActivity.startActivityForResult(iImage1, GALLERY_REQ_CODE);
//        oldEditPlanActivity.startActivityForResult(iImage1, GALLERY_REQ_CODE);
    }

//    public String imagePath(Bitmap bitmap) {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        if (bitmap != null) {
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // convert image to ONLY .jpeg
//            byte[] bytes = byteArrayOutputStream.toByteArray(); // store in bytes array
//            base64Image = Base64.encodeToString(bytes, Base64.DEFAULT); // string version of image
//
//            //fileUpload.uploadFile(file, base64Image, continuation); // upload file to supabase storage
//
//        } else {
//            Toast.makeText(editPlanActivity.getApplicationContext(), "Select the image first", Toast.LENGTH_SHORT).show();
//        }
//        return null;
//    }

    public void uploadToDB(TravelPlan plan, String filePath){
        DataBase db = new DataBase();
        String post_id = ""+plan.getPost_id();
        String remotePath = "images/travel_plan/"+post_id+"/"+post_id+"_"+imageType+".jpg";
        db.uploadImage(filePath,remotePath);
    }

    public void submitTravelPlanDetails(String title, ArrayList<Integer> reviews, String username, String duration, String estimated_cost,String description, String destinations) {
        travelPlan = new TravelPlan(title, reviews, username, duration, estimated_cost, description, destinations);
        travelPlan.insertTravelPlan();

        uploadToDB(travelPlan, imagePath);

    }

    public void saveImagePath(Context applicationContext, Bitmap bitmap) {
        String imageFileName = "IMG_" + 1 + ".jpg";

        File storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageFileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            imagePath = imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        validator(imagePath);

    }

    private void validator(String imagePath) {

        if (imagePath != null) {
            System.out.println("Success");
        } else {
            System.out.println("Failed");
        }
    }
}
