package com.bramerlabs.platformer.objects;

public class Hitbox {

    // collision variables
    public static final int NO_COLLISION = -1;
    public static final int NORTH_SOUTH_COLLISION = 0;
    public static final int EAST_WEST_COLLISION = 1;
    public static final int ALREADY_COLLIDED = 2;

    // flags for collision checking
    public static final int IGNORE_X_DIRECTION = 2;
    public static final int IGNORE_Y_DIRECTION = 4;
    public static final int IGNORE_X_POSITIVE_DIRECTION = 8;
    public static final int IGNORE_X_NEGATIVE_DIRECTION = 16;
    public static final int IGNORE_Y_POSITIVE_DIRECTION = 32;
    public static final int IGNORE_Y_NEGATIVE_DIRECTION = 64;
    public static final int IGNORE_FLAGS = -1;

    // min and max x and y points of the platform
    private int x1, x2, y1, y2;

    // width and height of the hitbox
    private int width, height;

    /**
     * main constructor
     * @param x1 - min x value
     * @param y1 - min y value
     * @param width - the width of the hitbox
     * @param height - the height of the hitbox
     */
    public Hitbox(int x1, int y1, int width, int height) {
        this.x1 = x1;
        this.y1 = y1;
        this.width = width;
        this.height = height;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
    }

    /**
     * creates a hitbox from the min and max x and y value
     * @param x1 - the min x value
     * @param y1 - the min y value
     * @param x2 - the max x value
     * @param y2 - the max y value
     * @return - a new hitbox from these defined values
     */
    public static Hitbox hitboxFromMaxima(int x1, int y1, int x2, int y2) {
        return new Hitbox(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * determines if a rectangular bounding box defined by a min and max x and y position intersects with this hitbox
     * @param xMin - the min x value
     * @param yMin - the min y value
     * @param xMax - the max x value
     * @param yMax - the max y value
     * @return - true if the bounding box intersects this hitbox
     */
    public boolean intersects(int xMin, int yMin, int xMax, int yMax) {
        return (xMax > x1 && xMin < x2) && (yMax > y1 && yMin < y2);
    }

    /**
     * determines if a rectangular bounding box defined by a min and max x and y position intersects with this hitbox
     * @param boundingBox - an array of ints containing the min and max x and y position in the form [x1, y1, x2, y2]
     * @return - true if the bounding box intersects this hitbox
     */
    public boolean intersects(int[] boundingBox) {
        if (boundingBox.length < 4) {
            System.err.println("Error: Not enough bounding box points to check collision.");
            return false;
        }
        return intersects(boundingBox[0], boundingBox[1], boundingBox[2], boundingBox[3]);
    }

    /**
     * determines if two hitboxes intersect
     * @param other - the other hitbox
     * @return - true if the hitboxes collide
     */
    public boolean intersects(Hitbox other) {
        return intersects(other.getXMin(), other.getYMin(), other.getXMax(), other.getYMax());
    }

    /**
     * determines if a rectangular bounding box is going to collide at the next position and if so, the direction
     * @param c - the current rectangular bounding box
     * @param n - the next rectangular bounding box
     * @return - a static variable representing which direction collision occurs in, if it occurs
     */
    public int intersects(int[] c, int[] n) {
        if (intersects(c)) {
            return ALREADY_COLLIDED;
        }
        if (intersects(c[0], n[1], c[2], n[3])) {
            return NORTH_SOUTH_COLLISION;
        }
        if (intersects(n[0], c[1], n[2], c[3])) {
            return EAST_WEST_COLLISION;
        }
        return NO_COLLISION;
    }

    /**
     * moves the hitbox by a delta x and y
     * @param dx - the change in x position
     * @param dy - the change in y position
     */
    public void moveTo(int dx, int dy) {
        this.x1 += dx;
        this.x2 += dx;
        this.y1 += dy;
        this.y2 += dy;
    }

    /**
     * scales the hitbox by moving the bottom right corner
     * @param mx - the scale factor for the width
     * @param my - the scale factor for the height
     */
    public void scale(int mx, int my) {
        this.width *= mx;
        this.height *= my;
        this.x2 = x1 + width;
        this.y2 = y1 + height;
    }

    /**
     * getter method
     * @return - the width of this hitbox
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter method
     * @return - the height of this hitbox
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * getter method
     * @return - the rectangular bounding box in the form [xMin, yMin, xMax, yMax]
     */
    public int[] getBoundingBox() {
        return new int[]{x1, y1, x2, y2};
    }

    /**
     * getter method
     * @return - the min x value
     */
    public int getXMin() {
        return this.x1;
    }

    /**
     * getter methid
     * @return - the max x value
     */
    public int getXMax() {
        return this.x2;
    }

    /**
     * getter method
     * @return - the min y value
     */
    public int getYMin() {
        return this.y1;
    }

    /**
     * getter method
     * @return - the max y value
     */
    public int getYMax() {
        return this.y2;
    }
}
