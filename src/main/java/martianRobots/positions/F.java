package martianRobots.positions;

import martianRobots.exceptions.ValidationException;

import java.util.Arrays;
import java.util.List;

import static martianRobots.lang.Constants.EAST;
import static martianRobots.lang.Constants.NORTH;
import static martianRobots.lang.Constants.SOUTH;

class F extends PositionImpl {

    public F(List<Integer> location, char orientation) throws ValidationException {
        super(location, orientation);
        setLocation.accept(getMove(location));
    }

    private List<Integer> getMove(List<Integer> location) {
        int x = location.get(0);
        int y = location.get(1);
        if (getOrientation() == NORTH) return Arrays.asList(x, y + 1);
        else if (getOrientation() == SOUTH) return Arrays.asList(x, y - 1);
        else if (getOrientation() == EAST) return Arrays.asList(x + 1, y);
        else return Arrays.asList(x - 1, y);
    }
}
