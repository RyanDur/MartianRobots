package martianRobots.exceptions;

import martianRobots.lang.Constants;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(int row, int column, char orientation) {
        super(row + " " + column + " " + orientation + " " + Constants.NOT_EXISTS);
    }
}
