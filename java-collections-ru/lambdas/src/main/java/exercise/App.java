package exercise;

import java.util.Arrays;

// BEGIN
class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(s -> new String[][]{s,s})
                .flatMap(Arrays::stream)
                .map(strings -> Arrays.stream(strings)
                        .map(s -> new String[]{s,s})
                        .flatMap(Arrays::stream)
                        .toArray(String[]::new))
                .toArray(String[][]::new);
    }
}
// END
