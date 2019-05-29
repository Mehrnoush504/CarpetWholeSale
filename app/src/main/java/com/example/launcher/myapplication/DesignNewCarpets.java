package com.example.launcher.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Amir Muhammad
 */

public class DesignNewCarpets extends Fragment {
    Button submit, submit_neighbouring, set;
    TextView result;
    EditText neighbour1, neighbour2, areas;
    int graph_size = 0, matrix[][];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.design_new_carpet, container, false);
        submit = view.findViewById(R.id.submit);
        submit_neighbouring = view.findViewById(R.id.submit_neigbouring);
        result = view.findViewById(R.id.result);
        set = view.findViewById(R.id.set);
        areas = view.findViewById(R.id.areas_count);
        neighbour1 = view.findViewById(R.id.neighbour1);
        neighbour2 = view.findViewById(R.id.neighbour2);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areas.getText().toString().equals("") || areas.getText().toString().equals("0")) {
                    return;
                }
                graph_size = Integer.parseInt(areas.getText().toString());
                matrix = new int[graph_size][graph_size];
            }
        });
        submit_neighbouring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (neighbour1.getText().toString().equals("") || neighbour2.getText().toString().equals("")) {
                    return;
                }
                int node1 = Integer.valueOf(neighbour1.getText().toString());
                int node2 = Integer.valueOf(neighbour2.getText().toString());
                if (node1 >= graph_size || node2 >= graph_size) {
                    return;
                }
                joinNodes(node1, node2);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (graph_size == 0) {
                    return;
                }
                getAnswer();
            }
        });
        return view;
    }

    private void getAnswer() {
        result.clearComposingText();
        String first_line = getNeededColors() + "\n";
        result.setText(first_line);
        writePairs();
    }

    private void writePairs() {
//        for (int i = 0; i < graph_size; i++) {
//
//        }
    }

    private String getNeededColors() {
        int needed = 0;
        return String.valueOf(needed);
    }

    private void joinNodes(Integer node1, Integer node2) {
    }
}
