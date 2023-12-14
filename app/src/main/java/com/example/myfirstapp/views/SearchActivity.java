package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.myfirstapp.R;
import com.example.myfirstapp.controllers.SearchController;
import com.example.myfirstapp.views.adapters.ViewPagerAdapter;
import com.example.myfirstapp.views.fragments.PopularFragment;
import com.example.myfirstapp.views.fragments.RecentFragment;
import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    SearchController searchController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchController = new SearchController(this);
        initViews();

        ImageView imageView2 = findViewById(R.id.imageView2);
        TextView searchExplore = findViewById(R.id.searchExplore);
        ImageButton searchAvatar = findViewById(R.id.searchAvatar);
        CardView searchSearchBar = findViewById(R.id.searchSearchBar);
        LinearLayout searchSearchBarContents = findViewById(R.id.searchSearchBarContents);
        ImageView searchSearchLogo = findViewById(R.id.searchSearchLogo);
        EditText searchSearchField = findViewById(R.id.searchSearchField);
        CardView mainBottomBarDarkCream = findViewById(R.id.mainBottomBarDarkCream);
        LinearLayout mainBottomBarContents = findViewById(R.id.mainBottomBarContents);
        ImageButton mainHomeButton = findViewById(R.id.mainHomeButton);
        ImageButton mainSearchButton = findViewById(R.id.mainSearchButton);
        ImageButton mainMButton = findViewById(R.id.mainMButton);
        ImageButton mainEditButton = findViewById(R.id.mainEditButton);
        ImageButton mainUserProfileButton = findViewById(R.id.M_userSettingsBtn);
        CardView mainBottomBarOrange = findViewById(R.id.mainBottomBarOrange);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter vpAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new PopularFragment(),"Popular");
        vpAdapter.addFragment(new RecentFragment(), "Recent");
        viewPager.setAdapter(vpAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0 -> new PopularFragment();
                    case 1 -> new RecentFragment();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void homeBtn(View view) {
        searchController.displayMainActivity();
    }
    public void searchBtn(View view) {
        searchController.displaySearchActivity();
    }
    public void popUpBtn(View view) {
        searchController.displayPopUpDialog();
    }
    public void editPlanBtn(View view) {
        searchController.displayEditPlanActivity();
    }
    public void userSettingsBtn(View view) {
        searchController.displayUserSettingsActivity();
    }
    private void initViews() {

    }

}