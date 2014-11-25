package martianRobots.robots;

import martianRobots.lang.Compass;
import org.junit.Before;
import org.junit.Test;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class LTest {

    private Robots robots;

    @Before
    public void setup() {
        robots = mock(Robots.class);
    }

    @Test
    public void shouldTurnWestIfNorth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.N;
        Compass turn = Compass.W;
        Robot robot = new L(x, y, orientation, robots);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnEastIfSouth() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.S;
        Compass turn = Compass.E;
        Robot robot = new L(x, y, orientation, robots);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfWest() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.W;
        Compass turn = Compass.S;
        Robot robot = new L(x, y, orientation, robots);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfEast() {
        int x = 1;
        int y = 2;
        Compass orientation = Compass.E;
        Compass turn = Compass.N;
        Robot robot = new L(x, y, orientation, robots);
        assertThat(robot.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}