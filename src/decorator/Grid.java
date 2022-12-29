package decorator;

import java.awt.*;
import javafx.scene.paint.Color;
/**
 * Grid interface
 */
public interface Grid {

    /**
     * Get the color of the trail following the moving character
     */
    public Color getTrailColor();

    /**
     * Get the color of the character that is being controlled
     */
    public Color getCharColor();

    /**
     * Get the color of the goal square at the end of the game
     */
    public Color getGoalColor();

    /**
     * Modify the trail color on the grid
     */
    public void modifyTrailColor(Color c);

    /**
     * Modify the character color on the grid
     */
    public void modifyCharColor(Color c);

    /**
     * Modify the goal square color on the grid
     */
    public void modifyGoalColor(Color c);

}
