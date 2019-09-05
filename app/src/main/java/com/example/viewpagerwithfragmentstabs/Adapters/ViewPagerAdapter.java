package com.example.viewpagerwithfragmentstabs.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.viewpagerwithfragmentstabs.Fragments.GameListFragment;
import com.example.viewpagerwithfragmentstabs.Fragments.SaveCategoryFragment;
import com.example.viewpagerwithfragmentstabs.Fragments.SaveGameFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position)
        {
            case 0:
                fragment=new SaveCategoryFragment();
                break;
            case 1:
                fragment=new SaveGameFragment();
                break;
            case 2:
                fragment=new GameListFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
