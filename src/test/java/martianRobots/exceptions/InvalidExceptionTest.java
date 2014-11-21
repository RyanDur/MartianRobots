package martianRobots.exceptions;

import martianRobots.lang.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class InvalidExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetAnAppropriateMessageIfThrown() throws InvalidException {
        int row = 1;
        int column = 2;
        exception.expectMessage(row + " " + column + " " + Constants.INVALID_GRID_SIZE);
        throw new InvalidException(Arrays.asList(row, column));
    }

    @Test
    public void shouldProvideAppropriateMessage() throws InvalidException {
        String instructions = "FFFFFFGGGGHHmsdfgmsdfg";
        exception.expectMessage(instructions + " " + Constants.INVALID_INSTRUCTIONS);
        throw new InvalidException(instructions);
    }

    @Test
    public void shouldGetAnAppropriateMessageIfThrownException() throws InvalidException {
        int row = 1;
        int column = 2;
        char orientation = 'E';
        exception.expectMessage(row + " " + column + " " + orientation + " " + Constants.NOT_EXISTS);
        throw new InvalidException(Arrays.asList(row, column), orientation);
    }
}