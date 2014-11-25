package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

public interface Robots {
    Robot createRobot(char direction, int x, int y, Compass compass) throws ValidationException;
}
