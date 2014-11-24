package martianRobots.positions;

import martianRobots.exceptions.ValidationException;

import java.util.List;

public interface Position {
    List<Integer> getLocation();

    Character getOrientation();

    Position move(char direction) throws ValidationException;
}
