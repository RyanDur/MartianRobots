package martianRobots;

import martianRobots.exceptions.InvalidException;

public interface MarsRobot {

    void setPosition(int x, int y, char orientation) throws InvalidException;

    String getPosition();

    void setup(int row, int column) throws InvalidException;

    void move(String move) throws InvalidException;
}
