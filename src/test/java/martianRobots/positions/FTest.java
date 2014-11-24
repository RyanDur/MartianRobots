package martianRobots.positions;

import martianRobots.exceptions.ValidationException;
import martianRobots.lang.Constants;
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
        char orientation = Constants.EAST;
        Position position = new F(x, y, orientation);
        assertThat(position.toString(), is(equalTo(2 + SPACE + y + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceSouthIfSouth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.SOUTH;
        Position position = new F(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + 1 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceNorthIfNorth() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.NORTH;
        Position position = new F(x, y, orientation);
        assertThat(position.toString(), is(equalTo(x + SPACE + 3 + SPACE + orientation)));
    }

    @Test
    public void shouldMoveOneSpaceWestIfWest() throws ValidationException {
        int x = 1;
        int y = 2;
        char orientation = Constants.WEST;
        Position position = new F(x, y, orientation);
        assertThat(position.toString(), is(equalTo(0 + SPACE + y + SPACE + orientation)));
    }
}