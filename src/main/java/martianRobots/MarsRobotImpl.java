package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;

import java.util.Arrays;
import java.util.List;

import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {

    private int x;
    private int y;
    private char orientation;
    private List<Integer> coordinates;

    @Override
    public void setup(int x, int y) throws InvalidGridSizeException {
        if (invalidGridSize(x, y)) throw new InvalidGridSizeException(x, y);
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(String instructions) {
        if (instructions.length() == 0) return;
        executeInstruction(instructions.charAt(0));
        move(instructions.substring(1, instructions.length()));
    }

    @Override
    public void setPosition(int x, int y, char orientation) throws InvalidMoveException {
        if (outOfBounds(x, y) || invalidOrientation(orientation)) throw new InvalidMoveException(x, y, orientation);
        coordinates = Arrays.asList(x, y);
        this.orientation = orientation;
    }

    @Override
    public String getPosition() {
        return coordinates.get(0) + " " + coordinates.get(1) + " " + orientation;
    }

    private void executeInstruction(char move) {
        if (move == RIGHT || move == LEFT) orientation = getOrientation(move);
        else coordinates = calculateCoordinates(coordinates, orientation);
    }

    private List<Integer> calculateCoordinates(List<Integer> coordinates, char orientation) {
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        if (orientation == NORTH) y += 1;
        else if (orientation == SOUTH) y -= 1;
        else if (orientation == EAST) x += 1;
        else x -= 1;
        return Arrays.asList(x, y);
    }

    private char getOrientation(char move) {
        int index = COMPASS.indexOf(orientation);
        if (move == RIGHT) index = index + 1 > COMPASS.size() - 1 ? 0 : index + 1;
        else index = index - 1 < 0 ? COMPASS.size() - 1 : index - 1;
        return COMPASS.get(index);
    }

    private boolean outOfBounds(int row, int column) {
        return row < 0 || row > x || column < 0 || column > y;
    }

    private boolean invalidOrientation(char orientation) {
        return !COMPASS.contains(orientation);
    }

    private boolean invalidGridSize(int x, int y) {
        return x > MAX_SIZE || x < 0 || y > MAX_SIZE || y < 0;
    }
}