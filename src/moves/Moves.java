package moves;

/**
 * Moves class which increments moves made through one singleton moves object
 */
public class Moves {
    private static Moves moveMade = null;
    // static var to keep track of moves made
    private static int numMoves = 0;

    public Moves() {}

    /**
     * Instantiates a moves object if it doesn't exist, else returns the existing one
     * This instantiates the singleton moves object
     */
    public static synchronized Moves getInstance() {

        // create the singleton Moves object if it
        // isn't already created
        if(moveMade == null) {
            moveMade = new Moves();
        }
        // movesMade increments
        numMoves += 1;
        // return the singleton Moves object
        return moveMade;
    }

    /**
     * If ever board is cleared, counter is reset
     */
    public void reset() {
        numMoves = 0;
    }

    /**
     * Private method which returns a string saying obj. is initialized for sanity
     */
    private String createObj() {
        return "Moves object initialized";
    }

    /**
     * Returns static variable with number of moves made
     */
    public static int movesMade() {
        return numMoves;
    }
}