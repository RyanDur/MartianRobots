package martianRobots.lang;

import java.util.Arrays;
import java.util.List;

public interface Constants {
    char NORTH = 'N';
    char EAST = 'E';
    char SOUTH = 'S';
    char WEST = 'W';
    String NOT_EXISTS = "Does not exist, please use another.";
    int MAX_SIZE = 50;
    String INVALID_GRID_SIZE = "is an invalid size, please use another.";
    char FORWARD = 'F';
    char RIGHT = 'R';
    char LEFT = 'L';
    List<Character> COMPASS = Arrays.asList(WEST, NORTH, EAST, SOUTH);
    String LOST = "LOST";
    String INVALID_INSTRUCTIONS = "are invalid, please correct your input.";
    int MAX_INSTRUCTION_SIZE = 100;
}
