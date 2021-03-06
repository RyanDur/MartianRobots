package martianRobots.exceptions;

import martianRobots.lang.Messages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

public class ValidationExceptionTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldGetAnAppropriateMessageIfThrown() throws ValidationException {
        int row = 1;
        int column = 2;
        exception.expectMessage("[" + row + ", " + column + "]" + " " + Messages.INVALID_X_SIZE);
        throw new ValidationException(Arrays.asList(row, column), Messages.INVALID_X_SIZE);
    }
}