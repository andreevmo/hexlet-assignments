package exercise;

import java.util.*;

// BEGIN
class App {

    public static Map<String, Object> genDiff(Map<String, Object> data1, Map<String, Object> data2) {
        Map<String, Object> resultMap = new TreeMap<>();
        for (Map.Entry element : data1.entrySet()) {
            if (data2.containsKey(element.getKey())
                    && data2.get(element.getKey()).equals(element.getValue())) {
                resultMap.put((String) element.getKey(), "unchanged");
            } else if (data2.containsKey(element.getKey())
                    && !(data2.get(element.getKey()).equals(element.getValue()))) {
                resultMap.put((String) element.getKey(), "changed");
            } else if (!(data2.containsKey(element.getKey()))) {
                resultMap.put((String) element.getKey(), "deleted");
            }
        }
        for (Map.Entry element : data2.entrySet()) {
            if (!data1.containsKey(element.getKey())) {
                resultMap.put((String) element.getKey(), "added" );
            }
        }
        return resultMap;
    }
}
//END
