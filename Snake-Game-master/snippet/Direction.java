package snippet;

public enum Direction {
    RIGHT, LEFT, UP, DOWN, NONE;

   
    public boolean isInverse(Direction direction) {
        switch (this) {
            case UP:
                return direction == DOWN;
            case DOWN:
                return direction == UP;
            case LEFT:
                return direction == RIGHT;
            case RIGHT:
                return direction == LEFT;
            default:
                return false;
        }
    }

}