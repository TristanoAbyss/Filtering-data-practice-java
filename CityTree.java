import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CityTree {
  private static final SimpleDateFormat df = new SimpleDateFormat("YYYYMMDD");
  private String id;
  private String streetNumber;
  private String streetName;
  private String neighbourhood;
  private int height;
  private double diameter;
  private String genus;
  private String species;
  private String commonName;

  /**
   * For each tree, we print a summary of the tree
   * @param trees
   */
  public static void printList(List<CityTree> trees) {
    trees.stream().forEach(t -> {
      System.out.printf("Height: %d, Diameter: %.2f, Common Name: %s\n", t.getHeight(), t.getDiameter(), t.getCommonName());
    });
  }

  public String getId() {
    return id;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public String getStreetName() {
    return streetName;
  }

  public String getNeighbourhood() {
    return neighbourhood;
  }

  public int getHeight() {
    return height;
  }

  public double getDiameter() {
    return diameter;
  }

  public String getGenus() {
    return genus;
  }

  public String getSpecies() {
    return species;
  }

  public String getCommonName() {
    return commonName;
  }


  public Date getPlanted() {
    return planted;
  }

  public String getBlock() {
    return this.getStreetNumber() + " " + this.getStreetName();
  }

  private Date planted = null;

  public CityTree(String l) {
    /**
     * Process each line of the CSV
     */
    String[] fields = l.split(",");
    this.id = fields[0].trim();
    this.streetNumber = fields[5].trim();
    this.streetName = fields[4].trim();
    this.neighbourhood = fields[3].trim();
    this.height = Integer.parseInt(fields[8]);
    this.diameter = Double.parseDouble(fields[9]);
    this.genus = fields[15].trim();
    this.species = fields[16].trim();
    this.commonName = fields[17].trim();
    try {
      this.planted = df.parse(fields[10].trim());
    } catch (ParseException e) {
    }

    /*
    0, TREE_ID
    1, CIVIC_NUMBER
    2, STD_STREET
    3, NEIGHBOURHOOD_NAME
    4, ON_STREET
    5, ON_STREET_BLOCK
    6, STREET_SIDE_NAME
    7, ASSIGNED
    8, HEIGHT_RANGE_ID
    9, DIAMETER
    10, DATE_PLANTED
    11, PLANT_AREA
    12, ROOT_BARRIER
    13, CURB
    14, CULTIVAR_NAME
    15, GENUS_NAME
    16, SPECIES_NAME
    17, COMMON_NAME
    18, LATITUDE
    19, LONGITUDE
    */

  }
}
