import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void test_isPalindrome(){
        String x = "cat";
        String y = "madam";
        String z = "";
        String m = "a";
        String n = "toot";
        String k = "Toot";
        String i = "MADAM";
        String j = "TOOT";
        assertFalse(palindrome.isPalindrome(x));
        assertTrue(palindrome.isPalindrome(y));
        assertTrue(palindrome.isPalindrome(z));
        assertTrue(palindrome.isPalindrome(m));
        assertTrue(palindrome.isPalindrome(n));
        assertFalse(palindrome.isPalindrome(k));
        assertTrue(palindrome.isPalindrome(i));
        assertTrue(palindrome.isPalindrome(j));
    }

    @Test
    public void test_OffByOne(){
        char x = 'a';
        char y = 'b';
        char z = '&';
        char m = 'B';
        char n = '%';
        OffByOne test = new OffByOne();
        assertTrue(test.equalChars(x,y));
        assertTrue(test.equalChars(n,z));
        assertFalse(test.equalChars(x,z));
        assertFalse(test.equalChars(y,m));
    }

    @Test
    public void TestOdOPalindrome(){
        Palindrome test = new Palindrome();
        OffByOne cc = new OffByOne();
        String x = "adb";
        String y = "dqpe";
        String z = "happy";
        String m = "FUTE";
        String n = "";
        String k = "%";
        assertTrue(test.isPalindrome(x,cc));
        assertTrue(test.isPalindrome(y,cc));
        assertFalse(test.isPalindrome(z,cc));
        assertTrue(test.isPalindrome(m,cc));
        assertTrue(test.isPalindrome(n,cc));
        assertTrue(test.isPalindrome(k,cc));

    }
}
