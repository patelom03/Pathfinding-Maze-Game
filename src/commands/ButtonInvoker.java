package commands;

/**
 * Class ButtonInvoker manages MazeCommands
 */
public class ButtonInvoker {
    private MazeCommand command; // the command to execute

    /**
     * Store the command.
     *
     * @param command Command to accept
     */
    public void acceptCommand(MazeCommand command) {
        this.command = command;
        System.out.println("Command accepted!");
    }

    /**
     * Execute the command that is accepted, then clear the command.
     * If there is no command, notify client and return.
     */
    public void executeCommand() {
        if (this.command == null) {
            System.out.println("No command to execute!");
            return;
        }
        this.command.execute();
        this.command = null;
    }
}
