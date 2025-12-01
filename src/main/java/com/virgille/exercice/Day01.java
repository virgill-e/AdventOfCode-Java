package com.virgille.exercice;

import com.virgille.Solution;

import java.util.List;

public class Day01 implements Solution {

    //constante
    private static final int DIAL_INIT = 50;


    @Override
    public String part1(List<String> input) {
        int password = 0;
        int dial = DIAL_INIT;
        for(String line : input) {
            if(line.charAt(0) == 'R'){
                dial += Integer.parseInt(line.substring(1));
                dial = ((dial % 100) + 100) % 100;
            } else if(line.charAt(0) == 'L'){
                dial -= Integer.parseInt(line.substring(1));
                dial = ((dial % 100) + 100) % 100;
            }
            if(dial == 0){
                password++;
            }
        }
        return String.valueOf(password);
    }

    @Override
    public String part2(List<String> input) {
        int password = 0;
        int dial = 50;

        char direction;
        int numberOfSpin = 0;

        for(String line : input) {
            direction = line.charAt(0);
            numberOfSpin = Integer.parseInt(line.substring(1));

            password += numberOfSpin/100;
            numberOfSpin = numberOfSpin%100;

            boolean startAtZero = (dial == 0)||(dial == 100);

            if(line.charAt(0) == 'R'){
                dial = dial + numberOfSpin;
                if(dial > 99 && dial != 100){
                    if(!startAtZero){
                        password++;
                    }
                    dial = ((dial % 100) + 100) % 100;
                }

            } else if(line.charAt(0) == 'L'){
                dial -= numberOfSpin;
                if(dial < 0 && dial != 0){
                    if(!startAtZero){
                        password++;
                    }
                    dial = ((dial % 100) + 100) % 100;
                }
            }

            if(dial == 0 || dial == 100){
                password++;
            }

        }
        return String.valueOf(password);
    }
}
