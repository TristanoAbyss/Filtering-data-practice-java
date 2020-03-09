import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExplorerUtil {
  public static int getYear(Date d) {
    Calendar c = Calendar.getInstance();
    c.setTime(d);
    return c.get(Calendar.YEAR);
  }

  public static <T> String listToString(List<T> ss) {
    /*
      1. Convert each item (of Class T) into a String
      2. Concatenate all the String with "\n"
     */
    return ss.stream()
        .map(s -> s.toString())
        .collect(Collectors.joining("\n"));
  }
}
