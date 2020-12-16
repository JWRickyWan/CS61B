import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    @Test
    public void testOffbyN() {
        OffByN OB5 = new OffByN(5);
        char a = 'a';
        char b = 'f';
        char c = '%';
        char d = ' ';
        assertTrue(OB5.equalChars(a,b));
        assertTrue(OB5.equalChars(c,d));
        assertFalse(OB5.equalChars(a,c));
        assertFalse(OB5.equalChars(a,d));
    }

}
