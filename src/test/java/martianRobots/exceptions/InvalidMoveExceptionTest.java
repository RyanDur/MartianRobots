package martianRobots.exceptions;

import martianRobots.lang.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidMoveExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetAnAppropriateMessageIfThrownException() throws InvalidMoveException {
        int row = 1;
        int column = 2;
        char orientation = 'E';
        exception.expectMessage(row + " " + column + " " + orientation + " " + Constants.NOT_EXISTS);
        throw new InvalidMoveException(row, column, orientation);
    }
}