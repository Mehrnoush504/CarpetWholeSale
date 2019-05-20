package com.example.launcher.myapplication.Basic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

    public static void main(String[] args) {
        java.util.Scanner input = new java.util.Scanner(System.in);
        int n, W, HowMany;
        n = input.nextInt();
        W = input.nextInt();
        int[] w = new int[n + 1];
        Vector values = new Vector();

        for (int i = 1; i <= n; i++)
            w[i] = input.nextInt();
        Arrays.sort(w);
        System.out.println("sorted array is: " + Arrays.toString(w));
        HowMany = KnapSack(n, W, w, values);
        Set<Integer> options = new HashSet<Integer>();
        System.out.println("the number of carpets you can buy: " + HowMany);
        System.out.println("the whole values which is selected: ");
        for (int i = 0; i < values.size(); i++) {
            System.out.print(values.elementAt(i) + " ");
            options.add((int) values.get(i));
        }
        System.out.println();
        System.out.print("options: " + options);
        System.out.println();
    }

}
