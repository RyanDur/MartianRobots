package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidInstructions;
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
    private List<Integer> pos;
    private Set<List<Integer>> scents;

    public MarsRobotImpl() {
        scents = new HashSet<>();
    }

    @Override
    public void setup(int x, int y) throws InvalidGridSizeException {
        if (invalid(x, y)) throw new InvalidGridSizeException(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(String instructions) throws InvalidInstructions {
        if (invalid(instructions)) throw new InvalidInstructions(instructions);
        for (int i = 0; i < instructions.length() && !lost; i++)
            execute(instructions.charAt(i));
    }

    @Override
    public void setPosition(int x, int y, char orientation) throws InvalidMoveException {
        if (outOfBounds(x, y) || invalid(orientation)) throw new InvalidMoveException(x, y, orientation);
        lost = false;
        this.orientation = orientation;
        pos = Arrays.asList(x, y);
    }

    @Override
    public String getPosition() {
        return pos.get(0) + " " + pos.get(1) + " " + orientation + (lost ? " " + LOST : "");
    }

    private void execute(char direction) {
        if (direction == FORWARD) {
            List<Integer> newPos = moveForward(pos.get(0), pos.get(1), orientation);
            if (!scents.contains(newPos)) {
                if (outOfBounds(newPos.get(0), newPos.get(1))) {
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

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x > this.x || y < 0 || y > this.y;
    }

    private boolean invalid(char orientation) {
        return !COMPASS.contains(orientation);
    }

    private boolean invalid(int x, int y) {
        return x > MAX_SIZE || x < 0 || y > MAX_SIZE || y < 0;
    }

    private boolean invalid(String instructions) {
        return instructions.length() >= MAX_INSTRUCTION_SIZE ||
                instructions.chars().filter(c -> c != LEFT && c != RIGHT && c != FORWARD).count() > 0;
    }
}