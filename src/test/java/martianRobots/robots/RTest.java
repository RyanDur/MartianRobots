package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
import org.junit.Test;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RTest {
    @Test
    public void shouldTurnEastIfNorth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.NORTH;
        char turn = Constants.EAST;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnWestIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.SOUTH;
        char turn = Constants.WEST;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.WEST;
        char turn = Constants.NORTH;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfEast() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.EAST;
        char turn = Constants.SOUTH;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}