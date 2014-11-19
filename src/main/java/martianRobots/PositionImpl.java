package martianRobots;

import static martianRobots.lang.Constants.*;
import static martianRobots.lang.Constants.EAST;

public class PositionImpl implements Position {
    private final int x;
    private final int y;
    private final char orientation;

    public PositionImpl(int x, int y, char orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getOrientation() {
        return orientation;
    }

    @Override
    public Position turn(char direction) {
        int index = COMPASS.indexOf(getOrientation());
        if (direction == RIGHT) index = index + 1 > COMPASS.size() - 1 ? 0 : index + 1;
        else index = index - 1 < 0 ? COMPASS.size() - 1 : index - 1;
        return new PositionImpl(getX(), getY(), COMPASS.get(index));
    }

    @Override
    public Position moveForward() {
        int x = getX();
        int y = getY();
        if (getOrientation() == NORTH) y += 1;
        else if (getOrientation() == SOUTH) y -= 1;
        else if (getOrientation() == EAST) x += 1;
        else x -= 1;
        return new PositionImpl(x, y, getOrientation());
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getOrientation() == position.getOrientation() &&
                getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        result = 31 * result + (int) getOrientation();
        return result;
    }
}
