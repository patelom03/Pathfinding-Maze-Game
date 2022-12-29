package commands;
import views.MazeView;

/**
 * Class ResetCommand
 * A Command to be applied to the maze
 */
public class ResetCommand implements MazeCommand {
    private final MazeView maze; // the receiver of the command

    /**
     * Constructor
     *
     * @param maze Maze to reset
     */
    public ResetCommand(MazeView maze) {
        this.maze = maze;
    }

    /**
     * execute the reset command.
     */
    @Override
    public void execute() {
        this.maze.reset();
        System.out.println("Maze reset!");
    }
}
