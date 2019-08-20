import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
  public static void main(String args[]) throws NoSuchAlgorithmException {
    BloomFilter bloomFilter = new BloomFilter(24);
    BufferedReader reader;
    try {
      reader = new BufferedReader(new FileReader(
          "/usr/share/dict/words"));
      String line = reader.readLine();
      while (line != null) {
        System.out.println(line);
        // read next line
        line = reader.readLine();
        if (line != null)
          bloomFilter.add(line);
      }
      reader.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

    Scanner in = new Scanner(System.in);
    while (true) {
      String s = in.nextLine();
      System.out.println(bloomFilter.test(s)? "Probably exists" : "Definitely not exists");
    }
  }
}
