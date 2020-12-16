package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Percolation {
    private boolean[][] world;
    private WeightedQuickUnionUF union;
    private int numberOfOccupiedSites = 0;
    int size;
    boolean percolation = false;
    int virtualTop;
    int virtualBot;

    public Percolation(int N) {
        world = new boolean[N * N][2];
        union = new WeightedQuickUnionUF(N * N+2);
        size = N;
        virtualTop = N*N;
        virtualBot = N*N+1;
        for (int i = 0; i < N * N; i++) {
            world[i][0] = false;
            world[i][1] = false;
        }

    }

    private int xyTo1D(int x, int y) {
        return ((x - 1) * size + y - 1);
    }// create N-by-N grid, with all sites initially blocked

    public void open(int row, int col) {
        if(row==1){
            world[xyTo1D(row,col)][1]=true;
            union.union(xyTo1D(row,col),virtualTop);
        }
        if(row == size){
            union.union(xyTo1D(row,col),virtualBot);
        }
        if (!world[xyTo1D(row, col)][0]) {
            world[xyTo1D(row, col)][0] = true;
            numberOfOccupiedSites += 1;
        }
        if (row < size && isOpen(row + 1, col)) {
            union.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (row > 1 && isOpen(row - 1, col)) {
            union.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (col > 1 && isOpen(row, col - 1)) {
            union.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col < size && isOpen(row, col + 1)) {
            union.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        if(union.connected(xyTo1D(row,col),virtualBot)||union.connected(xyTo1D(row,col),virtualTop)){
            world[xyTo1D(row,col)][1]=true;
        }

    }// open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return world[xyTo1D(row, col)][0]; } // is the site (row, col) open?

    public boolean isFull(int row, int col) {
        if(union.connected(xyTo1D(row,col),virtualBot)||union.connected(xyTo1D(row,col),virtualTop)){
            world[xyTo1D(row,col)][1]=true;
        }
        return world[xyTo1D(row, col)][1];
    }
    // is the site (row, col) full?

    public int numberOfOpenSites() { return size*size-numberOfOccupiedSites; }         // number of open sites

    public boolean percolates(){
    return union.connected(virtualTop,virtualBot);
    }

    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(3, 4);
        test.open(1, 2);
        test.open(2, 2);
        test.open(3, 2);
        System.out.println(test.isFull(1, 2));
        System.out.println(test.isOpen(3, 2));
        System.out.println(test.isFull(3, 4));
        test.open(3, 3);
        boolean YN = test.isFull(3, 4);
        System.out.println(test.isFull(3, 4));
        test.open(4, 4);
        test.open(5, 4);
        System.out.println(test.percolates());
        for (int i = 0; i < 25; i++) {
            System.out.print(" ");
            if (i>=4 && (i+1) % 5 == 0) {
                System.out.println(test.world[i][1]);
            } else {
                System.out.print(test.world[i][1]);
            }
        }
    }
}// use for unit testing (not required)
    

