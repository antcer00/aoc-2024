package day1;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static utils.InputReader.readInput;

public class Part_2 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY1, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 1.2: " + solution(input));
    }

    private static int solution(List<String> input) {
        final List<Integer> firstList = new ArrayList<>();
        final List<Integer> secondList = new ArrayList<>();
        input.forEach(line -> {
            final String[] split = line.split("\\s+");
            firstList.add(Integer.valueOf(split[0]));
            secondList.add(Integer.valueOf(split[1]));
        });
        return computeSimilarityScore(firstList, secondList);
    }

    private static int computeSimilarityScore(List<Integer> firstList, List<Integer> secondList) {
        return firstList.stream()
                .mapToInt(num -> num * countOccurencies(num, secondList))
                .filter(num -> num > 0)
                .sum();
    }

    private static int countOccurencies(int num, List<Integer> list) {
        return (int) list.stream()
                .filter(x -> x == num)
                .count();
    }

}
