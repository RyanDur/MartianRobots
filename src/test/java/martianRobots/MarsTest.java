
package martianRobots;


import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import martianRobots.lang.Lost;
import martianRobots.lang.Max;
import martianRobots.lang.Messages;
import martianRobots.robots.Robot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyChar;
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

        exception.expectMessage(x + " " + y + " " + orientation + " " + Messages.NOT_EXISTS);
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
        exception.expectMessage(x + " " + y + " " + orientation1 + " " + Messages.IS_TAKEN);
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
    public void shouldNotBeAbleToInputTheMaxInstructionSize() throws ValidationException {
        exception.expect(ValidationException.class);
        String instructions = new String(new char[Max.MAX_INSTRUCTION_SIZE.getMax()]).replace('\0', 'F');
        int x = 1;
        int y = 1;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(Compass.E);
        mars.setRobot(robot);
        mars.move(instructions);
    }

    @Test
    public void shouldNGetAHelpfulMessageWhenInputingTheMaxInstructionSize() throws ValidationException {
        String instructions = new String(new char[Max.MAX_INSTRUCTION_SIZE.getMax()]).replace('\0', 'F');
        exception.expectMessage(instructions + " " + Messages.INVALID_INSTRUCTIONS);
        int x = 1;
        int y = 1;
        when(robot.getLocation()).thenReturn(Arrays.asList(x, y));
        when(robot.getOrientation()).thenReturn(Compass.E);
        mars.setRobot(robot);
        mars.move(instructions);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldIndicateWhenARobotIsLost() throws ValidationException {
        int x = 0;
        int y = 0;

        Compass orientation = Compass.W;
        List<Integer> integers = Arrays.asList(x, y);
        List<Integer> integers1 = Arrays.asList(-1, 0);
        when(robot.move(anyChar())).thenReturn(robot);
        when(robot.toString()).thenReturn(x + " " + y + " " + Compass.S);
        when(robot.getLocation()).thenReturn(integers, integers, integers, integers, integers1);
        when(robot.getOrientation()).thenReturn(orientation);
        mars.setRobot(robot);
        mars.move("F");
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + Compass.S + " " + Lost.LOST)));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldIgnoreMoveThatHasAScentToMoveOffTheBoard() throws ValidationException {
        int x = 0;
        int y = 0;

        Compass orientation = Compass.W;
        List<Integer> integers = Arrays.asList(x, y);
        List<Integer> integers1 = Arrays.asList(-1, 0);
        Robot robot1 = mock(Robot.class);
        when(robot.move(anyChar())).thenReturn(robot1);
        when(robot1.move(anyChar())).thenReturn(robot1);
        when(robot1.toString()).thenReturn(x + " " + y + " " + Compass.S);
        when(robot.getLocation()).thenReturn(integers);
        when(robot1.getLocation()).thenReturn(integers1, integers1, integers, integers, integers, integers, integers1);
        when(robot.getOrientation()).thenReturn(orientation);
        when(robot1.getOrientation()).thenReturn(orientation);
        mars.setRobot(robot);
        mars.move("F");
        mars.setRobot(robot1);
        mars.move("F");
        assertThat(mars.getRobot(), is(equalTo(x + " " + y + " " + Compass.S)));
    }
}