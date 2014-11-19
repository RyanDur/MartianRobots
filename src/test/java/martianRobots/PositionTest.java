package martianRobots;

import martianRobots.exceptions.InvalidMoveException;
import martianRobots.lang.Constants;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PositionTest {

    @Test
    public void shouldBeAbleToDisplayThePosition() throws InvalidMoveException {
        int row = 1;
        int column = 1;
        char orientation = Constants.EAST;
        Position position = new PositionImpl(row, column, orientation);
        assertThat(position.toString(), is(equalTo(row + " " + column + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws InvalidMoveException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws InvalidMoveException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws InvalidMoveException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws InvalidMoveException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.moveForward().toString(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws InvalidMoveException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.moveForward().toString(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.moveForward().toString(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws InvalidMoveException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.moveForward().toString(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.turn(Constants.RIGHT).toString(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.turn(Constants.LEFT).toString(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldBeTrueIfPositionsAreEqual() {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position1 = new PositionImpl(x, y, orientation);
        Position position2 = new PositionImpl(x, y, orientation);
        assertThat(position1.equals(position2), is(true));
    }

    @Test
    public void shouldBeFalseIfPositionsAreEqualIfDifferentOrientations() {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position1 = new PositionImpl(x, y, orientation);
        Position position2 = new PositionImpl(x, y, Constants.NORTH);
        assertThat(position1.equals(position2), is(false));
    }

    @Test
    public void shouldBeFalseIfPositionsAreEqualIfDifferentXCoordinates() {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position1 = new PositionImpl(x, y, orientation);
        Position position2 = new PositionImpl(2, y, orientation);
        assertThat(position1.equals(position2), is(false));
    }

    @Test
    public void shouldBeFalseIfPositionsAreEqualIfDifferentYCoordinates() {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position1 = new PositionImpl(x, y, orientation);
        Position position2 = new PositionImpl(x, 7, orientation);
        assertThat(position1.equals(position2), is(false));
    }
}