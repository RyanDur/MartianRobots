package martianRobots.exceptions;

import martianRobots.lang.Constants;

public class InvalidMoveException extends Exception {

    public InvalidMoveException(int x, int y, char orientation) {
        super(x + " " + y + " " + orientation + " " + Constants.NOT_EXISTS);
    }
}
