package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.SPACE;

public class RobotImpl implements Robot {
    private Supplier<Compass> orientation;
    private Supplier<Integer> x;
    private Supplier<Integer> y;
    private Robots robots;

    /**
     * To setup a robot, input an x and y coordinate to define the location
     * and an orientation the robot is facing.
     *
     * @param x           coordinate
     * @param y           coordinate
     * @param orientation for the direction the robot is facing
     */
    public RobotImpl(final int x, final int y, final Compass orientation, Robots robots) {
        setX.accept(x);
        setY.accept(y);
        setOrientation.accept(orientation);
        this.robots = robots;
    }

    @Override
    public List<Integer> getLocation() {
        return Arrays.asList(x.get(), y.get());
    }

    @Override
    public Compass getOrientation() {
        return orientation.get();
    }

    @Override
    public Robot move(final char direction) throws ValidationException {
        return robots.createRobot(direction, x.get(), y.get(), orientation.get(), robots);
    }

    /**
     * Return the location and orientation as a string
     *
     * @return "x y orientation"
     */
    @Override
    public String toString() {
        return Stream.of(x, y, orientation).map(word -> word.get().toString()).collect(joining(SPACE));
    }

    /**
     * Returns true if the objects are the same,
     * else it will return true if the location and orientation are the same.
     * It will return false if the objects are not of the same class or
     * if the location and orientation are not the same.
     *
     * @param o representing the other object
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotImpl position = (RobotImpl) o;
        return getLocation().equals(position.getLocation()) && getOrientation().equals(position.getOrientation());
    }

    @Override
    public int hashCode() {
        int result = getLocation() != null ? getLocation().hashCode() : 0;
        result = 31 * result + (getOrientation() != null ? getOrientation().hashCode() : 0);
        return result;
    }

    Consumer<Integer> setX = x -> this.x = () -> x;
    Consumer<Integer> setY = y -> this.y = () -> y;
    Consumer<Compass> setOrientation = orientation -> this.orientation = () -> orientation;
}