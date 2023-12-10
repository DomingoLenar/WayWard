package com.example.myfirstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
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
import com.example.myfirstapp.view.fragments.PopularFragment;
import com.example.myfirstapp.view.fragments.RecentFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import org.w3c.dom.Text;

public class SearchActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

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

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new PopularFragment(),"Popular");
        vpAdapter.addFragment(new RecentFragment(), "Recent");
        viewPager.setAdapter(vpAdapter);

    }
}