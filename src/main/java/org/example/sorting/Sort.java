package org.example.sorting;

import java.util.ArrayList;

import static java.util.Collections.swap;

public class Sort {

    public static ArrayList<Integer> customSort(ArrayList<Integer> numbers) {

        int endIndex = numbers.size()-1;
        int currentMax;
        int currentMaxIndex;
        for (int currentEndIndex=endIndex ; currentEndIndex > 0 ; currentEndIndex --) {
            currentMax=Integer.MIN_VALUE;
            currentMaxIndex=Integer.MIN_VALUE;
            for (int i=0 ; i <= currentEndIndex ; i++) {
                if (numbers.get(i) > currentMax) {
                    currentMax = numbers.get(i);
                    currentMaxIndex = i;
                }
            }
            swap(numbers, currentMaxIndex, currentEndIndex);
        }
        return numbers;
    }

    public static ArrayList<Integer> bubbleSort(ArrayList<Integer> numbers) {

        boolean isSwapped = false;
        for ( int j = numbers.size()-1; j>0 ; j--) {
            for (int i = 0; i < j; i++) {
                if (numbers.get(i) > numbers.get(i+1)) {
                    swap(numbers, i, i+1);
                    isSwapped = true;
                }
            }
            if (!isSwapped) break;
        }
        return numbers;
    }
}
