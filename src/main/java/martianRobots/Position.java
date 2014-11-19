package martianRobots;

public interface Position {

    int getX();

    int getY();

    char getOrientation();

    Position turn(char direction);

    Position moveForward();
}
