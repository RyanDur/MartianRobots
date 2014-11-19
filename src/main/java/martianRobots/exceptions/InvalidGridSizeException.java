package martianRobots.exceptions;

import martianRobots.lang.Constants;

public class InvalidGridSizeException extends Exception {
    public InvalidGridSizeException(int row, int column) {
        super(row + " " + column + " " + Constants.INVALID_GRID_SIZE);
    }
}
