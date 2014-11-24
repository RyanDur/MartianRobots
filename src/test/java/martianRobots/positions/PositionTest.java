package martianRobots.positions;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PositionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBeAbleToCheckTheEqualityOfPositions() throws ValidationException {
        Position position = new PositionImpl(1, 2, Constants.EAST);
        Position position1 = new PositionImpl(1, 2, Constants.EAST);
        assertThat(position.equals(position1), is(true));
    }

    @Test
    public void shouldBeAbleToCheckTheContentsOfPositions() throws ValidationException {
        Set<Position> scents = new HashSet<>();
        Position position = new PositionImpl(1, 2, Constants.EAST);
        scents.add(position);
        Position position1 = new PositionImpl(1, 2, Constants.EAST);
        assertThat(scents.contains(position1), is(true));
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidOrientation() throws ValidationException {
        char orientation = 'R';
        int x = 1;
        int y = 2;
        exception.expectMessage(orientation + Constants.NOT_EXISTS);
        new PositionImpl(x, y, orientation);
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws ValidationException {
        int x = 1;
        int y = 1;
        char orientation = Constants.EAST;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + x + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws ValidationException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws ValidationException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws ValidationException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws ValidationException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws ValidationException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws ValidationException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('R');
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position = position.move('L');
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldNotBeAbleToInputInvalidInstructions() throws ValidationException {
        exception.expect(ValidationException.class);
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        position.move('D');
    }

    @Test
    public void shouldGetHelpfulMessageForInvalidInstructions() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Position position = new PositionImpl(x, y, orientation);
        char direction = 'D';
        exception.expectMessage(direction + Constants.INVALID_DIRECTION);
        position.move(direction);
    }
}