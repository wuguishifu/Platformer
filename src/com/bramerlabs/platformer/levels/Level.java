package com.bramerlabs.platformer.levels;

import com.bramerlabs.platformer.main.Main;

import java.util.ArrayList;

public class Level {

    // the width and height of the level
    private int width, height;

    // a list of platforms in the level
    private ArrayList<Platform> platforms;

    /**
     * constructor - creates a level
     * @param width - the width of the level
     * @param height - the height of the level
     * @param platforms - the list of platforms in the level
     */
    public Level(int width, int height, ArrayList<Platform> platforms) {
        this.width = width;
        this.height = height;
        this.platforms = platforms;
    }

    /**
     * default constructor
     */
    public Level() {
        this.width = Main.width;
        this.height = Main.height;
        this.platforms = new ArrayList<>();
    }

    /**
     * adds a platform to the list of platforms in this level
     * @param p - the platform to be added
     */
    public void addPlatform(Platform p) {
        this.platforms.add(p);
    }

    /**
     * getter method
     * @return - returns the list of platforms that are in this level
     */
    public ArrayList<Platform> getPlatforms() {
        return this.platforms;
    }

    /**
     * gets a specific platform in this level
     * @param index - the index of the platform - typically going from left to right
     * @return - the platform specified by the index
     */
    public Platform getLevel(int index) {
        return this.platforms.get(index);
    }

    /**
     * getter method
     * @return - the width of this level
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * getter method
     * @return - the height of this level
     */
    public int getHeight() {
        return this.height;
    }

}