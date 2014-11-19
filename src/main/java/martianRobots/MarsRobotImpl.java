package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;
import martianRobots.lang.Constants;

import java.util.Arrays;

public class MarsRobotImpl implements MarsRobot {

    private int row;
    private int column;
    private int gridRow;
    private int gridColumn;
    private char orientation;

    @Override
    public void setup(int row, int column) throws InvalidGridSizeException {
        if (invalidGridSize(row, column)) throw new InvalidGridSizeException(row, column);
        gridRow = row;
        gridColumn = column;
    }

    @Override
    public void setPosition(int row, int column, char orientation) throws InvalidMoveException {
        if (outOfBounds(row, column) || invalidOrientation(orientation))
            throw new InvalidMoveException(row, column, orientation);
        this.row = row;
        this.column = column;
        this.orientation = orientation;
    }

    @Override
    public String getPosition() {
        return row + " " + column + " " + orientation;
    }

    private boolean outOfBounds(int row, int column) {
        return row < 0 || row > gridRow || column < 0 || column > gridColumn;
    }

    private boolean invalidOrientation(char orientation) {
        return !Arrays.asList(Constants.EAST, Constants.NORTH, Constants.SOUTH, Constants.WEST).contains(orientation);
    }

    private boolean invalidGridSize(int row, int column) {
        return row > Constants.MAX_SIZE || row < 0 || column > Constants.MAX_SIZE || column < 0;
    }
}
