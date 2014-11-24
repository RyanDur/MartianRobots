package martianRobots.robots;

import martianRobots.exceptions.ValidationException;

import java.util.List;

public interface Robot {
    List<Integer> getLocation();

    Character getOrientation();

    Robot move(char direction) throws ValidationException;
}
