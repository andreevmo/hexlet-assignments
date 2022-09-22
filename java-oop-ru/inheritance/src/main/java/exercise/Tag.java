package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {

    public String tagName;
    public Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        String attr = attributes.entrySet().stream()
                .map(e -> " " + e.getKey() + "=\"" + e.getValue() + "\"")
                .collect(Collectors.joining());
        return "<"
                + tagName
                + attr
                + ">";
    }
}
// END
