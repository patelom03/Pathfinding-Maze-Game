package decorator;

import javafx.scene.paint.Color;
import models.MazeBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecoratorTests {

    @Test
    void testCharDecorator()
    {
        MazeBoard m = new MazeBoard(1,1);
        assertSame(m.getCharColor(), Color.BLUE);
        new ColorCharacter(m, "Red");
        assertSame(m.getCharColor(), Color.DARKRED);
        new ColorCharacter(m, "Blue");
        assertSame(m.getCharColor(), Color.BLUE);
        new ColorCharacter(m, "");
        assertSame(m.getCharColor(), Color.DARKGREEN);
    }
    @Test
    void testTrailDecorator()
    {
        MazeBoard m = new MazeBoard(1,1);
        assertSame(m.getTrailColor(), Color.LIGHTGREEN);
        new ColorTrail(m, "Red");
        assertSame(m.getTrailColor(), Color.INDIANRED);
        new ColorTrail(m, "Blue");
        assertSame(m.getTrailColor(), Color.LIGHTBLUE);
        new ColorTrail(m, "");
        assertSame(m.getTrailColor(), Color.LIGHTGREEN);
    }
    @Test
    void testGoalDecorator()
    {
        MazeBoard m = new MazeBoard(1,1);
        assertSame(m.getGoalColor(), Color.RED);
        new ColorGoal(m, "Yellow");
        assertSame(m.getGoalColor(), Color.GOLD);
        new ColorGoal(m, "Orange");
        assertSame(m.getGoalColor(), Color.ORANGE);
        new ColorGoal(m, "");
        assertSame(m.getGoalColor(), Color.RED);
    }
}
