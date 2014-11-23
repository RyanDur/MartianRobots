package martianRobots;

import martianRobots.exceptions.InvalidException;
import martianRobots.positions.Position;

import java.util.List;

public interface MarsRobot {

    void setPosition(Position position) throws InvalidException;

    String getPosition();

    void setup(List<Integer> bounds) throws InvalidException;

    void move(String move) throws InvalidException;
}
