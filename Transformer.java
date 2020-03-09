import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.stream.Stream;

public abstract class Transformer {
  protected abstract String lineToLine(String line) throws InvalidLineException;

  // Implement this
  public void run(String filename) {
    try {
      File r = new File(filename);
      Scanner rs = new Scanner(r);
      Stream<String> lines = Stream.iterate(null, (String s) -> rs.hasNextLine(), (String s) -> rs.nextLine());

      File o = new File(filename + ".out");
      try (PrintWriter pw = new PrintWriter(o);) {
        while (rs.hasNextLine()) {
          try {
            pw.println(this.lineToLine(rs.nextLine()));
          } catch (InvalidLineException e) {
            pw.println("");
          }
        }
      }

      // must close PrintWriter or use try-resource
    } catch (FileNotFoundException e) {
      System.out.println("Input file not found");
    }
  }

}
