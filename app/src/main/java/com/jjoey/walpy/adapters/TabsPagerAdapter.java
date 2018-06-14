package com.jjoey.walpy.adapters;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = TabsPagerAdapter.class.getSimpleName();
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titlesList = new ArrayList<>();

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addTab(Fragment fragment, String name){
        fragmentList.add(fragment);
        titlesList.add(name);
    }

    public void addTabAtLastPosition(Fragment fragment, String name){
        fragmentList.add(fragmentList.size(), fragment);
        titlesList.add(name);
    }

    public void removeTab(Fragment fragment, String name){
        for (int i = 0; i < fragmentList.size(); i++){
            String title = titlesList.get(i);
            Log.d(TAG, "Fragment Titles:\t" + title);
            if (title.equals(name)){
                Log.d(TAG, "Fragment at position:\t" + i);
                fragmentList.remove(fragment);
                titlesList.remove(name);
            }
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
