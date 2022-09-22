package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        String stringCar = car.serialize();
        try {
            Files.writeString(path, stringCar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Car extract(Path path) {
        String stringCar = null;
        try {
            stringCar = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Car.unserialize(stringCar);
    }
}
// END
