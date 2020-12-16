package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the plip class
 *  @authr FIXME
 */

public class TestClorus {
    public static final String MAGIC_WORD = "";

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(2, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(1.0);
        Clorus babyClorus = c.replicate();
        assertEquals(0.5,babyClorus.energy(),0.01);
        assertEquals(c.energy(),babyClorus.energy(),0.01);
        assertNotSame(c,babyClorus);
    }

    @Test
    public void testAttack(){
        Clorus c = new Clorus(2.0);
        double expected = 3.0;
        c.attack(new Plip(1.0));
        double actual = c.energy();
        assertEquals(expected,actual,0.01);

    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);
        surrounded.replace(Direction.TOP, new Plip(1));
        surrounded.replace(Direction.BOTTOM, new Empty());
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK,Direction.TOP);
        assertEquals(expected,actual);

        surrounded.replace(Direction.TOP, new Impassible());
        c = new Clorus(1);
        actual = c.chooseAction(surrounded);
        expected = new Action(Action.ActionType.REPLICATE,Direction.BOTTOM);
        assertEquals(expected,actual);
        c = new Clorus(0.7);
        actual = c.chooseAction(surrounded);
        expected = new Action((Action.ActionType.MOVE),Direction.BOTTOM);
        assertEquals(expected,actual);

    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
}


