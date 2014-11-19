package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;
import martianRobots.lang.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class MarsRobotTest {

    private MarsRobot marsRobot;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws InvalidGridSizeException {
        marsRobot = new MarsRobotImpl();
        int row = 5;
        int column = 3;
        marsRobot.setup(row, column);
    }

    @Test
    public void shouldBeAbleToGetThePositionOfARobot() throws InvalidMoveException {
        int row = 1;
        int column = 1;
        char orientation = Constants.EAST;
        marsRobot.setPosition(row, column, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + Constants.EAST)));
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(-1, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(6, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(1, -2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(3, 4, orientation);
    }

    @Test
    public void shouldBeAbleToOrientARobotNorth() throws InvalidMoveException {
        char orientation = Constants.NORTH;
        marsRobot.setPosition(1, 2, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 2 " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientARobotEast() throws InvalidMoveException {
        char orientation = Constants.EAST;
        marsRobot.setPosition(1, 2, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 2 " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientARobotSouth() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        marsRobot.setPosition(1, 2, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 2 " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientARobotWest() throws InvalidMoveException {
        char orientation = Constants.WEST;
        marsRobot.setPosition(1, 2, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 2 " + orientation)));
    }

    @Test
    public void shouldNotBeAbleToSetAnOrientationThatDoesNotExist() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = 'R';
        marsRobot.setPosition(1, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAYCoordinate() throws InvalidGridSizeException {
        exception.expect(InvalidGridSizeException.class);
        marsRobot.setup(3, 51);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAYCoordinate() throws InvalidGridSizeException {
        exception.expect(InvalidGridSizeException.class);
        marsRobot.setup(3, -1);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAnXCoordinate() throws InvalidGridSizeException {
        exception.expect(InvalidGridSizeException.class);
        marsRobot.setup(67, 50);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAnXCoordinate() throws InvalidGridSizeException {
        exception.expect(InvalidGridSizeException.class);
        marsRobot.setup(-3, 1);
    }
}
