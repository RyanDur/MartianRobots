package martianRobots.robots;

import martianRobots.exceptions.ValidationException;

import static martianRobots.lang.Constants.EAST;
import static martianRobots.lang.Constants.NORTH;
import static martianRobots.lang.Constants.SOUTH;

class F extends RobotImpl {

    public F(final int x, final int y, final char orientation) throws ValidationException {
        super(x, y, orientation);
        setMove(x, y);
    }

    private void setMove(final int x, final int y) {
        if (getOrientation() == NORTH) setY.accept(y + 1);
        else if (getOrientation() == SOUTH) setY.accept(y - 1);
        else if (getOrientation() == EAST) setX.accept(x + 1);
        else setX.accept(x - 1);
    }
}
