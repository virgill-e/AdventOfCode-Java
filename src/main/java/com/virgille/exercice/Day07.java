package com.virgille.exercice;

import com.virgille.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day07 implements Solution {

    private static final Pattern RULES = Pattern.compile("\\d+");

    @Override
    public String part1(List<String> input) {
        Long solution = 0L;
        for (String s : input) {
            List<Long> list = getNumbers(s);
            Long objectif = list.getFirst();
            if(isSum(list.getFirst(), 0L, list.subList(1, list.size()), false)) {
                solution += objectif;
            }
        }

        return String.valueOf(solution);
    }

    @Override
    public String part2(List<String> input) {
        Long solution = 0L;
        for (String s : input) {
            List<Long> list = getNumbers(s);
            Long objectif = list.getFirst();
            if(isSum(list.getFirst(), 0L, list.subList(1, list.size()), true)) {
                solution += objectif;
            }
        }

        return String.valueOf(solution);
    }

    private List<Long> getNumbers(String s) {
        List<Long> list = new ArrayList<>();
        Matcher nombres = RULES.matcher(s);
        while (nombres.find()) {
            list.add(Long.parseLong(nombres.group()));
        }
        return list;
    }


    private boolean isSum(Long objectif ,Long actual, List<Long> toAdd, boolean doConcat) {
        if(actual > objectif){
            return false;
        }
        if(toAdd.isEmpty()){
            return objectif.equals(actual);
        }
        boolean isSum=isSum(objectif,actual+toAdd.getFirst(), toAdd.subList(1,toAdd.size()),doConcat);
        boolean isMultiply = isSum(objectif,actual*toAdd.getFirst(), toAdd.subList(1,toAdd.size()),doConcat);
        if(doConcat){
            boolean isConcat = isSum(objectif,Long.parseLong(actual.toString()+toAdd.getFirst().toString()), toAdd.subList(1,toAdd.size()),doConcat);
            return isSum || isMultiply || isConcat;
        }
        return isSum || isMultiply;
    }
}

