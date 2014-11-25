package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Constants;
import martianRobots.robots.Robot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MarsTest {

    private Mars mars;

    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Robot robot;
    private char forward = 'F';

    @Before
    public void setup() throws ValidationException {
        mars = new MarsImpl();
        mars.setup(5, 3);
        robot = mock(Robot.class);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        when(robot.getLocation()).thenReturn(Arrays.asList(-1, 2));
        when(robot.getOrientation()).thenReturn(orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidCoordinates() throws ValidationException {
        Compass orientation = Compass.E;
        int x = -1;
        int y = 2;
        exception.expectMessage(x + " " + y + " " + orientation + Constants.NOT_EXISTS);
        when(robot.getLocation()).thenReturn(Arrays.asList(-1, 2));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        int x = 6;
        int y = 2;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        int x = 1;
        int y = -2;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        mars.setRobot(robot);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        int x = 3;
        int y = 4;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
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
        exception.expectMessage(x + Constants.INVALID_X_SIZE);
        mars.setup(x, y);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidGridSetupForY() throws ValidationException {
        int x = 1;
        int y = 72;
        exception.expectMessage(y + Constants.INVALID_Y_SIZE);
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
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE]).replace('\0', forward);
        mars.move(instructions);
    }

    @Test
    public void shouldNotBeAbleToSetARobotOnAnother() throws ValidationException {
        exception.expect(ValidationException.class);
        Compass orientation = Compass.E;
        int x = 1;
        int y = 1;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        Robot robot1 = mock(Robot.class);
        Compass orientation1 = Compass.W;
        int x1 = 1;
        int y1 = 1;
        when(robot1.getLocation()).thenReturn(Arrays.asList(x1, y1));
        when(robot1.getOrientation()).thenReturn(orientation1);
        when(robot1.toString()).thenReturn(x1 + " " + y1 + " " + orientation1);
        mars.setRobot(robot);
        mars.setRobot(robot1);
    }

    @Test
    public void shouldGiveHelpfulMessageIfSpotIsTakenOnPlacement() throws ValidationException {
        Compass orientation = Compass.E;
        int x = 1;
        int y = 1;
        Compass orientation1 = Compass.W;
        exception.expectMessage(x + " " + y + " " + orientation1 + Constants.IS_TAKEN);
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        Robot robot1 = mock(Robot.class);
        int x1 = 1;
        int y1 = 1;
        when(robot1.getLocation()).thenReturn(Arrays.asList(x1, y1));
        when(robot1.getOrientation()).thenReturn(orientation1);
        when(robot1.toString()).thenReturn(x1 + " " + y1 + " " + orientation1);
        mars.setRobot(robot);
        mars.setRobot(robot1);
    }

    @Test
    public void shouldIgnoreInstructionIfMoveSpaceIsOccupied() throws ValidationException {
        Compass orientation = Compass.E;
        int x = 1;
        int y = 1;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        Robot robot1 = mock(Robot.class);
        int x1 = 1;
        int y1 = 1;
        when(robot1.getLocation()).thenReturn(Arrays.asList(x1, y1));
        when(robot1.getOrientation()).thenReturn(orientation);
        when(robot1.toString()).thenReturn(x1 + " " + y1 + " " + orientation);

        Robot moveRobot = mock(Robot.class);
        when(moveRobot.getLocation()).thenReturn(Arrays.asList(x + 1, y));
        when(moveRobot.getOrientation()).thenReturn(orientation);
        when(moveRobot.toString()).thenReturn(x + " " + y + " " + orientation);

        Robot moveRobot1 = mock(Robot.class);
        when(moveRobot1.getLocation()).thenReturn(Arrays.asList(x1 + 1, y1));
        when(moveRobot1.getOrientation()).thenReturn(orientation);
        when(moveRobot1.toString()).thenReturn(x1 + " " + y1 + " " + orientation);

        when(robot.move(forward)).thenReturn(moveRobot);
        when(robot1.move(forward)).thenReturn(moveRobot);
        mars.setRobot(robot);
        mars.move("F");
        mars.setRobot(robot1);
        mars.move("FF");
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + orientation)));

    }

    @Test
    public void shouldBeAbleToInputJustUnderTheMaxInstructionSize() throws ValidationException {
        String instructions = new String(new char[Constants.MAX_INSTRUCTION_SIZE - 1]).replace('\0', forward);
        Compass orientation = Compass.E;
        int x = 1;
        int y = 1;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
        Robot moveRobot = mock(Robot.class);

        when(moveRobot.getLocation()).thenReturn(Arrays.asList(x + 10, y));
        when(moveRobot.getOrientation()).thenReturn(orientation);
        when(moveRobot.toString()).thenReturn(x + " " + y + " " + orientation);
        when(robot.move(forward)).thenReturn(moveRobot);
        when(moveRobot.move(forward)).thenReturn(moveRobot);

        mars.setRobot(robot);
        mars.move(instructions);
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + orientation + " " + Constants.LOST)));
    }
}