package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private int target;
    private boolean targetFound = false;
    private Maze maze;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        source = m.xyTo1D(sourceX,sourceY);
        target = m.xyTo1D(targetX,targetY);
        distTo[source] = 0;
        edgeTo[source] = source;


        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue = new Queue<>();
        marked[source] = true;
        announce();
        queue.enqueue(source);
        if(source==target){
            targetFound=true;
        }
        if(targetFound){
            return;
        }
        while(!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w]=v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    if(w==target){
                        targetFound=true;
                        break;
                    }
                    queue.enqueue(w);
                }
            }
        }
    }
    @Override
    public void solve() {
        bfs();
    }
}

