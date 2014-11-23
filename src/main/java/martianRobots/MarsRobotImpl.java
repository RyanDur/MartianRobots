package martianRobots;

import martianRobots.exceptions.InvalidException;
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
import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {
    private Supplier<Position> position;
    private Supplier<String> lost;
    private Set<Position> scents;
    private Predicate<List<Integer>> isOutOfBounds;


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
            Position newPos = position.get().move(instructions.charAt(i));
            if (!scents.contains(newPos)) {
                if (isOutOfBounds.test(newPos.getLocation())) {
                    setLost.accept(LOST);
                    scents.add(newPos);
                } else setPosition.accept(newPos);
            }
        }
    }

    @Override
    public void setPosition(Position position) throws InvalidException {
        if (isOutOfBounds.test(position.getLocation()) || !COMPASS.contains(position.getOrientation()))
            throw new InvalidException(position);
        setLost.accept("");
        setPosition.accept(position);
    }

    @Override
    public String getPosition() {
        return Stream.of(position, lost).map(word -> word.get().toString()).collect(joining(" ")).trim();
    }

    private Consumer<Position> setPosition = pos -> position = () -> pos;

    private Consumer<String> setLost = message -> lost = () -> message;

    private Function<List<Integer>, Predicate<List<Integer>>> getBoundary = maxLoc ->
            loc -> loc.get(0) < 0 || loc.get(0) > maxLoc.get(0) || loc.get(1) < 0 || loc.get(1) > maxLoc.get(1);

    private Consumer<List<Integer>> setBounds = location -> isOutOfBounds = getBoundary.apply(location);

    private Predicate<List<Integer>> isInvalidSize = loc -> getBoundary.apply(MAX_BOUNDS).test(loc);

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE ||
            instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
}