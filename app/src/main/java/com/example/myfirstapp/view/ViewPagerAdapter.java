package com.example.myfirstapp.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfirstapp.view.fragments.PopularFragment;
import com.example.myfirstapp.view.fragments.RecentFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PopularFragment();

            case 1:
                return new RecentFragment();

            default:
                return new PopularFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}