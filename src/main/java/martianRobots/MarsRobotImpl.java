package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {
    private int x;
    private int y;
    private boolean lost;
    private char orientation;
    private List<Integer> position;
    private Set<List<Integer>> scents;

    public MarsRobotImpl() {
        scents = new HashSet<>();
    }

    @Override
    public void setup(int x, int y) throws InvalidGridSizeException {
        if (invalidGridSize(x, y)) throw new InvalidGridSizeException(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(String instructions) {
        for (int i = 0; i < instructions.length() && !lost; i++)
            execute(instructions.charAt(i));
    }

    @Override
    public void setPosition(int x, int y, char orientation) throws InvalidMoveException {
        if (outOfBounds(x, y) || invalidOrientation(orientation)) throw new InvalidMoveException(x, y, orientation);
        lost = false;
        this.orientation = orientation;
        position = Arrays.asList(x, y);
    }

    @Override
    public String getPosition() {
        return position.get(0) + " " + position.get(1) + " " + orientation + (lost ? " " + LOST : "");
    }

    private void execute(char direction) {
        if (direction == FORWARD) {
            List<Integer> newPosition = moveForward();
            if (!scents.contains(newPosition)) {
                if (outOfBounds(newPosition.get(0), newPosition.get(1))) {
                    lost = true;
                    scents.add(newPosition);
                } else position = newPosition;
            }
        } else orientation = turn(direction);
    }

    private char turn(char direction) {
        int index = COMPASS.indexOf(orientation);
        if (direction == RIGHT) index = index + 1 > COMPASS.size() - 1 ? 0 : index + 1;
        else index = index - 1 < 0 ? COMPASS.size() - 1 : index - 1;
        return COMPASS.get(index);
    }

    private List<Integer> moveForward() {
        int x = position.get(0);
        int y = position.get(1);
        if (orientation == NORTH) y += 1;
        else if (orientation == SOUTH) y -= 1;
        else if (orientation == EAST) x += 1;
        else x -= 1;
        return Arrays.asList(x, y);
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x > this.x || y < 0 || y > this.y;
    }

    private boolean invalidOrientation(char orientation) {
        return !COMPASS.contains(orientation);
    }

    private boolean invalidGridSize(int x, int y) {
        return x > MAX_SIZE || x < 0 || y > MAX_SIZE || y < 0;
    }
}