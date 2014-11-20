package martianRobots.exceptions;

import martianRobots.lang.Constants;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class InvalidInstructionsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldProvideAppropriateMessage() throws InvalidInstructions {
        String instructions = "FFFFFFGGGGHHmsdfgmsdfg";
        exception.expectMessage(instructions + " " + Constants.INVALID_INSTRUCTIONS);
        throw new InvalidInstructions(instructions);
    }
}