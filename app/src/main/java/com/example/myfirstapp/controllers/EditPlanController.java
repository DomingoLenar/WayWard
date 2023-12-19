package com.example.myfirstapp.controllers;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
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
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Retrofit;

public class EditPlanController  {
    public String imageType;
    public String imagePath;
    private final String[] arr_img_path = new String[5]; // max image to be uploaded per query
    private final int[] arr_gallery_code = new int[] {0, 1, 2, 3, 4}; // helper variable to upload an image
    EditPlanActivity editPlanActivity;
    com.example.myfirstapp.modelsV2.TravelPlan travelPlanWithID;
    public int[] getArr_gallery_code() {
        return arr_gallery_code;
    }

    public String[] getArr_img_path() {
        return arr_img_path;
    }

    /**
     * inner class of EditPlanController to used AsyncTask class; it is a helper class to perform background operation on the UI thread.
     *
     * @method doInBackground(Params param) invoked when background operation (i.e., business logic, network request, etc) is required
     *
     */
    @SuppressLint("StaticFieldLeak")
    private class DownloadFilesTask extends AsyncTask<TravelPlan, Void, Integer> {
        @Override
        protected Integer doInBackground(TravelPlan... travelPlans) {
            int travelPlanID = travelPlanWithID.getId();
            String[] arr_file_path = getArr_img_path();
            int[] ifGalleryIndex = getArr_gallery_code();
            DataBaseAPI db = new DataBaseAPI();
            try {
                for (int i = 0; i < arr_file_path.length; i++) {
                    if (arr_file_path[i] == null) {
                        continue;
                    }
                    if (arr_file_path[i] != null && ifGalleryIndex[i] == 0) {
                        db.uploadImage(arr_file_path[i], "travel_plan/" + travelPlanID + "/" + travelPlanID + "_thumbnail.png");
                    } else if (arr_file_path[i] != null && ifGalleryIndex[i] == i) {
                        db.uploadImage(arr_file_path[i], "travel_plan/" + travelPlanID + "/" + travelPlanID + "_gallery_" + ifGalleryIndex[i] + ".png");
                    }
                }
                return 1;
            } catch (Exception e) {
                Log.e("EditPlanController", "doInBackground error", e);
                return -1;
            }
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            System.out.println("SUCCESS" + integer);
        }
    }

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
     * @param travelPlan        object of travelplan which owns the image
     * @param arr_file_path     array local path of the image
     * @param ifGalleryIndex    array count of what the image is, -1 if it is a thumbnail
     */
    public void uploadToDB(TravelPlan travelPlan, String[] arr_file_path, int[] ifGalleryIndex){}
    public void submitTravelPlanDetails(int id, String title, int[] reviews, String username, String duration, String estimated_cost,String description, String destinations) {
        travelPlanWithID = new com.example.myfirstapp.modelsV2.TravelPlan(id, title, reviews, duration, estimated_cost, description, destinations, username);
        TravelPlan travelPlan = new com.example.myfirstapp.modelsV2.TravelPlan(title, reviews, duration, estimated_cost, description, destinations, username);

        DataBaseAPI dbAPI = new DataBaseAPI();
        Retrofit retrofit = dbAPI.getClient();

        DataBaseAPI.TravelPlanCallback travelPlanCallback = new DataBaseAPI.TravelPlanCallback() {
            @Override
            public void onReceived(com.example.myfirstapp.modelsV2.TravelPlan travelPlan) {
                try {
                    assert travelPlan != null;
                } catch (AssertionError e){
                    Log.e("EditPlanController", "Saved travel Plan", e);
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        };
        dbAPI.insertTravelPlan(retrofit, travelPlan, travelPlanCallback);

        for (int i = 0; i < arr_gallery_code.length; i++) {
            if (arr_img_path[i] != null) {
                new DownloadFilesTask().execute(travelPlanWithID); // Worker Thread
            }
        }
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
            this.imagePath = imageFile.getAbsolutePath();

            populateArrayImagePath(imagePath, code);

            // object of file (i.e., imageFile) is ready to be uploaded in remote server

        } catch (IOException e) {
            Log.e("EditPlanController", "Error saving image", e);
        }

    }

    private void populateArrayImagePath(String imagePath, int code) {
        for (int i = 0; i < arr_img_path.length; i++) { // brute force
            if (arr_img_path[i] == null && arr_gallery_code[i] == code) {
                arr_img_path[i] = imagePath;
                arr_gallery_code[i] = code;
            }
        }
    }

    private boolean validator(String imagePath) {
       return imagePath != null;
    }
}
