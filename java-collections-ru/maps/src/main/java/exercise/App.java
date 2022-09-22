package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {

    public static Map getWordCount(String text) {
        Map<String, Integer> wordsCount = new HashMap<>();
        if (text.isEmpty()) {
            return wordsCount;
        }
        String[] words = text.split(" ");
        for (String word : words) {
            if (wordsCount.containsKey(word)) {
                wordsCount.put(word, wordsCount.get(word) + 1);
            } else {
                wordsCount.put(word, 1);
            }
        }
        return wordsCount;
    }

    public static String toString(Map wordsCount) {
        String result = "";
        if (wordsCount.isEmpty()) {
            return "{}";
        }
        Map<String, Integer> mapForEntry = new HashMap<>(wordsCount);
        for (Map.Entry<String, Integer> word : mapForEntry.entrySet()) {
            result += "  " + word.getKey() + ": " + word.getValue() + "\n";
        }
        return "{\n" + result + "}";
    }
}
//END
