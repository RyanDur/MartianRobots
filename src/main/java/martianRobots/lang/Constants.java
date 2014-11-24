package martianRobots.lang;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    final char NORTH = 'N';
    final char EAST = 'E';
    final char SOUTH = 'S';
    final char WEST = 'W';
    final char FORWARD = 'F';
    final char RIGHT = 'R';
    final char LEFT = 'L';
    final int MAX_SIZE = 50;
    final int MAX_INSTRUCTION_SIZE = 100;
    final List<Character> COMPASS = Arrays.asList(WEST, NORTH, EAST, SOUTH);
    final List<Character> VALID_INSTRUCTIONS = Arrays.asList(FORWARD, RIGHT, LEFT);
    final List<Integer> MAX_BOUNDS = Arrays.asList(MAX_SIZE, MAX_SIZE);
    final char DOT = '.';
    final String SPACE = " ";
    final String EMPTY = "";
    final String LOST = "LOST";
    final String NOT_EXISTS = " Does not exist, please use another.";
    final String INVALID_GRID_SIZE = " is an invalid size, please use another.";
    final String INVALID_INSTRUCTIONS = " are invalid, please correct your input.";
    final String IS_TAKEN = " is occupied, please choose another";
}
