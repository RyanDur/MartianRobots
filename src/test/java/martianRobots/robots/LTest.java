package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
import org.junit.Test;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LTest {

    @Test
    public void shouldTurnWestIfNorth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.NORTH;
        char turn = Constants.WEST;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnEastIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.SOUTH;
        char turn = Constants.EAST;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.WEST;
        char turn = Constants.SOUTH;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfEast() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.EAST;
        char turn = Constants.NORTH;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}