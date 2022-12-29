package models;

import decorator.Grid;
import javafx.scene.paint.Color;

import java.io.Serializable;

public class MazeBoard implements Serializable, Grid {

    private int width; //board height and width
    private int height;

    //Color of the trail
    private Color trailColor;

    //Color of the character
    private Color charColor;

    //Color of the goal
    private Color goalColor;


    protected int[][] mazeGrid; //board grid

    protected int[][] rightPath; //board grid

    public MazeBoard(int width, int height) {
        this.height = height;
        this.width = width;

        //Default board colors
        this.trailColor = Color.LIGHTGREEN;
        this.charColor = Color.BLUE;
        this.goalColor = Color.RED;

        this.mazeGrid = new int[width][height];
        this.rightPath = new int[width][height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                mazeGrid[i][j] = 0;
                rightPath[i][j] = 0;
            }
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int[][] getMazeGrid() {
        return mazeGrid;
    }

    public int[][] getRightPath() {
        return rightPath;
    }

    @Override
    public Color getTrailColor() {
        return this.trailColor;
    }

    @Override
    public Color getCharColor() {
        return this.charColor;
    }

    @Override
    public Color getGoalColor() {
        return this.goalColor;
    }

    @Override
    public void modifyTrailColor(Color c) {
        this.trailColor = c;
    }

    @Override
    public void modifyCharColor(Color c) {
        this.charColor = c;
    }

    @Override
    public void modifyGoalColor(Color c) {
        this.goalColor = c;
    }

    public void setPath() {
        int i = 0;
        int j = 0;
        while (i < this.width && j < this.height) {
            this.mazeGrid[i][j] = 1;
            this.rightPath[i][j] = 1;
            if (Math.round(Math.random()) == 0) {
                i += 1;
            } else {
                j += 1;
            }
        }
        if (i >= this.width) {
            while (j < this.height) {
                this.mazeGrid[this.width - 1][j] = 1;
                this.rightPath[this.width - 1][j] = 1;
                j++;
            }
        } else {
            while (i < this.width) {
                this.mazeGrid[i][this.height - 1] = 1;
                this.rightPath[i][this.height - 1] = 1;
                i++;
            }
        }
    }

    public void makeWalls() {
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (mazeGrid[i][j] != 1) {
                    if (Math.round(Math.random()) == 0) {
                        this.mazeGrid[i][j] = 2;
                    }
                }
            }
        }
        this.mazeGrid[0][0] = 4;
    }



}
