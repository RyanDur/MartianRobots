package martianRobots.positions;

import martianRobots.exceptions.InvalidException;
import martianRobots.lang.Constants;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PositionTest {

    @Test
    public void shouldBeAbleToCheckTheEqualityOfPositions() {
        Position position = new PositionImpl(Arrays.asList(1,2), Constants.EAST);
        Position position1 = new PositionImpl(Arrays.asList(1,2), Constants.EAST);
        assertThat(position.equals(position1), is(true));
    }

    @Test
    public void shouldBeAbleToCheckTheContentsOfPositions() {
        Set<Position> scents = new HashSet<>();
        Position position = new PositionImpl(Arrays.asList(1,2), Constants.EAST);
        scents.add(position);
        Position position1 = new PositionImpl(Arrays.asList(1,2), Constants.EAST);
        assertThat(scents.contains(position1), is(true));
    }

    @Test
    public void shouldBeAbleToDisplayThePosition() throws InvalidException {
        int x = 1;
        int y = 1;
        List<Integer> coordinates = Arrays.asList(x, y);
        char orientation = Constants.EAST;
        Position position = new PositionImpl(coordinates, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + x + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToOrientAPositionWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveAPositionForwardEast() throws InvalidException {
        char orientation = Constants.EAST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(2 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardNorth() throws InvalidException {
        char orientation = Constants.NORTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(x + " " + 3 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardSouth() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(x + " " + 1 + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToMoveARobotForwardWest() throws InvalidException {
        char orientation = Constants.WEST;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('F');
        assertThat(position.toString(), is(equalTo(0 + " " + y + " " + orientation)));
    }

    @Test
    public void shouldBeAbleToTurnRight() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('R');
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + Constants.WEST)));
    }

    @Test
    public void shouldBeAbleToTurnLeft() throws InvalidException {
        char orientation = Constants.SOUTH;
        int x = 1;
        int y = 2;
        List<Integer> coordinates = Arrays.asList(x, y);
        Position position = new PositionImpl(coordinates, orientation);
        position = position.move('L');
        assertThat(position.toString(), is(equalTo(x + " " + y + " " + Constants.EAST)));
    }
}