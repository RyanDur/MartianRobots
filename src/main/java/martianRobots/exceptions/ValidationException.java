package martianRobots.exceptions;

import martianRobots.lang.Messages;

public class ValidationException extends Exception {

    public ValidationException(Object o, Messages message) {
        super(o + " " + message);
    }
}
