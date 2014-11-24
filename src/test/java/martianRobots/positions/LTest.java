package martianRobots.positions;

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
        Position position = new L(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnEastIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.SOUTH;
        char turn = Constants.EAST;
        Position position = new L(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnSouthIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.WEST;
        char turn = Constants.SOUTH;
        Position position = new L(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }

    @Test
    public void shouldTurnNorthIfEast() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.EAST;
        char turn = Constants.NORTH;
        Position position = new L(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + y + SPACE + turn)));
    }
}