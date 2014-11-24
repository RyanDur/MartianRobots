package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
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
import static martianRobots.lang.Constants.COMPASS;
import static martianRobots.lang.Constants.EMPTY;
import static martianRobots.lang.Constants.FORWARD;
import static martianRobots.lang.Constants.INVALID_GRID_SIZE;
import static martianRobots.lang.Constants.INVALID_INSTRUCTIONS;
import static martianRobots.lang.Constants.IS_TAKEN;
import static martianRobots.lang.Constants.LEFT;
import static martianRobots.lang.Constants.LOST;
import static martianRobots.lang.Constants.MAX_BOUNDS;
import static martianRobots.lang.Constants.MAX_INSTRUCTION_SIZE;
import static martianRobots.lang.Constants.NOT_EXISTS;
import static martianRobots.lang.Constants.RIGHT;


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
            Position newPos = position.get().move(instructions.charAt(i));
            if (!scents.contains(newPos) && !occupied.contains(newPos.getLocation())) {
                if (isOutOfBounds.test(newPos.getLocation())) {
                    setLost.accept(LOST);
                    scents.add(newPos);
                } else setPosition.accept(newPos);
            }
        }
        if (!isLost) occupied.add(position.get().getLocation());
    }

    @Override
    public void setPosition(final Position position) throws ValidationException {
        if (isOutOfBounds.test(position.getLocation()) || !COMPASS.contains(position.getOrientation()))
            throw new ValidationException(position + NOT_EXISTS);
        if (occupied.contains(position.getLocation())) throw new ValidationException(position + IS_TAKEN);
        setLost.accept(EMPTY);
        setPosition.accept(position);
        occupied.add(position.getLocation());
    }

    @Override
    public String getPosition() {
        return Stream.of(position, lost).map(word -> word.get().toString()).collect(joining(Constants.SPACE)).trim();
    }

    private Consumer<Position> setPosition = pos -> position = () -> pos;

    private Consumer<String> setLost = message -> {
        isLost = message.length() != 0;
        lost = () -> message;
    };

    private Function<List<Integer>, Predicate<List<Integer>>> getBoundary = maxLoc ->
            loc -> loc.get(0) < 0 || loc.get(0) > maxLoc.get(0) || loc.get(1) < 0 || loc.get(1) > maxLoc.get(1);

    private Consumer<List<Integer>> setBounds = location -> isOutOfBounds = getBoundary.apply(location);

    private Predicate<List<Integer>> isInvalidSize = loc -> getBoundary.apply(MAX_BOUNDS).test(loc);

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
}