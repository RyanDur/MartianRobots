package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.DOT;
import static martianRobots.lang.Constants.INVALID_DIRECTION;
import static martianRobots.lang.Constants.SPACE;

public class RobotImpl implements Robot {
    private Supplier<Compass> orientation;
    private Supplier<Integer> x;
    private Supplier<Integer> y;

    public RobotImpl(final int x, final int y, final Compass orientation) throws ValidationException {
        setX.accept(x);
        setY.accept(y);
        setOrientation.accept(orientation);
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
        try {
            return (Robot) Class.forName(this.getClass().getPackage().getName() + DOT + direction)
                    .getDeclaredConstructor(int.class, int.class, Compass.class)
                    .newInstance(x.get(), y.get(), orientation.get());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            throw new ValidationException(direction + INVALID_DIRECTION);
        }
    }

    @Override
    public String toString() {
        return Stream.of(x, y, orientation).map(word -> word.get().toString()).collect(joining(SPACE));
    }

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