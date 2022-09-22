package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{

    private final String originalLine;
    private final String usingLine;

    public ReversedSequence(String line) {
        this.originalLine = line;
        this.usingLine = setUsingLine(line);
    }

    @Override
    public String toString() {
        return usingLine;
    }

    @Override
    public int length() {
        return usingLine.length();
    }

    @Override
    public char charAt(int i) {
        return usingLine.charAt(i);
    }

    @Override
    public CharSequence subSequence(int i, int i1) {
        int normalizeI1 = Math.min(i1, usingLine.length());
        int normalizeI = i < 0 ? 0 : Math.min(i, normalizeI1);
        return usingLine.substring(normalizeI, normalizeI1);
    }

    private String setUsingLine(String originalLine) {
        StringBuilder resultLine = new StringBuilder();
        for (int i = originalLine.length() - 1; i >= 0; i--) {
            resultLine.append(originalLine.charAt(i));
        }
        return resultLine.toString();
    }

    public String getOriginalLine() {
        return originalLine;
    }
}
// END
