package martianRobots.exceptions;

import martianRobots.lang.Constants;

public class InvalidException extends Exception {
    public InvalidException(int x, int y) {
        super(x + " " + y + " " + Constants.INVALID_GRID_SIZE);
    }

    public InvalidException(String instructions) {
        super(instructions + " " + Constants.INVALID_INSTRUCTIONS);
    }

    public InvalidException(int x, int y, char direction) {
        super(x + " " + y + " " + direction + " " + Constants.NOT_EXISTS);
    }
}
