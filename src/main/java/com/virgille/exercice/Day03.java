package com.virgille.exercice;

import com.virgille.Solution;

import java.util.List;

public class Day03 implements Solution {
    @Override
    public String part1(List<String> input) {
        int answer=0;
        for(String line : input){
            int num1=0;
            int num2=0;
            int len = line.length();


            for(int i=0;i<len;i++){
                int workInt=Character.getNumericValue(line.charAt(i));
                if(workInt>num1 && i<len-1){
                    num1=workInt;
                    num2=Character.getNumericValue(line.charAt(i+1));
                } else if (workInt>num2) {
                    num2=workInt;
                }
            }
            answer += Integer.valueOf(num1+""+num2);

        }
        return String.valueOf(answer);
    }

    @Override
    public String part2(List<String> input) {
        return "";
    }
}
