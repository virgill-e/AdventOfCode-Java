package com.virgille.exercice;

import com.virgille.Solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day07 implements Solution {
    @Override
    public String part1(List<String> input) {
        int answer = 0;
        Set<Integer> positions = new HashSet<>();
        positions.add(input.get(0).indexOf("S"));
        for(String line : input){
            if (line.indexOf("S") == -1) {
                Set<Integer> newPositions = new HashSet<>();
                for(int pos:positions){
                    if(line.charAt(pos)=='^'){
                        answer++;
                        newPositions.add(pos-1);
                        newPositions.add(pos+1);
                    }else{
                        newPositions.add(pos);
                    }
                }
                if(newPositions.size()>0){
                    positions=Set.copyOf(newPositions);
                }
            }
        }
        return String.valueOf(answer);
    }

    @Override
    public String part2(List<String> input) {
        Map<Integer, Long> timelines = new HashMap<>();
        timelines.put(input.get(0).indexOf("S"), 1L);
        for(String line : input){
            if (line.indexOf("S") == -1) {
                Map<Integer, Long> newTimelines = new HashMap<>();
                for(Map.Entry<Integer, Long> entry : timelines.entrySet()){
                    int pos = entry.getKey();
                    long count = entry.getValue();
                    if(line.charAt(pos)=='^'){
                        addTimelines(newTimelines, pos-1, count);
                        addTimelines(newTimelines, pos+1, count);
                    }else{
                        addTimelines(newTimelines, pos, count);
                    }
                }
                if(newTimelines.size()>0){
                    timelines=newTimelines;
                }
            }
        }
        long answer = 0;
        for(long count : timelines.values()){
            answer += count;
        }
        return String.valueOf(answer);
    }

    private void addTimelines(Map<Integer, Long> map, int pos, long count) {
        long previousCount = 0;
        if(map.containsKey(pos)){
            previousCount = map.get(pos);
        }
        map.put(pos, previousCount + count);
    }
}
