package exercise;

import java.util.*;
// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage map) {
        Set<String> setKeys = map.toMap().keySet();
        for (Map.Entry<String, String> e : map.toMap().entrySet()) {
            if (!setKeys.contains(e.getKey())) {
                continue;
            }
            swap(map, e.getKey(), e.getValue(), setKeys);
        }
    }

    public static void swap(KeyValueStorage map, String key, String value, Set<String> keys) {
        if (map.toMap().containsKey(value)) {
            swap(map, value, map.toMap().get(value), keys);
        }
        map.unset(key);
        map.set(value, key);
        keys.remove(key);
    }
}
// END
