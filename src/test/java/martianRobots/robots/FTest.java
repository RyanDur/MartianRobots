package martianRobots.robots;

import martianRobots.lang.Compass;
import org.junit.Before;
import org.junit.Test;

import static martianRobots.lang.Messages.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class FTest {

    @Test
    public void shouldMoveOneSpaceEastIfEast() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;

        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(2 + SPACE.toString() + y + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceSouthIfSouth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.S;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE.toString() + 1 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceNorthIfNorth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.N;
        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE.toString() + 3 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceWestIfWest() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.W;

        Robot robot = new F(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(0 + SPACE.toString() + y + SPACE + orientation)));
    }
}