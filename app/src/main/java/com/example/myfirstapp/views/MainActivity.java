package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.MainController;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    MainController mainController;
    ImageButton homeBtn, searchBtn, popUpBtn, editPlanBtn, userSettingsBtn;
    Intent pIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pIntent = getIntent();
        mainController = new MainController(this);

        initViews();

    }

    private void initViews() {
        homeBtn = findViewById(R.id.M_homeBtn);
        searchBtn =findViewById(R.id.M_searchBtn);
        popUpBtn = findViewById(R.id.M_popUpBtn);
        editPlanBtn = findViewById(R.id.M_editPlanBtn);
        userSettingsBtn = findViewById(R.id.M_userSettingsBtn);
    }

    public void homeBtn(View view) {
        mainController.displayMainActivity(this);
    }
    public void searchBtn(View view) {
        mainController.displaySearchActivity(this);
    }
    public void popUpBtn(View view) {
        mainController.displayPopUpDialog();
    }
    public void editPlanBtn(View view) {
        mainController.displayEditPlanActivity(this, pIntent);
    }
    public void userSettingsBtn(View view) {
        mainController.displayUserSettingsActivity(this, pIntent);
    }
}
