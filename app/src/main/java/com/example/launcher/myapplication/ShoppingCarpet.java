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
import android.widget.TextView;

import com.example.launcher.myapplication.Basic.Knapsack;
import com.example.launcher.myapplication.Adapters.AvailableCarpetsAdapter;
import com.example.launcher.myapplication.Database.CarpetDBManager;
import com.example.launcher.myapplication.Models.Carpet;

import java.util.ArrayList;
import java.util.Hashtable;

public class ShoppingCarpet extends Fragment {
    Button submitMoney;
    RecyclerView recyclerView;
    EditText moneyText;
    TextView resText;
    AvailableCarpetsAdapter availableCarpetsAdapter;
    int money = 0;
    public static final String TITLE = "خرید";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shopping_carpet, container, false);
        submitMoney = view.findViewById(R.id.submitMoney);
        recyclerView = view.findViewById(R.id.recycler);
        resText = view.findViewById(R.id.resText);
        moneyText = view.findViewById(R.id.moneyeditText);
        submitMoney.setOnClickListener(new View.OnClickListener() {
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
        CarpetDBManager carpetDBManager = new CarpetDBManager(getContext());
        carpetDBManager.open();
        ArrayList<Carpet> carpets = carpetDBManager.getALLCarpets();
        Hashtable<Integer,Integer> options = knapsack.main(money, carpets);
        StringBuilder res = new StringBuilder("You can buy " + options.keySet().size() + " carpets.");
        ArrayList<Carpet> available = new ArrayList<>();
        for (Integer key : options.keySet()) {
            int counter = 0;
            for (int i = 0; i < carpets.size(); i++) {
                if (carpets.get(i).getPrice() == key){
                    available.add(carpets.get(i));counter++;
                }
            }
            if (counter != 0){
                res.append("\n").append(counter).append(" carpet with price: ").append(key);
            }
        }
        resText.setText(res.toString());
        carpetDBManager.close();
        availableCarpetsAdapter = new AvailableCarpetsAdapter(getContext(), available);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(availableCarpetsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
