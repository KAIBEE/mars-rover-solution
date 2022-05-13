package fr.kaibee.rover;

public class ObstacleDetectedException extends RuntimeException {
    private final Coordinate lastRoverPosition;
    private final Coordinate obstaclePosition;

    public ObstacleDetectedException(Coordinate lastRoverPosition, Coordinate obstaclePosition) {
        this.lastRoverPosition = lastRoverPosition;
        this.obstaclePosition = obstaclePosition;
    }

    @Override
    public String getMessage() {
        return String.format("Obstacle detected at %s. Rover stopped at %s", obstaclePosition, lastRoverPosition);
    }
}
