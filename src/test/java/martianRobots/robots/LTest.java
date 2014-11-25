package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
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
        Compass orientation = Compass.N;
        Compass turn = Compass.W;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnEastIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.S;
        Compass turn = Compass.E;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.W;
        Compass turn = Compass.S;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfEast() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;
        Compass turn = Compass.N;
        Robot robot = new L(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}