import java.util.*;
import java.io.*;

public class Day13 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("input2.txt"));
        String line;
        long total = 0;

        int num = 0;
        while (scan.hasNext()) {
            num++;

            // setup vars
            line = scan.nextLine();
            long[] a = new long[] {
                    Integer.parseInt(line.substring(line.indexOf("X+") + 2, line.indexOf(","))),
                    Integer.parseInt(line.substring(line.indexOf("Y+") + 2, line.length()))
            };

            line = scan.nextLine();
            long[] b = new long[] {
                    Integer.parseInt(line.substring(line.indexOf("X+") + 2, line.indexOf(","))),
                    Integer.parseInt(line.substring(line.indexOf("Y+") + 2, line.length()))
            };

            line = scan.nextLine();
            long px = (long) Math.pow(10, 15)
                    + Integer.parseInt(line.substring(line.indexOf("X=") + 2, line.indexOf(","))) + 1;
            long py = (long) Math.pow(10, 15)
                    + Integer.parseInt(line.substring(line.indexOf("Y=") + 2, line.length())) + 1;

            // bil, mil, thou, one
            // 9,6,3,0
            // brute force lol
            int[] powA = new int[4];
            int[] powB = new int[4];

            for (int i = 0; i < 20000; i++) {
                for (int i2 = 0; i2 < 20000; i2++) {
                    powA[3] = i;
                    powB[3] = i2;
                    if (checkNum(px, py, a, b, powA, powB)) {
                        break;
                    }
                }
            }

            for (int i = 0; i < 1000; i++) {
                for (int i2 = 0; i2 < 1000; i2++) {
                    powA[2] = i;
                    powB[2] = i2;
                    if (checkNum(px, py, a, b, powA, powB)) {
                        break;
                    }
                }
            }

            for (int i = 0; i < 1000; i++) {
                for (int i2 = 0; i2 < 1000; i2++) {
                    powA[1] = i;
                    powB[1] = i2;
                    if (checkNum(px, py, a, b, powA, powB)) {
                        break;
                    }
                }
            }

            long B = (long) Math.pow(10, 9), M = (long) Math.pow(10, 6), K = (long) Math.pow(10, 3);

            for (int i = 0; i < 1000; i++) {
                for (int i2 = 0; i2 < 1000; i2++) {
                    powA[0] = i;
                    powB[0] = i2;
                    long px1 = (B * powA[3] * a[0] + B * powB[3] * b[0]) + (M * powA[2] * a[0] + M * powB[2] * b[0])
                            + (K * powA[1] * a[0] + K * powB[1] * b[0]) + (powA[0] * a[0] + powB[0] * b[0]);
                    long py1 = (B * powA[3] * a[1] + B * powB[3] * b[1]) + (M * powA[2] * a[1] + M * powB[2] * b[1])
                            + (K * powA[1] * a[1] + K * powB[1] * b[1]) + (powA[0] * a[1] + powB[0] * b[1]);
                    if (px1 == px && py1 == py) {
                        total += 3 * (B * powA[3] + M * powA[2] + K * powA[1] + powA[0]);
                        total += (B * powB[3] + M * powB[2] + K * powB[1] + powB[0]);
                        System.out.println(num);
                    }
                }
            }

            System.out.println("----");
            try {
                scan.nextLine();
            } catch (Exception e) {
            }
        }

        System.out.println(total);
        scan.close();
    }

    public static boolean checkNum(long tx, long ty, long[] b1, long[] b2, int[] pow1, int[] pow2) {
        long B = (long) Math.pow(10, 9), M = (long) Math.pow(10, 6), K = (long) Math.pow(10, 3);

        long px1 = (B * pow1[3] * b1[0] + B * pow2[3] * b2[0]) + (M * pow1[2] * b1[0] + M * pow2[2] * b2[0])
                + (K * pow1[1] * b1[0] + K * pow2[1] * b2[0]) + (pow1[0] * b1[0] + pow2[0] * b2[0]);
        long py1 = (B * pow1[3] * b1[1] + B * pow2[3] * b2[1]) + (M * pow1[2] * b1[1] + M * pow2[2] * b2[1])
                + (K * pow1[1] * b1[1] + K * pow2[1] * b2[1]) + (pow1[0] * b1[1] + pow2[0] * b2[1]);
        long px2 = (B * (pow1[3] + 1) * b1[0] + B * (pow2[3] + 1) * b2[0])
                + (M * (pow1[2] + 1) * b1[0] + M * (pow2[2] + 1) * b2[0])
                + (K * (pow1[1] + 1) * b1[0] + K * (pow2[1] + 1) * b2[0])
                + ((pow1[0] + 1) * b1[0] + (pow2[0] + 1) * b2[0]);
        long py2 = (B * (pow1[3] + 1) * b1[1] + B * (pow2[3] + 1) * b2[1])
                + (M * (pow1[2] + 1) * b1[1] + M * (pow2[2] + 1) * b2[1])
                + (K * (pow1[1] + 1) * b1[1] + K * (pow2[1] + 1) * b2[1])
                + ((pow1[0] + 1) * b1[1] + (pow2[0] + 1) * b2[1]);

        return px1 <= tx && px2 > tx && py1 <= tx && py2 > tx;
    }
}