package martianRobots;

import martianRobots.exceptions.InvalidGridSizeException;
import martianRobots.exceptions.InvalidMoveException;
import martianRobots.factories.PositionFactory;
import martianRobots.factories.PositionFactoryImpl;
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
        PositionFactory positionFactory = new PositionFactoryImpl();
        marsRobot = new MarsRobotImpl(positionFactory);
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
    public void shouldIndicateWhenARobotIsLost() throws InvalidMoveException {
        marsRobot.setPosition(1, 1, Constants.WEST);
        marsRobot.move("LFFLLFF");
        assertThat(marsRobot.getPosition(), is(equalTo("1 0 " + Constants.SOUTH + " " + Constants.LOST)));
    }
}
