package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;

public class Board implements WorldState{

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    int[][] Board;
    int size;
    int hamming=0;
    int manhattan=0;
    int estimateDistanceToGoal;
    int BLANK = 0;
    public Board(int[][] tiles){
        int temp = tiles.length;
        Board = new int[temp][temp];
        for(int i=0;i<temp;i++){
            System.arraycopy(tiles[i], 0, Board[i], 0, temp);
        }
        size = Board.length;
        hamming = hamming();
        manhattan = manhattan();
        estimateDistanceToGoal = manhattan;
    }
    public int tileAt(int i, int j){
        if(i>size-1||j>size-1||i<0||j<0){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return Board[i][j];
    }
    public int size(){
        return size;
    }
    public Iterable<WorldState> neighbors(){
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;


    }
    public int hamming(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(Board[i][j]!=i*size+j+1||Board[i][j]!=BLANK){
                    hamming+=1;
                }
            }
        }
        return hamming;
    }
    public int manhattan(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                int y = Board[i][j]%size;
                int x = Board[i][j]-y*size;
                manhattan+=Math.abs(y-j)+Math.abs(x-i);
                }
            }

        return manhattan;
    }

    public int estimatedDistanceToGoal(){
        return estimateDistanceToGoal;

    }
    public boolean equals(Object y){
        if(y==null||y.getClass()!=this.getClass()){
            return false;
        }
        else{
            Board temp = (Board) y;
            if(temp.size!=this.size){
                return false;
            }
            for(int i =0;i<size;i++){
                for(int j=0;j<size;j++){
                    if(Board[i][j]== temp.Board[i][j]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
