import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;

public class TestMergeSort {
    @Test
    public void testMergeSort(){
        Queue<Integer> test = new Queue<>();
        test.enqueue(2);
        test.enqueue(20);
        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(17);
        test.enqueue(10);
        test.enqueue(0);
        System.out.println("original queue: ");
        for (int integer : test) {
            System.out.print(integer+" ");
        }
        System.out.println("");
        Queue<Integer> sorted = MergeSort.mergeSort(test);
        System.out.println("Merge sorted queue: ");
        System.out.println("");
        for(int integer : sorted){
            System.out.print(integer+" ");
        }
        assertEquals((int)sorted.dequeue(),0);
        assertEquals((int)sorted.dequeue(),1);
        assertEquals((int)sorted.dequeue(),2);
        assertEquals((int)sorted.dequeue(),2);
        assertEquals((int)sorted.dequeue(),10);
        assertEquals((int)sorted.dequeue(),17);
        assertEquals((int)sorted.dequeue(),20);

        }
    }

