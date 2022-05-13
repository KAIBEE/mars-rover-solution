package fr.kaibee.rover;

public class ObstacleDetectedException extends RuntimeException {
    public ObstacleDetectedException(String message) {
    }

    public ObstacleDetectedException(Coordinate lastRoverPosition, Coordinate obstaclePosition) {

    }
}
