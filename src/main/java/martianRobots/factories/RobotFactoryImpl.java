package martianRobots.factories;

import martianRobots.lang.Compass;
import martianRobots.robots.Robot;
import martianRobots.robots.RobotImpl;

public class RobotFactoryImpl implements RobotFactory {

<<<<<<< HEAD
<<<<<<< HEAD
=======
    private Robots robots;

    @Inject
    public RobotFactoryImpl(Robots robots) {
        this.robots = robots;
    }

>>>>>>> working di
=======
>>>>>>> remove robot factory
    @Override
    public Robot createRobot(int x, int y, Compass orientation) {
        return new RobotImpl(x, y, orientation);
    }
}
