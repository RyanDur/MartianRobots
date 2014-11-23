package martianRobots.positions;

import martianRobots.lang.Constants;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static martianRobots.lang.Constants.SPACE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RTest {
    @Test
    public void shouldTurnEastIfNorth() {
        int x = 1;
        int y = 2;
        List<Integer> list = Arrays.asList(x, y);
        char orientation = Constants.NORTH;
        char turn = Constants.EAST;
        Position position = new R(list, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnWestIfSouth() {
        int x = 1;
        int y = 2;
        List<Integer> list = Arrays.asList(x, y);
        char orientation = Constants.SOUTH;
        char turn = Constants.WEST;
        Position position = new R(list, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfWest() {
        int x = 1;
        int y = 2;
        List<Integer> list = Arrays.asList(x, y);
        char orientation = Constants.WEST;
        char turn = Constants.NORTH;
        Position position = new R(list, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfEast() {
        int x = 1;
        int y = 2;
        List<Integer> list = Arrays.asList(x, y);
        char orientation = Constants.EAST;
        char turn = Constants.SOUTH;
        Position position = new R(list, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}