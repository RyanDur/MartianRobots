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
    private Supplier<Integer> x;
    private Supplier<Integer> y;
    private Set<List<Integer>> scents;
    private BiPredicate<Integer, Integer> isOutOfBounds;

    @Override
    public void setup(int x, int y) throws InvalidException {
        if (isInvalidSize.test(x, y)) throw new InvalidException(x, y);
        setBounds.accept(x, y);
        scents = new HashSet<>();
    }

    @Override
    public void move(String instructions) throws InvalidException {
        if (isInvalid.test(instructions)) throw new InvalidException(instructions);
        for (int i = 0; i < instructions.length() && !lost.get().equals(LOST); i++) {
            char direction = instructions.charAt(i);
            if (direction == FORWARD) moveForward();
            else turn(direction);
        }
    }

    @Override
    public void setPosition(int x, int y, char orientation) throws InvalidException {
        if (isOutOfBounds.test(x, y) || !COMPASS.contains(orientation)) throw new InvalidException(x, y, orientation);
        setLost.accept("");
        setCoordinates(x, y);
        setOrientation.accept(orientation);
    }

    @Override
    public String getPosition() {
        return Stream.of(x, y, orientation, lost).map(word -> word.get().toString()).collect(joining(" ")).trim();
    }

    private void moveForward() {
        List<Integer> newPos = getMove(x.get(), y.get());
        if (!scents.contains(newPos)) {
            if (isOutOfBounds.test(newPos.get(0), newPos.get(1))) {
                setLost.accept(LOST);
                scents.add(newPos);
            } else setCoordinates(newPos.get(0), newPos.get(1));
        }
    }

    private void turn(char direction) {
        int index = COMPASS.indexOf(orientation.get());
        setOrientation.accept(COMPASS.get(direction == RIGHT ?
                index + 1 > COMPASS.size() - 1 ? 0 : index + 1 :
                index - 1 < 0 ? COMPASS.size() - 1 : index - 1));
    }

    private List<Integer> getMove(int x, int y) {
        if (orientation.get() == NORTH) y += 1;
        else if (orientation.get() == SOUTH) y -= 1;
        else if (orientation.get() == EAST) x += 1;
        else x -= 1;
        return Arrays.asList(x, y);
    }

    private void setCoordinates(int x, int y) {
        this.x = () -> x;
        this.y = () -> y;
    }

    private Consumer<String> setLost = message -> lost = () -> message;

    private Consumer<Character> setOrientation = orientation -> this.orientation = () -> orientation;

    private BiFunction<Integer, Integer, BiPredicate<Integer, Integer>> getBoundary = (maxX, maxY) ->
            (x, y) -> x < 0 || x > maxX || y < 0 || y > maxY;

    private BiConsumer<Integer, Integer> setBounds = (x, y) -> isOutOfBounds = getBoundary.apply(x, y);

    private BiPredicate<Integer, Integer> isInvalidSize = (x, y) -> getBoundary.apply(MAX_SIZE, MAX_SIZE).test(x, y);

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
}