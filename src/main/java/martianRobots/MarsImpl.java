package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.EMPTY;
import static martianRobots.lang.Constants.INVALID_GRID_SIZE;
import static martianRobots.lang.Constants.INVALID_INSTRUCTIONS;
import static martianRobots.lang.Constants.IS_TAKEN;
import static martianRobots.lang.Constants.LOST;
import static martianRobots.lang.Constants.MAX_INSTRUCTION_SIZE;
import static martianRobots.lang.Constants.MAX_SIZE;
import static martianRobots.lang.Constants.NOT_EXISTS;
import static martianRobots.lang.Constants.SPACE;

public class MarsImpl implements Mars {
    private Supplier<Robot> robot;
    private Supplier<String> lost;
    private Set<Robot> scents;
    private Set<List<Integer>> occupied;
    private Predicate<List<Integer>> isOutOfBounds;
    private boolean isLost;

    @Override
    public void setup(final int x, final int y) throws ValidationException {
        if (isInvalidSize.test(x, y)) throw new ValidationException(Arrays.asList(x, y) + INVALID_GRID_SIZE);
        setBounds.accept(x, y);
        scents = new HashSet<>();
        occupied = new HashSet<>();
    }

    @Override
    public void move(final String instructions) throws ValidationException {
        if (isInvalid.test(instructions)) throw new ValidationException(instructions + INVALID_INSTRUCTIONS);
        occupied.remove(robot.get().getLocation());
        for (int i = 0; i < instructions.length() && !isLost; i++) {
            Robot robot = this.robot.get().move(instructions.charAt(i));
            if (!scents.contains(robot) && !occupied.contains(robot.getLocation())) setRobot.accept(robot);
        }
        if (!isLost) occupied.add(robot.get().getLocation());
    }

    @Override
    public void setRobot(final Robot robot) throws ValidationException {
        if (isOutOfBounds.test(robot.getLocation())) throw new ValidationException(robot + NOT_EXISTS);
        if (occupied.contains(robot.getLocation())) throw new ValidationException(robot + IS_TAKEN);
        setLost.accept(false);
        setRobot.accept(robot);
        occupied.add(robot.getLocation());
    }

    @Override
    public String getRobot() {
        return Stream.of(robot, lost).map(word -> word.get().toString()).collect(joining(SPACE)).trim();
    }

    private Consumer<Boolean> setLost = bool -> {
        isLost = bool;
        lost = () -> isLost ? LOST : EMPTY;
    };

    private Consumer<Robot> setRobot = robot -> {
        if (isOutOfBounds.test(robot.getLocation())) {
            setLost.accept(true);
            scents.add(robot);
        } else this.robot = () -> robot;
    };

    private BiFunction<Integer, Integer, Predicate<List<Integer>>> getBoundary = (maxX, maxY) ->
            loc -> loc.get(0) < 0 || loc.get(0) > maxX || loc.get(1) < 0 || loc.get(1) > maxY;

    private BiConsumer<Integer, Integer> setBounds = (x, y) -> isOutOfBounds = getBoundary.apply(x, y);

    private BiPredicate<Integer, Integer> isInvalidSize = (x, y) ->
            getBoundary.apply(MAX_SIZE, MAX_SIZE).test(Arrays.asList(x, y));

    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE;
}