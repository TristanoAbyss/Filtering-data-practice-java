import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class TreeLoader {
  private File source;

  public TreeLoader(String path) {
    this.source = new File(path);
  }

  public Stream<CityTree> getTrees() throws IOException {
    /*
      1. This code loads the data file into a Stream<String> using `Files.lines`
      2. Then it skips the first line (the headings)
      3. For each line, we convert it from String to CityTree

      So we return a Stream<CityTree> after
     */
    return Files.lines(this.source.toPath()).skip(1).map(CityTree::new);
  }
}
