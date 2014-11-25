package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;

class RobotsImpl implements Robots {
    @Override
    public Robot createRobot(char direction, int x, int y, Compass compass) throws ValidationException {
        switch (direction) {
            case 'F':
                return new F(x, y, compass);
            case 'L':
                return new L(x, y, compass);
            case 'R':
                return new R(x, y, compass);
            default:
                throw new ValidationException(direction + Constants.INVALID_DIRECTION);
        }
    }
}
