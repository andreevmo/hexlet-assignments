package exercise;

import java.util.stream.Collectors;
import java.util.Arrays;

// BEGIN
class App {

    public static String getForwardedVariables(String file) {
        return Arrays.stream(file.split("\\n"))
                .filter(s -> s.startsWith("environment"))
                .map(s -> s.replaceAll(",", "X_"))
                .map(s -> s.split("X_"))
                .flatMap(Arrays::stream)
                .filter(s -> s.startsWith("FORWARDED_"))
                .map(s -> s.replace("FORWARDED_", "").replaceAll("\"", ""))
                .collect(Collectors.joining(","));
    }
}
//END
