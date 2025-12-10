import java.awt.*;
import java.io.*;
import java.util.*;

public class Day9 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025/input.txt"));

        ArrayList<Point> points = new ArrayList<>();
        Polygon poly = new Polygon();
        long total = 0;
        Point point1 = null, point2 = null;

        while (scan.hasNextLine()) {
            String[] coords = scan.nextLine().split(",");
            int x = Integer.parseInt(coords[0]);
            int y = Integer.parseInt(coords[1]);

            points.add(new Point(x, y));
            poly.addPoint(x, y);
        }

        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1.equals(p2))
                    continue;

                // correct point order
                int minX = Math.min(p1.x, p2.x);
                int maxX = Math.max(p1.x, p2.x);
                int minY = Math.min(p1.y, p2.y);
                int maxY = Math.max(p1.y, p2.y);

                // rectangle edges
                Point ul = new Point(minX, minY);
                Point ur = new Point(maxX, minY);
                Point br = new Point(maxX, maxY);
                Point bl = new Point(minX, maxY);

                // Check rectangle interior validity
                if (validRectangle(poly, ul, ur, br, bl)) {
                    long area = (long) (maxX - minX + 1) * (long) (maxY - minY + 1);

                    if (area > total) {
                        total = area;
                        point1 = p1;
                        point2 = p2;
                        System.out.println(total + ": " + p1 + " | " + p2);
                    }
                }
            }
        }

        System.out.println(total + ": " + point1 + " | " + point2);
        scan.close();
    }

    static boolean validRectangle(Polygon poly, Point ul, Point ur, Point br, Point bl) {
        // check if corners inside
        if (!validPoint(poly, ul))
            return false;
        if (!validPoint(poly, ur))
            return false;
        if (!validPoint(poly, br))
            return false;
        if (!validPoint(poly, bl))
            return false;

        // check if edges inside
        if (!validSegment(poly, ul, ur))
            return false;
        if (!validSegment(poly, ur, br))
            return false;
        if (!validSegment(poly, br, bl))
            return false;
        if (!validSegment(poly, bl, ul))
            return false;

        return true;
    }

    // check if line segment is contained in polygon
    static boolean validSegment(Polygon poly, Point p1, Point p2) {
        if (p1.x == p2.x) {
            for (int i = Math.min(p1.y, p2.y); i < Math.max(p1.y, p2.y); i++) {
                if (!validPoint(poly, new Point(p1.x, i))) {
                    return false;
                }
            }
            return true;
        }
        if (p1.y == p2.y) {
            for (int i = Math.min(p1.x, p2.x); i < Math.max(p1.x, p2.x); i++) {
                if (!validPoint(poly, new Point(i, p1.y))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    // contains or on boundary
    static boolean validPoint(Polygon poly, Point p) {
        if (poly.contains(p.x, p.y))
            return true;

        // boundary check
        for (int i = 0; i < poly.npoints; i++) {
            Point p1 = new Point(poly.xpoints[i], poly.ypoints[i]);
            Point p2 = new Point(poly.xpoints[(i + 1) % poly.npoints], poly.ypoints[(i + 1) % poly.npoints]);

            if (onSegment(p1, p2, p)) {
                return true;
            }
        }

        return false;
    }

    // if point is on segment
    static boolean onSegment(Point s1, Point s2, Point p) {
        if (s1.x == s2.x) { // horizontal line
            return p.x == s1.x && p.y >= Math.min(s1.y, s2.y) && p.y <= Math.max(s1.y, s2.y);
        }
        if (s1.y == s2.y) { // vertical line
            return p.y == s1.y && p.x >= Math.min(s1.x, s2.x) && p.x <= Math.max(s1.x, s2.x);
        }
        return false;
    }

    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Point))
                return false;
            Point p = (Point) o;
            return x == p.x && y == p.y;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}