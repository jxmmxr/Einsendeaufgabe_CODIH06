package org.example;

import javax.swing.tree.DefaultTreeCellEditor;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        PrimeFactors.primeFactors(20);
        int[][] searchfield = {
                {0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };
        DetectLine checklength = new DetectLine();
        int lengthOfLine = checklength.LineLengthInt(searchfield);
        System.out.printf("\nDie Linie ist %d lang\n", lengthOfLine);
    }
}
