package com.example.launcher.myapplication.Basic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.launcher.myapplication.Models.Carpet;

import java.util.*;

/**
 * @author Mehrnoush
 */
public class Knapsack {

    static int KnapSack(int n, int W, int w[], Vector values) {
        int[][] table = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            boolean flag = false;
            for (int j = 0; j <= W; j++) {

                if (i == 0 || j == 0)
                    table[i][j] = 0;
                else if (w[i] <= j) {
                    if (table[i - 1][j - w[i]] + 1 > table[i - 1][j]) {
                        table[i][j] = table[i - 1][j - w[i]] + 1;
                        flag = true;
                    } else {
                        table[i][j] = table[i - 1][j];
                    }
                } else {
                    table[i][j] = table[i - 1][j];
                }

            }
            if (flag)
                values.addElement(w[i]);
        }

        return table[n][W];
    }

    public ArrayList<Carpet> main(int W, ArrayList<Integer> arrayList) {
        ArrayList<Carpet> carpets = new ArrayList<>();
        int n, HowMany;
        n = arrayList.size();
        int[] w = new int[n + 1];
        Vector values = new Vector();

        for (int i = 1; i <= n; i++)
            w[i] = arrayList.get(i);
        Arrays.sort(w);
        System.out.println("sorted array is: " + Arrays.toString(w));
        HowMany = KnapSack(n, W, w, values);
        // TODO: Knapsack method should be edited to get carpets instead of their prices
        Set<Integer> options = new HashSet<>();
        System.out.println("the number of carpets you can buy: " + HowMany);
        System.out.println("the whole values which is selected: ");
        for (int i = 0; i < values.size(); i++) {
            System.out.print(values.elementAt(i) + " ");
            options.add((int) values.get(i));
        }
        System.out.println();
        System.out.print("options: " + options);
        System.out.println();
        return carpets;
    }

}
