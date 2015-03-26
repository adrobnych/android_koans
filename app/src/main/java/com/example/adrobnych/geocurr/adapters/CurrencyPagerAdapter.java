package com.example.adrobnych.geocurr.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.Map;

/**
 * Created by adrobnych on 3/26/15.
 */
public class CurrencyPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Map<String, Fragment> fragments;

    public CurrencyPagerAdapter(FragmentManager fm, String[] titles, Map<String, Fragment> fragments) {
        super(fm);

        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(titles[position]);
    }

}