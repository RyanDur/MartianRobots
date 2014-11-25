package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RobotTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Robot robot;
    private Robots robots;

    @Before
    public void setup() {
        robot = new RobotImpl(1, 2, Compass.E);
        robots = mock(Robots.class);
        ((RobotImpl) robot).setRobotFactory(robots);
    }

    @Test
    public void shouldBeAbleToCheckTheEqualityOfPositions() {
        Robot robot1 = new RobotImpl(1, 2, Compass.E);
        assertThat(robot.equals(robot1), is(true));
    }

    @Test
    public void shouldBeAbleToCheckTheContentsOfPositions() {
        Set<Robot> scents = new HashSet<>();
        scents.add(robot);
        Robot robot1 = new RobotImpl(1, 2, Compass.E);
        assertThat(scents.contains(robot1), is(true));
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() {
        Compass orientation = Compass.N;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() {
        Compass orientation = Compass.E;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() {
        Compass orientation = Compass.S;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() {
        Compass orientation = Compass.W;
        int x = 1;
        int y = 2;
        Robot robot = new RobotImpl(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForward() throws ValidationException {
        char direction = 'F';
        robot = robot.move(direction);
        verify(robots).createRobot(direction, 1, 2, Compass.E);
    }

    @Test
    public void shouldBeAbleToMoveARobotLeft() throws ValidationException {
        char direction = 'L';
        robot = robot.move(direction);
        verify(robots).createRobot(direction, 1, 2, Compass.E);
    }

    @Test
    public void shouldBeAbleToMoveARobotRight() throws ValidationException {
        char direction = 'R';
        robot = robot.move(direction);
        verify(robots).createRobot(direction, 1, 2, Compass.E);
    }
}