package lab11.graphs;


import edu.princeton.cs.algs4.Stack;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean CycleDetected = false;
    private int[] cameFrom;
    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        cameFrom = new int[maze.V()];
        Random Rand = new Random();
        int xCoord = 1+Rand.nextInt(maze.N());
        int yCoord = 1+Rand.nextInt(maze.N());
        System.out.println(xCoord);
        System.out.println(yCoord);
        int source = maze.xyTo1D(xCoord,yCoord);
        System.out.println(source);
        marked[source] = true;
        cameFrom[source]=source;
        dfs(source);
        announce();
    }

    private void dfs(int v){

        if(CycleDetected) {
            return;
        }
        for(int w: maze.adj(v)){
            if(!marked[w]) {
                marked[w] = true;
                cameFrom[w] =v;
                announce();
                dfs(w);
            }
            else if(cameFrom[w]!=v){
                cameFrom[w] = v;
                announce();
                CycleDetected=true;
            }
            int cur = v;
            edgeTo[cur] = cameFrom[cur];
            while(cur!=w){
                cur = cameFrom[cur];
                edgeTo[cur] = cameFrom[cur];
            }
            announce();
            return;
                }
            }
        }




