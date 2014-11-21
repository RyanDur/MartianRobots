package martianRobots;

import martianRobots.exceptions.InvalidException;

import java.util.List;

public interface MarsRobot {

    void setPosition(List<Integer> coordinates, char orientation) throws InvalidException;

    String getPosition();

    void setup(List<Integer> bounds) throws InvalidException;

    void move(String move) throws InvalidException;
}
