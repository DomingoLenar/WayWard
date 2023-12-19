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
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.MainController;
import com.example.myfirstapp.modelsV2.DataBaseAPI;
import com.example.myfirstapp.modelsV2.TravelPlan;


import org.w3c.dom.Text;

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

    private void initViews() {
        imageBtn1 = findViewById(R.id.mainRecommendedButton2);

        String[] userInfo = pIntent.getStringArrayExtra("datum");
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
    public void M_profileSettingsBtn(View view) {
        mainController.displayUserSettingsActivity(this, pIntent);
    }
}
