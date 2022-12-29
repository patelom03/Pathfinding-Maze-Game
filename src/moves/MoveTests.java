package moves;

/**
 * Sanity checks for moves singleton object
 */
class Test {
    public static void main(String[] args) {
        // instantiate 3 objects
        moves.Moves db1;
        moves.Moves db2;
        moves.Moves db3;

        // Instantiates 3 new moves objects
        db1= moves.Moves.getInstance();
        db2= moves.Moves.getInstance();
        db3= moves.Moves.getInstance();

        // prints moves made which should be 3 since we created 3 above
        System.out.println(moves.Moves.movesMade());
        // checks if all 3 objects are the same
        System.out.println(db1.equals(db2));
        System.out.println(db3.equals(db1));
        System.out.println(db2.equals(db3));
        // resets counter
        db1.reset();
        // should be 0
        System.out.println(moves.Moves.movesMade());
    }
}

