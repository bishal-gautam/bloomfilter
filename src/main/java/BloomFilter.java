import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import javax.xml.bind.DatatypeConverter;

public class BloomFilter {

  private BitSet bitSet;
  private int numberOfBitsPerChunk;
  private MessageDigest messageDigest;


  public BloomFilter(int numberOfBitsPerChunk)
      throws NoSuchAlgorithmException {
    this.numberOfBitsPerChunk = numberOfBitsPerChunk;
    int sizeOfFilter  = (int)Math.pow(2,numberOfBitsPerChunk);
    bitSet = new BitSet(sizeOfFilter);
    messageDigest = MessageDigest.getInstance("MD5");
  }

 private String md5HashString(String input) {
    messageDigest.update(input.getBytes());
    byte[] digest = messageDigest.digest();
    return  DatatypeConverter.printHexBinary(digest);
  }


  public void add(String input) {
    String hexValueHash = md5HashString(input);
    int hexChunkSize = numberOfBitsPerChunk / 4;
    List<Integer> indices = getIndices(hexValueHash, hexChunkSize);
    for (Integer index: indices) {
      bitSet.set(index, true);
    }
  }

  public boolean test(String input) {
    String hexValueHash = md5HashString(input);
    int hexChunkSize = numberOfBitsPerChunk / 4;
    List<Integer> indices = getIndices(hexValueHash, hexChunkSize);
    for (Integer index: indices) {
      if (!bitSet.get(index)) return false;
    }
    return  true;
  }

  List<Integer> getIndices(String hexValueHash, int chunkSize) {
    List<Integer> results = new ArrayList<Integer>();
    int numberOfIteration = hexValueHash.length()/chunkSize;
    for (int i = 0; i < numberOfIteration; i += 1) {
      int currIndex = i * chunkSize;
      String currentHexChunk = hexValueHash.substring(currIndex, currIndex + chunkSize);
      results.add(Integer.parseInt(currentHexChunk, 16));
    }
    return  results;
  }

  public boolean test8(String input) {
    messageDigest.update(input.getBytes());
    byte[] digest = messageDigest.digest();
    for (byte byteValue: digest) {
      int i = (byteValue & 0xFF);
      if (!bitSet.get(i)) {
        return false;
      }
    }
    return true;
  }

  public void add8(String input) {
    messageDigest.update(input.getBytes());
    byte[] digest = messageDigest.digest();
    for (byte byteValue: digest) {
      int i = (byteValue & 0xFF);
      bitSet.set(i, true);
    }
  }


}
