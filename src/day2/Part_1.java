package day2;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;
import static java.util.stream.IntStream.range;
import static utils.InputReader.readInput;

public class Part_1 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY2, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 2.1: " + solution(input));
    }

    private static int solution(List<String> input) {
        return (int) reports(input).stream()
                .filter(Part_1::isReportSafe)
                .count();
    }

    private static boolean isReportSafe(List<Integer> report) {
        return isIncreasingMonothone(report) || isDecreasingMonothone(report);
    }

    private static boolean isIncreasingMonothone(List<Integer> report) {
        return range(1, report.size())
                .noneMatch(i -> report.get(i) <= report.get(i - 1) || !checkPair(report.get(i), report.get(i - 1)));
    }

    private static boolean isDecreasingMonothone(List<Integer> report) {
        return range(1, report.size())
                .noneMatch(i -> report.get(i) >= report.get(i - 1) || !checkPair(report.get(i), report.get(i - 1)));
    }

    private static boolean checkPair(int n1, int n2) {
        return abs(n1 - n2) >= 1 && abs(n1 - n2) <= 3;
    }

    private static List<List<Integer>> reports(List<String> input) {
        return input.stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::valueOf)
                        .toList())
                .toList();
    }
}
