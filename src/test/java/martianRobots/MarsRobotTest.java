package martianRobots;

import martianRobots.exceptions.InvalidMoveException;
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
    public void setup() {
        marsRobot = new MarsRobotImpl();
        int row = 5;
        int column = 3;
        marsRobot.setup(row, column);
    }

    @Test
    public void shouldBeAbleToGetThePositionOfARobot() throws InvalidMoveException {
        int row = 1;
        int column = 1;
        char orientation = 'E';
        marsRobot.setPosition(row, column, orientation);
        assertThat(marsRobot.getPosition(), is(equalTo("1 1 E")));
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheRowOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = 'E';
        marsRobot.setPosition(-1, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheRowOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = 'E';
        marsRobot.setPosition(6, 2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotLessThanTheColumnOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = 'E';
        marsRobot.setPosition(1, -2, orientation);
    }

    @Test
    public void shouldNotBeAbleToSetARobotGreaterThanTheColumnOfTheGrid() throws InvalidMoveException {
        exception.expect(InvalidMoveException.class);
        char orientation = 'E';
        marsRobot.setPosition(3, 4, orientation);
    }
}
