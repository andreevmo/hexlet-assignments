package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private String body;
    private List<Tag> children;

    public PairedTag(String tagName, Map<String, String> attributes, String body, List<Tag> children) {
        super(tagName, attributes);
        this.body = body;
        this.children = children;
    }

    public static String buildPairedTagPart(List<Tag> children, String body) {
        String child = children.stream()
                .map(Tag::toString)
                .collect(Collectors.joining());
        return child + body;
    }

    @Override
    public String toString() {
        return super.toString()
                + buildPairedTagPart(children, body)
                + "</"
                + tagName
                + ">";
    }
}
// END
