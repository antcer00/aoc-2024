package day6;

import utils.DaysEnum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static utils.InputReader.readInput;

public class Part_1 {

    public static void main(String[] args) {
        final List<String> input;
        try {
            input = readInput(DaysEnum.DAY6, ArrayList::new);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solution for Day 6.1: " + solution(input));
    }

    private static int solution(List<String> input) {
        final List<List<Position>> grid = toGrid(input);
        Optional<Position> currentPosition = getStartingPosition(grid);
        while (currentPosition.isPresent()) {
            currentPosition = advance(currentPosition.get(), grid);
        }
        return (int) countVisitedPositions(grid);
    }

    private static Optional<Position> advance(Position currentPosition, List<List<Position>> grid) {
        int nextY, nextX;
        switch (currentPosition.getContent()) {
            case '^' -> {
                nextY = currentPosition.getyPos() - 1;
                nextX = currentPosition.getxPos();
                if (isOutsideGrid(nextX, nextY, grid)) {
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.empty();
                } else if (grid.get(nextY).get(nextX).isObstacle()) {
                    grid.get(nextY + 1).get(nextX + 1).setContent('>');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY + 1).get(nextX + 1));
                } else {
                    grid.get(nextY).get(nextX).setContent('^');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY).get(nextX));
                }
            }
            case '>' -> {
                nextY = currentPosition.getyPos();
                nextX = currentPosition.getxPos() + 1;
                if (isOutsideGrid(nextX, nextY, grid)) {
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.empty();
                } else if (grid.get(nextY).get(nextX).isObstacle()) {
                    grid.get(nextY + 1).get(nextX - 1).setContent('v');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY + 1).get(nextX - 1));
                } else {
                    grid.get(nextY).get(nextX).setContent('>');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY).get(nextX));
                }
            }
            case 'v' -> {
                nextY = currentPosition.getyPos() + 1;
                nextX = currentPosition.getxPos();
                if (isOutsideGrid(nextX, nextY, grid)) {
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.empty();
                } else if (grid.get(nextY).get(nextX).isObstacle()) {
                    grid.get(nextY - 1).get(nextX - 1).setContent('<');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY - 1).get(nextX - 1));
                } else {
                    grid.get(nextY).get(nextX).setContent('v');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY).get(nextX));
                }
            }
            case '<' -> {
                nextY = currentPosition.getyPos();
                nextX = currentPosition.getxPos() - 1;
                if (isOutsideGrid(nextX, nextY, grid)) {
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.empty();
                } else if (grid.get(nextY).get(nextX).isObstacle()) {
                    grid.get(nextY - 1).get(nextX + 1).setContent('^');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY - 1).get(nextX + 1));
                } else {
                    grid.get(nextY).get(nextX).setContent('<');
                    grid.get(currentPosition.getyPos()).get(currentPosition.getxPos()).setVisited(true);
                    return Optional.of(grid.get(nextY).get(nextX));
                }
            }
        }
        return Optional.empty();
    }

    private static Optional<Position> getStartingPosition(List<List<Position>> grid) {
        return grid.stream()
                .flatMap(Collection::stream)
                .filter(Position::isGuard)
                .findFirst();
    }

    private static List<List<Position>> toGrid(List<String> input) {
        final List<List<Position>> grid = new ArrayList<>();
        for (int yPos = 0; yPos < input.size(); yPos++) {
            final List<Position> row = new ArrayList<>();
            for (int xPos = 0; xPos < input.get(0).length(); xPos++) {
                row.add(new Position(xPos, yPos, input.get(yPos).charAt(xPos)));
            }
            grid.add(row);
        }
        return grid;
    }

    private static boolean isOutsideGrid(int xPos, int yPos, List<List<Position>> grid) {
        return xPos >= grid.get(0).size() || yPos >= grid.size() || xPos < 0 || yPos < 0;
    }

    private static long countVisitedPositions(List<List<Position>> grid) {
        return grid.stream()
                .flatMap(Collection::stream)
                .filter(Position::isVisited)
                .count();
    }

}
