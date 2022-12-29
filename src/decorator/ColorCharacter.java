package decorator;

import javafx.scene.paint.Color;

import java.util.Objects;

/**
 * A wrapper for a grid object that modifies the state of the character on the board.
 * Once called, it will take in the current grid and the requested color, and modify the state accordingly.
 */
public class ColorCharacter extends GridDecorator{
    Color charColor;

    /**
     * Constructor for the character wrapper class.
     * @param currGrid The grid that needs to be modified.
     * @param color The color that the new character must be.
     */
    public ColorCharacter(Grid currGrid, String color) {
        super(currGrid);
        if (Objects.equals(color, "Red"))
        {
            this.charColor = Color.DARKRED;
        } else if (Objects.equals(color, "Blue")) {
            this.charColor = Color.BLUE;
        }else {
            this.charColor = Color.DARKGREEN;
        }
        this.grid.modifyCharColor(charColor);
    }

    /**
     * Gets the color that the character in this grid object is represented as.
     * @return the current character color
     */

    @Override
    public Color getCharColor() {
        return this.charColor;
    }
}
