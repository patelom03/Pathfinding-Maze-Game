package decorator;

import javafx.scene.paint.Color;
/**
 * GridDecorator class that acts as a template for all grid decorators.
 */
public abstract class GridDecorator implements Grid{

    protected Grid grid;

    public GridDecorator(Grid currGrid)
    {
        this.grid = currGrid;
    }

    /**
     * Get the color of the trail following the moving character
     */
    @Override
    public Color getTrailColor() {
        return this.grid.getTrailColor();
    }

    /**
     * Get the color of the character that is being controlled
     */
    @Override
    public Color getCharColor() {
        return this.grid.getCharColor();
    }

    /**
     * Get the color of the goal at the end of the game
     */
    @Override
    public Color getGoalColor() {
        return this.grid.getGoalColor();
    }

    /**
     * Modify the trail color on the grid
     */
    public void modifyTrailColor(Color c)
    {
        this.grid.modifyTrailColor(c);
    }

    /**
     * Modify the character color on the grid
     */
    public void modifyCharColor(Color c){
        this.grid.modifyCharColor(c);
    }

    /**
     * Modify the goal square color on the grid
     */
    public void modifyGoalColor(Color c){
        this.grid.modifyGoalColor(c);
    }
}
