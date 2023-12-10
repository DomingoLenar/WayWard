package com.example.myfirstapp.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myfirstapp.models.FileUpload;
import com.example.myfirstapp.views.EditPlanActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

public class EditPlanController {

    EditPlanActivity editPlanActivity;
    FileUpload fileUpload;
    public EditPlanController (EditPlanActivity editPlanActivity) {
        this.editPlanActivity = editPlanActivity;
//        fileUpload = new FileUpload();
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
    public void saveImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream); // convert image to ONLY .jpeg
            byte[] bytes = byteArrayOutputStream.toByteArray(); // store in bytes array
            final String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT); // string version of image
            File file = new File(base64Image); // file path
            Continuation<? super Unit> continuation = new Continuation<Unit>() {
                @NonNull
                @Override
                public CoroutineContext getContext() {
                    return EmptyCoroutineContext.INSTANCE;
                }

                @Override
                public void resumeWith(@NonNull Object o) {
                    System.out.println(o);
                }
            };

            fileUpload.uploadFile(file, base64Image, continuation); // upload file to supabase storage

        } else {
            Toast.makeText(editPlanActivity.getApplicationContext(), "Select the image first", Toast.LENGTH_SHORT).show();
        }
    }
}
