package com.example.launcher.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.launcher.myapplication.Basic.Knapsack;
import com.example.launcher.myapplication.Adapters.AvailableCarpetsAdapter;
import com.example.launcher.myapplication.Models.Carpet;

import java.util.ArrayList;

public class ShoppingCarpet extends Fragment {
    Button sumbitMoney;
    RecyclerView recyclerView;
    EditText moneyText;
    AvailableCarpetsAdapter availableCarpetsAdapter;
    int money = 0;
    public static final String TITLE = "خرید";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_carpet, container, false);
        sumbitMoney = view.findViewById(R.id.submitMoney);
        recyclerView = view.findViewById(R.id.recycler);
        moneyText = view.findViewById(R.id.moneyeditText);
        sumbitMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moneyText.getText().toString().equals("")) {
                    return;
                }
                money = Integer.valueOf(moneyText.getText().toString());
                displayRecyclerView(money);
            }
        });
        return view;
    }

    private void displayRecyclerView(int money) {
        Knapsack knapsack = new Knapsack();
        ArrayList<Integer> arrayList = new ArrayList<>();
        // TODO: arrayList should be caught from database
        ArrayList<Carpet> carpets = knapsack.main(money, arrayList);
        availableCarpetsAdapter = new AvailableCarpetsAdapter(getContext(), carpets);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(availableCarpetsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
