import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.*;
import java.util.Locale;
/**
 * @version (20220501)
 **/
public class Prog43Test {
    InputStream originalIn;
    PrintStream originalOut;
    ByteArrayOutputStream bos;
    StandardInputStream in;

    @BeforeEach
    void before() {
        //back up binding
        originalIn  = System.in;
        originalOut = System.out;
        //modify binding
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));
        
        in = new StandardInputStream();
        System.setIn(in);
    }
    
    @AfterEach
    void after() {
       System.setOut(originalOut);
       System.setIn(originalIn);
    }


    @Test
    public void testNumLines()
    {
        try {
        // action
            Prog43.main(new String[]{"7", "8"});
        // assertion
            String[] prints = bos.toString().split(System.lineSeparator());
            assertEquals(7, prints.length,"実行時の第１パラメータの値から期待されるものと結果（縦の行数）が一致しません[A]!");

        // action
            bos = new ByteArrayOutputStream();   // 二度めのmain実行に向けて出力先を再調整
            System.setOut(new PrintStream(bos));
            Prog43.main(new String[]{"5", "6"});
        // assertion
            prints = bos.toString().split(System.lineSeparator());
            assertEquals(5, prints.length,"実行時の第１パラメータの値から期待されるものと結果（縦の行数）が一致しません[B]!");
        } catch (AssertionError err) {
            after();
            throw err;
        } catch ( Exception excpt) { // just in case
            after();
            throw excpt;
        }
    }

    @Test
    public void testNumColumns()
    {
        try {
        // action
            Prog43.main(new String[]{"7", "8"});
        // assertion
            String[] prints = bos.toString().split(System.lineSeparator());
            assertEquals(8 * 3, prints[0].length(),"実行時の第２パラメータの値から期待されるものとその結果（スペース文字を含めた横方向の文字数）が一致しません[A]!");

        // action
            bos = new ByteArrayOutputStream();   // 二度めのmain実行に向けて出力先を再調整
            System.setOut(new PrintStream(bos));
            Prog43.main(new String[]{"5", "6"});
        // assertion
            prints = bos.toString().split(System.lineSeparator());
            assertEquals(6 * 3, prints[0].length(),"実行時の第２パラメータの値から期待されるものとその結果（スペース文字を含めた横方向の文字数）が一致しません[B]!");
        } catch (AssertionError err) {
            after();
            throw err;
        } catch ( Exception excpt) { // just in case
            after();
            throw excpt;
        }
    }

    @Test
    public void testFirstLineAll0()
    {
        // action
        Prog43.main(new String[]{"7", "8"});
        // assertion
        String[] prints = bos.toString().split(System.lineSeparator());
        String msg = "print結果の1行目に0以外の数字が含まれています";
        try {
            assertTrue(prints[0].contains("0"),  msg);
            assertFalse(prints[0].contains("1"), msg);
            assertFalse(prints[0].contains("2"), msg);
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }

    @Test
    public void testAllOutputs()
    {
        // action
        Prog43.main(new String[]{"3", "4"});
        // assertion
        String[] expected1 = {
                "  0  0  0  0",
                "  0  1  2  3",
                "  0  2  4  6",
        };
        String[] prints = bos.toString().split(System.lineSeparator());
        String msg1 = "print結果の";
        String msg2 = "行目が期待したものと異なります!";
        try {
            assertEquals(expected1[0], prints[0], msg1 + 1 + msg2);
            assertEquals(expected1[1], prints[1], msg1 + 2 + msg2);
            assertEquals(expected1[2], prints[2], msg1 + 3 + msg2);
        } catch (AssertionError err) {
            after();
            throw err;
        }
        // action
        bos = new ByteArrayOutputStream();   // 二度めのmain実行に向けて出力先を再調整
        System.setOut(new PrintStream(bos));
        Prog43.main(new String[]{"4", "4"});
        // assertion
        String[] expected2 = {
                "  0  0  0  0",
                "  0  1  2  3",
                "  0  2  4  6",
                "  0  3  6  9"
        };
        prints = bos.toString().split(System.lineSeparator());
        try {
            assertEquals(expected2[0], prints[0], msg1 + 1 + msg2);
            assertEquals(expected2[1], prints[1], msg1 + 2 + msg2);
            assertEquals(expected2[2], prints[2], msg1 + 3 + msg2);
            assertEquals(expected2[3], prints[3], msg1 + 4 + msg2);
        } catch (AssertionError err) {
            after();
            throw err;
        }
    }
}
