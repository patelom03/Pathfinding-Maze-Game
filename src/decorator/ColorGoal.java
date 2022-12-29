package decorator;


import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * A wrapper for a grid object that modifies the state of the goal square on the board.
 * Once called, it will take in the current grid and the requested color, and modify the state accordingly.
 */
public class ColorGoal extends GridDecorator{
    Color goalColor;

    /**
     * Constructor for the goal square wrapper class.
     * @param currGrid The grid that needs to be modified.
     * @param color The color that the new goal square must be.
     */
    public ColorGoal(Grid currGrid, String color) {
        super(currGrid);
        if (Objects.equals(color, "Yellow"))
        {
            this.goalColor = Color.GOLD;
        } else if (Objects.equals(color, "Orange")) {
            this.goalColor = Color.ORANGE;
        }else {
            this.goalColor = Color.RED;
        }
        this.grid.modifyGoalColor(goalColor);
    }

    /**
     * Gets the color that the goal square in this grid object is represented as.
     * @return the current goal color
     */
    @Override
    public Color getGoalColor() {
        return this.goalColor;
    }
}
