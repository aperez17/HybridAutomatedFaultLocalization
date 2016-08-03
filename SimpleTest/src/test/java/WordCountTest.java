import org.junit.Test;
import afl.WordCount;
import afl.Word;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
public class WordCountTest {
   String filepath = "/Users/alexperez/researchProject/HybridAutomatedFaultLocalization/SimpleTest/src/test/resources/sampleFiles/simpleTest.txt";
   @Test
   public void wordObjTest() {
      Word simpleWord = new Word("foobar");
      assertEquals(simpleWord.getWord(), "foobar");
      assertEquals(simpleWord.getCount(), 1);
   }

   @Test
   public void wordFuncTest() {
       Word simpleWord = new Word("foobar");
       simpleWord.addWord();
       assertEquals(simpleWord.getCount(), 2);
   }

   @Test
   public void wordCountObjTest() {
       WordCount simpleTxt = new WordCount("");
       assertEquals(simpleTxt.getWords().size(), 0);
       assertEquals(simpleTxt.toString(), "");
   }

   @Test
   public void wordCountFuncTest() {
       WordCount simpleTxt = new WordCount(filepath);
       simpleTxt.readFile();
       assertEquals(9, simpleTxt.getWords().size());
       assertEquals("test : 3\n" +
        "this : 2\n" +
        "is : 2\n" +
        "a : 2\n" +
        "count : 1\n" +
        "the : 1\n" +
        "words : 1\n" +
        "of : 1\n" +
        "which : 1\n", simpleTxt.toString());
   }
}
