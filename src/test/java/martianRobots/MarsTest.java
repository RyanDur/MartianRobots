
package martianRobots;


import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Lost;
import martianRobots.lang.Max;
import martianRobots.lang.Messages;
import martianRobots.robots.Robot;
import martianRobots.robots.RobotImpl;
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

        exception.expectMessage(x + " " + y + " " + orientation + Messages.NOT_EXISTS);
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
        exception.expectMessage(x + " " + Messages.INVALID_X_SIZE.toString());
        mars.setup(x, y);
    }

    @Test
    public void shouldNGetHelpfulMessageForInvalidGridSetupForY() throws ValidationException {
        int x = 1;
        int y = 72;
        exception.expectMessage(y + " " + Messages.INVALID_Y_SIZE.toString());
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
        String instructions = new String(new char[Max.MAX_INSTRUCTION_SIZE.getMax()]).replace('\0', 'F');
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
        exception.expectMessage(x + " " + y + " " + orientation1 + Messages.IS_TAKEN);
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

    @SuppressWarnings("unchecked")
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
        when(moveRobot.getLocation()).thenReturn(Arrays.asList(x + 1, y), Arrays.asList(x + 2, y));
        when(moveRobot.getOrientation()).thenReturn(orientation);
        when(moveRobot.toString()).thenReturn((x + 1) + " " + y + " " + orientation);

        char forward = 'F';
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
        String instructions = new String(new char[Max.MAX_INSTRUCTION_SIZE.getMax() - 1]).replace('\0', 'F');
        int x = 1;
        int y = 1;
        Compass orientation = Compass.E;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move(instructions);
        assertThat(mars.getRobot(), is(equalTo(5 + " " + y + " " + orientation + " " + Lost.LOST)));
    }

    @Test
    public void shouldIndicateWhenARobotIsLost() throws ValidationException {
        int x = 1;
        int y = 1;

        Compass orientation = Compass.W;
        Robot robot = new RobotImpl(x, y, orientation);
        mars.setRobot(robot);
        mars.move("LFFLLFF");
        assertThat(mars.getRobot(), is(equalTo(x + " " + 0 + " " + Compass.S + " " + Lost.LOST)));
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
        assertThat(mars.getRobot(), is(equalTo(x + " " + 3 + " " + orientation + " " + Lost.LOST)));
    }
//
//        String instructions = new String(new char[Max.MAX_INSTRUCTION_SIZE.getMax() - 1]).replace('\0', forward);
//        Compass orientation = Compass.E;
//        int x = 1;
//        int y = 1;
//        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
//        when(robot.getOrientation()).thenReturn(orientation);
//        when(robot.toString()).thenReturn(x + " " + y + " " + orientation);
//        Robot moveRobot = mock(Robot.class);
//
//
//        when(moveRobot.getLocation()).thenReturn(Arrays.asList(x + 10, y));
//        when(moveRobot.getOrientation()).thenReturn(orientation);
//        when(moveRobot.toString()).thenReturn(x + " " + y + " " + orientation);
//        when(robot.move(forward)).thenReturn(moveRobot);
//        when(moveRobot.move(forward)).thenReturn(moveRobot);
//
//        mars.setRobot(robot);
//        assertThat(mars.getRobot(), is(equalTo("3 3 " + north + " " + Lost.LOST)));
//    }

//    @Test
//    public void shouldSatisfyRequirementsOfChallenge() throws ValidationException {
//        mars.setup(5, 3);
//
//        Robot robot = new RobotImpl(1, 1, Compass.E);
//        mars.setRobot(robot);
//        mars.move("RFRFRFRF");
//        assertThat(mars.getRobot(), is(equalTo("1 1 E")));
//
//        Robot robot1 = new RobotImpl(3, 2, Compass.N);
//        mars.setRobot(robot1);
//        mars.move("FRRFLLFFRRFLL");
//        assertThat(mars.getRobot(), is(equalTo("3 3 N LOST")));
//
//        Robot robot2 = new RobotImpl(0, 3, Compass.W);
//        mars.setRobot(robot2);
//        mars.move("LLFFFLFLFL");
//        assertThat(mars.getRobot(), is(equalTo("2 3 S")));
//    }
}