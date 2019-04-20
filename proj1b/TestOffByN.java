import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offByN = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertFalse(offByN.equalChars('f', 'h'));
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
    }
}

