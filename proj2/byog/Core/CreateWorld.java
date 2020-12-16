package byog.Core;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.knowm.xchart.style.MatlabTheme;

import java.lang.reflect.WildcardType;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


// nextInt is normally exclusive of the top value,
// so add 1 to make it inclusive
//int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

public class CreateWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 49;
    private static final int min = 3;
    private static final int max = 6;
    private static int[] addRoomDoor(int xCoord, int yCoord, int width, int length){
        int xDoor = xCoord+1 + (int)(Math.random() * ((xCoord+width-3 - xCoord) + 1));
        int yDoor = yCoord+1 + (int)(Math.random() * ((yCoord+width-3 - yCoord) + 1));
        int doorSide = (int)(Math.random() * 2);
        int[] sides = new int[2];
        sides[0] = 0;
        sides[1] = 1;
        int[] doorCoord = new int[2];
        if(sides[doorSide]==0){
            doorCoord[0] = xDoor;
            doorCoord[1] = new Random().nextBoolean()? yCoord:yCoord+length-1;
            return doorCoord;
        }
        doorCoord[0] = new Random().nextBoolean()? xCoord:xCoord+width-1;
        doorCoord[1] = yDoor;
        return doorCoord;

    }
    private static void addRoom(int xCoord, int yCoord,int width, int length,TETile[][] world){
        for(int i = xCoord; i < xCoord+width;i++){
            for(int j = yCoord; j < yCoord+length; j++){
                if(i==xCoord|| j==yCoord ||i==xCoord+width-1||j==yCoord+length-1){
                    world[i][j] = Tileset.WALL;
                }
                else{
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
        int[] door = addRoomDoor(xCoord,yCoord,width,length);
        world[door[0]][door[1]] = Tileset.UNLOCKED_DOOR;
    }
    private static int[][] getOrthogonal(int xCoord, int yCoord, int width, int length){
        int[][] orthogonal = new int[2][2];
        orthogonal[0][0] = xCoord;
        orthogonal[0][1] = yCoord;
        orthogonal[1][0] = xCoord+width-1;
        orthogonal[1][1] = yCoord+length-1;
        return orthogonal;
    }
    public static boolean Overlap(int[][] orthogonal1, int[][] orthogonal2){
        int x1 = orthogonal1[0][0];
        int y1 = orthogonal1[0][1];
        int x2 = orthogonal1[1][0];
        int y2 = orthogonal1[1][1];
        int x3 = orthogonal2[0][0];
        int y3 = orthogonal2[0][1];
        int x4 = orthogonal2[1][0];
        int y4 = orthogonal2[1][1];
        return((x1<x4)&&(x2>x3)&&
                (y2>y3)&&(y1<y4));

    }
    private static void drawMultiRooms(int n,TETile[][] world){
        int count = 0;
        String overlap = "no overlap";
        int[][][] orthogonals= new int[n][2][2];
        while(count<n){
            int width = min + (int)(Math.random() * ((max- min) + 1));
            int length = min + (int)(Math.random() * ((max - min) + 1));
            int xCoord = (int)(Math.random() * (WIDTH-width-2+ 1));
            int yCoord = (int)(Math.random() * (HEIGHT-length-2+ 1));
            int[][] orthogonal = getOrthogonal(xCoord,yCoord,width,length);
            for(int j = 0; j<n;j++){
                if(Overlap(orthogonals[j],orthogonal)){
                    overlap = "overlap!";
                    break;
                }
            }
            if(overlap.equals("no overlap")) {
                addRoom(xCoord, yCoord, width, length, world);
                orthogonals[count] = orthogonal;
                count += 1;
            }
        }
    }
    private static int[] addRoomDoor(){
        return null;
    }

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for(int x =0;x<WIDTH;x++){
            for(int y =0;y<HEIGHT;y++){
                world[x][y] = Tileset.NOTHING;
                }
            }
        drawMultiRooms(10,world);
        //addRoom(86,7,3,4,world);
        //addRoom(80,11,3,5,world);
        //addRoom(54,28,3,3,world);
        //addRoom(35,9,3,5,world);
        //addRoom(52,16,6,6,world);
        ter.renderFrame(world);
        /*int[][] test1 = new int[4][4];
        int[][] test2 = new int[4][4];
        int[][] test3 = new int[4][4];
        test1[0][0] = 0;
        test1[0][1] = 0;
        test1[1][0] = 2;
        test1[1][1] = 2;
        test2[0][0] = 1;
        test2[0][1] = 1;
        test2[1][0] = 3;
        test2[1][1] = 3;
        test3[0][0] = 3;
        test3[0][1] = 3;
        test3[1][0] = 6;
        test3[1][1] = 6;
        if(Overlap(test1,test2) ){
            if(!Overlap(test1,test3)){
                System.out.println("passed!");
            }
        }*/
    }
}

