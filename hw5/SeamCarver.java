import java.awt.Color;
import java.nio.channels.SeekableByteChannel;

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdRandom;
public class SeamCarver {
    private  Picture image;
    private final int width;
    private final int height;
    public SeamCarver(Picture picture) {
        image = picture;
        width = picture.width();
        height = picture.height();

    }
    public Picture picture() {
        return image;
    }
    public     int width(){
        return width;
    }; // width of current picture
    public     int height()  {
        return height;
    }
    public  double energy(int x, int y){
       Color left = image.get(((x-1)+width)%width,y);
       Color right = image.get(((x+1)+width)%width,y);
       Color up = image.get(x,((y-1)+height)%height);
       Color down = image.get(x,((y+1)+height)%height);
       int HorizonRed = left.getRed()-right.getRed();
       int HorizonBlue = left.getBlue()-right.getBlue();
       int HorizonGreen = left.getGreen()-right.getGreen();
       int verticalRed = up.getRed()-down.getRed();
       int verticalBlue = up.getBlue()-down.getBlue();
       int verticalGreen = up.getGreen()-down.getGreen();
        return (double) HorizonBlue*HorizonBlue+HorizonRed*HorizonRed+HorizonGreen*HorizonGreen+
                verticalBlue*verticalBlue+verticalGreen*verticalGreen+verticalRed*verticalRed;
    }
    private Picture transpose() {
        Picture transpose= new Picture(image.height(),image.width());
        for(int i =0;i<width();i++){
            for(int j =0;j<height();j++){
                transpose.set(j,i,image.get(i,j));
            }
        }
        return transpose;
    }
    public   int[] findHorizontalSeam() {
        Picture transposed = transpose();
        SeamCarver transpose = new SeamCarver(transposed);
        return transpose.findVerticalSeam();
    }// sequence of indices for horizontal seam
    public   int[] findVerticalSeam() {
        int[] VerticalSeam = new int[height];
        int Min = Integer.MAX_VALUE;
        int prevX = 0;
        for(int i=0;i<width;i++) {
            int energy = (int)energy(i, 0);
            if(Min>energy){
                Min = energy;
                prevX = i;
            }
        }
        VerticalSeam[0]=prevX;
        for(int i=1;i<height;i++) {
            VerticalSeam[i] = findMinIndex(VerticalSeam[i-1],i);
        }
        return VerticalSeam;
    }// sequence of indices for vertical seam
    private int findMinIndex(int prevX,int prevY){
        int bottom_left = (int) energy(((prevX - 1) + width) % width, prevY);
        int bottom_center = (int) energy(prevX, prevY);
        int bottom_right = (int) energy(((prevX + 1) + width) % width, prevY);
        int Min = Math.min(bottom_right, Math.min(bottom_center, bottom_left));
        if(Min==bottom_center){
            return prevX;
        }
        else if(Min == bottom_right){
            return ((prevX + 1) + width)%width;
        }
        else return((prevX - 1) + width)%width;
    }
    public    void removeHorizontalSeam(int[] seam) {
        image = SeamRemover.removeHorizontalSeam(image, findHorizontalSeam());
    }
    public    void removeVerticalSeam(int[] seam) {
        image = SeamRemover.removeHorizontalSeam(image, findVerticalSeam());
        }// remove vertical seam from picture
    }

