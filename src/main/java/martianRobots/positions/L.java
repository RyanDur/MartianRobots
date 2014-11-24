package martianRobots.positions;

import martianRobots.exceptions.ValidationException;

import static martianRobots.lang.Constants.COMPASS;

class L extends PositionImpl {
    public L(final int x, final int y, final char orientation) throws ValidationException {
        super(x, y, orientation);
        int index = COMPASS.indexOf(orientation);
        setOrientation.accept(COMPASS.get(index - 1 < 0 ? COMPASS.size() - 1 : index - 1));
    }
}
