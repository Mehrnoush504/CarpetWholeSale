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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author Amir Muhammad
 */

public class DesignNewCarpets extends Fragment {
    public static String TITLE = "طراحی فرش جدید";
    Button submit, submit_neighbouring, set;
    TextView result;
    EditText neighbour1, neighbour2, areas;
    int graph_size = 0, matrix[][];
    int needed_colors = 0;
    LinkedList<Integer> adjListArray[];


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
                adjListArray = new LinkedList[graph_size];
                for (int i = 0; i < graph_size; i++) {
                    adjListArray[i] = new LinkedList<>();
                }
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
                neighbour1.clearComposingText();
                neighbour2.clearComposingText();
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
        Hashtable<Integer,Integer> hashtable = greedyColoring();
        StringBuilder first_line = new StringBuilder("تعداد رنگ مورد نیاز:" + needed_colors+ "\n");
        result.setText(first_line.toString());
        for (int i = 0; i < graph_size; i++) {
            first_line.append("ناحیه " + i + " :" + hashtable.get(i)).append("\n");
        }
        result.setText(first_line.toString());
    }

    private void joinNodes(Integer node1, Integer node2) {
        adjListArray[node1].add(node2);
        adjListArray[node2].add(node1);
        matrix[node1][node2] = matrix[node2][node1] = 1;
    }

    private Hashtable<Integer,Integer> greedyColoring() {
        int result[] = new int[graph_size];
        Hashtable<Integer,Integer> hashtable = new Hashtable<>();
        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);

        // Assign the first color to first vertex
        result[0] = 0;

        // A temporary array to store the available colors. False
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[graph_size];

        // Initially, all colors are available
        Arrays.fill(available, true);

        // Assign colors to remaining V-1 vertices
        for (int u = 1; u < graph_size; u++) {
            // Process all adjacent vertices and flag their colors
            // as unavailable
            for (Integer i : adjListArray[u]) {
                if (result[i] != -1)
                    available[result[i]] = false;
            }

            // Find the first available color
            int cr;
            for (cr = 0; cr < graph_size; cr++) {
                if (available[cr])
                    break;
            }

            result[u] = cr; // Assign the found color
            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }

        // print the result
        HashSet<Integer> colors = new HashSet<>();
        for (int u = 0; u < graph_size; u++) {
            System.out.println("Vertex " + u + " --->  Color " + result[u]);
            hashtable.put(u,result[u]);
            colors.add(result[u]);
        }
        needed_colors = colors.size();
        return hashtable;
    }
}
