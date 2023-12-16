package com.example.myfirstapp.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.TravelPlan;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.PostActivity;
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
    PostActivity postActivity;
    TravelPlan travelPlan;

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
    public void displayPopUpDialog() {
        final Dialog dialog = new Dialog(editPlanActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_bottom_dialog_menu);
        ImageView postBtn = dialog.findViewById(R.id.F_postBtn);
        ImageView rateBtn = dialog.findViewById(R.id.F_rateBtn);
        ImageView cancelButton = dialog.findViewById(R.id.F_exitBtn);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(editPlanActivity, PostActivity.class);
                editPlanActivity.startActivity(i);
            }
        });

        rateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent i = new Intent(editPlanActivity, EditPlanActivity.class);
                editPlanActivity.startActivity(i);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataBase db = new DataBase();
                String post_id = ""+plan.getPost_id();
                String remotePath = "images/travel_plan/"+post_id+"/"+post_id+"_"+imageType+".jpg";
                db.uploadImage(filePath,remotePath);
            }
        });
        
    }

    public void submitTravelPlanDetails(String title, ArrayList<Integer> reviews, String username, String duration, String estimated_cost,String description, String destinations) {
        travelPlan = new TravelPlan(title, reviews, username, duration, estimated_cost, description, destinations);
//        travelPlan.insertTravelPlan();

//        uploadToDB(travelPlan, imagePath);

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
