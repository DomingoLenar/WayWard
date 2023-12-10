package com.example.myfirstapp.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.EditPlanController;

import java.io.IOException;

public class EditPlanActivity extends AppCompatActivity {

    private final int GALLERY_CODE_1 = 1;
    private final int GALLERY_CODE_2 = 2;
    private final int GALLERY_CODE_3 = 3;
    private final int GALLERY_CODE_4 = 4;
    EditPlanController editPlanController;
    ImageView img1, img2, img3, img4, app_bar_img;
    TextView estimatedPrice, duration, title, description;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);

        editPlanController = new EditPlanController(this);

        initViews();

    }

    private void initViews() {

        app_bar_img = findViewById(R.id.E_app_bar_image);

        estimatedPrice = findViewById(R.id.E_estimatedPrice);
        findViewById(R.id.E_estimatedPriceLabel);

        duration = findViewById(R.id.E_duration);
        findViewById(R.id.E_durationLabel);

        title = findViewById(R.id.E_title);
        description = findViewById(R.id.E_body);


        findViewById(R.id.E_app_bar_image);
        img1 = findViewById(R.id.E_img1);
        img2 = findViewById(R.id.E_img2);
        img3 = findViewById(R.id.E_img3);
        img4 = findViewById(R.id.E_img4);
    }

    public void insert_img1(View view) {
        editPlanController.loadImage(GALLERY_CODE_1);
    }

    public void insert_img2(View view) {
        editPlanController.loadImage(GALLERY_CODE_2);
    }

    public void insert_img3(View view) {
        editPlanController.loadImage(GALLERY_CODE_3);
    }

    public void insert_img4(View view) {
        editPlanController.loadImage(GALLERY_CODE_4);
    }

    /**
     * - This method is associated with startActivityForResult(); display the image uploaded by the user; setup the file transfer in the database
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data An Intent, which can return result data to the caller
     *               (various data can be attached to Intent "extras").
     *
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_1 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img1.setImageBitmap(bitmap);
                editPlanController.saveImage(bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_2 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img2.setImageBitmap(bitmap);
                editPlanController.saveImage(bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_3 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img3.setImageBitmap(bitmap);
                editPlanController.saveImage(bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_4 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img4.setImageBitmap(bitmap);
                editPlanController.saveImage(bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}