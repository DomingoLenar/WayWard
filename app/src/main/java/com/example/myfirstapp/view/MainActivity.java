package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mainExplore = findViewById(R.id.mainExplore);
        TextView mainTopRated = findViewById(R.id.mainTopRated);
        TextView mainRecommended = findViewById(R.id.mainRecommended);

        ImageView mainAvatar = findViewById(R.id.mainAvatar);

        CardView mainSearchBar = findViewById(R.id.mainSearchBar);
        CardView mainTopRatedCard = findViewById(R.id.mainTopRatedCard);
        CardView mainRecommendedButton = findViewById(R.id.mainRecommendedButton);
        CardView mainBottomBarDarkCream = findViewById(R.id.mainBottomBarDarkCream);
        CardView mainBottomBarOrange = findViewById(R.id.mainBottomBarOrange);

        LinearLayout mainSearchBarContents = findViewById(R.id.mainSearchBarContents);
        LinearLayout mainTopRatedFirstButton = findViewById(R.id.mainTopRatedFirstButton);
        LinearLayout mainTopRatedFirstButtonContents1 = findViewById(R.id.mainTopRatedFirstButtonContents1);
        LinearLayout mainTopRatedFirstButtonContents2 = findViewById(R.id.mainTopRatedFirstButtonContents2);
        LinearLayout mainTopRatedSecondButton = findViewById(R.id.mainTopRatedSecondButton);
        LinearLayout mainTopRatedSecondButtonContents1 = findViewById(R.id.mainTopRatedSecondButtonContents1);
        LinearLayout mainTopRatedSecondButtonContents2 = findViewById(R.id.mainTopRatedSecondButtonContents2);
        LinearLayout mainTopRatedThirdButton = findViewById(R.id.mainTopRatedThirdButton);
        LinearLayout mainTopRatedThirdButtonContents1 = findViewById(R.id.mainTopRatedThirdButtonContents1);
        LinearLayout mainTopRatedThirdButtonContents2 = findViewById(R.id.mainTopRatedThirdButtonContents2);
        LinearLayout mainTopRatedFourthButton = findViewById(R.id.mainTopRatedFourthButton);
        LinearLayout mainTopRatedFourthButtonContents1 = findViewById(R.id.mainTopRatedFourthButtonContents1);
        LinearLayout mainTopRatedFourthButtonContents2 = findViewById(R.id.mainTopRatedFourthButtonContents2);
        LinearLayout mainTopRatedFifthButton = findViewById(R.id.mainTopRatedFifthButton);
        LinearLayout mainTopRatedFifthButtonContents1 = findViewById(R.id.mainTopRatedFifthButtonContents1);
        LinearLayout mainTopRatedFifthButtonContents2 = findViewById(R.id.mainTopRatedFifthButtonContents2);
        LinearLayout mainRecommendedFirstButton = findViewById(R.id.mainRecommendedFirstButton);
        LinearLayout mainRecommendedSecondButton = findViewById(R.id.mainRecommendedSecondButton);
        LinearLayout mainRecommendedThirdButton = findViewById(R.id.mainRecommendedThirdButton);
        LinearLayout mainBottomBarContents = findViewById(R.id.mainBottomBarContents);

        ScrollView mainRecommendedButtonVerticalScrollView = findViewById(R.id.mainRecommendedButtonVerticalScrollView);

        HorizontalScrollView mainTopRatedHorizontalScrollView = findViewById(R.id.mainTopRatedHorizontalScrollView);

    }
}
