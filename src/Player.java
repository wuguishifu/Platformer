import java.awt.*;
import java.util.ArrayList;

public class Player {

    // if the player is going in a direction
    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = false;

    // flags for altering values;
    public static final int FLAG_MOVE_UP = 2;
    public static final int FLAG_MOVE_DOWN = 4;
    public static final int FLAG_MOVE_LEFT = 8;
    public static final int FLAG_MOVE_RIGHT = 16;
    public static final int FLAG_JUMP = 32;

    // physics variables
    private static final int MAX_VELOCITY = 10;
    private static final int JUMP_VELOCITY = 15;
    private static final int GRAVITY = 1;
    private static final int COYOTE_FRAMES = 5;

    // player motion
    private int dx, dy;
    private int currentCoyoteFrame = 0;
    private boolean onGround = false;
    private boolean jump = false;

    // player position and size;
    private int x, y; // the position
    private static final int width = 20, height = 20; // the dimensions (currently represented by a rectangle)

    /**
     * main constructor
     * @param x - the x position
     * @param y - the y position
     */
    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * uses flags to set the player's motion
     * @param motionFlag - the flag
     */
    public void setPlayerMotion(int motionFlag, boolean value) {
        switch (motionFlag) {
            case FLAG_MOVE_LEFT:
                left = value;
                break;
            case FLAG_MOVE_RIGHT:
                right = value;
                break;
            case FLAG_JUMP:
                if (onGround) {
                    jump = value;
                }
                break;
        }
    }

    /**
     * updates the player's position
     * @param platforms - a list of platforms
     */
    public void update(ArrayList<Platform> platforms) {
        // check to see if the player is on the ground
        boolean temp = false;
        for (Platform p : platforms) {
            if (p.intersects(x, y + 1, x + width, y + height + 1)) {
                temp = true;
                break;
            }
        }
        if (!temp && currentCoyoteFrame > COYOTE_FRAMES) { // account for coyote time
            onGround = false;
            currentCoyoteFrame = 0;
        } else if (!temp) { // if the coyote time is not up but the player isn't on ground, add 1 frame to the counter
            currentCoyoteFrame++;
        }

        // if the player is on the ground, make their dy = 0. Otherwise, accelerate them
        if (!onGround) {
            dy = dy + GRAVITY;
        } else if (jump) { // if the player jumps, add -10 to dy
            dy -= JUMP_VELOCITY;
            onGround = false;
            jump = false;
        } else {
            dy = 0;
        }

        // motion in the x direction
        dx = 0;
        if (left && !right) { // if the player is going only left make their dx negative
            dx = -MAX_VELOCITY;
        } else if (right && !left) { // if the player is going only right make their dx positive
            dx = MAX_VELOCITY;
        } // otherwise their dx is defaulted to 0

        // assign temp variables describing the players position in the next frame
        int newX = x + dx;
        int newY = y + dy;

        // attempt to move the player and check collision in the x direction against every platform
        for (Platform p : platforms) {
            // if the x value collides with any platform don't move the player in the x direction
            if (p.intersects(newX, y, newX + width, y + height)) {
                newX = x;
            }
        }

        // attempt to move the player and check collision in the y direction if and only if the player isn't on the ground
        if (!onGround) {
            for (Platform p : platforms) {
                // if the y value collides, don't move the player in the y value
                // furthermore, if the player collides then set the y value to the platform height so it looks smooth
                if (p.intersects(x, newY, x + width, newY + height)) {
                    dy = 0;
                    onGround = true;
                    newY = p.getY1() - height;
                }
            }
        }

        // move the player to the next position
        x = newX;
        y = newY;
    }

    /**
     * paints the player
     * @param g - the graphics object created by the javax.swing
     */
    public void paint(Graphics g) {
        g.setColor(Main.c2);
        g.fillRect(x, y, width, height);
    }

}
