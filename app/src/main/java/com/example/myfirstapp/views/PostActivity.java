package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

public class PostActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView postProfileTitle = findViewById(R.id.postProfileTitle);
        TextView postDescription = findViewById(R.id.postDescription);
        TextView postNameTitle1 = findViewById(R.id.postNameTitle1);
        TextView postNameTitle2 = findViewById(R.id.postNameTitle2);

        ScrollView postVerticalScrollView = findViewById(R.id.postVerticalScrollView);

        LinearLayout postCardMainContent = findViewById(R.id.postCardMainContent);
        LinearLayout postCardContentsHorizontal1 = findViewById(R.id.postCardContentsHorizontal1);
        LinearLayout postCardContentsVertical1 = findViewById(R.id.postCardContentsVertical1);
        LinearLayout postCardContentsHorizontal2 = findViewById(R.id.postCardContentsHorizontal2);
        LinearLayout postCardContentsVertical2 = findViewById(R.id.postCardContentsVertical2);
        LinearLayout postBottomBarContents = findViewById(R.id.postBottomBarContents);

        EditText postLocation1 = findViewById(R.id.postLocation1);
        EditText postLocation2 = findViewById(R.id.postLocation2);

        Button postBackground1 = findViewById(R.id.postBackground1);
        postBackground1.setClickable(false);
        Button postBackground2 = findViewById(R.id.postBackground2);
        postBackground2.setClickable(false);

        RatingBar postStarRating1 = findViewById(R.id.postStarRating1);
        RatingBar postStarRating2 = findViewById(R.id.postStarRating2);

        CardView postCard1 = findViewById(R.id.postCard1);
        CardView postCard2 = findViewById(R.id.postCard2);
        CardView postBottomBarDarkCream = findViewById(R.id.postBottomBarDarkCream);
        CardView postBottomBarOrange = findViewById(R.id.postBottomBarOrange);

        ImageView postAvatar1 = findViewById(R.id.postAvatar1);
        ImageView postPicture1 = findViewById(R.id.postPicture1);
        ImageView postAvatar2 = findViewById(R.id.postAvatar2);
        ImageView postPicture2 = findViewById(R.id.postPicture2);

        ImageButton postHomeButton = findViewById(R.id.postHomeButton);
        ImageButton postSearchButton = findViewById(R.id.postSearchButton);
        ImageButton postMButton = findViewById(R.id.postMButton);
        ImageView postEditButton = findViewById(R.id.postEditButton);
        ImageView postUserProfileButton = findViewById(R.id.postUserProfileButton);

    }
}