package martianRobots.lang;

public enum View {
    RESET_ID("#reset"), GO_ID("#go"), MOVE_ID("#move"), X_ID("#x"), Y_ID("#y"),
    POS_ID("#position"), INS_ID("#instructions"), OUTPUT_ID("#output"), MARS("/mars.fxml"),
    CONTROL_ID("#control"), MESSAGES_ID("#messages");
    private String name;

    private View(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
