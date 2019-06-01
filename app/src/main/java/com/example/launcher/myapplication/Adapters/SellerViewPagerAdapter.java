package com.example.launcher.myapplication.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.launcher.myapplication.ChangeCarpet;
import com.example.launcher.myapplication.DesignNewCarpets;

public class SellerViewPagerAdapter extends FragmentStatePagerAdapter {

    public SellerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1){
            return new ChangeCarpet();
        }else{
            return new DesignNewCarpets();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return ChangeCarpet.TITLE;
        }else{
            return DesignNewCarpets.TITLE;
        }
    }
}
