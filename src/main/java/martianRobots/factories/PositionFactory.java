package martianRobots.factories;

import martianRobots.Position;

public interface PositionFactory {
    Position createPosition(int x, int y, char orientation);
}
