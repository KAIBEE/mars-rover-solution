package fr.kaibee.rover;

public record Coordinate(int x, int y) {

    // KISS => No need to extract following constants for now
    public static final int Y_MIN = 1;
    public static final int X_MIN = 1;
    public static final int WITDH = 5;

    public Coordinate decrementY() {
        int y = (this.y - 1 + WITDH - Y_MIN) % WITDH + Y_MIN;
        return new Coordinate(this.x, y);
    }

    public Coordinate incrementY() {
        int y = (this.y + 1 + WITDH - Y_MIN) % WITDH + Y_MIN;
        return new Coordinate(x, y);
    }

    public Coordinate decrementX() {
        int x = (this.x - 1 + WITDH - X_MIN) % WITDH + X_MIN;
        return new Coordinate(x, y);
    }

    public Coordinate incrementX() {
        int x = (this.x + 1 + WITDH - X_MIN) % WITDH + X_MIN;
        return new Coordinate(x, y);
    }
}
