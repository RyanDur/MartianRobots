package martianRobots.exceptions;

import martianRobots.lang.Constants;
import martianRobots.positions.Position;

import java.util.List;

public class InvalidException extends Exception {
    public InvalidException(String instructions) {
        super(instructions + " " + Constants.INVALID_INSTRUCTIONS);
    }

    public InvalidException(List<Integer> location, char orientation) {
        super(location.get(0) + " " + location.get(1) + " " + orientation + " " + Constants.NOT_EXISTS);
    }

    public InvalidException(List<Integer> bounds) {
        super(bounds.get(0) + " " + bounds.get(1) + " " + Constants.INVALID_GRID_SIZE);
    }

    public InvalidException(Position position) {

    }
}
