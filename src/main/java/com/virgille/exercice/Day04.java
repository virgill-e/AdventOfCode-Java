package com.virgille.exercice;

import com.virgille.Solution;

import java.util.List;

public class Day04 implements Solution {
    @Override
    public String part1(List<String> input) {
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };
        int answer=0;
        for(int y=0;y<input.size();y++){
            for(int x=0;x<input.get(y).length();x++){
                if (input.get(y).charAt(x) != '@') {
                    continue;
                }
                int nbNeighboor = 0;
                for (int[] dir : directions) {
                    int dy = y + dir[0];
                    int dx = x + dir[1];

                    if (dy >= 0 && dy < input.size() && dx >= 0 && dx < input.get(dy).length()) {
                        if (input.get(dy).charAt(dx) == '@') {
                            nbNeighboor++;
                        }
                    }
                }
                if(nbNeighboor<4){
                    answer++;
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
