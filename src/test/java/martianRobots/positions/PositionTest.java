package martianRobots.positions;

import martianRobots.lang.Constants;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
}