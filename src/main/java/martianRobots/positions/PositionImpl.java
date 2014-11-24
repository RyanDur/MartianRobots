package martianRobots.positions;

import martianRobots.exceptions.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.COMPASS;
import static martianRobots.lang.Constants.DOT;
import static martianRobots.lang.Constants.INVALID_DIRECTION;
import static martianRobots.lang.Constants.NOT_EXISTS;
import static martianRobots.lang.Constants.SPACE;
import static martianRobots.lang.Constants.VALID_DIRECTIONS;

public class PositionImpl implements Position {
    private Supplier<Character> orientation;
    private Supplier<Integer> x;
    private Supplier<Integer> y;

    public PositionImpl(final int x, final int y, final char orientation) throws ValidationException {
        if (!COMPASS.contains(orientation)) throw new ValidationException(orientation + NOT_EXISTS);
        setX.accept(x);
        setY.accept(y);
        setOrientation.accept(orientation);
    }

    @Override
    public List<Integer> getLocation() {
        return Arrays.asList(x.get(), y.get());
    }

    @Override
    public Character getOrientation() {
        return orientation.get();
    }

    @Override
    public Position move(final char direction) throws ValidationException {
        if (!VALID_DIRECTIONS.contains(direction)) throw new ValidationException(direction + INVALID_DIRECTION);
        try {
            return (Position) Class.forName(this.getClass().getPackage().getName() + DOT + direction)
                    .getDeclaredConstructor(int.class, int.class, char.class)
                    .newInstance(x.get(), y.get(), orientation.get());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return this;
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
        PositionImpl position = (PositionImpl) o;
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
    Consumer<Character> setOrientation = orientation -> this.orientation = () -> orientation;
}