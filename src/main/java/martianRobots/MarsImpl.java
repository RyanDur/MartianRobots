package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.robots.Robot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static martianRobots.lang.Constants.EMPTY;
import static martianRobots.lang.Constants.INVALID_INSTRUCTIONS;
import static martianRobots.lang.Constants.INVALID_X_SIZE;
import static martianRobots.lang.Constants.INVALID_Y_SIZE;
import static martianRobots.lang.Constants.IS_TAKEN;
import static martianRobots.lang.Constants.LOST;
import static martianRobots.lang.Constants.MAX_INSTRUCTION_SIZE;
import static martianRobots.lang.Constants.MAX_X;
import static martianRobots.lang.Constants.MAX_Y;
import static martianRobots.lang.Constants.NOT_EXISTS;
import static martianRobots.lang.Constants.SPACE;

public class MarsImpl implements Mars {
    private Supplier<Robot> robot;
    private Supplier<String> lost;
    private Set<Robot> scents;// lost robots leave a robot “scent” that prohibits future robots from dropping off
    // the world at the same grid point.
    private Set<List<Integer>> occupied;
    private Predicate<List<Integer>> isOutOfBounds;
    private boolean isLost;

    @Override
    public void setup(final int x, final int y) throws ValidationException {
        if (isInvalidSize.test(MAX_X, x)) throw new ValidationException(x + INVALID_X_SIZE);
        if (isInvalidSize.test(MAX_Y, y)) throw new ValidationException(y + INVALID_Y_SIZE);
        setBounds.accept(bound.apply(x), bound.apply(y));
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
        if (occupied.contains(robot.getLocation())) throw new ValidationException(robot + IS_TAKEN);
        if (isOutOfBounds.test(robot.getLocation())) throw new ValidationException(robot + NOT_EXISTS);
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

    private Function<Integer, Predicate<Integer>> bound = max -> loc -> loc < 0 || loc > max;
    private BiPredicate<Integer, Integer> isInvalidSize = (max, size) -> bound.apply(max).test(size);
    private Predicate<String> isInvalid = instructions -> instructions.length() >= MAX_INSTRUCTION_SIZE;
    private BiConsumer<Predicate<Integer>, Predicate<Integer>> setBounds = (maxX, maxY) ->
            isOutOfBounds = loc -> maxX.test(loc.get(0)) || maxY.test(loc.get(1));
}