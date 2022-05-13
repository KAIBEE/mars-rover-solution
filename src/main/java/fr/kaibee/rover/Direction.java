package fr.kaibee.rover;

public enum Direction {
    N, E, S, W;

    public static Direction create(char direction) {
        return switch (direction) {
            case 'S' -> S;
            case 'E' -> E;
            case 'W' -> W;
            case 'N' -> N;
            default -> throw new IllegalArgumentException(String.format("Invalid direction %c. Should be N, S, E or W", direction));
        };
    }

    public Direction rotateRight() {
        return Direction.values()[(this.ordinal() + 1) % Direction.values().length];
    }

    public Direction rotateLeft() {
        int length = Direction.values().length;
        return Direction.values()[(this.ordinal() - 1 + length) % length];
    }
}
