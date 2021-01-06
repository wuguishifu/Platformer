package com.bramerlabs.platformer.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LevelLoader {

    // the header of the levels to be loaded
    public static String levelHeader = "res/levels/Level";

    public static Level loadLevel(int index) {
        try {
            // create the scanner object scanning the specific level specified
            Scanner input = new Scanner(new File(levelHeader + index));

            // the level width and height
            int levelWidth = input.nextInt();
            int levelHeight = input.nextInt();

            // the player starting
            int playerX = input.nextInt();
            int playerY = input.nextInt();

            // initialize the list of platforms
            ArrayList<Platform> platforms = new ArrayList<>();

            // load the platforms
            while (input.hasNextLine()) {
                int x = input.nextInt();
                int y = input.nextInt();
                int width = input.nextInt();
                int height = input.nextInt();
                platforms.add(new Platform(x, y, width, height));
            }

            // create the new level
            return new Level(levelWidth, levelHeight, platforms, playerX, playerY);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}