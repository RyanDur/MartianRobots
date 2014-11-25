package martianRobots.factories;

import com.google.inject.Inject;
import martianRobots.lang.Compass;
import martianRobots.robots.Robot;
import martianRobots.robots.RobotImpl;
import martianRobots.robots.Robots;

public class RobotFactoryImpl implements RobotFactory {

<<<<<<< HEAD
=======
    private Robots robots;

    @Inject
    public RobotFactoryImpl(Robots robots) {
        this.robots = robots;
    }

>>>>>>> working di
    @Override
    public Robot createRobot(int x, int y, Compass orientation) {
        return new RobotImpl(x, y, orientation, robots);
    }
}
