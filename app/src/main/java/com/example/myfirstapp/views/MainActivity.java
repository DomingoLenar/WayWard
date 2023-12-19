package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.MainController;
import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.modelsV2.TravelPlan;


import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private final String url = "https://fauokmrzqpowzdiqqxxg.supabase.co/storage/v1/object/public/images/travel_plan/";
    MainController mainController;
    ImageButton homeBtn, searchBtn, popUpBtn, editPlanBtn, userSettingsBtn, imageBtn1;
    Intent pIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pIntent = getIntent();
        mainController = new MainController(this);

        initViews();

    }

    public void M_imageBtn1(View view) {
        mainController.displayEditPlanActivity(this, pIntent);
    }
    private void initViews() {
        homeBtn = findViewById(R.id.M_homeBtn);
        searchBtn =findViewById(R.id.M_searchBtn);
        popUpBtn = findViewById(R.id.M_popUpBtn);
        editPlanBtn = findViewById(R.id.M_editPlanBtn);
        userSettingsBtn = findViewById(R.id.M_userSettingsBtn);

        imageBtn1 = findViewById(R.id.mainRecommendedButton2);

        String[] userInfo = pIntent.getStringArrayExtra("userInfo");
        assert userInfo != null;
        int user_id = Integer.parseInt(userInfo[2]);

//        dbAPI.downloadImage("/travel_plan/" + user_id + "/" + user_id + "_thumbnail.png", "images", user_id + "_thumbnail.png");

        String imageUrl = url + user_id + "/" + user_id + "_thumbnail.png";
        mainController.preloadImages(imageUrl, imageBtn1);

        DataBaseAPI dbAPI = new DataBaseAPI();
        Retrofit retrofit = dbAPI.getClient();
        DataBaseAPI.TravelPlanCallback travelPlanCallback = new DataBaseAPI.TravelPlanCallback() {
            @Override
            public void onReceived(TravelPlan travelPlan) {

            }

            @Override
            public void onError(String errorMessage) {

            }
        };
//        dbAPI.getListOfTravelPlan(retrofit, user_id, travelPlanCallback);

    }

    public void M_homeBtn(View view) {
        mainController.displayMainActivity(this);
    }
    public void M_searchBtn(View view) {
        mainController.displaySearchActivity(this, pIntent);
    }
    public void M_popUpBtn(View view) {
        mainController.displayPopUpDialog();
    }
    public void M_editPlanBtn(View view) {
        mainController.displayEditPlanActivity(this, pIntent);
    }
    public void M_profileSettingsBtn(View view) {
        mainController.displayUserSettingsActivity(this, pIntent);
    }
}
