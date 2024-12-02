package day2;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;
import static utils.InputReader.readInput;

public class Part_2 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY2, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 2.2: " + solution(input));
    }

    private static int solution(List<String> input) {
        int safeReports = 0;
        final List<List<Integer>> reports = getReports(input);
        for (List<Integer> report : reports) {
            if (isReportSafe(report) || problemDampener(report)) {
                safeReports++;
            }
        }
        return safeReports;
    }

    private static boolean problemDampener(List<Integer> report) {
        final List<List<Integer>> subreports = getPossibleSubReports(report);
        for (List<Integer> subReport : subreports) {
            if (isReportSafe(subReport)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isReportSafe(List<Integer> report) {
        return isIncreasingMonothone(report) || isDecreasingMonothone(report);
    }

    private static boolean isIncreasingMonothone(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) <= report.get(i - 1) || !checkPair(report.get(i), report.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDecreasingMonothone(List<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            if (report.get(i) >= report.get(i - 1) || !checkPair(report.get(i), report.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkPair(int n1, int n2) {
        return abs(n1 - n2) >= 1 && abs(n1 - n2) <= 3;
    }

    private static List<List<Integer>> getReports(List<String> input) {
        final List<List<Integer>> reports = new ArrayList<>();
        for (String line : input) {
            final List<Integer> report = new ArrayList<>(Arrays.stream(line.split(" "))
                    .map(Integer::valueOf)
                    .toList());
            reports.add(report);
        }
        return reports;
    }

    private static List<List<Integer>> getPossibleSubReports(List<Integer> report) {
        final List<List<Integer>> subReports = new ArrayList<>();
        for (int i = 0; i < report.size(); i++) {
            final List<Integer> subReport = new ArrayList<>(report);
            subReport.remove(i);
            subReports.add(subReport);
        }
        return subReports;
    }
}
