package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import com.example.myfirstapp.models.DataBase;
import com.example.myfirstapp.models.TravelPlan;
import com.example.myfirstapp.views.EditPlanActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class EditPlanController {
    private String filePath;
    public String imageType;
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

    /**
     * - convert bitmap into string
     * @param bitmap
     */
    public void imagePath(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // convert image to ONLY .jpeg
            byte[] bytes = byteArrayOutputStream.toByteArray(); // store in bytes array
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT); // string version of image
            File file = new File(base64Image); // file path
            filePath = file.getPath();

//            Continuation<? super Unit> continuation = new Continuation<Unit>() {
//                @NonNull
//                @Override
//                public CoroutineContext getContext() {
//                    return EmptyCoroutineContext.INSTANCE;
//                }
//
//                @Override
//                public void resumeWith(@NonNull Object o) {
//                    System.out.println(o);
//                }
//            };

            //fileUpload.uploadFile(file, base64Image, continuation); // upload file to supabase storage

        } else {
            Toast.makeText(editPlanActivity.getApplicationContext(), "Select the image first", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadToDB(TravelPlan plan, String filePath){
        DataBase db = new DataBase();
        String post_id = ""+plan.getPost_id();
        String remotePath = "images/travel_plan/"+post_id+"/"+post_id+"_"+imageType+".jpg";
        db.uploadImage(filePath,remotePath);
    }

    public void submitTravelPlanDetails(String title, ArrayList<Integer> reviews, String username, String duration, String estimated_cost,String description, String destinations) {
        travelPlan = new TravelPlan(title, reviews, username, duration, estimated_cost, description, destinations);
        travelPlan.insertTravelPlan();

        uploadToDB(travelPlan, filePath);

    }
}
