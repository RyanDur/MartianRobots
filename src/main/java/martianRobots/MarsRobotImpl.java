package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.positions.Position;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.EMPTY;
import static martianRobots.lang.Constants.INVALID_GRID_SIZE;
import static martianRobots.lang.Constants.INVALID_INSTRUCTIONS;
import static martianRobots.lang.Constants.IS_TAKEN;
import static martianRobots.lang.Constants.LOST;
import static martianRobots.lang.Constants.MAX_BOUNDS;
import static martianRobots.lang.Constants.MAX_INSTRUCTION_SIZE;
import static martianRobots.lang.Constants.NOT_EXISTS;
import static martianRobots.lang.Constants.SPACE;
import static martianRobots.lang.Constants.VALID_INSTRUCTIONS;


public class MarsRobotImpl implements MarsRobot {
    private Supplier<Position> position;
    private Supplier<String> lost;
    private Set<Position> scents;
    private Set<List<Integer>> occupied;
    private Predicate<List<Integer>> isOutOfBounds;
    private boolean isLost;

    @Override
    public void setup(final List<Integer> bounds) throws ValidationException {
        if (isInvalidSize.test(bounds)) throw new ValidationException(bounds + INVALID_GRID_SIZE);
        setBounds.accept(bounds);
        scents = new HashSet<>();
        occupied = new HashSet<>();
    }

    @Override
    public void move(final String instructions) throws ValidationException {
        if (isInvalid.test(instructions)) throw new ValidationException(instructions + INVALID_INSTRUCTIONS);
        occupied.remove(position.get().getLocation());
        for (int i = 0; i < instructions.length() && !isLost; i++) {
            Position pos = position.get().move(instructions.charAt(i));
            if (!scents.contains(pos) && !occupied.contains(pos.getLocation())) setPosition.accept(pos);
        }
        if (!isLost) occupied.add(position.get().getLocation());
    }

    @Override
    public void setPosition(final Position position) throws ValidationException {
        if (isOutOfBounds.test(position.getLocation())) throw new ValidationException(position + NOT_EXISTS);
        if (occupied.contains(position.getLocation())) throw new ValidationException(position + IS_TAKEN);
        setLost.accept(false);
        setPosition.accept(position);
        occupied.add(position.getLocation());
    }

    @Override
    public String getPosition() {
        return Stream.of(position, lost).map(word -> word.get().toString()).collect(joining(SPACE)).trim();
    }

    private Consumer<Boolean> setLost = bool -> {
        isLost = bool;
        lost = () -> isLost ? LOST : EMPTY;
    };

    private Consumer<Position> setPosition = pos -> {
        if (isOutOfBounds.test(pos.getLocation())) {
            setLost.accept(true);
            scents.add(pos);
        } else position = () -> pos;
    };

    private Function<List<Integer>, Predicate<List<Integer>>> getBoundary = maxLoc ->
            loc -> loc.get(0) < 0 || loc.get(0) > maxLoc.get(0) || loc.get(1) < 0 || loc.get(1) > maxLoc.get(1);

    private Consumer<List<Integer>> setBounds = bounds -> isOutOfBounds = getBoundary.apply(bounds);

    private Predicate<List<Integer>> isInvalidSize = bounds -> getBoundary.apply(MAX_BOUNDS).test(bounds);

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> !VALID_INSTRUCTIONS.contains((char) c)).count() > 0;
}