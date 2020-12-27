public class Platform {

    // final variables
    public static final int NO_COLLISION = -1;
    public static final int NORTH_SOUTH = 0;
    public static final int EAST_WEST = 1;
    public static final int ALREADY_COLLIDED = 2;

    // flags for collision checking
    public static final int IGNORE_X_DIRECTION = 1000;
    public static final int IGNORE_Y_DIRECTION = 1001;
    public static final int IGNORE_X_POSITIVE_DIRECTION = 1002;
    public static final int IGNORE_X_NEGATIVE_DIRECTION = 1003;
    public static final int IGNORE_Y_POSITIVE_DIRECTION = 1004;
    public static final int IGNORE_Y_NEGATIVE_DIRECTION = 1005;
    public static final int FLAG_IGNORE = 1006;

    // min and max x and y points of the platform
    private int x1, y1, x2, y2;

    // the width and height of the platform
    private int width, height;

    /**
     * main constructor
     * @param x1 - int x min
     * @param y1 - int y min
     * @param width - int width
     * @param height - int height
     */
    public Platform(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
        this.width = width;
        this.height = height;
    }

    /**
     * checks to see if a rectangular bounding box collides with this platform
     * @param xMin - the min x value
     * @param yMin - the min y value
     * @param xMax - the max x value
     * @param yMax - the max y value
     * @return - true if the bounding box will collide
     */
    public boolean intersects(int xMin, int yMin, int xMax, int yMax) {
        return (xMax >= x1 && xMin <= x2) && (yMax >= y1 && yMin <= y2);
    }

    /**
     * checks to see if a rectangular bounding bo collides with this platform
     * @param xMin - the min x value
     * @param yMin - the min y value
     * @param xMax - the max x value
     * @param yMax - the max y value
     * @param flags - optional flags
     * @return - true if the bounding box will collide
     */
    public boolean intersects(int xMin, int yMin, int xMax, int yMax, int flags) {
        if (flags == IGNORE_X_DIRECTION) {
            return yMax > y1 && yMin < y2;
        }
        if (flags == IGNORE_Y_DIRECTION) {
            return xMax > x1 && xMin < x2;
        }
        if (flags == FLAG_IGNORE) {
            return intersects(xMin, yMin, xMax, yMax);
        }
        return false;
    }

    /**
     * checks to see if a rectangular bounding box collides with this platform
     * @param boundingBox - the rectangular coordinates of the bounding box to be checked in the form [xMin, yMin, xMax, yMax]
     * @return - true if the bounding box collides
     */
    public boolean intersects(int[] boundingBox) {
        if (boundingBox.length < 4) {
            System.err.println("Error: Not enough bounding box points to check collision.");
            return false;
        }
        return intersects(boundingBox[0], boundingBox[1], boundingBox[2], boundingBox[3]);
    }

    /**
     * checks to see if a rectangular bounding box is going to collide and if so, which direction of motion
     * @param currentPoints - the current rectangular bounding box
     * @param nextPoints - the next rectangular bounding box
     * @return - a static variable representing which direction collision occurs in
     */
    public int directionalCollisionCheck(int[] currentPoints, int[] nextPoints) {
        if (intersects(currentPoints)) {
            return ALREADY_COLLIDED;
        }
        if (intersects(currentPoints[0], nextPoints[1], currentPoints[2], nextPoints[3])) {
            return NORTH_SOUTH;
        }
        if (intersects(nextPoints[0], currentPoints[1], nextPoints[2], currentPoints[3])) {
            return EAST_WEST;
        }
        return NO_COLLISION;
    }

    /**
     * getter method
     * @return - the width of this rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter method
     * @return - the height of this rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter method
     * @return - the bounding rectangle in the form [xMin, yMin, xMax, yMax]
     */
    public int[] getBoundingRectangle() {
        return new int[]{x1, y1, x2, y2};
    }

    /**
     * getter method
     * @return - the min x value
     */
    public int getX1() {
        return x1;
    }

    /**
     * getter method
     * @return - the min y value
     */
    public int getY1() {
        return y1;
    }

    /**
     * getter method
     * @return - the max x value
     */
    public int getX2() {
        return x2;
    }

    /**
     * getter method
     * @return - the max y value
     */
    public int getY2() {
        return y2;
    }
}