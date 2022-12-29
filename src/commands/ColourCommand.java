package commands;
import views.MazeView;

/**
 * Class ColourCommand
 * A Command to be applied to the maze
 */
public class ColourCommand implements MazeCommand {
    private final MazeView maze; // the receiver of the command

    /**
     * Constructor
     *
     * @param maze Maze to change colour in
     */
    public ColourCommand(MazeView maze) {
        this.maze = maze;
    }

    /**
     * execute the colour change command.
     */
    @Override
    public void execute() {
        this.maze.colourChange();
        System.out.println("Maze colour changed!");
    }

}
