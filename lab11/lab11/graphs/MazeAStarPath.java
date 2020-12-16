package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    int tX;
    int tY;
    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        tX = targetX;
        tY = targetY;
        }
    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return  Math.abs(maze.toX(v) - tX) + Math.abs(maze.toY(v) - tY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }
    private int findMinimumNeighbor(int v){
        int minHeuristic = 10000;

        int minNeighbor = 0;
        for(int w:maze.adj(v)){
            if(h(w)<minHeuristic&&!marked[w]){
                minNeighbor = w;
                minHeuristic = h(w);
            }
        }
        System.out.println(minNeighbor);
        return minNeighbor;
    }
    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        marked[s] = true;
        announce();
        if(targetFound){
            return;
        }
        if(s==t){
            targetFound=true;
        }
        else{
        int MinNeighbor = findMinimumNeighbor(s);
        edgeTo[MinNeighbor] = s;
        distTo[MinNeighbor] = distTo[s]+1;
        announce();
        astar(MinNeighbor);
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

