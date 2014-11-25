package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
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
        Robot robot = new RobotImpl(1, 2, Compass.E);
        Robot robot1 = new RobotImpl(1, 2, Compass.E);
        assertThat(robot.equals(robot1), is(true));
    }

    @Test
    public void shouldBeAbleToCheckTheContentsOfPositions() throws ValidationException {
        Set<Robot> scents = new HashSet<>();
        Robot robot = new RobotImpl(1, 2, Compass.E);
        scents.add(robot);
        Robot robot1 = new RobotImpl(1, 2, Compass.E);
        assertThat(scents.contains(robot1), is(true));
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws ValidationException {
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + x + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws ValidationException {
        Compass orientation = Compass.N;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws ValidationException {
        Compass orientation = Compass.E;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws ValidationException {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws ValidationException {
        Compass orientation = Compass.W;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws ValidationException {
        Compass orientation = Compass.E;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws ValidationException {
        Compass orientation = Compass.N;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws ValidationException {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws ValidationException {
        Compass orientation = Compass.W;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('F');
        assertThat(robot.toString(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws ValidationException {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('R');
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + Compass.W)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws ValidationException {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot = robot.move('L');
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + Compass.E)));
    }

    @Test
    public void shouldNotBeAbleToInputInvalidInstructions() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        robot.move('D');
    }

    @Test
    public void shouldGetHelpfulMessageForInvalidInstructions() throws ValidationException {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        char direction = 'D';
        exception.expectMessage(direction + Constants.INVALID_DIRECTION);
        robot.move(direction);
    }
}