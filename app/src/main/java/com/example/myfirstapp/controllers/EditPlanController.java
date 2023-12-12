package com.example.myfirstapp.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.TravelPlan;
import com.example.myfirstapp.views.EditPlanActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class EditPlanController {
    private String base64Image;
    public String imageType;
    public String imagePath;
    EditPlanActivity editPlanActivity;
    TravelPlan travelPlan;
    public EditPlanController (EditPlanActivity editPlanActivity) {
        this.editPlanActivity = editPlanActivity;
    }

    public void loadImage(int GALLERY_REQ_CODE) {
        Intent iImage1 = new Intent(Intent.ACTION_PICK);
        iImage1.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        editPlanActivity.startActivityForResult(iImage1, GALLERY_REQ_CODE);
    }

//    /**
//     * - convert bitmap into string
//     * @param bitmap
//     */
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
