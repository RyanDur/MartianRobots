package martianRobots.lang;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    final char NORTH = 'N';
    final char EAST = 'E';
    final char SOUTH = 'S';
    final char WEST = 'W';
    final String NOT_EXISTS = "Does not exist, please use another.";
    final int MAX_SIZE = 50;
    final String INVALID_GRID_SIZE = "is an invalid size, please use another.";
    final char FORWARD = 'F';
    final char RIGHT = 'R';
    final char LEFT = 'L';
    final List<Character> COMPASS = Arrays.asList(WEST, NORTH, EAST, SOUTH);
    final String LOST = "LOST";
    final String INVALID_INSTRUCTIONS = "are invalid, please correct your input.";
    final List<Integer> MAX_BOUNDS = Arrays.asList(MAX_SIZE, MAX_SIZE);
    final int MAX_INSTRUCTION_SIZE = 100;
    char DOT = '.';
    String SPACE = " ";
    String EMPTY = "";
}
