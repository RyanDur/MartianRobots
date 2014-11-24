package martianRobots.robots;

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

public class RobotTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldBeAbleToCheckTheEqualityOfPositions() throws ValidationException {
        Robot robot = new RobotImpl(1, 2, Constants.EAST);
        Robot robot1 = new RobotImpl(1, 2, Constants.EAST);
        assertThat(robot.equals(robot1), is(true));
    }

    @Test
    public void shouldBeAbleToCheckTheContentsOfPositions() throws ValidationException {
        Set<Robot> scents = new HashSet<>();
        Robot robot = new RobotImpl(1, 2, Constants.EAST);
        scents.add(robot);
        Robot robot1 = new RobotImpl(1, 2, Constants.EAST);
        assertThat(scents.contains(robot1), is(true));
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidOrientation() throws ValidationException {
        char orientation = 'R';
        int x = 1;
        int y = 2;
        exception.expectMessage(orientation + Constants.NOT_EXISTS);
        new RobotImpl(x, y, orientation);
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws ValidationException {
        int x = 1;
        int y = 1;
        char orientation = Constants.EAST;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + x + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws ValidationException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws ValidationException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws ValidationException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws ValidationException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws ValidationException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws ValidationException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('R');
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('L');
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldNotBeAbleToInputInvalidInstructions() throws ValidationException {
        exception.expect(ValidationException.class);
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot.move('D');
    }

    @Test
    public void shouldGetHelpfulMessageForInvalidInstructions() throws ValidationException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        char direction = 'D';
        exception.expectMessage(direction + Constants.INVALID_DIRECTION);
        robot.move(direction);
    }
}