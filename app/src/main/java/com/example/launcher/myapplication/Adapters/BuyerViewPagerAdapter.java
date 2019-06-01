package com.example.launcher.myapplication.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.launcher.myapplication.Navigation;
import com.example.launcher.myapplication.SearchingCarpets;
import com.example.launcher.myapplication.ShoppingCarpet;

public class BuyerViewPagerAdapter extends FragmentStatePagerAdapter{

    public BuyerViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new ShoppingCarpet();
        }else if (position == 1){
            return new SearchingCarpets();
        }else{
            return new Navigation();
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
            return ShoppingCarpet.TITLE;
        }else if (position == 1){
            return SearchingCarpets.TITLE;
        }else{
            return Navigation.TITLE;
        }
    }
}

