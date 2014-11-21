package martianRobots;

import martianRobots.exceptions.InvalidException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {
    private Supplier<String> lost;
    private Supplier<Character> orientation;
    private Set<List<Integer>> scents;
    private Predicate<List<Integer>> isOutOfBounds;
    private Supplier<List<Integer>> coordinates;


    @Override
    public void setup(List<Integer> bounds) throws InvalidException {
        if (isInvalidSize.test(bounds)) throw new InvalidException(bounds);
        setBounds.accept(bounds);
        scents = new HashSet<>();
    }

    @Override
    public void move(final String instructions) throws InvalidException {
        if (isInvalid.test(instructions)) throw new InvalidException(instructions);
        for (int i = 0; i < instructions.length() && !lost.get().equals(LOST); i++) {
            char direction = instructions.charAt(i);
            if (direction == FORWARD) moveForward();
            else turn(direction);
        }
    }

    @Override
    public void setPosition(final List<Integer> location, final char orientation) throws InvalidException {
        if (isOutOfBounds.test(location) || !COMPASS.contains(orientation))
            throw new InvalidException(location, orientation);
        setLost.accept("");
        setCoordinates.accept(location);
        setOrientation.accept(orientation);
    }

    @Override
    public String getPosition() {
        return Stream.of(coordinates, orientation, lost).map(word -> word.get().toString())
                .collect(joining(" ")).replaceAll("\\[*\\]*\\,*", "").trim();
    }

    private void moveForward() {
        List<Integer> newPos = getMove(coordinates);
        if (!scents.contains(newPos)) {
            if (isOutOfBounds.test(newPos)) {
                setLost.accept(LOST);
                scents.add(newPos);
            } else setCoordinates.accept(newPos);
        }
    }

    private void turn(final char direction) {
        int index = COMPASS.indexOf(orientation.get());
        setOrientation.accept(COMPASS.get(direction == RIGHT ?
                index + 1 > COMPASS.size() - 1 ? 0 : index + 1 :
                index - 1 < 0 ? COMPASS.size() - 1 : index - 1));
    }

    private List<Integer> getMove(Supplier<List<Integer>> coordinates) {
        List<Integer> c = coordinates.get();
        int x = c.get(0);
        int y = c.get(1);
        if (orientation.get() == NORTH) y += 1;
        else if (orientation.get() == SOUTH) y -= 1;
        else if (orientation.get() == EAST) x += 1;
        else x -= 1;
        return Arrays.asList(x, y);
    }

    private Consumer<List<Integer>> setCoordinates = coordinates -> this.coordinates = () -> coordinates;

    private Consumer<String> setLost = message -> lost = () -> message;

    private Consumer<Character> setOrientation = orientation -> this.orientation = () -> orientation;

    private Function<List<Integer>, Predicate<List<Integer>>> getBoundary = maxLoc ->
            loc -> loc.get(0) < 0 || loc.get(0) > maxLoc.get(0) || loc.get(1) < 0 || loc.get(1) > maxLoc.get(1);

    private Consumer<List<Integer>> setBounds = location -> isOutOfBounds = getBoundary.apply(location);

    private Predicate<List<Integer>> isInvalidSize = loc -> getBoundary.apply(MAX_BOUNDS).test(loc);

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
}