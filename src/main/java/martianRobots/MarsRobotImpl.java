package martianRobots;

import martianRobots.exceptions.InvalidMoveException;

public class MarsRobotImpl implements MarsRobot {

    private int row;
    private int column;
    private int gridRow;
    private int gridColumn;
    private char orientation;

    @Override
    public void setup(int row, int column) {
        gridRow = row;
        gridColumn = column;
    }

    @Override
    public void setPosition(int row, int column, char orientation) throws InvalidMoveException {
        if (outOfBounds(row, column)) throw new InvalidMoveException();
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
}
