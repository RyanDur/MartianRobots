package martianRobots;

import martianRobots.exceptions.InvalidException;
import martianRobots.lang.Constants;
import martianRobots.positions.Position;
import martianRobots.positions.PositionImpl;
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
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(6, 2);
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(1, -2);
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = Constants.EAST;
        List<Integer> coordinates = Arrays.asList(3, 4);
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
    }

    @Test
    public void shouldNotBeAbleToSetAnOrientationThatDoesNotExist() throws InvalidException {
        exception.expect(InvalidException.class);
        char orientation = 'R';
        List<Integer> coordinates = Arrays.asList(1, 2);
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
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
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
        marsRobot.move(instructions);
        assertThat(marsRobot.getPosition(), is(equalTo(5 + " " + y + " " + orientation + " " + Constants.LOST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(1, 1);
        char orientation = Constants.WEST;
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
        marsRobot.move("LFFLLFF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 0 " + Constants.SOUTH + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToEndUpInTheSamePlace() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(1, 1);
        char orientation = Constants.EAST;
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + orientation)));
    }

    @Test
    public void shouldBeAbleToFallOffTheGrid() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        char orientation = Constants.NORTH;
        Position position = new PositionImpl(coordinates, orientation);
        marsRobot.setPosition(position);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + orientation + " " + Constants.LOST)));
    }

    @Test
    public void shouldNotLetAnotherRobotFallOffTheGridAtAPointWhereAPastRobotFell() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        char north = Constants.NORTH;
        Position position = new PositionImpl(coordinates, north);
        marsRobot.setPosition(position);
        marsRobot.move("FRRFLLFFRRFLL");
        List<Integer> coordinates1 = Arrays.asList(0, 3);
        char west = Constants.WEST;
        Position position1 = new PositionImpl(coordinates1, west);
        marsRobot.setPosition(position1);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }

    @Test
    public void shouldBeAbleToFallOffTheEdgeIfResetTheGrid() throws InvalidException {
        List<Integer> coordinates = Arrays.asList(3, 2);
        char north = Constants.NORTH;
        Position position = new PositionImpl(coordinates, north);
        marsRobot.setPosition(position);
        marsRobot.move("FRRFLLFFRRFLL");
        List<Integer> coordinates1 = Arrays.asList(0, 3);
        char west = Constants.WEST;
        Position position1 = new PositionImpl(coordinates1, west);
        marsRobot.setPosition(position1);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
        marsRobot.setup(Arrays.asList(5, 3));
        marsRobot.setPosition(position1);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + north + " " + Constants.LOST)));
    }

    @Test
    public void shouldSatisfyRequirementsOfChallenge() throws InvalidException {
        marsRobot.setup(Arrays.asList(5, 3));
        List<Integer> coordinates = Arrays.asList(1, 1);
        char east = Constants.EAST;
        Position position = new PositionImpl(coordinates, east);
        marsRobot.setPosition(position);
        marsRobot.move("RFRFRFRF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 " + east)));
        List<Integer> coordinates1 = Arrays.asList(3, 2);
        char north = Constants.NORTH;
        Position position1 = new PositionImpl(coordinates1, north);
        marsRobot.setPosition(position1);
        marsRobot.move("FRRFLLFFRRFLL");
        assertThat(marsRobot.getPosition(), is(equalTo("3 3 " + north + " " + Constants.LOST)));
        List<Integer> coordinates2 = Arrays.asList(0, 3);
        char west = Constants.WEST;
        Position position2 = new PositionImpl(coordinates2, west);
        marsRobot.setPosition(position2);
        marsRobot.move("LLFFFLFLFL");
        assertThat(marsRobot.getPosition(), is(equalTo("2 3 " + Constants.SOUTH)));
    }
}
