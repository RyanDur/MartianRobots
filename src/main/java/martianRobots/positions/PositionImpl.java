package martianRobots.positions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.DOT;
import static martianRobots.lang.Constants.SPACE;

public class PositionImpl implements Position {
    private Supplier<Character> orientation;
    private Supplier<List<Integer>> location;

    public PositionImpl(List<Integer> location, char orientation) {
        setOrientation.accept(orientation);
        setLocation.accept(location);
    }

    @Override
    public List<Integer> getLocation() {
        return location.get();
    }

    @Override
    public Character getOrientation() {
        return orientation.get();
    }

    @Override
    public Position move(final char direction) {
        try {
            return (Position) Class.forName(this.getClass().getPackage().getName() + DOT + direction)
                    .getDeclaredConstructor(List.class, char.class).newInstance(getLocation(), getOrientation());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return this;
        }
    }

    @Override
    public String toString() {
        return location.get().stream().map(Object::toString).collect(joining(SPACE))
                .concat(SPACE).concat(orientation.get().toString());
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

    Consumer<List<Integer>> setLocation = loc -> location = () -> loc;
    Consumer<Character> setOrientation = orientation -> this.orientation = () -> orientation;
}