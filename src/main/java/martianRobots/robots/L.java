package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

class L extends RobotImpl {
    public L(final int x, final int y, final Compass orientation) throws ValidationException {
        super(x, y, orientation);
        int index = Compass.valueOf(orientation.toString()).ordinal();
        setOrientation.accept(Compass.values()[index - 1 < 0 ? Compass.values().length - 1 : index - 1]);
    }
}
