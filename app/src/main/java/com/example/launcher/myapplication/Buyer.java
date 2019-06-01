package com.example.launcher.myapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.launcher.myapplication.Adapters.BuyerViewPagerAdapter;

public class Buyer extends AppCompatActivity {

    private TabLayout tabLayout2;
    private BuyerViewPagerAdapter buyerViewPagerAdapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        tabLayout2 = findViewById(R.id.tabLayout2);
        viewPager = findViewById(R.id.buyer_viewPager);
        buyerViewPagerAdapter = new BuyerViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(buyerViewPagerAdapter);
        tabLayout2.setupWithViewPager(viewPager);
    }
}
