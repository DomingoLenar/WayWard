package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

public class UserProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        TextView userProfileLastName = findViewById(R.id.userProfileLastName);
        TextView userProfileFirstName = findViewById(R.id.userProfileFirstName);
        TextView userProfileDescription = findViewById(R.id.userProfileDescription);

        ImageView userProfileAvatar = findViewById(R.id.userProfileAvatar);

        Button userProfilePreviousTravels = findViewById(R.id.userProfilePreviousTravels);
        Button userProfileLocationServices = findViewById(R.id.userProfileLocationServices);
        Button userProfileProfileSettings = findViewById(R.id.userProfileProfileSettings);
        Button userProfilePrivacy = findViewById(R.id.userProfilePrivacy);
        Button userProfileLogout = findViewById(R.id.userProfileLogout);

        ImageButton userProfileHomeButton = findViewById(R.id.userProfileHomeButton);
        ImageButton userProfileSearchButton = findViewById(R.id.userProfileSearchButton);
        ImageButton userProfileMainButton = findViewById(R.id.userProfileMainButton);
        ImageButton userProfileEditButton = findViewById(R.id.userProfileEditButton);
        ImageButton userProfileUPButton = findViewById(R.id.userProfileUPButton);

        CardView userProfileBottomBarOrange = findViewById(R.id.userProfileBottomBarOrange);
        CardView userProfileBottomBarDarkCream = findViewById(R.id.userProfileBottomBarDarkCream);

        LinearLayout userProfileBottomBarContents = findViewById(R.id.userProfileBottomBarContents);
    }
}