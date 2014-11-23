package martianRobots.positions;

import java.util.List;

public interface Position {
    List<Integer> getLocation();

    Character getOrientation();

    Position move(char direction);
}
