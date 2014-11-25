package martianRobots.factories;

import martianRobots.lang.Compass;
import martianRobots.robots.Robot;

public interface RobotFactory {
    Robot createRobot(int x, int y, Compass orientation);
}
