package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

import java.util.List;

/**
 * A robot to traverse the surface of mars.
 * A robots position consists of a grid coordinate (a pair of integers: x-coordinate followed by y-coordinate)
 * and a Compass orientation (N, S, E, W for north, south, east, and west).
 */
public interface Robot {

    /**
     * Retrieve the location of the robot
     *
     * @return the location of the robot as a pair of coordinates
     */
    List<Integer> getLocation();

    /**
     * Retrieve the direction the robot is facing.
     *
     * @return the compass orientation of the robot
     */
    Compass getOrientation();

    /**
     * To move a robot, provide a valid direction (e.g. F, L, R)
     *
     * @param direction a robot must move
     * @return a robot in the position or orientation specified by the direction
     * @throws ValidationException if the direction does not exist
     */
    Robot move(char direction) throws ValidationException;
}
