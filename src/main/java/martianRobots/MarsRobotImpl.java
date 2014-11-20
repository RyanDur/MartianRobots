package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;
import martianRobots.factories.PositionFactory;

import java.util.HashSet;
import java.util.Set;

import static martianRobots.lang.Constants.*;

public class MarsRobotImpl implements MarsRobot {
    private int x;
    private int y;
    private boolean lost;
    private Position position;
    private Set<Position> scents;
    private PositionFactory positionFactory;

    public MarsRobotImpl(PositionFactory positionFactory) {
        this.positionFactory = positionFactory;
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
        position = positionFactory.createPosition(x, y, orientation);
    }

    @Override
    public String getPosition() {
        return position + (lost ? " " + LOST : "");
    }

    private void execute(char direction) {
        if (direction == FORWARD) {
            Position newPosition = position.moveForward();
            if (!scents.contains(newPosition)) {
                if (outOfBounds(newPosition.getX(), newPosition.getY())) {
                    lost = true;
                    scents.add(newPosition);
                } else position = newPosition;
            }
        } else position = position.turn(direction);
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