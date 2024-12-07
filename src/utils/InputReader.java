package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.logging.Level.SEVERE;
import static utils.DaysEnum.*;


public final class InputReader {

    private static final Logger LOGGER = Logger.getLogger(InputReader.class.getName());

    private InputReader() {
    }

    private static final Map<DaysEnum, String> INPUT_PATHS = Map.of(
            DAY1, "input/day1.txt",
            DAY2, "input/day2.txt",
            DAY3, "input/day3.txt",
            DAY4, "input/day4.txt",
            DAY6, "input/day6.txt"
    );

    public static <T extends Collection<String>> T readInput(DaysEnum daysEnum, Supplier<T> collectionFactory) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(INPUT_PATHS.get(daysEnum)))) {
            return lines.collect(Collectors.toCollection(collectionFactory));
        } catch (IOException e) {
            LOGGER.log(SEVERE, ">>> Error reading from input " + INPUT_PATHS.get(daysEnum), e);
            throw e;
        }
    }

}
