package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidInstructions;
import martianRobots.exceptions.InvalidMoveException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {
    private boolean lost;
    private char orientation;
    private List<Integer> pos;
    private Set<List<Integer>> scents;
    private BiPredicate<Integer, Integer> outOfBounds;

    public MarsRobotImpl() {
        scents = new HashSet<>();
    }

    @Override
    public void setup(int x, int y) throws InvalidGridSizeException {
        if (invalidSize.test(x, y)) throw new InvalidGridSizeException(x, y);
        setBoundary.accept(x, y);
    }

    @Override
    public void move(String instructions) throws InvalidInstructions {
        if (invalid.test(instructions)) throw new InvalidInstructions(instructions);
        for (int i = 0; i < instructions.length() && !lost; i++) execute(instructions.charAt(i));
    }

    @Override
    public void setPosition(int x, int y, char orientation) throws InvalidMoveException {
        if (outOfBounds.test(x, y) || invalidCompass.test(orientation))
            throw new InvalidMoveException(x, y, orientation);
        lost = false;
        this.orientation = orientation;
        pos = Arrays.asList(x, y);
    }

    @Override
    public String getPosition() {
        return Stream.of(pos.get(0), pos.get(1), orientation, lost ? LOST : "")
                .map(word -> word.toString()).collect(Collectors.joining(" ")).trim();
    }

    private void execute(char direction) {
        if (direction == FORWARD) {
            List<Integer> newPos = moveForward(pos.get(0), pos.get(1), orientation);
            if (!scents.contains(newPos)) {
                if (outOfBounds.test(newPos.get(0), newPos.get(1))) {
                    lost = true;
                    scents.add(newPos);
                } else pos = newPos;
            }
        } else orientation = turn(direction, orientation);
    }

    private char turn(char direction, char orientation) {
        int index = COMPASS.indexOf(orientation);
        return COMPASS.get(direction == RIGHT ?
                index + 1 > COMPASS.size() - 1 ? 0 : index + 1 :
                index - 1 < 0 ? COMPASS.size() - 1 : index - 1);
    }

    private List<Integer> moveForward(int x, int y, char orientation) {
        if (orientation == NORTH) y += 1;
        else if (orientation == SOUTH) y -= 1;
        else if (orientation == EAST) x += 1;
        else x -= 1;
        return Arrays.asList(x, y);
    }

    private BiFunction<Integer, Integer, BiPredicate<Integer, Integer>> setValidation = (maxX, maxY) ->
            (x, y) -> x < 0 || x > maxX || y < 0 || y > maxY;

    private BiConsumer<Integer, Integer> setBoundary = (x, y) -> outOfBounds = setValidation.apply(x, y);

    private Predicate<Character> invalidCompass = orientation -> !COMPASS.contains(orientation);

    private BiPredicate<Integer, Integer> invalidSize = (x, y) -> setValidation.apply(MAX_SIZE, MAX_SIZE).test(x, y);

    private Predicate<String> invalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
}