package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private final double[] xSum;
    private final int repeat;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        repeat = T;
        xSum = new double[T];
        if(N<=0||T<0){
            throw new IllegalArgumentException();}
        for(int i =0; i<T;i++) {
            Percolation test = pf.make(N);
            while (!test.percolates()) {
                int x = StdRandom.uniform(1,N+1);
                int y = StdRandom.uniform(1,N+1);
                test.open(x, y);
            }
            xSum[i] = (double) test.numberOfOpenSites()/(N*N);

        }
    }
    // perform T independent experiments on an N-by-N grid
    public double mean(){
        return StdStats.mean(xSum);
    }
    // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(xSum);
    }
    // sample standard deviation of percolation threshold
    public double confidenceLow(){
        return(mean()-1.6*stddev()/Math.sqrt(repeat));
    }                                 // low endpoint of 95% confidence interval
    public double confidenceHigh(){
        return(mean()+1.6*stddev()/Math.sqrt(repeat));
    }                                 // high endpoint of 95% confidence interval
    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20,30,pf);
        System.out.println(test.mean());
        System.out.println(test.stddev());
        System.out.println(test.confidenceLow());
        System.out.println(test.confidenceHigh());

    }
}
