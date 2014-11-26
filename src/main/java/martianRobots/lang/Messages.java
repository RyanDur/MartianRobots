package martianRobots.lang;

public enum Messages {
    DOT("."),
    SPACE(" "),
    EMPTY(""),
    NOT_EXISTS("Does not exist, please use another."),
    INVALID_X_SIZE("is an invalid size for x, please use another."),
    INVALID_Y_SIZE("is an invalid size for y, please use another."),
    INVALID_DIRECTION("is an invalid direction, please correct your input."),
    INVALID_INSTRUCTIONS("is an invalid length of instructions, please correct your input."),
    IS_TAKEN("is occupied, please choose another"), MULTI_SPACE(" +"),
    NOT_A_NUMBER("is not a number!!"),
    NOT_A_COMPASS("is not a Compass position!"),
    MARS("/mars.fxml");
    private String message;

    private Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
