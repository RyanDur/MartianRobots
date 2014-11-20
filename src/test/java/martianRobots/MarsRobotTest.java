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

    @Test
    public void shouldBeAbleToDisplayThePosition() throws InvalidMoveException {
        int x = 1;
        int y = 1;
        char orientation = Constants.EAST;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws InvalidMoveException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws InvalidMoveException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws InvalidMoveException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws InvalidMoveException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws InvalidMoveException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws InvalidMoveException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("R");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws InvalidMoveException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("L");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws InvalidMoveException {
        marsRobot.setPosition(1, 1, Constants.WEST);
        marsRobot.move("LFFLLFF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 0 " + Constants.SOUTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToEndUpInTheSamePlace() throws InvalidMoveException {
        marsRobot.setPosition(1, 1, Constants.EAST);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + Constants.EAST)));
    }

    @Test
    public void shouldBeAbleToFallOffTheGrid() throws InvalidMoveException {
        marsRobot.setPosition(3, 2, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + Constants.NORTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldNotLetAnotherRobotFallOffTheGridAtAPointWhereAPastRobotFell() throws InvalidMoveException {
        marsRobot.setPosition(3, 2, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        marsRobot.setPosition(0, 3, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }
}
