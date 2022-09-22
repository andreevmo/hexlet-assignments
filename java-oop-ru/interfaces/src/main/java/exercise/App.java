package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {

    public static List<String> buildApartmentsList(List<Home> apartments, int count) {
        int normalizeCount = count < 0 ? 0 : Math.min(count, apartments.size());
        return apartments.stream()
                .sorted((Comparator.comparingDouble(Home::getArea)))
                .limit(normalizeCount)
                .map(Object::toString)
                .toList();
    }
}
// END
