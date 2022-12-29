package decorator;

import java.util.Objects;
import javafx.scene.paint.Color;


/**
 * A wrapper for a grid object that modifies the state of the trail that follows the character on the board.
 * Once called, it will take in the current grid and the requested color, and modify the state accordingly.
 */
public class ColorTrail extends GridDecorator{
    Color trailColor;

    /**
     * Constructor for the trail wrapper class.
     * @param currGrid The grid that needs to be modified.
     * @param color The color that the new trail must be.
     */
    public ColorTrail(Grid currGrid, String color) {
        super(currGrid);
        if (Objects.equals(color, "Red"))
        {
            this.trailColor = Color.INDIANRED;
        } else if (Objects.equals(color, "Blue")) {
            this.trailColor = Color.LIGHTBLUE;
        }else {
            this.trailColor = Color.LIGHTGREEN;
        }
        this.grid.modifyTrailColor(trailColor);
    }

    /**
     * Gets the color that the trail in this grid object is represented as.
     * @return the current trail color
     */
    @Override
    public Color getTrailColor() {
        return this.trailColor;
    }
}
