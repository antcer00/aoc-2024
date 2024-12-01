package day1;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.util.Collections.sort;
import static java.util.stream.IntStream.range;
import static utils.InputReader.readInput;

public class Part_1 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY1, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 1.1: " + solution(input));
    }

    private static int solution(List<String> input) {
        final List<Integer> firstList = new ArrayList<>();
        final List<Integer> secondList = new ArrayList<>();
        input.forEach(line -> {
            final String[] split = line.split("\\s+");
            firstList.add(Integer.valueOf(split[0]));
            secondList.add(Integer.valueOf(split[1]));
        });
        sort(firstList);
        sort(secondList);
        return computeDistance(firstList, secondList);
    }

    public static int computeDistance(List<Integer> firstList, List<Integer> secondList) {
        return range(0, min(firstList.size(), secondList.size()))
                .map(index -> abs(firstList.get(index) - secondList.get(index)))
                .sum();
    }

}
