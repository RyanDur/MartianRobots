package martianRobots;

import martianRobots.exceptions.ValidationException;
import martianRobots.positions.Position;

import java.util.List;

public interface MarsRobot {

    void setPosition(Position position) throws ValidationException;

    String getPosition();

    void setup(List<Integer> bounds) throws ValidationException;

    void move(String move) throws ValidationException;
}
