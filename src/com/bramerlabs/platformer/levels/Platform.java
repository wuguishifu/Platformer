package com.bramerlabs.platformer.levels;

import com.bramerlabs.platformer.main.Main;
import com.bramerlabs.platformer.objects.Hitbox;

import java.awt.*;

public class Platform {

    // the x and y position of the top left corner of the platform
    private int x, y;

    // the width and height of the platform
    private int width, height;

    // the hitbox of this platform
    private Hitbox hitbox;

    /**
     * main constructor for specified location and size
     * @param x - the x position of the top left corner
     * @param y - the y position of the top left corner
     * @param width - the width of the platform
     * @param height - the height of the platform
     */
    public Platform(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        // construct a hitbox for this platform
        this.hitbox = new Hitbox(x, y, width, height);
    }

    /**
     * creates a platform from the min and max x and y values
     * @param x1 - the min x value
     * @param y1 - the min y value
     * @param x2 - the max x value
     * @param y2 - the max y value
     * @return - a new hitbox from these defined values
     */
    public static Platform platformFromMaxima(int x1, int y1, int x2, int y2) {
        return new Platform(x1, y1, x2 - x1, y2 - y1);
    }

    /**
     * updates the platform
     */
    public void update() {
        // null method for now
    }

    /**
     * moves the platform to a specified location
     * @param x1 - the new x value
     * @param y1 - the new y value
     */
    public void moveTo(int x1, int y1) {
        this.hitbox.moveTo(x1 - x, y1 - y);
        this.x = x1;
        this.y = y1;
    }

    /**
     * scales the platform by moving the bottom right corner
     * @param mx - the scale factor for the width
     * @param my - the scale factor for the height
     */
    public void scale(int mx, int my) {
        this.hitbox.scale(mx, my);
        this.width *= mx;
        this.height *= my;
    }

    /**
     * getter method
     * @return - the hitbox
     */
    public Hitbox getHitbox() {
        return this.hitbox;
    }

    /**
     * paints the platform
     * @param g - the graphics object created by javax.swing
     */
    public void paint(Graphics g) {
        g.setColor(Main.c4);
        g.fillRect(x, y, width, height);
    }

    /**
     * getter method
     * @return - the x position of the top right corner of this platform
     */
    public int getX() {
        return this.x;
    }

    /**
     * getter method
     * @return - the y position of the top left corner of this platform
     */
    public int getY() {
        return this.y;
    }

    /**
     * getter method
     * @return - the width of this platform
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter method
     * @return - the height of this platform
     */
    public int getHeight() {
        return this.height;
    }

}