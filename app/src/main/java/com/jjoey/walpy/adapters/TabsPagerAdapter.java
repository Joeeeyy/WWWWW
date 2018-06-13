package com.jjoey.walpy.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.activeandroid.query.Select;
import com.jjoey.walpy.models.Customize;
import com.jjoey.walpy.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = TabsPagerAdapter.class.getSimpleName();
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titlesList = new ArrayList<>();

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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get(position);
    }
}
