package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

import java.util.List;

public interface Robot {
    List<Integer> getLocation();

    Compass getOrientation();

    Robot move(char direction) throws ValidationException;
}
