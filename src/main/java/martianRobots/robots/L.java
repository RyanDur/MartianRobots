package martianRobots.robots;

import martianRobots.lang.Compass;

class L extends RobotImpl {
    public L(final int x, final int y, final Compass orientation, Robots robots) {
        super(x, y, orientation, robots);
        int index = Compass.valueOf(orientation.toString()).ordinal();
        setOrientation.accept(Compass.values()[index - 1 < 0 ? Compass.values().length - 1 : index - 1]);
    }
}
