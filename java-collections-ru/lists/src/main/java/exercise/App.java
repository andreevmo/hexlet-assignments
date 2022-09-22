package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {

    public static boolean scrabble(String kit, String word) {
        List<Character> charsKit = new ArrayList<>();
        for (int i = 0; i < kit.length(); i++) {
            charsKit.add(kit.toLowerCase().charAt(i));
        }
        for (Character required : word.toLowerCase().toCharArray()) {
            if (charsKit.contains(required)) {
                charsKit.remove(required);
                continue;
            }
            return false;
        }
        return true;
    }
}
//END
