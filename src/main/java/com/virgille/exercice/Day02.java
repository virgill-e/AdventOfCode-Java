package com.virgille.exercice;

import com.virgille.Solution;

import java.util.List;

public class Day02 implements Solution {
    @Override
    public String part1(List<String> input) {
        long answer=0;
        input = List.of(input.get(0).split(","));
        for(String line : input){
            long start=Long.valueOf(line.split("-")[0]);
            long end=Long.valueOf(line.split("-")[1]);
            for(long i=start;i<=end;i++){
                String id=String.valueOf(i);
                int lengthId=id.length();
                if(lengthId%2!=0) continue;
                if(id.substring(0,lengthId/2).equals(id.substring(lengthId/2, lengthId))){
                    String part1=id.substring(0,lengthId/2);
                    String part2= id.substring(lengthId/2, lengthId);
                    //System.out.println(part1 + " "+ part2);
                    answer+=Long.valueOf(id);
                }
            }

        }
        return String.valueOf(answer);
    }

    @Override
    public String part2(List<String> input) {
        return "";
    }
}
