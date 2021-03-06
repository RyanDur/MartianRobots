package martianRobots.robots;

import martianRobots.lang.Compass;

class R extends RobotImpl {
    public R(final int x, final int y, final Compass orientation) {
        super(x, y, orientation);
        int index = Compass.valueOf(orientation.toString()).ordinal();
        setOrientation.accept(Compass.values()[index + 1 > Compass.values().length - 1 ? 0 : index + 1]);
    }
}
