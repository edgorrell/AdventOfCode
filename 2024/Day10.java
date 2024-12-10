import java.util.*;
import java.io.*;

public class Day10 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input.txt"));
        String line, str;
        ArrayList<Point> list = new ArrayList<Point>(), q = new ArrayList<Point>();
        int[][] map = new int[58][60];
        Point p;
        long total = 0;
        int num;
        boolean check;

        num = 0;
        while (scan.hasNext()) {
            line = scan.nextLine();

            for (int i = 0; i < line.length(); i++) {
                map[num][i] = Integer.parseInt("" + line.charAt(i));
                if (map[num][i] == 0) {
                    list.add(new Point(num, i));
                }
            }

            num++;
        }

        int[] xdif = new int[] { 1, -1, 0, 0 };
        int[] ydif = new int[] { 0, 0, 1, -1 };
        for (Point start : list) {
            q = new ArrayList<Point>();
            q.add(new Point(start));
            str = "";

            while (true) {
                p = q.get(0);
                for (Point test : q) {
                    if (map[test.x][test.y] != 9) {
                        p = test;
                        break;
                    }
                }
                q.remove(p);

                num = q.size();
                for (int i = 0; i < 4; i++) {
                    try {
                        Point test = new Point(p.x + xdif[i], p.y + ydif[i]);
                        if (test.get(map) == p.get(map) + 1) {
                            str += test.toString();
                            test.prev += p.prev + 1;
                            q.add(test);
                        }

                    } catch (Exception e) {}
                }

                check = true;
                for (Point test : q) {
                    if (test.get(map) != 9) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    for(Point end : q){
                    }
                    break;
                }

            }

            System.out.println(start + ": " + q.size());
            total += q.size();
        }

        System.out.println(total);
        scan.close();

    }

    public static class Point {
        int x, y, prev = 0;
        boolean moved = false;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }

        public int get(int[][] map) {
            return map[this.x][this.y];
        }

        public boolean equals(Point point) {
            return this.x == point.x && this.y == point.y;
        }

        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }
}
