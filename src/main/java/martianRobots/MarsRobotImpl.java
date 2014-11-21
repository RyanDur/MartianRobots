package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidInstructions;
import martianRobots.exceptions.InvalidMoveException;

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

    public MarsRobotImpl() {
        scents = new HashSet<>();
    }

    @Override
    public void setup(int x, int y) throws InvalidGridSizeException {
        if (isInvalidSize.test(x, y)) throw new InvalidGridSizeException(x, y);
        setBounds.accept(x, y);
    }

    @Override
    public void move(String instructions) throws InvalidInstructions {
        if (isInvalid.test(instructions)) throw new InvalidInstructions(instructions);
        for (int i = 0; i < instructions.length() && !lost.get().equals(LOST); i++)
            execute(instructions.charAt(i));
    }

    @Override
    public void setPosition(int x, int y, char direction) throws InvalidMoveException {
        if (isOutOfBounds.test(x, y) || !COMPASS.contains(direction)) throw new InvalidMoveException(x, y, direction);
        setLost.accept("");
        setOrientation.accept(direction);
        setCoordinates(x, y);
    }

    @Override
    public String getPosition() {
        return Stream.of(x, y, orientation, lost).map(word -> word.get().toString()).collect(joining(" ")).trim();
    }

    private void execute(char direction) {
        if (direction == FORWARD) {
            List<Integer> newPos = moveForward(x.get(), y.get());
            if (!scents.contains(newPos)) {
                if (isOutOfBounds.test(newPos.get(0), newPos.get(1))) {
                    setLost.accept(LOST);
                    scents.add(newPos);
                } else setCoordinates(newPos.get(0), newPos.get(1));
            }
        } else setOrientation.accept(turn(direction));
    }

    private char turn(char direction) {
        int index = COMPASS.indexOf(orientation.get());
        return COMPASS.get(direction == RIGHT ?
                index + 1 > COMPASS.size() - 1 ? 0 : index + 1 :
                index - 1 < 0 ? COMPASS.size() - 1 : index - 1);
    }

    private List<Integer> moveForward(int x, int y) {
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