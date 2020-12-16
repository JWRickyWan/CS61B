import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {
    @Test
    public void Test(){
        int a = 128;
        int b = 600;
        int c = 128;
        assertTrue(Flik.isSameNumber(a,c));
        assertFalse(Flik.isSameNumber(a,b));
    }
    public static void main(String[] args){
        Flik.isSameNumber(128,128);
    }
}


