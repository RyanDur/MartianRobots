package martianRobots.lang;

public enum Lost {
    LOST("LOST"), NOT_LOST("");
    private String lost;

    Lost(String lost) {
        this.lost = lost;
    }

    @Override
    public String toString() {
        return lost;
    }
}
