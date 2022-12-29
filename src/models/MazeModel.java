package models;

import decorator.Grid;
import moves.Moves;

import java.io.Serializable;

/**
 * class MazeModel to simulate the game.
 * (Borrowed idea from Tetris Assignment)
 */
public class MazeModel implements Serializable {
    public static final int WIDTH = 25; //size of the board in blocks
    public static final int HEIGHT = 25; //height of the board in blocks

    protected MazeBoard board;  // Board data structure

    protected int currentX;
    protected int currentY;

    public enum MoveType {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public MazeModel()
    {
        this.board = new MazeBoard(WIDTH, HEIGHT);
        this.currentX = 0;
        this.currentY = 0;
    }

    /**
     * Overloaded constructor for model.
     * Creates a new maze board with a specified size, rather than utilizing default sizes.
     */
    public MazeModel(int width, int height) {
        this.board = new MazeBoard(width, height);
        this.currentX = 0;
        this.currentY = 0;
    }

    public MazeBoard getBoard()
    {
        return this.board;
    }

    /**
     * Move the current position on the board according to player movement.
     * @param move from the user
     */
    public void moveCharacter(MoveType move) {
        //MUST COMPLETE TO MOVE CHARACTER ON MAZE
        if (move == MoveType.RIGHT) {
            if (this.currentX < this.board.getWidth() - 1) {
                if (this.board.mazeGrid[this.currentX + 1][this.currentY] != 2) {
                    moves.Moves a;
                    a = moves.Moves.getInstance();
                    this.board.mazeGrid[this.currentX][this.currentY] = 3;
                    this.currentX++;
                    this.board.mazeGrid[this.currentX][this.currentY] = 4;
                }
            }
        } else if (move == MoveType.LEFT) {
            if (this.currentX > 0) {
                if (this.board.mazeGrid[this.currentX - 1][this.currentY] != 2) {
                    moves.Moves a;
                    a = moves.Moves.getInstance();
                    this.board.mazeGrid[this.currentX][this.currentY] = 3;
                    this.currentX--;
                    this.board.mazeGrid[this.currentX][this.currentY] = 4;
                }
            }
        } else if (move == MoveType.DOWN) {
            if (this.currentY < this.board.getHeight() - 1) {
                if (this.board.mazeGrid[this.currentX][this.currentY + 1] != 2) {
                    moves.Moves a;
                    a = moves.Moves.getInstance();
                    this.board.mazeGrid[this.currentX][this.currentY] = 3;
                    this.currentY++;
                    this.board.mazeGrid[this.currentX][this.currentY] = 4;
                }
            }
        } else if (move == MoveType.UP) {
            if (this.currentY > 0) {
                if (this.board.mazeGrid[this.currentX][this.currentY - 1] != 2) {
                    moves.Moves a;
                    a = moves.Moves.getInstance();
                    this.board.mazeGrid[this.currentX][this.currentY] = 3;
                    this.currentY--;
                    this.board.mazeGrid[this.currentX][this.currentY] = 4;
                }
            }
        }
    }

    public double getHeight()
    {
        return HEIGHT;
    }

    public double getWidth()
    {
        return WIDTH;
    }
}
