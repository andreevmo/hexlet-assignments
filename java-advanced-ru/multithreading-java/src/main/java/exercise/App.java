package exercise;

import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static Logger getLOGGER() {
        return LOGGER;
    }

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] array) {
        MinThread minThread = new MinThread(array);
        MaxThread maxThread = new MaxThread(array);

        minThread.start();
        maxThread.start();

        try {
            minThread.join();
            maxThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        int min = minThread.getMin();
        int max = maxThread.getMax();

        return Map.of("min", min, "max", max);
    }
    // END
}
