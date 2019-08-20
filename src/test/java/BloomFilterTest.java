import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BloomFilterTest {
  private List<String> dictionary;

  @Before
  public void init() {
     dictionary = Arrays.asList("a", "aa", "ab", "abc", "b", "c", "d", "e", "f", "gh");
     System.out.println(Math.log(1130491)/Math.log(2));
    System.out.println(Math.pow(2,21));
  }


  @Test
  public void emptyBloomFilterAlwaysReturnNotFound() throws NoSuchAlgorithmException {
    BloomFilter bloomFilter = new BloomFilter(16);
    assertFalse(bloomFilter.test("a"));
  }

  @Test
  public void whenElementAddedThenPossiblyExist() throws NoSuchAlgorithmException {
    BloomFilter bloomFilter = new BloomFilter(16);
    bloomFilter.add("a");
    assertTrue(bloomFilter.test("a"));
  }

  @Test
  public void addAllDictionaryWordsAndTestAllOfThemShouldBeTrue() throws NoSuchAlgorithmException {
    BloomFilter bloomFilter = new BloomFilter(16);
    for (String word: dictionary) {
      bloomFilter.add(word);
    }
    for (String word: dictionary) {
      assertTrue(bloomFilter.test(word));
    }
    assertFalse(bloomFilter.test("aaa"));
  }
}
