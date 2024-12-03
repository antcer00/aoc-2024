package day3;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static utils.InputReader.readInput;

public class Part_1 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY3, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 3.1: " + solution(input));
    }

    private static int solution(List<String> input) {
        int result = 0;
        for (String line : input) {
            final Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                result += performMultiplication(matcher.group());
            }
        }
        return result;
    }

    private static int performMultiplication(String mul) {
        final String[] numbers = mul.replaceAll("[^0-9]+", " ").trim().split(" ");
        return parseInt(numbers[0]) * parseInt(numbers[1]);
    }
}
