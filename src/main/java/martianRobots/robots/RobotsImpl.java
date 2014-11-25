package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;

public class RobotsImpl implements Robots {
    @Override
    public Robot createRobot(char direction, int x, int y, Compass compass, Robots robots) throws ValidationException {
        switch (direction) {
            case 'F':
                return new F(x, y, compass, robots);
            case 'L':
                return new L(x, y, compass, robots);
            case 'R':
                return new R(x, y, compass, robots);
            default:
                throw new ValidationException(direction + Constants.INVALID_DIRECTION);
        }
    }
}
