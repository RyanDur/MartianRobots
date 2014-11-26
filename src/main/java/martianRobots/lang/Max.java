package martianRobots.lang;

public enum Max {
    MAX_X(50),
    MAX_Y(50),
    MAX_INSTRUCTION_SIZE(100);
    private int max;

    Max(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}
