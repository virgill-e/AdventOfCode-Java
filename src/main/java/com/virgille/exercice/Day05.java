package com.virgille.exercice;

import com.virgille.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements Solution {
    private static final Pattern RANGE_PATTERN = Pattern.compile("^(\\d+)-(\\d+)$");

    private record Range(long start, long end) {
        public boolean contains(long value) {
            return value >= start && value <= end;
        }
    }

    @Override
    public String part1(List<String> input) {
        List<Range> ranges = new ArrayList<>();
        long answer = 0;
        int i = 0;
        for (; i < input.size(); i++) {
            String line = input.get(i);
            Matcher matcher = RANGE_PATTERN.matcher(line);
            if (matcher.matches()) {
                long start = Long.parseLong(matcher.group(1));
                long end = Long.parseLong(matcher.group(2));
                ranges.add(new Range(start, end));
            } else if (line.isEmpty()) {
                i++;
                break;
            }
        }

        for (; i < input.size(); i++) {
            String line = input.get(i).trim();
            if (!line.isEmpty()) {
                    long val = Long.parseLong(line);
                    for (Range range : ranges) {
                        if (range.contains(val)) {
                            answer++;
                            break;
                        }
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
