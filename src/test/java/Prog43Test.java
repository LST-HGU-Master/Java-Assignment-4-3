import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Locale;

public class Prog43Test {

    @Test
    public void testNumLines()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog43.main(new String[]{"7", "8"});
        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals(7, prints.length);
        // action
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Prog43.main(new String[]{"5", "6"});
        // assertion
        prints = bos.toString().split("\n");
        assertEquals(5, prints.length);

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testNumColumns()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog43.main(new String[]{"7", "8"});
        // assertion
        String[] prints = bos.toString().split("\n");
        assertEquals(8 * 3, prints[0].length());
        // action
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Prog43.main(new String[]{"5", "6"});
        // assertion
        prints = bos.toString().split("\n");
        assertEquals(6 * 3, prints[0].length());

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testFirstLineAll0()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog43.main(new String[]{"7", "8"});
        // assertion
        String[] prints = bos.toString().split("\n");
        assertTrue(prints[0].contains("0"));
        assertFalse(prints[0].contains("1"));
        assertFalse(prints[0].contains("2"));

        // undo the binding in System
        System.setOut(originalOut);
    }

    @Test
    public void testAllOutputs()
    {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        StandardInputStream in = new StandardInputStream();
        System.setIn(in);

        // action
        Prog43.main(new String[]{"3", "4"});
        // assertion
        String[] expected1 = {
                "  0  0  0  0",
                "  0  1  2  3",
                "  0  2  4  6",
        };
        String[] prints = bos.toString().split("\n");
        assertEquals(expected1[0], prints[0]);
        assertEquals(expected1[1], prints[1]);
        assertEquals(expected1[2], prints[2]);

        // action
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        Prog43.main(new String[]{"4", "4"});
        // assertion
        String[] expected2 = {
                "  0  0  0  0",
                "  0  1  2  3",
                "  0  2  4  6",
                "  0  3  6  9"
        };
        prints = bos.toString().split("\n");
        assertEquals(expected2[0], prints[0]);
        assertEquals(expected2[1], prints[1]);
        assertEquals(expected2[2], prints[2]);
        assertEquals(expected2[3], prints[3]);


        // undo the binding in System
        System.setOut(originalOut);
    }
}
