package com.example.myfirstapp.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.ProfileSettingsController;

public class ProfileSettingsActivity extends AppCompatActivity {
    ProfileSettingsController profileSettingsController;
    EditText editText_password, editText_username;
    Intent pIntent;
    TextView userProfileFirstName;
    String pUsername;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_detail);

        pIntent = getIntent();
        String[] arr = pIntent.getStringArrayExtra("userInfo");
        pUsername = arr[3];
        initViews();

        editText_password = findViewById(R.id.editText_password);
        editText_username = findViewById(R.id.editText_username);

    }

    public void EDP_editBtn(View view) {
        String updatedUsername = editText_username.getText().toString();
        String updatedPassword = editText_password.getText().toString();

        profileSettingsController.updateAccountDetails(pUsername, updatedPassword, updatedUsername);
    }
    private void initViews() {
        userProfileFirstName = findViewById(R.id.userProfileFirstName);
        TextView userProfileLastName = findViewById(R.id.userProfileLastName);

        try {
            if (pIntent != null) {
                String[] userInfo = pIntent.getStringArrayExtra("userInfo");
                assert userInfo != null;
                userProfileFirstName.setText(userInfo[0]);
                userProfileLastName.setText(userInfo[1]);
            }
        } catch (AssertionError e) {
            Log.e("ProfileSettingsActivity" , "Empty user object", e);
        }

        ImageButton EDS_HomeButton = findViewById(R.id.EDS_homeBtn);
        ImageButton EDS_SearchButotn = findViewById(R.id.EDS_searchBtn);
        ImageButton EDS_MButton = findViewById(R.id.EDS_MButton);
        ImageButton EDS_userSettingsBtn = findViewById(R.id.EDS_userSettingsBtn);

        TextView textView_updatePassword = findViewById(R.id.textView_updatePassword);
        TextView textView_updateUsername = findViewById(R.id.textView_updateUsername);
    }
}
