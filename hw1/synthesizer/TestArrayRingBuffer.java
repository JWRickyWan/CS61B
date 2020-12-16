package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
        int a = 2;
        int b = 3;
        int c = 4;
        int d = 5;
        ArrayRingBuffer test = new ArrayRingBuffer(4);
        assertTrue(test.isEmpty());
        assertFalse(test.isFull());
        test.enqueue(a);
        assertEquals(test.capacity,4);
        assertFalse(test.isEmpty());
        test.enqueue(b);
        test.enqueue(c);
        int dequeue = (int) test.dequeue();
        assertEquals(dequeue,2);
        test.enqueue(d);
        test.enqueue(a);
        assertTrue(test.isFull());
        assertEquals(test.peek(),3);
        assertEquals(test.dequeue(),3);
        for(Object i:test){
            System.out.println(i);
        }
    }
} 
