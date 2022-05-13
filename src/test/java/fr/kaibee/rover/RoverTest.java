package fr.kaibee.rover;

import fr.kaibee.rover.internal.BidirectionalSafeRover;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import static fr.kaibee.rover.RoverTest.RoverAssertions.givenRover;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoverTest {

    @Test
    void should_init_rover() {
        givenRover(1, 2, 'N')
                .thenCoordinateIsEqualTo(1, 2)
                .thenDirectionIsEqualTo(Direction.N);

        givenRover(3, 2, 'N')
                .thenCoordinateIsEqualTo(3, 2)
                .thenDirectionIsEqualTo(Direction.N);

        givenRover(3, 5, 'N')
                .thenCoordinateIsEqualTo(3, 5)
                .thenDirectionIsEqualTo(Direction.N);

        givenRover(3, 5, 'S')
                .thenCoordinateIsEqualTo(3, 5)
                .thenDirectionIsEqualTo(Direction.S);

        givenRover(3, 5, 'E')
                .thenCoordinateIsEqualTo(3, 5)
                .thenDirectionIsEqualTo(Direction.E);

        givenRover(3, 5, 'W')
                .thenCoordinateIsEqualTo(3, 5)
                .thenDirectionIsEqualTo(Direction.W);
    }

    @Test
    void should_not_initialize_with_invalid_input() {
        IllegalArgumentException argumentException = assertThrows(IllegalArgumentException.class, () -> new BidirectionalSafeRover(1, 2, 'p'));
        assertThat(argumentException.getMessage()).isEqualTo("Invalid direction p. Should be N, S, E or W");

        argumentException = assertThrows(IllegalArgumentException.class, () -> new BidirectionalSafeRover(1, 2, 'z'));
        assertThat(argumentException.getMessage()).isEqualTo("Invalid direction z. Should be N, S, E or W");
    }

    @Test
    void should_move_forward_facing_north() {
        givenRover(1, 2, 'N')
                .when('f')
                .thenCoordinateIsEqualTo(1, 2 + 1);

        givenRover(1, 3, 'N')
                .when('f')
                .thenCoordinateIsEqualTo(1, 3 + 1);

        givenRover(2, 3, 'N')
                .when('f')
                .thenCoordinateIsEqualTo(2, 3 + 1);
    }

    @Test
    void should_move_forward_facing_south() {
        givenRover(1, 2, 'S')
                .when('f')
                .thenCoordinateIsEqualTo(1, 2 - 1);

        givenRover(1, 3, 'S')
                .when('f')
                .thenCoordinateIsEqualTo(1, 3 - 1);

        givenRover(5, 3, 'S')
                .when('f')
                .thenCoordinateIsEqualTo(5, 3 - 1);
    }

    @Test
    void should_move_forward_facing_east() {
        givenRover(3, 2, 'E')
                .when('f')
                .thenCoordinateIsEqualTo(3 + 1, 2);

        givenRover(4, 2, 'E')
                .when('f')
                .thenCoordinateIsEqualTo(4 + 1, 2);

        givenRover(4, 3, 'E')
                .when('f')
                .thenCoordinateIsEqualTo(4 + 1, 3);
    }

    @Test
    void should_move_forward_facing_west() {
        givenRover(3, 2, 'W')
                .when('f')
                .thenCoordinateIsEqualTo(3 - 1, 2);

        givenRover(2, 2, 'W')
                .when('f')
                .thenCoordinateIsEqualTo(2 - 1, 2);

        givenRover(2, 1, 'W')
                .when('f')
                .thenCoordinateIsEqualTo(2 - 1, 1);
    }

    @Test
    void should_move_backward_facing_north() {
        givenRover(1, 2, 'N')
                .when('b')
                .thenCoordinateIsEqualTo(1, 2 - 1);

        givenRover(1, 3, 'N')
                .when('b')
                .thenCoordinateIsEqualTo(1, 3 - 1);

        givenRover(2, 3, 'N')
                .when('b')
                .thenCoordinateIsEqualTo(2, 3 - 1);
    }

    @Test
    void should_move_backward_facing_south() {
        givenRover(1, 2, 'S')
                .when('b')
                .thenCoordinateIsEqualTo(1, 2 + 1);

        givenRover(1, 3, 'S')
                .when('b')
                .thenCoordinateIsEqualTo(1, 3 + 1);

        givenRover(5, 3, 'S')
                .when('b')
                .thenCoordinateIsEqualTo(5, 3 + 1);
    }

    @Test
    void should_move_backward_facing_east() {
        givenRover(3, 2, 'E')
                .when('b')
                .thenCoordinateIsEqualTo(3 - 1, 2);

        givenRover(4, 2, 'E')
                .when('b')
                .thenCoordinateIsEqualTo(4 - 1, 2);

        givenRover(4, 3, 'E')
                .when('b')
                .thenCoordinateIsEqualTo(4 - 1, 3);
    }

    @Test
    void should_move_backward_facing_west() {
        givenRover(3, 2, 'W')
                .when('b')
                .thenCoordinateIsEqualTo(3 + 1, 2);

        givenRover(1, 2, 'W')
                .when('b')
                .thenCoordinateIsEqualTo(1 + 1, 2);

        givenRover(1, 4, 'W')
                .when('b')
                .thenCoordinateIsEqualTo(1 + 1, 4);
    }

    @Test
    void should_turn_right() {
        givenRover(1, 4, 'N')
                .when('r')
                .thenDirectionIsEqualTo(Direction.E);

        givenRover(1, 4, 'E')
                .when('r')
                .thenDirectionIsEqualTo(Direction.S);

        givenRover(1, 4, 'S')
                .when('r')
                .thenDirectionIsEqualTo(Direction.W);

        givenRover(1, 4, 'W')
                .when('r')
                .thenDirectionIsEqualTo(Direction.N);
    }

    @Test
    void should_turn_left() {
        givenRover(1, 4, 'N')
                .when('l')
                .thenDirectionIsEqualTo(Direction.W);

        givenRover(1, 4, 'W')
                .when('l')
                .thenDirectionIsEqualTo(Direction.S);

        givenRover(1, 4, 'S')
                .when('l')
                .thenDirectionIsEqualTo(Direction.E);

        givenRover(1, 4, 'E')
                .when('l')
                .thenDirectionIsEqualTo(Direction.N);
    }

    @Test
    void should_wrapped_at_borders() {
        givenRover(1, 1, 'N')
                .when('b')
                .thenCoordinateIsEqualTo(1, 5);

        givenRover(1, 1, 'E')
                .when('b')
                .thenCoordinateIsEqualTo(5, 1);

        givenRover(2, 5, 'N')
                .when('f')
                .thenCoordinateIsEqualTo(2, 1);

        givenRover(5, 3, 'E')
                .when('f')
                .thenCoordinateIsEqualTo(1, 3);
    }

    @Test
    void should_fail_when_invalid_command() {
        char invalidCommand = 'z';
        givenRover(5, 3, 'E')
                .when(invalidCommand)
                .thenThrows(IllegalArgumentException.class, "Invalid command. Should be l,r,f or b");
    }

    @Test
    void should_abort_when_meets_obstacle() {
        givenRover(5, 4, 'N')
                .withObstacle(5, 5)
                .when('f')
                .thenThrows(ObstacleDetectedException.class, "Obstacle detected at Coordinate[x=5, y=5]. Rover stopped at Coordinate[x=5, y=4]");

        givenRover(3, 2, 'N')
                .withObstacle(3, 3)
                .when('f')
                .thenThrows(ObstacleDetectedException.class, "Obstacle detected at Coordinate[x=3, y=3]. Rover stopped at Coordinate[x=3, y=2]");

        // Multiple obstacles
        givenRover(3, 2, 'N')
                .withObstacle(5, 5)
                .withObstacle(3, 3)
                .when('f')
                .thenThrows(ObstacleDetectedException.class, "Obstacle detected at Coordinate[x=3, y=3]. Rover stopped at Coordinate[x=3, y=2]");
    }

    @Test
    void should_handle_multiple_commands() {
        givenRover(3, 1, 'N')
                .when('f', 'f')
                .thenCoordinateIsEqualTo(3, 1 + 2);

        givenRover(2, 2, 'N')
                .withObstacle(3, 3)
                .when('f', 'l', 'b')
                .thenThrows(ObstacleDetectedException.class, "Obstacle detected at Coordinate[x=3, y=3]. Rover stopped at Coordinate[x=2, y=3]");
    }

    static class RoverAssertions {

        private static final Runnable DEFAULT_RUNNABLE = () -> {
        };
        private int initX;
        private int initY;
        private char direction;
        private Runnable toExecute = DEFAULT_RUNNABLE;
        private Rover rover;
        private Set<Coordinate> obstacleCoordinates = new HashSet<>();

        public RoverAssertions(int initX, int initY, char direction) {
            this.initX = initX;
            this.initY = initY;
            this.direction = direction;
        }

        static RoverAssertions givenRover(int initX, int initY, char direction) {
            return new RoverAssertions(initX, initY, direction);
        }


        RoverAssertions when(char... commands) {
            initRoverIfNull();
            toExecute = () -> rover.execute(commands);
            return this;
        }

        private void initRoverIfNull() {
            rover = Objects.requireNonNullElse(rover, new BidirectionalSafeRover(initX, initY, direction, obstacleCoordinates()));
        }

        private Coordinate[] obstacleCoordinates() {
            if (obstacleCoordinates != null) {
                return obstacleCoordinates.toArray(new Coordinate[0]);
            }
            return new Coordinate[0];
        }

        RoverAssertions thenDirectionIsEqualTo(Direction expectedDirection) {
            initRoverIfNull();
            executeFirst();
            assertThat(rover.getDirection()).isEqualTo(expectedDirection);
            return this;
        }

        RoverAssertions thenCoordinateIsEqualTo(int expectedX, int expectedY) {
            initRoverIfNull();
            executeFirst();
            assertThat(rover.getCoordinate()).isEqualTo(new Coordinate(expectedX, expectedY));
            return this;
        }

        void thenThrows(Class<?> expectedClassException, String expectedMessage) {
            Exception thrownException = assertThrows(Exception.class, this::executeFirst);
            assertThat(thrownException.getClass()).isEqualTo(expectedClassException);
            assertThat(thrownException.getMessage()).isEqualTo(expectedMessage);
        }

        private void executeFirst() {
            toExecute.run();
            toExecute = DEFAULT_RUNNABLE;
        }

        public RoverAssertions withObstacle(int x, int y) {
            obstacleCoordinates.add(new Coordinate(x, y));
            return this;
        }
    }
}
