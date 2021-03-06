package com.example.android.reminder.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.android.reminder.Constants;
import com.example.android.reminder.fragments.ExampleFragment;
import com.example.android.reminder.fragments.NotificationFragment;

public class TabPagerFragmentAdapter extends FragmentPagerAdapter{

    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    private String[] tabs;

    public TabPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "Notifications",
                "Tab 2",
                "Tab 3"
        };
        registeredFragments.put(Constants.TAB_NOTIFICATION, new NotificationFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return registeredFragments.get(position);
            case 1:
                return ExampleFragment.getInstance();
            case 2:
                return ExampleFragment.getInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
