package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Solver {
    int moveSize;
    ArrayList<WorldState> moveSoFar = new ArrayList<>();
    public Solver(WorldState initial) {
        MinPQ<SearchNode> BMS = new MinPQ<>();
        BMS.insert(new SearchNode(initial, 0, null));
        SearchNode goal;
        while(!BMS.isEmpty()){
            SearchNode cur = BMS.delMin();
            SearchNode prev = cur.prev();
            moveSoFar.add(cur.world());
            if(cur.world.isGoal()){
                goal = cur;
                break;
            }
            Iterable<WorldState> neighbors = cur.world().neighbors();
            moveSize= moveSoFar.size();
            for(WorldState x:neighbors) {
                if (prev == null || (!x.equals(prev.world()))) {
                BMS.insert(new SearchNode(x, moveSize, prev));
                }
            }
        }

    }
        public Iterable<WorldState> solution(){
            return moveSoFar;
        }
        public int moves(){
            return moveSize-1;
        }


    private class SearchNode implements Comparable<SearchNode> {
        private WorldState world;
        private int moves=0;
        private SearchNode prev;
        private int priority;
        private SearchNode(WorldState x,int m, SearchNode p){
            world = x;
            moves = m;
            prev = p;
            priority = moves+x.estimatedDistanceToGoal();
        }
        public WorldState world(){
            return world;
        }
        public int moves(){
            return moves;
        }
        public SearchNode prev(){
            return prev;
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority-o.priority;
        }
    }

}
