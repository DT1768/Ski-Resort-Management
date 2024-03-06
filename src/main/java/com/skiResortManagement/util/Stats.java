package com.skiResortManagement.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stats {

    public static double calculateTotal(List<Double> list) {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return Math.round(sum*100.0)/100.0;
    }


    public static double calculateMean(List<Double> list) {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return Math.round((sum/ list.size())*100.0)/100.0 ;
    }

    public static double calculateMedian(List<Double> list) {

        List<Double> newList = new ArrayList<>(list);

        Collections.sort(newList);
        int middle = newList.size() / 2;
        if (newList.size() % 2 == 0) {
            return Math.round(((newList.get(middle - 1) + newList.get(middle)) / 2.0)*100.0)/100.0;
        } else {
            return Math.round(newList.get(middle)*100.0)/100.0;
        }
    }

    public static double calculate99thPercentile(List<Double> list) {

        List<Double> newList = new ArrayList<>(list);
        Collections.sort(newList);
        int index = (int) Math.ceil(0.99 * newList.size()) - 1;
        return Math.round(newList.get(index)*100.0)/100.0;
    }

    public static double findMin(List<Double> list) {
        return Math.round(Collections.min(list)*100.0)/100.0;
    }

    public static double findMax(List<Double> list) {
        return Math.round(Collections.max(list)*100.0)/100.0;
    }
    
    public static double throughput(int requests, double time){
        return Math.round((requests/time)*100.0)/100.0;
    }
}
