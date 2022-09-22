package exercise;

// BEGIN
public class Segment {

    public final Point begin;
    public final Point end;

    public Segment(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBeginPoint() {
        return begin;
    }

    public Point getEndPoint() {
        return end;
    }

    public Point getMidPoint() {
        int x = (begin.getX() + end.getX()) / 2;
        int y = (begin.getY() + end.getY()) / 2;
        return new Point(x, y);
    }
}
// END
