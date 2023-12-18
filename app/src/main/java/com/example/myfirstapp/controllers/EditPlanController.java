package com.example.myfirstapp.controllers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.modelsV2.TravelPlan;
import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.views.EditPlanActivity;
import com.example.myfirstapp.views.MainActivity;
import com.example.myfirstapp.views.PostActivity;
import com.example.myfirstapp.views.SearchActivity;
import com.example.myfirstapp.views.UserProfileActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Retrofit;

public class EditPlanController {
    public String imageType;
    public File imageFile;
    public String imagePath;
    EditPlanActivity editPlanActivity;
    PostActivity postActivity;
    com.example.myfirstapp.modelsV2.TravelPlan travelPlan;

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
//                Intent i = new Intent(editPlanActivity, EditPlanActivity.class);
//                editPlanActivity.startActivity(i);
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

    /**
     * Method uploads image to Supabase Storage
     * @param plan              object of travelplan which owns the image
     * @param filePath          local path of the image
     * @param ifGalleryIndex    count of what the image is, -1 if it is a thumbnail
     */
    public void uploadToDB(TravelPlan plan, String filePath, int ifGalleryIndex){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseAPI db = new DataBaseAPI();
                int travelPlanID = travelPlan.getId();
                if(ifGalleryIndex != -1){
                    db.uploadImage(filePath, "travel_plan/"+travelPlanID+"/"+travelPlanID+"_thumbnail.png");
                }
                db.uploadImage(filePath, "travel_plan/"+travelPlanID+"/"+travelPlanID+"_gallery_"+ifGalleryIndex+".png");
            }
        }).start();

    }

    public void submitTravelPlanDetails(String title, int[] reviews, String username, String duration, String estimated_cost,String description, String destinations) {
        travelPlan = new com.example.myfirstapp.modelsV2.TravelPlan(title, reviews, duration, estimated_cost, description, destinations, username);

        DataBaseAPI dbAPI = new DataBaseAPI();
        Retrofit retrofit = dbAPI.getClient();

        DataBaseAPI.TravelPlanCallback travelPlanCallback = new DataBaseAPI.TravelPlanCallback() {
            @Override
            public void onReceived(com.example.myfirstapp.modelsV2.TravelPlan travelPlan) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        dbAPI.insertTravelPlan(retrofit, travelPlan, travelPlanCallback);


//        uploadToDB(travelPlan, imagePath);

    }

    public void convertBitmapToFile(Context applicationContext, Bitmap bitmap, int code) {

        // Process this conversion in another thread except Main Thread and UI Thread
        String imageFileName = "IMG_" +code+ ".jpg";

        File storageDir = applicationContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageFileName);

        try {
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream); // NOTE: if save bitmap/image to a server
            outputStream.flush();
            outputStream.close();

            // object of file (i.e., imageFile) is ready to be uploaded in remote server

            // #TODO: upload file to remote server
        } catch (IOException e) {
            Log.e("EditPlanController", "Error saving image", e);
        }

    }
    private boolean validator(String imagePath) {
       return imagePath != null;
    }
}
