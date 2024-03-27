package com.skiResortManagement.util;

import java.util.Random;

public class RandomGenerator {
    public static int randomNumber(int start,int end, int errorProbability){
        Random random = new Random();

        int rangeDeterminer = random.nextInt(100) + 1;

        int randomNumber;

        if (rangeDeterminer <= 100-errorProbability){
            randomNumber = random.nextInt(end) + 1;
        }
        else{
            randomNumber = end + 1;
        }

        return randomNumber;
    }
}
