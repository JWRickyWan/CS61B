package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.lang.reflect.WildcardType;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 60;
    private static int s = 3;
    private static int[] sList = new int[3*s-2];
    private static int [] pList = new int[3*s-2];
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    private static int x_pos = 0;
    private static int y_pos = 30;
    private static final long seed = 2873123;
    private static final Random random = new Random(SEED);



    public static void main(String[] args) {

        int tileNum = RANDOM.nextInt(3);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for(int x =0;x<WIDTH;x++){
            for(int y =0;y<HEIGHT;y++){
                world[x][y] = Tileset.NOTHING;
            }
        }
        //addHex(x_pos,y_pos,s,world);
        drawMultiHex(x_pos,y_pos,3,world,Tileset.FLOWER);
        //addHex(bottomRightNeighbor(x_pos,y_pos)[0],bottomRightNeighbor(x_pos,y_pos)[1],s,world);
        // drawMultiHex(x_pos,y_pos,3,world);
        int[] hexCoords = getNextHex(x_pos,y_pos);
        drawMultiHex(hexCoords[0],hexCoords[1],colNum()[1],world, Tileset.TREE);
        drawMultiHex(hexCoords[2],hexCoords[3],colNum()[2],world, Tileset.WALL);
        drawMultiHex(hexCoords[4],hexCoords[5],colNum()[3],world, Tileset.WATER);
        drawMultiHex(hexCoords[6],hexCoords[7],colNum()[4],world, Tileset.TREE);
        ter.renderFrame(world);
        }
    private static int[] WallNumRow(int s,int[] SList){
        for(int i =0;i<s;i++){
            SList[i]=2+2*i;
         }
        for (int i = s; i <= 2*s-2; i++) {
            SList[i] = 2 * s;
        }
        for(int i =2*s-1;i<3*s-2;i++){
            SList[i] = SList[i-1]-2;
        }
        return SList;
    }
    private static int[] yCoords(int s, int[] sList,int[] pList){
        for(int i =0;i<3*s-2;i++){
            pList[i] = s-sList[i]/2;
        }
        return pList;

    }

    private static void addHex(int m,int n, int s,TETile[][] world,TETile t){
        sList = WallNumRow(s,sList);
        for(int x = m;x <m+3*s-2;x++){
            for(int y =yCoords(s,sList,pList)[x-m]+n;y<yCoords(s,sList,pList)[x-m]+n+sList[x-m];y++){
                world[x][y] = t;
            }
        }
    }
    private static int[] topRightNeighbor(int x,int y){
        int[] topRightNeighbor = new int[2];
        int nextx = x+2*s-1;
        int nexty = y+s;
        topRightNeighbor[0]=nextx;
        topRightNeighbor[1]=nexty;
        return topRightNeighbor;
    }

    private static int[] bottomRightNeighbor(int x, int y){
        int[] bottomRightNeighbor = new int[2];
        int nextx = x+2*s-1;
        int nexty = y-s;
        bottomRightNeighbor[0]=nextx;
        bottomRightNeighbor[1]=nexty;
        return bottomRightNeighbor;

    }

    private static int[] colNum(){
        int[] colNum = new int[5];
        for(int i =0;i<5;i++){
            colNum[i] = 5-Math.abs(i-2);
        }
        return colNum;
    }
    private static void drawMultiHex(int x,int y, int n,TETile[][] world,TETile t){
        addHex(x,y,s,world,t);
        for(int i=1;i<n;i++){
            y = y+2*s;
            addHex(x,y,s,world,t);
        }
    }
    private static int[] getNextHex(int x, int y){
        int[] HexCoords = new int[8];
        for(int i =0; i<4; i=i+2){
            HexCoords[i] = bottomRightNeighbor(x,y)[0];
            HexCoords[i+1] = bottomRightNeighbor(x,y)[1];
            x = HexCoords[i];
            y = HexCoords[i+1];
        }
        for(int i=4;i<7;i=i+2){
            HexCoords[i] = topRightNeighbor(x,y)[0];
            HexCoords[i+1] = topRightNeighbor(x,y)[1];
            x = HexCoords[i];
            y = HexCoords[i+1];
        }
        return HexCoords;
    }


}
