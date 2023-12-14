package com.example.myfirstapp.views;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.PrivacyController;

public class PrivacyActivity extends AppCompatActivity {
    PrivacyController privacyController;
    private WebView webView_privacyPolicy;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        ImageButton PrivacyHomeButton = findViewById(R.id.PrivacyHomeButton);
        ImageButton PrivacySearchButton = findViewById(R.id.PrivacySearchButton);
        ImageButton PrivacyeMainButton = findViewById(R.id.PrivacyMainButton);
        ImageButton PrivacyEditButton = findViewById(R.id.PrivacyEditButton);
        ImageButton PrivacyUPButton = findViewById(R.id.PrivacyUPButton);

        webView_privacyPolicy = (WebView) findViewById((R.id.webView_privacyPolicy));
        webView_privacyPolicy.setWebViewClient(new WebViewClient());
        webView_privacyPolicy.loadUrl("https://www.termsfeed.com/live/e6cdb596-2752-4cc4-8feb-e5118bc5a98a");
    }
}
