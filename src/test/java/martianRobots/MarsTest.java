package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;
import martianRobots.robots.Robot;
import martianRobots.robots.RobotImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarsTest {

    private Mars mars;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() throws ValidationException {
        mars = new MarsImpl();
        mars.setup(5, 3);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(-1, 2, orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidCoordinates() throws ValidationException {
        Compass orientation = Compass.E;
        int x = -1;
        int y = 2;
        exception.expectMessage(x + " " + y + " " + orientation + Constants.NOT_EXISTS);
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(6, 2, orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(1, -2, orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(3, 4, orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAYCoordinate() throws ValidationException {
        exception.expect(ValidationException.class);
        mars.setup(3, 51);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidGridSetupForX() throws ValidationException {
        int x = -1;
        int y = 2;
        exception.expectMessage(x  + Constants.INVALID_X_SIZE);
        mars.setup(x, y);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidGridSetupForY() throws ValidationException {
        int x = 1;
        int y = 72;
        exception.expectMessage(y  + Constants.INVALID_Y_SIZE);
        mars.setup(x, y);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAYCoordinate() throws ValidationException {
        exception.expect(ValidationException.class);
        mars.setup(3, -1);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToLargeOfAnXCoordinate() throws ValidationException {
        exception.expect(ValidationException.class);
        mars.setup(67, 50);
    }

    @Test
    public void shouldNotBeAbleToSetupAGridToSmallOfAnXCoordinate() throws ValidationException {
        exception.expect(ValidationException.class);
        mars.setup(-3, 1);
    }

    @Test
    public void shouldNotBeAbleToInputMoreOrEqualToTheMaxInstructionSizeInstructions() throws ValidationException {
        exception.expect(ValidationException.class);
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE]).replace('\0', 'F');
        mars.move(instructions);
    }

    @Test
    public void shouldNotBeAbleToSetARobotOnAnother() throws ValidationException {
        exception.expect(ValidationException.class);
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        Robot robot1 = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.setRobot(robot1);
    }

    @Test
    public void shouldGiveHelpfulMessageIfSpotIsTakenOnPlacement() throws ValidationException {
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        exception.expectMessage(x + " " + y + " " + orientation + Constants.IS_TAKEN);
        Robot robot = new RobotImpl(x, y, orientation);
        Robot robot1 = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.setRobot(robot1);
    }

    @Test
    public void shouldIgnoreInstructionIfMoveSpaceIsOccupied() throws ValidationException {
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        Robot robot1 = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move("F");
        mars.setRobot(robot1);
        mars.move("FFFFFFFFFFFFFFFFFFFFF");
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + orientation)));

    }

    @Test
    public void shouldBeAbleToInputJustUnderTheMaxInstructionSize() throws ValidationException {
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE - 1]).replace('\0', 'F');
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move(instructions);
        assertThat(mars.getRobot(), is(equalTo(5 + " " + y + " " + orientation + " " + Constants.LOST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws ValidationException {
        int x = 1;
        int y = 1;
        Compass orientation = Compass.W;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move("LFFLLFF");
        assertThat(mars.getRobot(), is(equalTo(x + " " + 0 + " " + Compass.S + " " + Constants.LOST)));
    }

    @Test
    public void shouldBeAbleToEndUpInTheSamePlace() throws ValidationException {
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move("RFRFRFRF");
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToFallOffTheGrid() throws ValidationException {
        int x = 3;
        int y = 2;
        Compass orientation = Compass.N;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move("FRRFLLFFRRFLL");
        assertThat(mars.getRobot(), is(equalTo(x + " " + 3 + " " + orientation  + " " + Constants.LOST)));
    }

    @Test
    public void shouldNotLetAnotherRobotFallOffTheGridAtAPointWhereAPastRobotFell() throws ValidationException {
        int x = 3;
        int y = 2;
        Compass north = Compass.N;
        Robot robot = new RobotImpl(x, y, north);
        mars.setRobot(robot);
        mars.move("FRRFLLFFRRFLL");

        int x1 = 0;
        int y1 = 3;
        Compass west = Compass.W;
        Robot robot1 = new RobotImpl(x1, y1, west);
        mars.setRobot(robot1);
        mars.move("LLFFFLFLFL");
        assertThat(mars.getRobot(), is(equalTo("2 3 " + Compass.S)));
    }

    @Test
    public void shouldBeAbleToFallOffTheEdgeIfResetTheGrid() throws ValidationException {
        int x = 3;
        int y = 2;
        Compass north = Compass.N;
        Robot robot = new RobotImpl(x, y, north);
        mars.setRobot(robot);
        mars.move("FRRFLLFFRRFLL");

        int x1 = 0;
        int y1 = 3;
        Compass west = Compass.W;
        Robot robot1 = new RobotImpl(x1, y1, west);
        mars.setRobot(robot1);
        mars.move("LLFFFLFLFL");
        assertThat(mars.getRobot(), is(equalTo("2 3 " + Compass.S)));

        mars.setup(5, 3);
        mars.setRobot(robot1);
        mars.move("LLFFFLFLFL");
        assertThat(mars.getRobot(), is(equalTo("3 3 " + north + " " + Constants.LOST)));
    }

    @Test
    public void shouldSatisfyRequirementsOfChallenge() throws ValidationException {
        mars.setup(5, 3);

        Robot robot = new RobotImpl(1, 1, Compass.E);
        mars.setRobot(robot);
        mars.move("RFRFRFRF");
        assertThat(mars.getRobot(), is(equalTo("1 1 E")));

        Robot robot1 = new RobotImpl(3, 2, Compass.N);
        mars.setRobot(robot1);
        mars.move("FRRFLLFFRRFLL");
        assertThat(mars.getRobot(), is(equalTo("3 3 N LOST")));

        Robot robot2 = new RobotImpl(0, 3, Compass.W);
        mars.setRobot(robot2);
        mars.move("LLFFFLFLFL");
        assertThat(mars.getRobot(), is(equalTo("2 3 S")));
    }
}