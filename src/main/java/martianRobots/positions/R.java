package martianRobots.positions;

import martianRobots.exceptions.ValidationException;

import java.util.List;

import static martianRobots.lang.Constants.COMPASS;

class R extends PositionImpl {
    public R(List<Integer> location, char orientation) throws ValidationException {
        super(location, orientation);
        int index = COMPASS.indexOf(orientation);
        setOrientation.accept(COMPASS.get(index + 1 > COMPASS.size() - 1 ? 0 : index + 1));
    }
}
