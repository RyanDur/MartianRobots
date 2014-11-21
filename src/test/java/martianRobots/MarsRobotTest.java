package martianRobots;

import martianRobots.exceptions.InvalidException;
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
    public void setup() throws InvalidException {
        marsRobot = new MarsRobotImpl();
        int row = 5;
        int column = 3;
        marsRobot.setup(row, column);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(-1, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(6, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(1, -2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        marsRobot.setPosition(3, 4, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetAnOrientationThatDoesNotExist() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = 'R';
        marsRobot.setPosition(1, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAYCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.setup(3, 51);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAYCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.setup(3, -1);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAnXCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.setup(67, 50);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAnXCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.setup(-3, 1);
    }

    @Test
    public void shouldNotBeAbleToInputInvalidException() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.move("FFFLLLFlubber");
    }

    @Test
    public void shouldNotBeAbleToInputAHundredOrMoreInstructions() throws InvalidException {
        exception.expect(InvalidException.class);
        String instructions = new String(new char[100]).replace('\0', 'F');
        marsRobot.move(instructions);
    }

    @Test
    public void shouldBeAbleToInputJustUnderAHundred() throws InvalidException {
        String instructions = new String(new char[99]).replace('\0', 'F');
        int x = 1;
        int y = 1;
        char orientation = Constants.EAST;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move(instructions);
        assertThat(marsRobot.getPosition(), is(equalTo(5 + " " + y + " " + orientation + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws InvalidException {
        int x = 1;
        int y = 1;
        char orientation = Constants.EAST;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("R");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        marsRobot.setPosition(x, y, orientation);
        marsRobot.move("L");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws InvalidException {
        marsRobot.setPosition(1, 1, Constants.WEST);
        marsRobot.move("LFFLLFF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 0 " + Constants.SOUTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToEndUpInTheSamePlace() throws InvalidException {
        marsRobot.setPosition(1, 1, Constants.EAST);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + Constants.EAST)));
    }

    @Test
    public void shouldBeAbleToFallOffTheGrid() throws InvalidException {
        marsRobot.setPosition(3, 2, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + Constants.NORTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldNotLetAnotherRobotFallOffTheGridAtAPointWhereAPastRobotFell() throws InvalidException {
        marsRobot.setPosition(3, 2, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        marsRobot.setPosition(0, 3, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }
}
