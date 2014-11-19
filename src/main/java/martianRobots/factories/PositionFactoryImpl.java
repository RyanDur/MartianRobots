package martianRobots.factories;

import martianRobots.Position;
import martianRobots.PositionImpl;

public class PositionFactoryImpl implements PositionFactory {
    @Override
    public Position createPosition(int x, int y, char orientation) {
        return new PositionImpl(x, y, orientation);
    }
}
