package martianRobots.factories;

import martianRobots.lang.Compass;
import martianRobots.robots.Robot;
import martianRobots.robots.RobotImpl;

public class RobotFactoryImpl implements RobotFactory {

    @Override
    public Robot createRobot(int x, int y, Compass orientation) {
        return new RobotImpl(x, y, orientation);
    }
}
