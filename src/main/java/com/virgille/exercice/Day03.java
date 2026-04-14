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
        long answer=0;
        for (String line : input) {
            String voltage = "";
            int startIdx = 0;

                int currentPos = startIdx;
                while (voltage.length() < 12 && currentPos < line.length()) {
                    int bestDigit = -1;
                    int bestPos = -1;
                    int remainingNeeded = 12 - voltage.length() - 1;
                    for (int i = currentPos; i <= line.length() - 1 - remainingNeeded; i++) {
                        int digit = Character.getNumericValue(line.charAt(i));
                        if (digit > bestDigit) {
                            bestDigit = digit;
                            bestPos = i;
                            if (digit == 9) break;
                        }
                    }
                    if (bestPos != -1) {
                        voltage+=bestDigit;
                        currentPos = bestPos + 1;
                    } else {
                        break;
                    }
                }
            answer += Long.valueOf(voltage);
            }
        return String.valueOf(answer);
    }
}
