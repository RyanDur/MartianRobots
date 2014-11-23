package martianRobots.positions;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.*;

public class PositionImpl implements Position {
    private final Supplier<Integer> x;
    private final Supplier<Integer> y;
    private final Supplier<List<Integer>> location;
    private final Supplier<Character> orientation;

    public PositionImpl(List<Integer> coordinates, char orientation) {
        location = () -> coordinates;
        x = ()-> coordinates.get(0);
        y = ()-> coordinates.get(1);
        this.orientation = () -> orientation;
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
    public Position move(char direction) {
        if (direction == FORWARD) return new PositionImpl(getMove(x.get(), y.get()), getOrientation());
        else return new PositionImpl(getLocation(), turn(direction));
    }

    @Override
    public String toString() {
        return Stream.of(x, y, orientation).map(word -> word.get().toString()).collect(joining(" "));
    }

    @Override
    public boolean equals(Object o) {
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

    private Character turn(final char direction) {
        int index = COMPASS.indexOf(orientation.get());
        return COMPASS.get(direction == RIGHT ?
                index + 1 > COMPASS.size() - 1 ? 0 : index + 1 :
                index - 1 < 0 ? COMPASS.size() - 1 : index - 1);
    }

    private List<Integer> getMove(int x, int y) {
        if (orientation.get() == NORTH) return Arrays.asList(x, y + 1);
        else if (orientation.get() == SOUTH) return Arrays.asList(x, y - 1);
        else if (orientation.get() == EAST) return Arrays.asList(x + 1, y);
        else return Arrays.asList(x - 1, y);
    }
}
