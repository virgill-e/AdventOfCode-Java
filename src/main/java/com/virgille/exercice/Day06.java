package com.virgille.exercice;

import com.virgille.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day06 implements Solution {
    private static final int[][] DIRECTIONS = {
            {-1, 0},  // haut
            {0, 1},   // droite
            {1, 0},   // bas
            {0, -1}   // gauche
    };

    @Override
    public String part1(List<String> input) {
        List<List<Character>> grid = new ArrayList<>();
        generateGrid(input, grid);
        int[] posS = findPos(input);
        int posX = posS[0];
        int posY = posS[1];
        int dir = 0;
        int count = 1;

        while (!checkIfEnd(grid, dir, posX, posY)) {
            int[] move = DIRECTIONS[dir];
            int newX = posX + move[1];
            int newY = posY + move[0];

            if (grid.get(newY).get(newX) != '#') {
                if (grid.get(newY).get(newX) == '.') {
                    count++;
                }
                posX = newX;
                posY = newY;
                grid.get(posY).set(posX, 'X');
            } else {
                dir = (dir + 1) % 4;
            }
        }
        return String.valueOf(count);
    }

    @Override
    public String part2(List<String> input) {
        List<List<Character>> grid = new ArrayList<>();
        generateGrid(input, grid);
        int[] posS = findPos(input);
        int posX = posS[0];
        int posY = posS[1];
        int count = 0;

        for (int y = 0; y < grid.size(); y++) {
            for (int x = 0; x < grid.getFirst().size(); x++) {
                if ((x == posX && y == posY) || grid.get(y).get(x) == '#') continue;
                char original = grid.get(y).get(x);
                grid.get(y).set(x, '#');
                if (isTrappedInLoop(grid, posX, posY)) {
                    count++;
                }
                grid.get(y).set(x, original);
            }
        }
        return String.valueOf(count);
    }

    private boolean isTrappedInLoop(List<List<Character>> grid, int startX, int startY) {
        int x = startX, y = startY, dir = 0;
        Set<String> visited = new HashSet<>();
        while (true) {
            String state = x + "," + y + "," + dir;
            if (visited.contains(state)) return true;
            visited.add(state);

            int[] move = DIRECTIONS[dir];
            int newX = x + move[1];
            int newY = y + move[0];

            if (newX < 0 || newX >= grid.getFirst().size() || newY < 0 || newY >= grid.size()) return false;

            if (grid.get(newY).get(newX) == '#') {
                dir = (dir + 1) % 4;
            } else {
                x = newX;
                y = newY;
            }
        }
    }

    private int[] findPos(List<String> input) {
        for (int row = 0; row < input.size(); row++) {
            for (int col = 0; col < input.get(row).length(); col++) {
                if (input.get(row).charAt(col) == '^') {
                    return new int[]{col, row};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private void generateGrid(List<String> input, List<List<Character>> grid) {
        for (String line : input) {
            List<Character> row = new ArrayList<>(line.length());
            for (int i = 0; i < line.length(); i++) {
                row.add(line.charAt(i));
            }
            grid.add(row);
        }
    }

    private boolean checkIfEnd(List<List<Character>> grid, int dir, int posX, int posY) {
        int[] move = DIRECTIONS[dir];
        int newX = posX + move[1];
        int newY = posY + move[0];
        return newX < 0 || newX >= grid.getFirst().size() || newY < 0 || newY >= grid.size();
    }
}
