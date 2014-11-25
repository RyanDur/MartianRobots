package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

import static martianRobots.lang.Compass.E;
import static martianRobots.lang.Compass.N;
import static martianRobots.lang.Compass.S;

class F extends RobotImpl {

    public F(final int x, final int y, final Compass orientation) throws ValidationException {
        super(x, y, orientation);
        setMove(x, y);
    }

    private void setMove(final int x, final int y) {
        if (getOrientation() == N) setY.accept(y + 1);
        else if (getOrientation() == S) setY.accept(y - 1);
        else if (getOrientation() == E) setX.accept(x + 1);
        else setX.accept(x - 1);
    }
}
