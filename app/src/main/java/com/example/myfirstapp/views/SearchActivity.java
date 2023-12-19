package com.example.myfirstapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    Intent pIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        pIntent = getIntent();
        searchController = new SearchController(this);
        initViews();

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
    private void initViews() {}
    public void S_homeBtn(View view) {
        searchController.displayMainActivity(pIntent);
    }
    public void S_searchBtn(View view) {
        searchController.displaySearchActivity();
    }
    public void S_popUpBtn(View view) {
        searchController.displayPopUpDialog();
    }
    public void S_editPlanBtn(View view) {
        searchController.displayEditPlanActivity(pIntent);
    }
    public void S_userSettingsBtn(View view) {
        searchController.displayUserSettingsActivity(pIntent);
    }

}