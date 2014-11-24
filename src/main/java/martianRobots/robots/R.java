package martianRobots.robots;

import martianRobots.exceptions.ValidationException;

import static martianRobots.lang.Constants.COMPASS;

class R extends RobotImpl {
    public R(final int x, final int y, final char orientation) throws ValidationException {
        super(x, y, orientation);
        int index = COMPASS.indexOf(orientation);
        setOrientation.accept(COMPASS.get(index + 1 > COMPASS.size() - 1 ? 0 : index + 1));
    }
}
