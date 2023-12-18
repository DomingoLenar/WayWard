package com.example.myfirstapp.views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.myfirstapp.controllers.EditPlanController;
import com.example.myfirstapp.databinding.ActivityEditPlanBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class EditPlanActivity extends AppCompatActivity {
    private ActivityEditPlanBinding binding;
    private final int GALLERY_CODE_0 = -1;
    private final int GALLERY_CODE_1 = 1;
    private final int GALLERY_CODE_2 = 2;
    private final int GALLERY_CODE_3 = 3;
    private final int GALLERY_CODE_4 = 4;

    EditPlanController editPlanController;
    ImageView img1, img2, img3, img4, app_bar_img;
    TextView estimatedPrice, duration, title, description;
    TextInputEditText des_field1, des_field2, des_field3, des_field4;
    BottomNavigationView navbar;
    Uri uri;
    Intent pIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pIntent = getIntent();

        binding = ActivityEditPlanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        editPlanController = new EditPlanController(this);

        initViews();

        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.equals(navbar.getMenu().getItem(0))) editPlanController.displayMainActivity();
                else if (item.equals(navbar.getMenu().getItem(1))) editPlanController.displaySearchActivity();
                else if (item.equals(navbar.getMenu().getItem(2))) editPlanController.displayPopUpDialog();
                else if (item.equals(navbar.getMenu().getItem(3))) editPlanController.displayEditPlanActivity();
                else if (item.equals(navbar.getMenu().getItem(4))) editPlanController.displayUserSettingsActivity();
                return true;
            }
        });


    }

    private void initViews() {

        navbar = findViewById(R.id.E_bottomNavBar);

        estimatedPrice = findViewById(R.id.E_estimatedPrice);
        findViewById(R.id.E_estimatedPriceLabel);

        duration = findViewById(R.id.E_duration);
        findViewById(R.id.E_durationLabel);

        title = findViewById(R.id.E_title);
        description = findViewById(R.id.E_body);

        app_bar_img = findViewById(R.id.E_app_bar_image);
        img1 = findViewById(R.id.E_img1);
        img2 = findViewById(R.id.E_img2);
        img3 = findViewById(R.id.E_img3);
        img4 = findViewById(R.id.E_img4);

        des_field1 = findViewById(R.id.E_destination1Field);
        des_field2 = findViewById(R.id.E_destination2Field);
        des_field3 = findViewById(R.id.E_destination3Field);
        des_field4 = findViewById(R.id.E_destination4Field);
    }

    public void insert_img1(View view) {
        editPlanController.imageType = "gallery_1";
        editPlanController.loadImage(GALLERY_CODE_1);
    }

    public void insert_img2(View view) {
        editPlanController.imageType = "gallery_2";
        editPlanController.loadImage(GALLERY_CODE_2);
    }

    public void insert_img3(View view) {
        editPlanController.imageType = "gallery_3";
        editPlanController.loadImage(GALLERY_CODE_3);
    }

    public void insert_img4(View view) {
        editPlanController.imageType = "gallery_4";
        editPlanController.loadImage(GALLERY_CODE_4);
    }

    public void saveNowBtn(View view) {
        if (title.getText().toString().equals("") || duration.getText().toString().equals("") || estimatedPrice.getText().toString().equals("") || description.getText().toString().equals("")) {
            Snackbar.make(view, "Input the required fields.", Snackbar.LENGTH_SHORT).setAnchorView(R.id.E_bottomNavBar).show();
        } else {
            if (des_field1.getText().toString().equals("") || des_field1.getText().toString().equals("") || des_field1.getText().toString().equals("") || des_field1.getText().toString().equals("")) {
                Snackbar.make(view, "Input at least one destination.", Snackbar.LENGTH_SHORT).setAnchorView(R.id.E_bottomNavBar).show();
            } else {
                Snackbar.make(view, "Plan saved.", Snackbar.LENGTH_SHORT).setAnchorView(R.id.E_bottomNavBar).show();
                String[] userInfo = pIntent.getStringArrayExtra("userInfo");
                try {
                    assert userInfo != null;
                    String fname = userInfo[0];
                    String lname = userInfo[1];
                    String user_id = userInfo[2];
                    editPlanController.submitTravelPlanDetails(Integer.parseInt(user_id), title.getText().toString(), new int[] {69}, fname, duration.getText().toString(),
                            estimatedPrice.getText().toString(), description.getText().toString(), des_field1.getText().toString());
                } catch (AssertionError error) {
                    error.printStackTrace();
                }
            }
        }
    }

    public void insert_thumbnail(View view) {
        editPlanController.imageType = "thumbnail";
        editPlanController.loadImage(GALLERY_CODE_0);
    }

    /**
     * - This method is associated with startActivityForResult(); display the image uploaded by the user; setup the file transfer in the database
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_0 && data != null){
            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img1.setImageBitmap(bitmap);
                editPlanController.convertBitmapToFile(getApplicationContext(), bitmap, GALLERY_CODE_0);
//                editPlanController.imagePath(bitmap);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_1 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img1.setImageBitmap(bitmap);
                editPlanController.convertBitmapToFile(getApplicationContext(), bitmap, GALLERY_CODE_1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_2 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img2.setImageBitmap(bitmap);
                editPlanController.convertBitmapToFile(getApplicationContext(), bitmap, GALLERY_CODE_2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_3 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img3.setImageBitmap(bitmap);
                editPlanController.convertBitmapToFile(getApplicationContext(), bitmap, GALLERY_CODE_3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_CODE_4 && data != null) {

            uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                img4.setImageBitmap(bitmap);
                editPlanController.convertBitmapToFile(getApplicationContext(), bitmap, GALLERY_CODE_4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
