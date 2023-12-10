package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.myfirstapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

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
        ImageButton mainUserProfileButton = findViewById(R.id.mainUserProfileButton);
        CardView mainBottomBarOrange = findViewById(R.id.mainBottomBarOrange);
        RelativeLayout searchFragments = findViewById(R.id.searchFragments);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });



    }

}