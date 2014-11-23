package martianRobots.positions;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.DOT;

public class PositionImpl implements Position {
    private Supplier<Integer> x;
    private Supplier<Integer> y;
    private Supplier<List<Integer>> location;
    private Supplier<Character> orientation;

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
                    .getDeclaredConstructor(List.class, char.class).newInstance(location.get(), orientation.get());
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return this;
        }
    }

    @Override
    public String toString() {
        return Stream.of(x, y, orientation).map(word -> word.get().toString()).collect(joining(" "));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionImpl position = (PositionImpl) o;
        return location.get().equals(position.getLocation()) && orientation.get().equals(position.getOrientation());
    }

    @Override
    public int hashCode() {
        int result = location != null ? location.get().hashCode() : 0;
        result = 31 * result + (orientation != null ? orientation.get().hashCode() : 0);
        return result;
    }

    Consumer<List<Integer>> setLocation = loc -> {
        x = () -> loc.get(0);
        y = () -> loc.get(1);
        location = () -> loc;
    };

    Consumer<Character> setOrientation = orientation -> this.orientation = () -> orientation;
}