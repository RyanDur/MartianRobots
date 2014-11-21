package martianRobots;

import martianRobots.exceptions.InvalidException;
import martianRobots.lang.Constants;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarsRobotTest {

    private MarsRobot marsRobot;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws InvalidException {
        marsRobot = new MarsRobotImpl();
        List<Integer> coordinates = Arrays.asList(5, 3);
        marsRobot.setup(coordinates);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(-1, 2);
        marsRobot.setPosition(coordinates, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(6, 2);
        marsRobot.setPosition(coordinates, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(1, -2);
        marsRobot.setPosition(coordinates, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(3, 4);
        marsRobot.setPosition(coordinates, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetAnOrientationThatDoesNotExist() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = 'R';
        List<Integer> coordinates = Arrays.asList(1, 2);
        marsRobot.setPosition(coordinates, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAYCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        List<Integer> coordinates = Arrays.asList(3, 51);
        marsRobot.setup(coordinates);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAYCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        List<Integer> coordinates = Arrays.asList(3, -1);
        marsRobot.setup(coordinates);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAnXCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        List<Integer> coordinates = Arrays.asList(67, 50);
        marsRobot.setup(coordinates);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAnXCoordinate() throws InvalidException {
        exception.expect(InvalidException.class);
        List<Integer> coordinates = Arrays.asList(-3, 1);
        marsRobot.setup(coordinates);
    }

    @Test
    public void shouldNotBeAbleToInputInvalidException() throws InvalidException {
        exception.expect(InvalidException.class);
        marsRobot.move("FFFLLLFlubber");
    }

    @Test
    public void shouldNotBeAbleToInputMoreOrEqualToTheMaxInstructionSizeInstructions() throws InvalidException {
        exception.expect(InvalidException.class);
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE]).replace('\0', 'F');
        marsRobot.move(instructions);
    }

    @Test
    public void shouldBeAbleToInputJustUnderTheMaxInstructionSize() throws InvalidException {
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE - 1]).replace('\0', 'F');
        int x = 1;
        int y = 1;
        List<Integer> coordinates = Arrays.asList(x, y);
        char orientation = Constants.EAST;
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move(instructions);
        assertThat(marsRobot.getPosition(), is(equalTo(5 + " " + y + " " + orientation + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws InvalidException {
        int x = 1;
        int y = 1;
        List<Integer> coordinates = Arrays.asList(x, y);
        char orientation = Constants.EAST;
        marsRobot.setPosition(coordinates, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("F");
        assertThat(marsRobot.getPosition(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("R");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        marsRobot.setPosition(coordinates, orientation);
        marsRobot.move("L");
        assertThat(marsRobot.getPosition(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(1, 1);
        marsRobot.setPosition(coordinates, Constants.WEST);
        marsRobot.move("LFFLLFF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 0 " + Constants.SOUTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToEndUpInTheSamePlace() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(1, 1);
        marsRobot.setPosition(coordinates, Constants.EAST);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + Constants.EAST)));
    }

    @Test
    public void shouldBeAbleToFallOffTheGrid() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        marsRobot.setPosition(coordinates, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + Constants.NORTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldNotLetAnotherRobotFallOffTheGridAtAPointWhereAPastRobotFell() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        marsRobot.setPosition(coordinates, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        List<Integer> coordinates1 = Arrays.asList(0, 3);
        marsRobot.setPosition(coordinates1, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }

    @Test
    public void shouldBeAbleToFallOffTheEdgeIfResetTheGrid() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        marsRobot.setPosition(coordinates, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        List<Integer> coordinates1 = Arrays.asList(0, 3);
        marsRobot.setPosition(coordinates1, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
        marsRobot.setup(Arrays.asList(5, 3));
        marsRobot.setPosition(coordinates1, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + Constants.NORTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldSatisfyRequirementsOfChallenge() throws InvalidException {
        marsRobot.setup(Arrays.asList(5, 3));
        List<Integer> coordinates = Arrays.asList(1, 1);
        marsRobot.setPosition(coordinates, Constants.EAST);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + Constants.EAST)));
        List<Integer> coordinates1 = Arrays.asList(3, 2);
        marsRobot.setPosition(coordinates1, Constants.NORTH);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + Constants.NORTH + " " + Constants.LOST)));
        List<Integer> coordinates2 = Arrays.asList(0, 3);
        marsRobot.setPosition(coordinates2, Constants.WEST);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }
}
