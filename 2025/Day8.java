import java.io.*;
import java.util.*;

public class Day8 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("2025\\input.txt"));
        String line;
        long total = 1;

        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Pair> distances = new ArrayList<>();
        ArrayList<Pair> connections = new ArrayList<>();
        ArrayList<HashSet<Point>> circuits = new ArrayList<>();

        int numConnections = 10, numCircuits = 3;

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            String[] coords = line.split(",");

            points.add(new Point(
                    Integer.parseInt(coords[0]),
                    Integer.parseInt(coords[1]),
                    Integer.parseInt(coords[2])));
        }

        for (Point p1 : points) {
            for (Point p2 : points) {
                if (p1.equals(p2) || p2.equals(p1)) {
                    continue;
                }

                Pair pair = new Pair(p1, p2);
                if (!distances.contains(pair)) {
                    distances.add(pair);
                }
                // System.out.println(p1 + "," + p2 + "|" + Point.distance(p1, p2));
            }
        }
        // System.out.println(distances.size());

        for (int i = 0; i < numConnections; i++) {
            Pair group = distances.get(0);
            double distance = Double.MAX_VALUE;

            for (Pair pair : distances) {
                if (pair.distance < distance && !(connections.contains(pair))) {
                    group = pair;
                    distance = pair.distance;
                    // System.out.println(distances.get(pair));
                }
            }

            // System.out.println(p1 + "|" + p2);
            // System.out.println("-----");
            distances.remove(group);
            Point p1 = group.p1, p2 = group.p2;

            boolean check = false;
            for (HashSet<Point> circuit : circuits) {
                if (circuit.contains(p1) || circuit.contains(p2)) {
                    check = true;
                    circuit.add(p1);
                    circuit.add(p2);
                }
            }

            if (check == false) {
                circuits.add(new HashSet<>());
                circuits.get(circuits.size() - 1).add(p1);
                circuits.get(circuits.size() - 1).add(p2);
            }

            connections.add(group);
            // System.out.println(group);
        }
        System.out.println("-----");

        for (HashSet<Point> circuit : circuits) {
            for (Point p : circuit) {
                System.out.print(p + "|");
            }
            System.out.println();
        }

        System.out.println("-----");

        for(Pair pair : connections){
            System.out.println(pair);
        }

        for (int i = 0; i < numCircuits; i++) {
            HashSet<Point> largest = new HashSet<>(0);

            for (HashSet<Point> circuit : circuits) {
                if (circuit.size() > largest.size()) {
                    largest = circuit;
                }
            }

            circuits.remove(largest);
            total *= largest.size();
            System.out.println("" + largest.size());
        }

        System.out.println(total);
        scan.close();
    }

    public static class Pair {
        public Point p1, p2;
        public double distance;

        public Pair(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
            this.distance = Point.distance(p1, p2);
        }

        public boolean contains(Point p) {
            return this.p1.equals(p) || this.p2.equals(p);
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            return pair.contains(p1) && pair.contains(p2);
        }

        @Override
        public String toString() {
            return p1.toString() + "|" + p2.toString() + "|" + this.distance;
        }
    }

    public static class Point {
        public int x, y, z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public static double distance(Point p1, Point p2) {
            return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2) + Math.pow(p1.z - p2.z, 2));
        }

        @Override
        public boolean equals(Object o) {
            Point p = (Point) o;
            return this.x == p.x &&
                    this.y == p.y &&
                    this.z == p.z;
        }

        @Override
        public String toString() {
            return "(" + this.x + "," + this.y + "," + this.z + ")";
        }
    }
}
