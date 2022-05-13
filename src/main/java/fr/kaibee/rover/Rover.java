package fr.kaibee.rover;

public interface Rover {
    void execute(char[] commands);
    Coordinate getCoordinate();
    Direction getDirection();
}
