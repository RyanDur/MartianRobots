package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;

/**
 * The surface of Mars is modelled by a rectangular grid around which
 * robots are able to move according to instructions provided from Earth.
 */
public interface Mars {

    /**
     * Set the robot on the surface of Mars
     *
     * @param robot to be set on Mars
     * @throws ValidationException if the location of the robot is out of bounds or if the location is already occupied
     */
    void setRobot(Robot robot) throws ValidationException;

    /**
     * Retrieves the position of the robot that was last set on the surface.
     *
     * @return "x y orientation" or "x y orientation LOST"
     */
    String getRobot();

    /**
     * Sets up the confines of the grid that represents the surface of Mars
     *
     * @param x boundary
     * @param y boundary
     * @throws ValidationException if the x or y coordinate exceeds the maximum confines.
     */
    void setup(final int x, final int y) throws ValidationException;

    /**
     * Sends the directions to the robot on hoe it is to move across the surface.
     *
     * @param instructions for how the robot moves on the surface
     * @throws ValidationException if the instructions exceed the maximum length
     */
    void move(String instructions) throws ValidationException;
}
