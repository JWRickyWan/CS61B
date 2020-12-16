
package creatures;
import edu.princeton.cs.algs4.Picture;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;
import java.util.Random;


public class Clorus extends Creature{

        /** red color. */
        private int r;
        /** green color. */
        private int g;
        /** blue color. */
        private int b;

        /** creates plip with energy equal to E. */
        public Clorus(double e) {
            super("clorus");
            r = 34;
            g = 0;
            b = 231;
            energy = e;
        }

    @Override
    public void move() {
        energy-=0.03;
    }

    @Override
    public void attack(Creature c) {
        energy=energy+c.energy();
    }

    @Override
    public Clorus replicate() {
        Clorus babyClorus = new Clorus(energy/2);
        energy = energy/2;
        return babyClorus;
    }

    @Override
    public void stay() {
        energy-=0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        Random rand = new Random();
        if(empties.size()==0){
            return new Action(Action.ActionType.STAY);
        }
        else if(plips.size()>0){
            int plipIndex = rand.nextInt(plips.size());
            return new Action(Action.ActionType.ATTACK,plips.get(plipIndex));
        }
        else if(energy>=1){
            int emptyIndex = rand.nextInt(empties.size());
            return new Action(Action.ActionType.REPLICATE,empties.get(emptyIndex));
        }
        int emptyIndex = rand.nextInt(empties.size());
        return new Action(Action.ActionType.MOVE,empties.get(emptyIndex));


    }

    @Override
    public Color color() {
        return color(r,g,b);
    }
}
