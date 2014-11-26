package martianRobots.robots;

import martianRobots.lang.Compass;
import org.junit.Test;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RTest {

    @Test
    public void shouldTurnEastIfNorth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.N;
        Compass turn = Compass.E;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnWestIfSouth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.S;
        Compass turn = Compass.W;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfWest() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.W;
        Compass turn = Compass.N;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfEast() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;
        Compass turn = Compass.S;
        Robot robot = new R(x, y, orientation);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}