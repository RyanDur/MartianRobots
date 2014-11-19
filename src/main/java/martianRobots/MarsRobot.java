package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;

public interface MarsRobot {

    void setPosition(int row, int column, char orientation) throws InvalidMoveException;

    String getPosition();

    void setup(int row, int column) throws InvalidGridSizeException;
}
