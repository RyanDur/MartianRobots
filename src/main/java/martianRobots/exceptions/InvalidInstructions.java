package martianRobots.exceptions;

import martianRobots.lang.Constants;

public class InvalidInstructions extends Exception {
    public InvalidInstructions(String instructions) {
        super(instructions + " " + Constants.INVALID_INSTRUCTIONS);
    }
}
