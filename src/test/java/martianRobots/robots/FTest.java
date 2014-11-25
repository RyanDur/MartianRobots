package martianRobots.robots;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Compass;
import org.junit.Test;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FTest {

    @Test
    public void shouldMoveOneSpaceEastIfEast() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(2 + SPACE + y + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceSouthIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.S;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + 1 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceNorthIfNorth() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.N;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + 3 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceWestIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.W;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(0 + SPACE + y + SPACE + orientation)));
    }
}