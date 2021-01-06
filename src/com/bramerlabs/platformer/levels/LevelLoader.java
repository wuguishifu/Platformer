package com.bramerlabs.platformer.levels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LevelLoader {

    public static String[] levelFiles = new String[]{"levels/Level1"};

    public static Level loadLevel(int level) {
        try {
            // create the scanner object scanning the specific level specified
            Scanner input = new Scanner(new File(levelFiles[level]));



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

}