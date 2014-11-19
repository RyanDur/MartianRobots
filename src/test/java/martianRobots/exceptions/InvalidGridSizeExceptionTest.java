package martianRobots.exceptions;

import martianRobots.lang.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidGridSizeExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetAnAppropriateMessageIfThrown() throws InvalidGridSizeException {
        int row = 1;
        int column = 2;
        exception.expectMessage(row + " " + column + " " + Constants.INVALID_GRID_SIZE);
        throw new InvalidGridSizeException(row, column);
    }
}