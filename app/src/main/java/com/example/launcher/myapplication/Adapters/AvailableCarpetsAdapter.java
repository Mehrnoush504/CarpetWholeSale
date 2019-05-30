package com.example.launcher.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.launcher.myapplication.Models.Carpet;
import com.example.launcher.myapplication.R;

import java.util.ArrayList;

public class AvailableCarpetsAdapter extends RecyclerView.Adapter<AvailableCarpetsAdapter.Item> {

    private Context context;
    private ArrayList<Carpet> carpets = new ArrayList<>();

    public AvailableCarpetsAdapter(Context context, ArrayList<Carpet> carpets) {
        this.context = context;
        this.carpets = carpets;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.available_carpet_view, parent, false);
        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {

    }

    @Override
    public int getItemCount() {
        return carpets.size();
    }

    class Item extends RecyclerView.ViewHolder {
        public Item(View itemView) {
            super(itemView);
        }
    }
}
