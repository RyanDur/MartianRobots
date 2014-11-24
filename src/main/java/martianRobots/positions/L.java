package martianRobots.positions;

import java.util.List;

import static martianRobots.lang.Constants.COMPASS;

class L extends PositionImpl {
    public L(List<Integer> location, char orientation) {
        super(location, orientation);
        int index = COMPASS.indexOf(orientation);
        setOrientation.accept(COMPASS.get(index - 1 < 0 ? COMPASS.size() - 1 : index - 1));
    }
}