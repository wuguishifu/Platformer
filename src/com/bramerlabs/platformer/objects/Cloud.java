package com.bramerlabs.platformer.objects;

import com.bramerlabs.platformer.main.Main;

import java.awt.*;

public class Cloud {

    // position of this cloud
    private int x, y;

    // height of this cloud
    private int width, height;

    // the color of the cloud
    private static final Color color = Main.c3;

    /**
     * constructor - creates a new cloud
     * @param x - the x position of the cloud
     * @param y - the y position of the cloud
     * @param width - the width of the cloud
     * @param height - the height of the cloud
     */
    public Cloud(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * default constructor - creates a 20x20 cloud at 0, 0
     */
    public Cloud() {
        this.x = 0;
        this.y = 0;
        this.width = 20;
        this.height = 20;
    }

    /**
     * moves the cloud to a new specified location
     * @param x - the new x position
     * @param y - the new y position
     */
    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * stretches a cloud to a new specified size
     * @param width - the new width
     * @param height - the new height
     */
    public void stretch(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * getter method
     * @return - the x position of this cloud
     */
    public int getX() {
        return this.x;
    }

    /**
     * getter method
     * @return - the y position of this cloud
     */
    public int getY() {
        return this.y;
    }

    /**
     * getter method
     * @return - the width of this cloud
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter method
     * @return - the height of this cloud
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * paints the cloud onto the window
     * @param g - the graphics object created by javax.swing
     */
    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}