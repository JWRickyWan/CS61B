package lab9;
import org.junit.Test;
import static org.junit.Assert.*;

public class BSTMapTest {
    @Test
    public void getTest(){
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        assertEquals((int)bstmap.get("fish"),22);
        assertEquals((int)bstmap.get("hello"),5);
        assertEquals((int)bstmap.get("cat"),10);
        assertEquals((int)bstmap.get("zebra"),90);
        assertEquals(bstmap.size(),4);
    }
    }


