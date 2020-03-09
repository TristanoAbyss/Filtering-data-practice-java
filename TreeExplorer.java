import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TreeExplorer {
  public static void main(String[] args) {
    String source = "data/trees.csv";
    TreeLoader loader = new TreeLoader(source);

    try {

      howManyTrees(loader.getTrees());

      treesIn2018(loader.getTrees());

      tallestTrees(loader.getTrees());

      widestTrees(loader.getTrees());


      douglasFir(loader.getTrees());

      averageDiameterDouglasFir1(loader.getTrees(), loader.getTrees());

      averageDiameterDouglasFir2(loader.getTrees());


      cherryTrees(loader.getTrees());

      cherryByNeighbourhood(loader.getTrees());

      streetWithMostCherry(loader.getTrees());

    } catch (IOException e) {
      System.out.println("Unable to open file");
      e.printStackTrace();
    }
  }

  /**
   * prints the streets with the most cherry blossoms on them
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void streetWithMostCherry(Stream<CityTree> trees) throws IOException {
    // Street with the most
    List<Map.Entry<String, Long>> byStreet = trees
            .filter( e -> e.getCommonName().contains("CHERRY"))
            .collect(
                    Collectors.groupingBy(
                            e -> e.getBlock(),
                            Collectors.counting()
                    ))
            .entrySet()
            .stream()
            .sorted( (e, f) -> (int) (f.getValue() - e.getValue()))
            .limit(5)
            .collect(Collectors.toList());

    System.out.println("Street Block with most Cherry Trees");
    System.out.println(ExplorerUtil.listToString(byStreet));
    System.out.println();
  }

  /**
   * prints the neighbourhood with the most trees
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void cherryByNeighbourhood(Stream<CityTree> trees) {
    // Group By Neightbourhood
    List<Map.Entry<String, Long>> byNeighbourhood = trees
            .filter(e -> e.getCommonName().contains("CHERRY"))
            .collect(
                    Collectors.groupingBy(
                        e -> e.getNeighbourhood(),
                        Collectors.counting()
                    ))
            .entrySet()
            .stream()
            .sorted( (e, f) -> (int) (f.getValue() - e.getValue()))
            .limit(3)
            .collect(Collectors.toList());

    System.out.println("Cherry Trees grouped by neighbourhood");
    System.out.println(ExplorerUtil.listToString(byNeighbourhood));
    System.out.println();
  }

  /**
   * How many trees there are named cherry
   *
   * @param trees stream of trees with data
   * @throws IOException
   */


  private static void cherryTrees(Stream<CityTree> trees) {
    // How many trees with CHERRY in the common name
    long cherry = trees
            .filter(e -> e.getCommonName().contains("CHERRY"))
            .count();

    System.out.println("Number of Cherry Trees: " + cherry);
    System.out.println();
  }

  /**
   * The average diameter of a Douglas Fir
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void averageDiameterDouglasFir1(Stream<CityTree> trees, Stream<CityTree> moreTrees) {
    // Total diameter of all DOUGLAS FIR?

    //getting the diameter of all trees named Douglas Fir

    double totalDiameter = trees
            .filter(e -> e.getCommonName().contains("DOUGLAS FIR"))
            .map(e -> e.getDiameter())
            .reduce(0.0, (a, b) -> a + b);

    // Number of DOUGLAS FIR
    long count = moreTrees //getting the amount
            .filter(e -> e.getCommonName().contains("DOUGLAS FIR"))
            .count();


    System.out.printf("(Method 1) Average diameter of DOUGLAS FIR: %.2f\n", totalDiameter / count);
    System.out.println();
  }

  /**
   * Prints the average diameter of Douglas Firs in the data table
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void averageDiameterDouglasFir2(Stream<CityTree> trees) {
    // Average diameter of DOUGLAS FIR?
    double averageDiameter = trees
            .filter(e -> e.getCommonName().contains("DOUGLAS FIR"))
            .mapToDouble(e -> e.getDiameter())
            .average()
            .getAsDouble();


    System.out.printf("(Method 2) Average diameter of DOUGLAS FIR: %.2f\n", averageDiameter);
    System.out.println();
  }

  /**
   * prints the amount of Douglas Firs in the data table
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void douglasFir(Stream<CityTree> trees) {
    // How many DOUGLAS FIR?
    long douglasFir = trees
            .filter(e -> e.getCommonName().contains("DOUGLAS FIR"))
            .count();

    System.out.println("Number of DOUGLAS FIR: " + douglasFir);
    System.out.println();
  }

  /**
   * prints 5 of the widest trees
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void widestTrees(Stream<CityTree> trees) {
    // Widest Trees
    List<CityTree> widest = trees
            .sorted((a, b) -> Double.compare(b.getDiameter(), a.getDiameter()))
            .limit(5)
            .collect(Collectors.toList());

    System.out.println("Widest Trees: ");
    CityTree.printList(widest);
    System.out.println();
  }

  /**
   * prints the tallest trees and limits to 7 inputs
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void tallestTrees(Stream<CityTree> trees) {
    // Tallest Trees
    List<CityTree> tallest = trees
            .sorted((a, b) -> (int) (b.getHeight() - a.getHeight()))
            .limit(7)
            .collect(Collectors.toList());



    System.out.println("Tallest Trees: ");
    CityTree.printList(tallest);
    System.out.println();
  }

  /**
   * prints the amount of trees that were planted in 2018
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void treesIn2018(Stream<CityTree> trees) {
    // Trees planted in 2018
    int plantedIn2018 = (int) trees
            .filter(e -> ExplorerUtil.getYear(e.getPlanted()) == 2018)
            .count();

    System.out.println("Planted in 2018: " + plantedIn2018);
    System.out.println();
  }

  /**
   * prints the amount of trees in the data table
   *
   * @param trees stream of trees with data
   * @throws IOException
   */

  private static void howManyTrees(Stream<CityTree> trees) {
    long num = trees.count();

    System.out.println("Total Number of Trees: " + num);
    System.out.println();
  }
}
