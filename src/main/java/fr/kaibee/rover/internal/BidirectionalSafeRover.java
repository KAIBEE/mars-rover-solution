package fr.kaibee.rover.internal;

import fr.kaibee.rover.Coordinate;
import fr.kaibee.rover.Direction;
import fr.kaibee.rover.ObstacleDetectedException;
import fr.kaibee.rover.Rover;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Implementation of rover avoiding obstacles and able to move forward and backward
 */
public class BidirectionalSafeRover implements Rover {
    private static final String INVALID_COMMAND_ERROR_MSG = "Invalid command. Should be l,r,f or b";
    private static final char CMD_BACKWARD = 'b';
    private static final char CMD_RIGHT = 'r';
    private static final char CMD_LEFT = 'l';
    private static final char CMD_FORWARD = 'f';
    private static final Set<Character> VALID_COMMANDS = Set.of(CMD_BACKWARD, CMD_FORWARD, CMD_LEFT, CMD_RIGHT);

    private Coordinate coordinate;
    private Direction direction;
    private final Set<Coordinate> obstaclesCoordinates; // If there were more context to set, we should extract it to an contextual object like "world" or "grid".

    public BidirectionalSafeRover(int x, int y, char direction, Coordinate... obstacleCoordinates) {
        this.direction = Direction.create(direction);
        coordinate = new Coordinate(x, y);
        this.obstaclesCoordinates = obstaclesFrom(obstacleCoordinates);
    }

    private Set<Coordinate> obstaclesFrom(Coordinate[] obstacleCoordinates) {
        obstacleCoordinates = Objects.requireNonNullElseGet(obstacleCoordinates, () -> new Coordinate[0]);
         return Set.of(obstacleCoordinates);
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void execute(char[] commands) {
        for (char command : commands) {
            if (!VALID_COMMANDS.contains(command)) {
                throw new IllegalArgumentException(INVALID_COMMAND_ERROR_MSG);
            }

            direction = updateDirection(command);
            coordinate = updateCoordinates(command);
        }
    }

    private Direction updateDirection(char command) {
        return switch (command) {
            case CMD_RIGHT -> direction.rotateRight();
            case CMD_LEFT -> direction.rotateLeft();
            default -> direction;
        };
    }

    private Coordinate updateCoordinates(char command) {
        Coordinate nextCoordinate = resolveNextCoordinate(command);
        if (obstaclesCoordinates.contains(nextCoordinate)) {
            throw new ObstacleDetectedException(coordinate, nextCoordinate);
        }
        return nextCoordinate;
    }

    private Coordinate resolveNextCoordinate(char command) {
        return switch (command) {
            case CMD_BACKWARD -> switch (direction) {
                case S -> coordinate.incrementY();
                case E -> coordinate.decrementX();
                case W -> coordinate.incrementX();
                case N -> coordinate.decrementY();
            };
            case CMD_FORWARD -> switch (direction) {
                case S -> coordinate.decrementY();
                case E -> coordinate.incrementX();
                case W -> coordinate.decrementX();
                case N -> coordinate.incrementY();
            };
            default -> coordinate;
        };
    }
}
