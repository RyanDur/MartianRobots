package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;

public interface Mars {

    void setRobot(Robot robot) throws ValidationException;

    String getRobot();

    void setup(final int x, final int y) throws ValidationException;

    void move(String move) throws ValidationException;
}
