package com.virgille;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import com.virgille.exercice.*;
import com.virgille.utils.InputParser;

import java.util.List;

public class Main {
    static void main() {
        for (int day = 1; day <= 25; day++) {
            String dayClassName = String.format("com.virgille.exercice.Day%02d", day);
            String inputPath = String.format("src/main/resources/day%02d.txt", day);

            try {
                Solution solution = (Solution) Class.forName(dayClassName).getDeclaredConstructor().newInstance();

                List<String> input = InputParser.readLines(inputPath);

                long startPart1 = System.nanoTime();
                String part1 = solution.part1(input);
                long endPart1 = System.nanoTime();

                long startPart2 = System.nanoTime();
                String part2 = solution.part2(input);
                long endPart2 = System.nanoTime();

                System.out.printf(
                        "Day %02d - Part 1: %s (%d ms)\n",
                        day,
                        part1,
                        (endPart1 - startPart1) / 1_000_000
                );
                System.out.printf(
                        "Day %02d - Part 2: %s (%d ms)\n",
                        day,
                        part2,
                        (endPart2 - startPart2) / 1_000_000
                );
            } catch (Exception e) {
                System.out.printf("Day %02d: ERROR - %s\n", day, e.getMessage());
            }
        }
    }
}

