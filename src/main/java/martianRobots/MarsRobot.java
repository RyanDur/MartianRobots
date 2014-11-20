package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidInstructions;
import martianRobots.exceptions.InvalidMoveException;

public interface MarsRobot {

    void setPosition(int x, int y, char orientation) throws InvalidMoveException;

    String getPosition();

    void setup(int row, int column) throws InvalidGridSizeException;

    void move(String move) throws InvalidInstructions;
}
