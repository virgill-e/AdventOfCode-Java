package com.virgille.exercice;

import com.virgille.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day06 implements Solution{

    @Override
    public String part1(List<String> input) {
        if (input == null || input.isEmpty()) return "0";
        List<String> mutableInput = new ArrayList<>(input);

        String lastLine = mutableInput.remove(mutableInput.size() - 1).trim();
        String[] operations = lastLine.split("\\s+");

        List<Calcul> answers = new ArrayList<>();
        for (String op : operations) {
            answers.add(new Calcul(op.charAt(0)));
        }

        for (String line : mutableInput) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] elems = line.split("\\s+");
            for (int i = 0; i < elems.length && i < answers.size(); i++) {
                answers.get(i).add(Long.parseLong(elems[i]));
            }
        }
        return String.valueOf(answers.stream().mapToLong(Calcul::getVal).sum());
    }

    @Override
    public String part2(List<String> input) {
        if (input == null || input.isEmpty()) return "0";
        List<String> mutableInput = new ArrayList<>(input);

        String opLine = mutableInput.remove(mutableInput.size() - 1);
        int maxLen = 0;
        for (String line : mutableInput) {
            maxLen = Math.max(maxLen, line.length());
        }
        maxLen = Math.max(maxLen, opLine.length());

        long grandTotal = 0;

        int currentCol = maxLen - 1;
        while (currentCol >= 0) {
            int opIndex = -1;

            for (int j = currentCol; j >= 0; j--) {
                if (j < opLine.length()) {
                    char c = opLine.charAt(j);
                    if (c == '+' || c == '-' || c == '*' || c == '/') {
                        opIndex = j;
                        break;
                    }
                }
            }

            if (opIndex == -1) break;

            char operation = opLine.charAt(opIndex);
            Calcul blocCalcul = new Calcul(operation);

            for (int col = currentCol; col >= opIndex; col--) {
                StringBuilder numStr = new StringBuilder();
                for (String line : mutableInput) {
                    if (col < line.length()) {
                        char digit = line.charAt(col);
                        if (Character.isDigit(digit)) {
                            numStr.append(digit);
                        }
                    }
                }
                if (!numStr.isEmpty()) {
                    blocCalcul.add(Long.parseLong(numStr.toString()));
                }
            }

            grandTotal += blocCalcul.getVal();
            currentCol = opIndex - 1;
        }

        return String.valueOf(grandTotal);
    }

    private static class Calcul {
        private long val;
        private final char operation;
        private boolean firstValue = true;

        public Calcul(char operation) {
            this.operation = operation;
        }

        public void add(long number) {
            if (firstValue) {
                val = number;
                firstValue = false;
            } else {
                if (operation == '+') {
                    val += number;
                } else if (operation == '-') {
                    val -= number;
                } else if (operation == '*') {
                    val *= number;
                } else if (operation == '/') {
                    if (number != 0) val /= number;
                }
            }
        }

        public long getVal() {
            return val;
        }
    }
}
