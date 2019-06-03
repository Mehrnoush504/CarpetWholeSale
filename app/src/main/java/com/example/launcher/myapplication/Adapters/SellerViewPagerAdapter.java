package com.example.launcher.myapplication.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.launcher.myapplication.AddCarpet;
import com.example.launcher.myapplication.ChangeCarpet;
import com.example.launcher.myapplication.DesignNewCarpets;

public class SellerViewPagerAdapter extends FragmentStatePagerAdapter {

    public SellerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new ChangeCarpet();
        }else if (position == 1){
            return new DesignNewCarpets();
        }else {
            return new AddCarpet();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return ChangeCarpet.TITLE;
        }else if (position == 1){
            return DesignNewCarpets.TITLE;
        }else {
            return AddCarpet.TITLE;
        }
    }
}
