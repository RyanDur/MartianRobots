package martianRobots.lang;

public enum View {
    RESET_ID("#reset"), GO_ID("#go"), MOVE_ID("#move"), X_ID("#x"), Y_ID("#y"),
    POS_ID("#position"), INSTRUCTIONS_ID("#instructions"), OUTPUT_ID("#output"), MARS("/mars.fxml"),
    CONTROL_ID("#control"), MESSAGES_ID("#messages"), RIGHT_ID("#right"), SCROLL_ID("#scroll"), INS_ID("#ins"),
    START_ID("#start");
    private String name;

    private View(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
